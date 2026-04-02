package com.syncra.supermarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syncra.supermarket.Entity.ProductEntity;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer>{

boolean ExistBarCode(String barcode);
List<ProductEntity> findByStateTrue();
Optional<ProductEntity> findByBarcode(String barcode);
}
