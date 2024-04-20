package com.msbank.sale.core.usecase;

import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.output.service.sale.dto.response.DataResponseDto;
import reactor.core.publisher.Mono;

public interface SendSaleUseCase {
    Mono<DataResponseDto> execute(Sale sale);
}
