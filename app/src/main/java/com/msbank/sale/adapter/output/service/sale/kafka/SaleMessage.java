package com.msbank.sale.adapter.output.service.sale.kafka;

import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.domain.enums.SaleEvent;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleMessage {

    private Sale sale;

    private SaleEvent saleEvent;


}
