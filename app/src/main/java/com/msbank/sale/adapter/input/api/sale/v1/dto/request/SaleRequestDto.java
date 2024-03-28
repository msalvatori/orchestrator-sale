package com.msbank.sale.adapter.input.api.sale.v1.dto.request;

import com.msbank.sale.core.domain.enums.SalesStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleRequestDto {
    private Integer id;
    private Integer productId;
    private Integer userId;
    private BigDecimal value;
    private SalesStatus status;
    private Integer quantity;
}
