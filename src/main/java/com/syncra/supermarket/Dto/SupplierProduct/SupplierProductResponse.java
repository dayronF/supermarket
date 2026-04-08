package com.syncra.supermarket.Dto.SupplierProduct;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SupplierProductResponse {

    private Integer id;
    private String productName;
    private String supplierName;
    private Integer quantity;
    private LocalDateTime entryDate;
}