package com.syncra.supermarket.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syncra.supermarket.Entity.SaleEntity;

public interface SaleRepository extends JpaRepository<SaleEntity,Integer>{

    List<SaleEntity> findByEmployeeCC(int cc );

}
