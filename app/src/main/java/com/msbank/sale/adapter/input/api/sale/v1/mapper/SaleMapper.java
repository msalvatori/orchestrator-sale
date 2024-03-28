package com.msbank.sale.adapter.input.api.sale.v1.mapper;

import com.msbank.sale.adapter.input.api.sale.v1.dto.request.SaleRequestDto;
import com.msbank.sale.core.domain.Sale;
import reactor.core.publisher.Mono;

public class SaleMapper {

    public static Mono<Sale> saleRequestDtoToSale(final SaleRequestDto saleRequestDto) {

        return   Mono.just(Sale.builder()
                .id(saleRequestDto.getId())
                .userId(saleRequestDto.getUserId())
                .value(saleRequestDto.getValue())
                .productId(saleRequestDto.getProductId())
                .quantity(saleRequestDto.getQuantity())
                .status(saleRequestDto.getStatus())
                .build());
    }
}
