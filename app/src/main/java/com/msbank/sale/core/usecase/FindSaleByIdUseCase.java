package com.msbank.sale.core.usecase;

import com.msbank.sale.core.domain.Sale;
import reactor.core.publisher.Mono;

public interface FindSaleByIdUseCase {
    Mono<Sale> find(final Integer id);
}
