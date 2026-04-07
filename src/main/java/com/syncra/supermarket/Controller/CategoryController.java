package com.syncra.supermarket.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.syncra.supermarket.Dto.Category.CategoryMessage;
import com.syncra.supermarket.Dto.Category.CategoryRequest;
import com.syncra.supermarket.Dto.Category.CategoryResponse;
import com.syncra.supermarket.Service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<CategoryResponse>> listCategory() {
        try {
            List<CategoryResponse> categories = categoryService.listCategory();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> searchById(@PathVariable int id) {
        try {
            CategoryResponse response = categoryService.searchId(id);
            if (response == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

  
    @PostMapping("/{cc}")
    public ResponseEntity<CategoryMessage> createCategory(
            @PathVariable Integer cc,
            @Valid @RequestBody CategoryRequest request) {
        try {
            CategoryMessage message = categoryService.createCategory(cc, request);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PutMapping("/{cc}/{id}")
    public ResponseEntity<CategoryMessage> updateCategory(
            @PathVariable Integer cc,
            @PathVariable int id,
            @Valid @RequestBody CategoryRequest request) {
        try {
            CategoryMessage message = categoryService.updateCategory(cc, id, request);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping("/{id}/{cc}")
    public ResponseEntity<CategoryMessage> deleteCategory(
            @PathVariable int id,
            @PathVariable Integer cc) {
        try {
            CategoryMessage message = categoryService.deleteCategory(id, cc);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
