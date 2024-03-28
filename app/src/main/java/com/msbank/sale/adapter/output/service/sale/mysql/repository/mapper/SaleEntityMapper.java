package com.msbank.sale.adapter.output.service.sale.mysql.repository.mapper;

import com.msbank.sale.adapter.output.service.sale.mysql.repository.entity.SaleEntity;
import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.domain.enums.SalesStatus;

public class SaleEntityMapper {

    public static SaleEntity saleToSaleEntity(final Sale sale){
      return   SaleEntity.builder()
                    .id(sale.getId())
                    .userId(sale.getUserId())
                    .value(sale.getValue())
                    .productId(sale.getProductId())
                    .quantity(sale.getQuantity())
                    .statusId(sale.getStatus().getStatusId())
                .build();

    }

    public static Sale saleEntityToSale(final SaleEntity saleEntity) {
        return   Sale.builder()
                .id(saleEntity.getId())
                .userId(saleEntity.getUserId())
                .value(saleEntity.getValue())
                .productId(saleEntity.getProductId())
                .status(SalesStatus.toEnum(saleEntity.getStatusId()))
                .quantity(saleEntity.getQuantity())
                .build();
    }
}
