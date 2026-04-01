package com.syncra.supermarket.Dto.Employee;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EmployeeRequest {

    @NotNull
    private Integer cc;

    @NotBlank
    private String name;

    @NotBlank
    private String post;

    @NotNull
    private LocalDate entryDate;

    @NotNull
    @Positive
    private Double salary;
}