package com.syncra.supermarket.Dto.Category;

import com.syncra.supermarket.Dto.Product.ProductResponse;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {

    private Integer id;
    private String name;
    private List<ProductResponse> products;
}