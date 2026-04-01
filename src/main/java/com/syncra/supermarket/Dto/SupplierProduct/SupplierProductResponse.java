package com.syncra.supermarket.Dto.SupplierProduct;

import lombok.Data;

@Data
public class SupplierProductResponse {

    private Integer productId;
    private String productName;

    private Integer supplierId;
    private String supplierName;
}
