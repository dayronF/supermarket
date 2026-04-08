package com.syncra.supermarket.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.syncra.supermarket.Dto.Supplier.SupplierMessage;
import com.syncra.supermarket.Dto.Supplier.SupplierRequest;
import com.syncra.supermarket.Dto.Supplier.SupplierResponse;
import com.syncra.supermarket.Dto.SupplierProduct.SupplierProductRequest;
import com.syncra.supermarket.Service.SupplierService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
@Validated
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierMessage> createSupplier(
            @RequestParam @Valid Integer cc,
            @Valid @RequestBody SupplierRequest request) {
        try {
            SupplierMessage response = supplierService.createSupplier(cc, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SupplierMessage(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAllSuppliers() {
        try {
            List<SupplierResponse> list = supplierService.getAllSuppliers();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getSupplierById(@PathVariable @Valid Integer id) {
        try {
            SupplierResponse response = supplierService.getSupplierById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/nit/{nit}")
    public ResponseEntity<SupplierResponse> getSupplierByNit(@PathVariable @Valid String nit) {
        try {
            SupplierResponse response = supplierService.getSupplierByNit(nit);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierMessage> updateSupplier(
            @RequestParam @Valid Integer cc,
            @PathVariable @Valid Integer id,
            @Valid @RequestBody SupplierRequest request) {
        try {
            SupplierMessage response = supplierService.updateSupplier(cc, id, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SupplierMessage(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierMessage> deleteSupplier(
            @RequestParam @Valid Integer cc,
            @PathVariable @Valid Integer id) {
        try {
            SupplierMessage response = supplierService.deleteSupplier(cc, id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SupplierMessage(e.getMessage()));
        }
    }

    @PostMapping("/warehouse-entry")
    public ResponseEntity<SupplierMessage> warehouseEntry(
            @RequestParam @Valid Integer cc,
            @Valid @RequestBody SupplierProductRequest request) {
        try {
            SupplierMessage response = supplierService.warehouseEntry(cc, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SupplierMessage(e.getMessage()));
        }
    }
}