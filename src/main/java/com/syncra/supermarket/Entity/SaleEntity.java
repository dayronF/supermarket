package com.syncra.supermarket.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sales")

public class SaleEntity {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn (name = "employee_id")
    private EmployeEntity employee;

    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "iva")
    private Double iva;
    @Column(name = "total")
    private Double total;

}
