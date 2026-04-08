package com.syncra.supermarket.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.syncra.supermarket.Dto.Sale.SaleMessage;
import com.syncra.supermarket.Dto.Sale.SaleRequest;
import com.syncra.supermarket.Dto.Sale.SaleResponse;
import com.syncra.supermarket.Service.SaleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
@Validated
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<?> createSale(@Valid @RequestBody SaleRequest request) {

        try {
            SaleResponse response = saleService.createSale(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new SaleMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSales() {

        try {
            List<SaleResponse> list = saleService.getAllSales();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(new SaleMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable @Valid int id) {

        try {
            SaleResponse response = saleService.getSaleById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(new SaleMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/employee/{cc}")
    public ResponseEntity<?> getSalesByEmployee(@PathVariable @Valid int cc) {

        try {
            List<SaleResponse> list = saleService.getSalesByEmployee(cc);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(new SaleMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}