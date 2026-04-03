package com.syncra.supermarket.Service;

import com.syncra.supermarket.Dto.Category.CategoryMessage;
import com.syncra.supermarket.Dto.Category.CategoryRequest;
import com.syncra.supermarket.Dto.Category.CategoryResponse;
import com.syncra.supermarket.Dto.Product.ProductResponse;
import com.syncra.supermarket.Entity.CategoryEntity;
import com.syncra.supermarket.Repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

  
    private CategoryResponse toResponse(CategoryEntity category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());

   
        List<ProductResponse> products = category.getProducts().stream()
                .map(prod -> {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setId(prod.getId());
                    productResponse.setName(prod.getName());
                    return productResponse;
                })
                .collect(Collectors.toList());

        response.setProducts(products);
        return response;
    }

  
    public List<CategoryResponse> getAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();

        return categories.stream()
                .map(cat -> toResponse(cat))
                .collect(Collectors.toList());
    }

 
    public CategoryResponse getCategoryById(Integer id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        return toResponse(category);
    }


    public CategoryMessage createCategory(CategoryRequest request) {
        CategoryEntity category = new CategoryEntity();
        category.setName(request.getName());

        categoryRepository.save(category);

        return new CategoryMessage("Categoría creada exitosamente");
    }

    
    public CategoryMessage updateCategory(Integer id, CategoryRequest request) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        category.setName(request.getName());
        categoryRepository.save(category);

        return new CategoryMessage("Categoría actualizada exitosamente");
    }


    public CategoryMessage deleteCategory(Integer id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        categoryRepository.deleteById(id);

        return new CategoryMessage("Categoría eliminada exitosamente");
    }
}