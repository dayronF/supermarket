package com.syncra.supermarket.Dto.Supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SupplierRequest {

    @NotBlank(message = "El NIT del proveedor es obligatorio")
    @Size(max = 50, message = "El NIT no puede superar los 50 caracteres")
    private String nit;

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String name;
}