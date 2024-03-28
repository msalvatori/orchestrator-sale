package com.msbank.sale.adapter.input.api.sale.v1.router;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface SalesInterface {
    Mono<ServerResponse> startSales(ServerRequest serverRequest);
}
