package com.syncra.supermarket.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "supplier_product")
public class SupplierProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;
}