package com.syncra.supermarket.Dto.Product;

import lombok.Data;

@Data
public class ProductResponse {

    private Integer id;
    private String name;
    private String barcode;
    private Double price;
    private Integer stock;
    private Boolean state;

    private String categoryName;
}
