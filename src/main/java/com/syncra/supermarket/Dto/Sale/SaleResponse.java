package com.syncra.supermarket.Dto.Sale;

import com.syncra.supermarket.Dto.SaleDetail.SaleDetailResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleResponse {

    private Integer id;
    private LocalDateTime date;
    private String employeeName;
    private Double subtotal;
    private Double iva;
    private Double total;
    private List<SaleDetailResponse> details;
}