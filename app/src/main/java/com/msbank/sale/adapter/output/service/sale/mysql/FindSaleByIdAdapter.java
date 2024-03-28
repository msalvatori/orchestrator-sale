package com.msbank.sale.adapter.output.service.sale.mysql;

import com.msbank.sale.adapter.output.service.sale.mysql.repository.SaleRepository;
import com.msbank.sale.adapter.output.service.sale.mysql.repository.mapper.SaleEntityMapper;
import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.output.service.sale.db.FindSaleById;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class FindSaleByIdAdapter implements FindSaleById {
    @Autowired
    private final SaleRepository saleRepository;
    @Override
    public Mono<Sale> find(Integer id) {
        var sale = saleRepository.findById(id)
                .stream()
                .findFirst()
                .map(SaleEntityMapper::saleEntityToSale);
        return sale.map(Mono::just).orElse(null);
    }
}
