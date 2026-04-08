package com.syncra.supermarket.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syncra.supermarket.Dto.Category.CategoryMessage;
import com.syncra.supermarket.Dto.Category.CategoryRequest;
import com.syncra.supermarket.Dto.Category.CategoryResponse;
import com.syncra.supermarket.Dto.Product.ProductResponse;
import com.syncra.supermarket.Entity.CategoryEntity;
import com.syncra.supermarket.Entity.EmployeeEntity;
import com.syncra.supermarket.Entity.EmployeeEntity.Post;
import com.syncra.supermarket.Repository.CategoryRepository;
import com.syncra.supermarket.Repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public CategoryMessage createCategory(Integer cc, CategoryRequest request) {
        validateAdmin(cc);

        if (categoryRepository.existsByName(request.getName())) {
            return new CategoryMessage("Ya existe una categoría con ese nombre");
        }

        CategoryEntity category = new CategoryEntity();
        category.setName(request.getName());
        categoryRepository.save(category);

        return new CategoryMessage("Categoría " + category.getName() + " creada exitosamente");
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> listCategory() {
        return categoryRepository.findAll().stream()
                .map(category -> {
                    CategoryResponse response = new CategoryResponse();
                    response.setId(category.getId());
                    response.setName(category.getName());
                    return response;
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryResponse searchId(int id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setProducts(category.getProducts().stream()
                .map(product -> {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setId(product.getId());
                    productResponse.setName(product.getName());
                    productResponse.setBarcode(product.getBarcode());
                    productResponse.setPrice(product.getPrice());
                    productResponse.setStock(product.getStock());
                    return productResponse;
                })
                .toList());

        return response;
    }

    @Transactional
    public CategoryMessage updateCategory(Integer cc, int id, CategoryRequest request) {
        validateAdmin(cc);

        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        category.setName(request.getName());
        categoryRepository.save(category);

        return new CategoryMessage("Categoría " + category.getName() + " actualizada exitosamente");
    }

    @Transactional
    public CategoryMessage deleteCategory(int id, Integer cc) {
        validateAdmin(cc);

        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        categoryRepository.delete(category);

        return new CategoryMessage("Categoría eliminada exitosamente");
    }

    private void validateAdmin(Integer cc) {
        EmployeeEntity employee = employeeRepository.findById(cc)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        if (!employee.getPost().equals(Post.ADMINISTRADOR)) {
            throw new RuntimeException("Empleado no autorizado");
        }
    }
}