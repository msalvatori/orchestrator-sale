package com.msbank.sale.adapter.input.api.sale.v1.router.impl;

import com.msbank.sale.adapter.input.api.sale.v1.router.SalesInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.msbank.sale.core.constants.Constants.Rel.START_SALES;

@Configuration
public class SalesRouter {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(final SalesInterface salesInterface) {
        return RouterFunctions
                .route(RequestPredicates.POST(START_SALES),
                        salesInterface::startSales);
    }
}
