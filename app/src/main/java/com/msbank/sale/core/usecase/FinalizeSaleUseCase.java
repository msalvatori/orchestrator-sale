package com.msbank.sale.core.usecase;

import com.msbank.sale.core.domain.Sale;

public interface FinalizeSaleUseCase {
    void execute(Sale sale);
}
