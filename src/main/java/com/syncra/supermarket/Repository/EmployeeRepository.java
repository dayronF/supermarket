package com.syncra.supermarket.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syncra.supermarket.Entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    boolean existsByCc(int cc);

    List<EmployeeEntity> findByPost(EmployeeEntity.Post post);

    List<EmployeeEntity> findByEntrydateBetween(LocalDate startDate, LocalDate endDate);

}