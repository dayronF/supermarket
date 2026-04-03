package com.syncra.supermarket.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syncra.supermarket.Entity.EmployeeEntity;
import java.time.LocalDate;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer>{

boolean existByCc (String cc);

List<EmployeeEntity> findByPost(EmployeeEntity.Post post);

List<EmployeeEntity> findByEntryDateBetween(LocalDate StarDate , LocalDate EndDate);

}
