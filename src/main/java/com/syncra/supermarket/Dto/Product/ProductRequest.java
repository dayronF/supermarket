package com.syncra.supermarket.Dto.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequest {

    @Size(max = 100, message = "El nombre del producto no puede pasar de 100 caracteres")
    @Size(min = 1, message = "El nombre del producto tiene que tener como minimo un caracter")
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String name;

    @Size(max = 50, message = "El codigo de barras no puede pasar de 50 caracteres")
    @Size(min = 1, message = "El codigo de barras tiene que tener como minimo un caracter")
    @NotBlank(message = "El codigo de barras es obligatorio")
    private String barcode;

    @NotNull(message = "El precio del producto es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double price;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    private Integer stock;

    @NotNull(message = "La categoria es obligatoria")
    @Positive(message = "El id de la categoria debe ser mayor a 0")
    private Integer categoryId;
}