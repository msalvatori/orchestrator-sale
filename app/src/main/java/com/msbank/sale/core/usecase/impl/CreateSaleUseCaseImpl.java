package com.msbank.sale.core.usecase.impl;

import com.msbank.sale.adapter.output.service.sale.mysql.repository.mapper.SaleEntityMapper;
import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.domain.enums.SaleEvent;
import com.msbank.sale.core.domain.enums.SalesStatus;
import com.msbank.sale.core.error.exception.BadRequestException;
import com.msbank.sale.core.error.exception.BaseException;
import com.msbank.sale.core.output.service.sale.db.SaveSale;
import com.msbank.sale.core.output.service.sale.dto.response.DataResponseDto;
import com.msbank.sale.core.output.service.sale.producer.SendCreate;
import com.msbank.sale.core.usecase.CreateSaleUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateSaleUseCaseImpl implements CreateSaleUseCase {

    Logger LOGGER = LogManager.getLogger("Log4Core");
    private final SaveSale saveSale;
    private final SendCreate sendCreate;

    @Override
    public Mono<DataResponseDto> start(Sale sale) {
       return this.createUpdate(sale)
                .flatMap(this::sendCreate)
                .doOnSuccess( dataResponseDto ->  LOGGER.info(dataResponseDto.toString()))
                .doOnError(err -> LOGGER.error("Error ao enviar mensagem", err))
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(1))
                        .filter(BadRequestException.class::isInstance))
               .timeout(Duration.ofSeconds(3))
               .onErrorResume(throwable -> throwable instanceof BaseException || throwable.getCause() instanceof BaseException
               ? Mono.error(getBaseExceptionOrCause(throwable))
               : Mono.error( new BadRequestException("Error ao enviar mensagem")));

    }

    @Override
    public Mono<Sale> createUpdate(Sale sale) {
        sale.setStatus(SalesStatus.PENDING);
        return saveSale.save(sale);
    }

    @Override
    public Mono<DataResponseDto> sendCreate(Sale sale) {
       return  sendCreate.create(sale, SaleEvent.CREATED_SALE);
    }

    private Throwable getBaseExceptionOrCause(final Throwable throwable) {
        if (throwable instanceof  BaseException) {
            return  throwable;
        }
        return throwable.getCause();
    }
}
