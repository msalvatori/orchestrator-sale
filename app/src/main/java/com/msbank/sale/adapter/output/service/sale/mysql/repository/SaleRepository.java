package com.msbank.sale.adapter.output.service.sale.mysql.repository;

import com.msbank.sale.adapter.output.service.sale.mysql.repository.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity,Integer> {
}
