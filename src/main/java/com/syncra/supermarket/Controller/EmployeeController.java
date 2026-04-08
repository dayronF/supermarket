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
    public ResponseEntity<?> getAllEmployees() {

        try {
            List<EmployeeResponse> list = employeeService.getAllEmployee();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(new EmployeeMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{cc}")
    public ResponseEntity<?> getEmployeeById(@PathVariable @Valid Integer cc) {

        try {
            EmployeeResponse response = employeeService.getById(cc);

            if (response == null) {
                return new ResponseEntity<>(new EmployeeMessage("Empleado no encontrado"), HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return new ResponseEntity<>(new EmployeeMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(
            @RequestParam @Valid Integer cc,
            @Valid @RequestBody EmployeeRequest request) {

        try {
            EmployeeMessage response = employeeService.create(request, cc);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new EmployeeMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{cc}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable @Valid Integer cc,
            @Valid @RequestBody EmployeeRequest request) {

        try {
            EmployeeMessage response = employeeService.Update(cc, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(new EmployeeMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{cc}")
    public ResponseEntity<?> deleteEmployee(@PathVariable @Valid Integer cc) {

        try {
            EmployeeMessage response = employeeService.delete(cc);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(new EmployeeMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/post/{post}")
    public ResponseEntity<?> getByPost(@PathVariable @Valid String post) {

        try {
            List<EmployeeResponse> list = employeeService.getByPost(post);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(new EmployeeMessage("Cargo inválido"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<?> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        try {
            List<EmployeeResponse> list = employeeService.getByDateRange(startDate, endDate);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(new EmployeeMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}