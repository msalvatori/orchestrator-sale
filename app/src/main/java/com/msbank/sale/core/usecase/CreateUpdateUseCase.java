package com.msbank.sale.core.usecase;

import com.msbank.sale.core.domain.Sale;
import reactor.core.publisher.Mono;

public interface CreateUpdateUseCase {
    Mono<Sale> execute(final Sale sale);
}
