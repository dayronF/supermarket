package com.syncra.supermarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.syncra.supermarket.Entity.SupplierProductEntity;

@Repository
public interface SupplierProductRepository extends JpaRepository<SupplierProductEntity,Integer> {

List<SupplierProductEntity> findByProductId(Integer productId);
List<SupplierProductEntity> findBySupplierId(Integer supplierId);

}
