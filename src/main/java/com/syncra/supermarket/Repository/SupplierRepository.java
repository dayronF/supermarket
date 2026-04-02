package com.syncra.supermarket.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syncra.supermarket.Entity.SupplierEntity;

public interface SupplierRepository extends JpaRepository<SupplierEntity,Integer>{

boolean ExistByNIt(String nit);
Optional<SupplierEntity> finByNit(String nit);
}
