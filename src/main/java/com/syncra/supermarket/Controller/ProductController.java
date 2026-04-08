package com.syncra.supermarket.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.syncra.supermarket.Dto.Product.ProductMessage;
import com.syncra.supermarket.Dto.Product.ProductRequest;
import com.syncra.supermarket.Dto.Product.ProductResponse;
import com.syncra.supermarket.Service.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductRequest request,
            @RequestParam @Valid Integer cc) {

        try {
            ProductMessage response = productService.createProduct(request, cc);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ProductMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {

        try {
            List<ProductResponse> list = productService.listProduct();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(new ProductMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable @Valid int id) {

        try {
            ProductResponse response = productService.seacrhId(id);

            if (response == null) {
                return new ResponseEntity<>(new ProductMessage("Producto no encontrado"), HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return new ResponseEntity<>(new ProductMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable @Valid int id,
            @Valid @RequestBody ProductRequest request,
            @RequestParam @Valid Integer cc) {

        try {
            ProductMessage response = productService.update(id, request, cc);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(new ProductMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable @Valid int id,
            @RequestParam @Valid Integer cc) {

        try {
            ProductMessage response = productService.deleteProduct(id, cc);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(new ProductMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}