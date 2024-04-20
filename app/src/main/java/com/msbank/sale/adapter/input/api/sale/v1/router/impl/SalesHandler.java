package com.msbank.sale.adapter.input.api.sale.v1.router.impl;

import com.msbank.sale.adapter.input.api.sale.v1.dto.request.SaleRequestDto;
import com.msbank.sale.adapter.input.api.sale.v1.mapper.SaleMapper;
import com.msbank.sale.adapter.input.api.sale.v1.router.SalesInterface;
import com.msbank.sale.adapter.input.api.sale.v1.validator.HandlerValidator;
import com.msbank.sale.core.output.service.sale.dto.response.DataResponseDto;
import com.msbank.sale.core.usecase.WorkFlowSaleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SalesHandler implements HandlerValidator, SalesInterface {

    private final WorkFlowSaleUseCase workFlowSaleUseCase;
    @Override
    public Mono<ServerResponse> startSales(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(SaleRequestDto.class)
                .doOnSuccess(this::bodyValidation)
                .flatMap(SaleMapper::saleRequestDtoToSale)
                .flatMap(body -> fieldsValidation(body).thenReturn(body)
                        .flatMap(sale -> ServerResponse.ok().body(BodyInserters.fromPublisher(
                                workFlowSaleUseCase.execute(sale), DataResponseDto.class))));
    }
}
