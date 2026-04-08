package com.syncra.supermarket.Dto.SupplierProduct;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SupplierProductRequest {

    @NotNull(message = "El id del producto es obligatorio")
    @Positive(message = "El id del producto debe ser mayor a 0")
    private Integer productId;

    @NotNull(message = "El id del proveedor es obligatorio")
    @Positive(message = "El id del proveedor debe ser mayor a 0")
    private Integer supplierId;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Integer quantity;
}