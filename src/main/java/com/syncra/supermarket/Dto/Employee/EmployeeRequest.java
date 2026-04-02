package com.syncra.supermarket.Dto.Employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {

    @NotNull(message = "La cédula es obligatoria")
    private Integer cc;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El cargo es obligatorio")
    private String post;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    private LocalDate entryDate;

    @NotNull(message = "El salario es obligatorio")
    @Positive(message = "El salario debe ser mayor a 0")
    private Double salary;
}