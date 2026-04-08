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
    public ResponseEntity<ProductMessage> createProduct(
            @Valid @RequestBody ProductRequest request,
            @RequestParam @Valid Integer cc) {
        try {
            ProductMessage response = productService.createProduct(request, cc);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductMessage(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        try {
            List<ProductResponse> list = productService.listProduct();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable @Valid int id) {
        try {
            ProductResponse response = productService.seacrhId(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductMessage> updateProduct(
            @PathVariable @Valid int id,
            @Valid @RequestBody ProductRequest request,
            @RequestParam @Valid Integer cc) {
        try {
            ProductMessage response = productService.update(id, request, cc);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductMessage(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductMessage> deleteProduct(
            @PathVariable @Valid int id,
            @RequestParam @Valid Integer cc) {
        try {
            ProductMessage response = productService.deleteProduct(id, cc);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductMessage(e.getMessage()));
        }
    }
}