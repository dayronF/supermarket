package com.syncra.supermarket.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")

public class EmployeeEntity {

    public enum Post {
        ADMINISTRADOR, CAJERO, AUXILIAR
    }

    @Id
    @Column(name = "cc")
    private int cc;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "post")
    private Post post;
    @Column(name = "entry_date")
    private LocalDate entrydate;
    @Column(name = "salary")
    private Double salary;

    @OneToMany(mappedBy = "employee")
    private List<SaleEntity> sales;

}
