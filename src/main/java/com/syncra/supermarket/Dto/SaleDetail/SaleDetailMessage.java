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
public class SaleDetailMessage {

    private boolean success;

    private String message;
    private Long detailId;
    private String barCode;

    public static SaleDetailMessage ok(Long detailId, String barCode) {
        return SaleDetailMessage.builder()
                .success(true)
                .message("El detalle de la venta ha sido procesado exitosamente")
                .detailId(detailId)
                .barCode(barCode)
                .build();
    }

    public static SaleDetailMessage error(String message, String barCode) {
        return SaleDetailMessage.builder()
                .success(false)
                .message(message)
                .detailId(null)
                .barCode(barCode)
                .build();

    }
}