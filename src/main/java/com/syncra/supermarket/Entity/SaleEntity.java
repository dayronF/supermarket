package com.syncra.supermarket.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sale")

public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "employee_cc")
    private EmployeeEntity employee;

    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "iva")
    private Double iva;
    @Column(name = "total")
    private Double total;

    @OneToMany(mappedBy = "sale")
    private List<SaleDetailEntity> saleDetails;
}
