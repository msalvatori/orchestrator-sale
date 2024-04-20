package com.msbank.sale.core.usecase.impl;

import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.domain.enums.SalesStatus;
import com.msbank.sale.core.output.service.sale.db.SaveSale;
import com.msbank.sale.core.usecase.CreateUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateUpdateUseCaseImpl implements CreateUpdateUseCase {

    private final SaveSale saveSale;

    @Override
    public Mono<Sale> execute(Sale sale) {
        sale.setStatus(SalesStatus.PENDING);
        return saveSale.save(sale);
    }
}
