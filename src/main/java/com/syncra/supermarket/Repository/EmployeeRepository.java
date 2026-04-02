package com.syncra.supermarket.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syncra.supermarket.Entity.EmployeeEntity;
import java.time.LocalDate;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer>{


List<EmployeeEntity> findByPost(EmployeeEntity.Post post);

List<EmployeeEntity> findByEntrydate(LocalDate StarDate , LocalDate EndDate);

}
