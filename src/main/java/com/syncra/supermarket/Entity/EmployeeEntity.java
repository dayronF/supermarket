package com.syncra.supermarket.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")

public class EmployeeEntity {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "post")
    private String post;
    @Column(name = " entry_date")
    private LocalDate entry_date;
    @Column(name = "salary")
    private Double salary;

}
