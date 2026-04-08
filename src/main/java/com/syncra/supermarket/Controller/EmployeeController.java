package com.syncra.supermarket.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.syncra.supermarket.Dto.Employee.EmployeeMessage;
import com.syncra.supermarket.Dto.Employee.EmployeeRequest;
import com.syncra.supermarket.Dto.Employee.EmployeeResponse;
import com.syncra.supermarket.Service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        try {
            List<EmployeeResponse> list = employeeService.getAllEmployee();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{cc}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable @Valid Integer cc) {
        try {
            EmployeeResponse response = employeeService.getById(cc);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeMessage> createEmployee(
            @RequestParam @Valid Integer cc,
            @Valid @RequestBody EmployeeRequest request) {
        try {
            EmployeeMessage response = employeeService.create(request, cc);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new EmployeeMessage(e.getMessage()));
        }
    }

    @PutMapping("/{cc}")
    public ResponseEntity<EmployeeMessage> updateEmployee(
            @PathVariable @Valid Integer cc,
            @Valid @RequestBody EmployeeRequest request) {
        try {
            EmployeeMessage response = employeeService.Update(cc, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new EmployeeMessage(e.getMessage()));
        }
    }

    @DeleteMapping("/{cc}")
    public ResponseEntity<EmployeeMessage> deleteEmployee(@PathVariable @Valid Integer cc) {
        try {
            EmployeeMessage response = employeeService.delete(cc);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new EmployeeMessage(e.getMessage()));
        }
    }

    @GetMapping("/post/{post}")
    public ResponseEntity<List<EmployeeResponse>> getByPost(@PathVariable @Valid String post) {
        try {
            List<EmployeeResponse> list = employeeService.getByPost(post);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<EmployeeResponse>> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            List<EmployeeResponse> list = employeeService.getByDateRange(startDate, endDate);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}