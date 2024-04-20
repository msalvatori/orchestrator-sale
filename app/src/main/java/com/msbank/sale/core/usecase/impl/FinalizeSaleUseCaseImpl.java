package com.msbank.sale.core.usecase.impl;

import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.domain.enums.SalesStatus;
import com.msbank.sale.core.output.service.sale.db.SaveSale;
import com.msbank.sale.core.usecase.FinalizeSaleUseCase;
import com.msbank.sale.core.usecase.FindSaleByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinalizeSaleUseCaseImpl implements FinalizeSaleUseCase {

    Logger LOGGER = LogManager.getLogger("Log4Core");

    private final FindSaleByIdUseCase findSaleByIdUseCase;

    private final SaveSale saveSale;

    @Override
    public void execute(Sale sale) {
        sale.setStatus(SalesStatus.FINALIZED);
        saveSale.save(sale);
    }

}
