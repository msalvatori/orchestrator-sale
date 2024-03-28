package com.msbank.sale.core.usecase;

import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.output.service.sale.dto.response.DataResponseDto;
import reactor.core.publisher.Mono;

public interface CreateSaleUseCase {

    Mono<DataResponseDto> start(Sale sale);
    Mono<Sale> createUpdate(final Sale sale);
    Mono<DataResponseDto> sendCreate(final Sale sale);
}
