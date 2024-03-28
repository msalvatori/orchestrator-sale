package com.msbank.sale.core.output.service.sale.producer;

import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.domain.enums.SaleEvent;
import com.msbank.sale.core.output.service.sale.dto.response.DataResponseDto;
import reactor.core.publisher.Mono;

public interface SendCreate {
    Mono<DataResponseDto> create(final Sale sale, final SaleEvent event);
}
