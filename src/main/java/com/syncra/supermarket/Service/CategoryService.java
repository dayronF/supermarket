package com.syncra.supermarket.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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

    public CategoryMessage createCategory(Integer cc, CategoryRequest request) {

        Optional<EmployeeEntity> employee = employeeRepository.findById(cc);

        if (employee.isEmpty()) {
            return new CategoryMessage("Empleado no encontrado");
        }

        if (!employee.get().getPost().equals(Post.ADMINISTRADOR)) {
            return new CategoryMessage("Empleado no autorizado para crear categorías");

        }

        if (categoryRepository.existsByName(request.getName()))

        {
            return new CategoryMessage("Ya existe una categoría con ese nombre");
        }

        CategoryEntity category = new CategoryEntity();
        category.setName(request.getName());
        categoryRepository.save(category);
        return new CategoryMessage("Categoría " + category.getName() + " creada exitosamente");
    }

    public List<CategoryResponse> listCategory() {

        return categoryRepository.findAll().stream()
                .map(category -> {
                    CategoryResponse response = new CategoryResponse();
                    response.setId(category.getId());
                    response.setName(category.getName());
                    return response;
                })
                .collect(Collectors.toList());
    }

    public CategoryResponse searchId(int id) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            return null;
        }
        CategoryResponse response = new CategoryResponse();
        response.setId(categoryOptional.get().getId());
        response.setName(categoryOptional.get().getName());
        response.setProducts(categoryOptional.get().getProducts().stream()
                .map(product -> {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setId(product.getId());
                    productResponse.setName(product.getName());
                    productResponse.setBarcode(product.getBarcode());
                    productResponse.setPrice(product.getPrice());
                    productResponse.setStock(product.getStock());
                    return productResponse;
                })
                .collect(Collectors.toList()));
        return response;
    }

    public CategoryMessage updateCategory(Integer cc, int id, CategoryRequest request) {

        Optional<EmployeeEntity> employee = employeeRepository.findById(cc);
        if (employee.isEmpty()) {
            return new CategoryMessage("Empleado no encontrado");
        }
        if (!employee.get().getPost().equals(Post.ADMINISTRADOR)) {
            return new CategoryMessage("Empleado no autorizado para actualizar categorías");
        }

        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            return new CategoryMessage("Categoría no encontrada");
        }
        CategoryEntity category = categoryOptional.get();
        category.setName(request.getName());
        categoryRepository.save(category);
        return new CategoryMessage("Categoría " + category.getName() + " actualizada exitosamente");
    }

    public CategoryMessage deleteCategory(int id, Integer cc) {

        Optional<EmployeeEntity> employee = employeeRepository.findById(cc);
        if (employee.isEmpty()) {
            return new CategoryMessage("Empleado no encontrado");
        }
        if (!employee.get().getPost().equals(Post.ADMINISTRADOR)) {
            return new CategoryMessage("Empleado no autorizado para eliminar categorías");
        }
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return new CategoryMessage("Categoría eliminada exitosamente");
        }
        return new CategoryMessage("Categoría no encontrada");
    }
}