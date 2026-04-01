package com.syncra.supermarket.Dto.Category;

import lombok.Data;
import java.util.List;
import com.syncra.supermarket.Dto.Product.ProductResponse;

@Data
public class CategoryResponse {

    private Integer id;
    private String name;

    private List<ProductResponse> products;
}