package com.msbank.sale.core.usecase.impl;

import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.domain.enums.SalesStatus;
import com.msbank.sale.core.error.exception.BadRequestException;
import com.msbank.sale.core.error.exception.BaseException;
import com.msbank.sale.core.output.service.sale.db.SaveSale;
import com.msbank.sale.core.usecase.CancelSaleUseCase;
import com.msbank.sale.core.usecase.FindSaleByIdUseCase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CancelSaleUseCaseImpl implements CancelSaleUseCase {

    Logger LOGGER = LogManager.getLogger("Log4Core");

    private final FindSaleByIdUseCase FindSaleByIdUseCase;

    private final SaveSale saveSale;
    @Override
    public void cancel(Sale sale) {
        sale.setStatus(SalesStatus.CANCELED);
        saveSale.save(sale);
    }

}
