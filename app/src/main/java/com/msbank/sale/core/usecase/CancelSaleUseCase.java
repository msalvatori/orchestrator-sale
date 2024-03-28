package com.msbank.sale.core.usecase;

import com.msbank.sale.core.domain.Sale;

public interface CancelSaleUseCase {
    public void cancel(Sale sale);
}
