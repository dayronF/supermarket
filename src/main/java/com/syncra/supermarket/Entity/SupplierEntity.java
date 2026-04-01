package com.syncra.supermarket.Entity;
 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
 
@Data
@Entity
@Table(name = "suppliers")
public class SupplierEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    @Column(name = "nit")
    private String nit;
 
    @Column(name = "name")
    private String name;
}