package com.syncra.supermarket.Dto.SupplierProduct;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SupplierProductRequest {

    @NotNull
    private Integer productId;

    @NotNull
    private Integer supplierId;
}
