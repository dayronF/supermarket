package com.syncra.supermarket.Dto.Product;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String barcode;

    @NotNull
    @Positive
    private Double price;

    @PositiveOrZero
    private Integer stock;

    @NotNull
    private Integer categoryId;
}
