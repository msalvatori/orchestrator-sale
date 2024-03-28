package com.msbank.sale.core.domain;

import com.msbank.sale.core.domain.enums.SalesStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
@Data
public class Sale {

    private Integer id;
    private Integer productId;
    private Integer userId;
    private BigDecimal value;
    private SalesStatus status;
    private Integer quantity;

}
