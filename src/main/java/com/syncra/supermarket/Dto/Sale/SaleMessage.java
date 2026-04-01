package com.syncra.supermarket.Dto.Sale;

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
public class SaleMessage {

    private boolean success;
    private String message;
    private Long saleId;

    public static SaleMessage ok(Long saleId) {
        return SaleMessage.builder()
                .success(true)
                .message("La venta fue realizada con exito")
                .saleId(saleId)
                .build();
    }

    public static SaleMessage error(String message) {
        return SaleMessage.builder()
                .success(false)
                .message(message)
                .build();
    }

}
