package com.msbank.sale.core.usecase.impl;

import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.error.exception.BadRequestException;
import com.msbank.sale.core.error.exception.BaseException;
import com.msbank.sale.core.output.service.sale.dto.response.DataResponseDto;
import com.msbank.sale.core.usecase.CreateUpdateUseCase;
import com.msbank.sale.core.usecase.SendSaleUseCase;
import com.msbank.sale.core.usecase.WorkFlowSaleUseCase;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class WorkFlowSaleUseCaseImpl implements WorkFlowSaleUseCase {

    Logger LOGGER = LogManager.getLogger("Log4Core");
    private final CreateUpdateUseCase createUpdateUseCase;
    private final SendSaleUseCase sendSaleUseCase;
    @Override
    public Mono<DataResponseDto> execute(Sale sale) {
        return createUpdateUseCase.execute(sale)
                .flatMap(sendSaleUseCase::execute)
                .doOnSuccess( dataResponseDto ->  LOGGER.info(dataResponseDto.toString()))
                .doOnError(err -> LOGGER.error("Error ao enviar mensagem", err))
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(1))
                        .filter(BadRequestException.class::isInstance))
                .timeout(Duration.ofSeconds(3))
                .onErrorResume(throwable -> throwable instanceof BaseException || throwable.getCause() instanceof BaseException
                        ? Mono.error(getBaseExceptionOrCause(throwable))
                        : Mono.error( new BadRequestException("Error ao enviar mensagem")));

    }

    private Throwable getBaseExceptionOrCause(final Throwable throwable) {
        if (throwable instanceof  BaseException) {
            return  throwable;
        }
        return throwable.getCause();
    }

}
