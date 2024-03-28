package com.msbank.sale.adapter.output.service.sale.mysql;

import com.msbank.sale.adapter.output.service.sale.mysql.repository.SaleRepository;
import com.msbank.sale.adapter.output.service.sale.mysql.repository.mapper.SaleEntityMapper;
import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.domain.enums.SalesStatus;
import com.msbank.sale.core.output.service.sale.db.SaveSale;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class SaveSaleAdapter implements SaveSale {

    @Autowired
    private final SaleRepository repository;

    @Override
    public Mono<Sale> save(Sale sale) {
        var saleEntity = SaleEntityMapper.saleToSaleEntity(sale);
        var saleEntityResponse =  repository.save(saleEntity);        ;
        return Mono.just(SaleEntityMapper.saleEntityToSale(saleEntityResponse));
    }
}
