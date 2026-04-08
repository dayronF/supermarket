package com.syncra.supermarket.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syncra.supermarket.Entity.SaleDetailEntity;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetailEntity, Integer> {
    List<SaleDetailEntity> findBySaleId(Integer saleId);
}