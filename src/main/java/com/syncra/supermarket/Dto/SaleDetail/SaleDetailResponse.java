package com.syncra.supermarket.Dto.SaleDetail;

import lombok.Data;

@Data
public class SaleDetailResponse {

    private Integer id;
    private String productName;
    private Integer quantity;
    private Double priceUnitary;
    private Double subtotalDetail;
}