package com.syncra.supermarket.Service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.syncra.supermarket.Dto.Employee.EmployeeRequest;
import com.syncra.supermarket.Dto.Product.ProductRequest;
import com.syncra.supermarket.Dto.Supplier.SupplierMessage;
import com.syncra.supermarket.Entity.EmployeeEntity;
import com.syncra.supermarket.Entity.ProductEntity;
import com.syncra.supermarket.Repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;

    public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

        Optional<ProductEntity> findByName(String name);

        Optional<ProductEntity> findByPrice(Double price);

        Optional<ProductEntity> findByStock(Integer stock);

        boolean existsByName(String name);

    }

    public SupplierMessage createEmployee(EmployeeRequest employeeRequest) {
        SupplierMessage message = new SupplierMessage(null);

        if (employeeRepository.existsById(employeeRequest.getCc())) {
            message.setMessage("El empleado ya existe");
            return message;
        }

        EmployeeEntity employee = new EmployeeEntity();
        employee.setCc(employeeRequest.getCc());
        employee.setName(employeeRequest.getName());
        employee.setPost(EmployeeEntity.Post.valueOf(employeeRequest.getPost().toUpperCase()));
        employee.setEntrydate(employeeRequest.getEntryDate());
        employee.setSalary(employeeRequest.getSalary());

        employeeRepository.save(employee);

        message.setMessage("Empleado " + employee.getName() + " creado exitosamente");
        return message;
    }

    public SupplierMessage deleteEmployee(int id) {
        SupplierMessage message = new SupplierMessage(null);

        Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isEmpty()) {
            message.setMessage("Empleado no encontrado");
            return message;
        }

        EmployeeEntity employee = employeeOptional.get();
        employeeRepository.delete(employee);

        message.setMessage("Empleado " + employee.getName() + " eliminado con éxito");
        return message;
    }

    public SupplierMessage searchEmployee(int id) {
        SupplierMessage message = new SupplierMessage(null);

        Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isEmpty()) {
            message.setMessage("Empleado no encontrado");
            return message;
        }

        EmployeeEntity employee = employeeOptional.get();
        message.setMessage("Empleado encontrado: " + employee.getName());

        return message;
    }

    public SupplierMessage updateEmployee(int id, EmployeeRequest employeeRequest) {
        SupplierMessage message = new SupplierMessage(null);

        Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isEmpty()) {
            message.setMessage("Empleado no encontrado");
            return message;
        }

        EmployeeEntity employee = employeeOptional.get();

        employee.setName(employeeRequest.getName());
        employee.setPost(EmployeeEntity.Post.valueOf(employeeRequest.getPost().toUpperCase()));
        employee.setEntrydate(employeeRequest.getEntryDate());
        employee.setSalary(employeeRequest.getSalary());

        employeeRepository.save(employee);

        message.setMessage("Empleado " + employee.getName() + " actualizado con éxito");
        return message;
    }

    public SupplierMessage getEmployee(int id) {
        SupplierMessage message = new SupplierMessage(null);

        Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isEmpty()) {
            message.setMessage("Empleado no encontrado");
            return message;
        }

        EmployeeEntity employee = employeeOptional.get();
        message.setMessage("Empleado encontrado: " + employee.getName());

        return message;
    }

    /*------------------------------------------------------------------------------ */

    public SupplierMessage createProduct(ProductRequest request) {
        SupplierMessage message = new SupplierMessage(null);

        if (productRepository.existsByName(request.getName())) {
            message.setMessage("El producto ya existe");
            return message;
        }

        ProductEntity product = new ProductEntity();

        product.setName(request.getName());
        product.setBarcode(request.getBarcode());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        productRepository.save(product);

        message.setMessage("Producto creado exitosamente: " + product.getName());
        return message;
    }

    public SupplierMessage getProductByName(String name) {
        SupplierMessage message = new SupplierMessage(null);

        if (name == null || name.isBlank()) {
            message.setMessage("El nombre es obligatorio");
            return message;
        }

        Optional<ProductEntity> product = productRepository.findByName(name);

        if (product.isEmpty()) {
            message.setMessage("Producto no encontrado");
            return message;
        }

        message.setMessage("Producto encontrado: " + product.get().getName());
        return message;
    }

    public SupplierMessage getProductByPrice(Double price) {
        SupplierMessage message = new SupplierMessage(null);

        if (price == null || price <= 0) {
            message.setMessage("El precio debe ser mayor a 0");
            return message;
        }

        Optional<ProductEntity> product = productRepository.findByPrice(price);

        if (product.isEmpty()) {
            message.setMessage("Producto no encontrado");
            return message;
        }

        message.setMessage("Producto encontrado con precio: " + product.get().getPrice());
        return message;
    }

    public SupplierMessage getProductByStock(Integer stock) {
        SupplierMessage message = new SupplierMessage(null);

        if (stock == null || stock < 0) {
            message.setMessage("El stock no puede ser negativo");
            return message;
        }

        Optional<ProductEntity> product = productRepository.findByStock(stock);

        if (product.isEmpty()) {
            message.setMessage("Producto no encontrado");
            return message;
        }

        message.setMessage("Producto encontrado con stock: " + product.get().getStock());
        return message;
    }

    public SupplierMessage updateProduct(Integer id, ProductRequest request) {
        SupplierMessage message = new SupplierMessage(null);

        Optional<ProductEntity> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            message.setMessage("Producto no encontrado");
            return message;
        }

        ProductEntity product = productOptional.get();

        product.setName(request.getName());
        product.setBarcode(request.getBarcode());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        productRepository.save(product);

        message.setMessage("Producto actualizado: " + product.getName());
        return message;
    }

    public SupplierMessage deleteProduct(Integer id) {
        SupplierMessage message = new SupplierMessage(null);

        Optional<ProductEntity> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            message.setMessage("Producto no encontrado");
            return message;
        }

        productRepository.delete(productOptional.get());

        message.setMessage("Producto eliminado correctamente");
        return message;
    }
}