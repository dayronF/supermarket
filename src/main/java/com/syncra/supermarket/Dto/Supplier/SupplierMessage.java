package com.syncra.supermarket.Dto.Supplier;

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
public class SupplierMessage {

    private boolean success;
    private String message;

    private Long supplierId;
    private String nit;

    public static SupplierMessage ok(Long supplierId, String nit) {
        return SupplierMessage.builder()
                .success(true)
                .message("Proveedor procesado exitosamente")
                .supplierId(supplierId)
                .nit(nit)
                .build();
    }

    public static SupplierMessage error(String message, String nit) {
        return SupplierMessage.builder()
                .success(false)
                .message(message)
                .supplierId(null)
                .nit(nit)
                .build();

    }
}