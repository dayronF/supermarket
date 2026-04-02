package com.syncra.supermarket.Dto.Sale;

import com.syncra.supermarket.Dto.SaleDetail.SaleDetailRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class SaleRequest {

    @NotNull(message = "La cédula del empleado es obligatoria")
    @Positive(message = "La cédula debe ser mayor a 0")
    private Integer employeeCc;

    @NotEmpty(message = "La venta debe tener al menos un producto")
    @Valid
    private List<SaleDetailRequest> items;
}