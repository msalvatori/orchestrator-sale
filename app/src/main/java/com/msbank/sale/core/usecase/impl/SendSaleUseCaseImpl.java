package com.msbank.sale.core.usecase.impl;

import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.domain.enums.SaleEvent;
import com.msbank.sale.core.output.service.sale.dto.response.DataResponseDto;
import com.msbank.sale.core.output.service.sale.producer.SendCreate;
import com.msbank.sale.core.usecase.SendSaleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SendSaleUseCaseImpl implements SendSaleUseCase {

    private final SendCreate sendCreate;

    @Override
    public Mono<DataResponseDto> execute(Sale sale) {
        return sendCreate.create(sale, SaleEvent.CREATED_SALE);
    }
}
