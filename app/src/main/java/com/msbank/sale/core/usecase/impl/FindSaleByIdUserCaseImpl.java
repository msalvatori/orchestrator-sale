package com.msbank.sale.core.usecase.impl;

import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.output.service.sale.db.FindSaleById;
import com.msbank.sale.core.usecase.FindSaleByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
@Component
public class FindSaleByIdUserCaseImpl implements FindSaleByIdUseCase {

    private final FindSaleById findSaleById;
     @Override
    public Mono<Sale> find(final Integer id) {
        return findSaleById.find(id);
    }

}
