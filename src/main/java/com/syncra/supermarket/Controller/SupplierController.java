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
    public ResponseEntity<?> createSupplier(
            @RequestParam @Valid Integer cc,
            @Valid @RequestBody SupplierRequest request) {

        try {
            SupplierMessage response = supplierService.createSupplier(cc, request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new SupplierMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSuppliers() {

        try {
            List<SupplierResponse> list = supplierService.getAllSuppliers();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(new SupplierMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable @Valid Integer id) {

        try {
            SupplierResponse response = supplierService.getSupplierById(id);

            if (response == null) {
                return new ResponseEntity<>(new SupplierMessage("Proveedor no encontrado"), HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return new ResponseEntity<>(new SupplierMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nit/{nit}")
    public ResponseEntity<?> getSupplierByNit(@PathVariable @Valid String nit) {

        try {
            SupplierResponse response = supplierService.getSupplierByNit(nit);

            if (response == null) {
                return new ResponseEntity<>(new SupplierMessage("Proveedor no encontrado"), HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return new ResponseEntity<>(new SupplierMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(
            @RequestParam @Valid Integer cc,
            @PathVariable @Valid Integer id,
            @Valid @RequestBody SupplierRequest request) {

        try {
            SupplierMessage response = supplierService.updateSupplier(cc, id, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(new SupplierMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(
            @RequestParam @Valid Integer cc,
            @PathVariable @Valid Integer id) {

        try {
            SupplierMessage response = supplierService.deleteSupplier(cc, id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(new SupplierMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/warehouse-entry")
    public ResponseEntity<?> warehouseEntry(
            @RequestParam @Valid Integer cc,
            @Valid @RequestBody SupplierProductRequest request) {

        try {
            SupplierMessage response = supplierService.warehouseEntry(cc, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(new SupplierMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}