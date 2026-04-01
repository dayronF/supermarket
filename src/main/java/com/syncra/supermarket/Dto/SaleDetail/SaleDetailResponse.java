package com.syncra.supermarket.Dto.SaleDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailResponse {
    
private Long detailId;

    private Long sale_id;
    private Long product_id;
    private String productName;
    private String barCode;
    private Integer quantity;
    private Double unitPrice;
    private Double lineSubtotal;

}
