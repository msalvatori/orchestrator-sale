package com.msbank.sale.core.output.service.sale.db;

import com.msbank.sale.core.domain.Sale;
import reactor.core.publisher.Mono;

public interface SaveSale {
    Mono<Sale> save(final Sale sale);
}
