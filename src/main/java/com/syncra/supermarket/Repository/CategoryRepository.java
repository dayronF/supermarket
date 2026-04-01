package com.syncra.supermarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syncra.supermarket.Entity.CategorieEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {


} 
