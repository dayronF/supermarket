package com.syncra.supermarket.Dto.Sale;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class SaleRequest {
    
@NotNull(message = "El id del empleado es obligatorio")
    private Long employeeId;

    @NotEmpty(message = "La venta debe contener al menos un producto")
    @Valid
    private List<SaleItemRequest> items;

    public static class SaleItemRequest {
        @NotNull(message = "El id del producto es obligatorio")
        private Long productId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser minimo uno")
    private Integer quantity;
    }

}
