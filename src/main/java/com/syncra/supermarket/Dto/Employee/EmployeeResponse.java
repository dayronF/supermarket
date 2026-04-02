package com.syncra.supermarket.Dto.Employee;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeResponse {

    private Integer cc;
    private String name;
    private String post;
    private LocalDate entryDate;
    private Double salary;
}