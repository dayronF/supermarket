package com.syncra.supermarket.Service;

import com.syncra.supermarket.Dto.Supplier.SupplierMessage;
import com.syncra.supermarket.Dto.Supplier.SupplierRequest;
import com.syncra.supermarket.Dto.Supplier.SupplierResponse;
import com.syncra.supermarket.Dto.SupplierProduct.SupplierProductRequest;
import com.syncra.supermarket.Entity.ProductEntity;
import com.syncra.supermarket.Entity.SupplierEntity;
import com.syncra.supermarket.Entity.SupplierProductEntity;
import com.syncra.supermarket.Repository.ProductRepository;
import com.syncra.supermarket.Repository.SupplierProductRepository;
import com.syncra.supermarket.Repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final SupplierProductRepository supplierProductRepository;
    private final EmployeeRepository employeeRepository;

    public SupplierMessage createSupplier(SupplierRequest request) {

        Optional<EmployeeEntity> employee = employeeRepository.findById(cc);

        if (employee.isEmpty()) {
            return new SupplierMessage("Empleado no encontrado");
        }

        if (employee.get().getPost() != EmployeeEntity.Post.ADMINISTRADOR) {
            return new SupplierMessage("No tienes permisos para esta acción");
        }

        if (request.getName() == null || request.getName().isBlank()) {
            return new SupplierMessage("El nombre del proveedor es obligatorio");
        }

        if (request.getNit() == null || request.getNit().isBlank()) {
            return new SupplierMessage("El NIT es obligatorio");
        }

        if (supplierRepository.existsByNit(request.getNit())) {
            return new SupplierMessage("Ya existe un proveedor con el NIT: " + request.getNit());
        }

        SupplierEntity supplier = new SupplierEntity();
        supplier.setName(request.getName());
        supplier.setNit(request.getNit());

        supplierRepository.save(supplier);

        return new SupplierMessage("Proveedor creado correctamente");

    }

    public SupplierResponse getSupplierById(Integer id) {

        if (id == null || id <= 0)
            return null;

        Optional<SupplierEntity> optional = supplierRepository.findById(id);

        if (optional.isEmpty())
            return null;

        SupplierEntity supplier = optional.get();

        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setNit(supplier.getNit());
        response.setName(supplier.getName());

        return response;
    }

    public SupplierResponse getSupplierByNit(String nit) {

        if (nit == null || nit.isBlank())
            return null;

        Optional<SupplierEntity> optional = supplierRepository.findByNit(nit);

        if (optional.isEmpty())
            return null;

        SupplierEntity supplier = optional.get();

        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setNit(supplier.getNit());
        response.setName(supplier.getName());

        return response;
    }

    public List<SupplierResponse> getAllSuppliers() {
        List<SupplierEntity> Proveedores = supplierRepository.findAll();
        List<SupplierResponse> responses = new ArrayList<>();

        for (SupplierEntity supplier : Proveedores) {
            SupplierResponse sResponse = new SupplierResponse();
            sResponse.setId(supplier.getId());
            sResponse.setName(supplier.getName());
            sResponse.setNit(supplier.getNit());
            responses.add(sResponse);
        }
        return responses;
    }

    public SupplierMessage updateSupplier(Integer id, SupplierRequest request) {

        Optional<EmployeeEntity> employee = employeeRepository.findById(cc);

        if (employee.isEmpty()) {
            return new SupplierMessage("Empleado no encontrado");
        }

        if (employee.get().getPost() != EmployeeEntity.Post.ADMINISTRADOR) {
            return new SupplierMessage("No tienes permisos para esta acción");
        }

        if (request.getName() == null || request.getName().isBlank()) {
            return new SupplierMessage("El nombre del proveedor es obligatorio");
        }

        if (request.getNit() == null || request.getNit().isBlank()) {
            return new SupplierMessage("El NIT es obligatorio");
        }

        Optional<SupplierEntity> optional = supplierRepository.findById(id);

        if (optional.isEmpty()) {
            return new SupplierMessage("Proveedor no encontrado");
        }

        SupplierEntity supplier = optional.get();

        if (!supplier.getNit().equals(request.getNit()) &&
                supplierRepository.existsByNit(request.getNit())) {
            return new SupplierMessage("NIT duplicado");
        }

        supplier.setName(request.getName());
        supplier.setNit(request.getNit());

        supplierRepository.save(supplier);

        return new SupplierMessage("Proveedor actualizado");
    }

    public SupplierMessage deleteSupplier(Integer id) {

        Optional<EmployeeEntity> employee = employeeRepository.findById(cc);

        if (employee.isEmpty()) {
            return new SupplierMessage("Empleado no encontrado");
        }

        if (employee.get().getPost() != EmployeeEntity.Post.ADMINISTRADOR) {
            return new SupplierMessage("No tienes permisos para esta acción");
        }

        Optional<SupplierEntity> optional = supplierRepository.findById(id);

        if (optional.isEmpty()) {
            return new SupplierMessage("Proveedor no encontrado");
        }

        supplierRepository.delete(optional.get());

        return new SupplierMessage("Proveedor eliminado correctamente");

    }

    public SupplierMessage warehouseEntry(Integer cc, SupplierProductRequest request) {

        Optional<EmployeeEntity> employee = employeeRepository.findById(cc);

        if (employee.isEmpty()) {
            return new SupplierMessage("Empleado no encontrado");
        }

        if (employee.get().getPost() != EmployeeEntity.Post.ADMINISTRADOR &&
                employee.get().getPost() != EmployeeEntity.Post.CAJERO) {
            return new SupplierMessage("No tienes permisos para esta acción");
        }

        if (request.getProductId() == null) {
            return new SupplierMessage("El ID del producto es obligatorio");
        }

        if (request.getSupplierId() == null) {
            return new SupplierMessage("El ID del proveedor es obligatorio");
        }

        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            return new SupplierMessage("La cantidad debe ser mayor a 0");
        }

        Optional<ProductEntity> optionalProduct = productRepository.findById(request.getProductId());
        Optional<SupplierEntity> optionalSupplier = supplierRepository.findById(request.getSupplierId());

        if (optionalProduct.isEmpty()) {
            return new SupplierMessage("Producto no encontrado");
        }

        if (optionalSupplier.isEmpty()) {
            return new SupplierMessage("Proveedor no encontrado");
        }

        ProductEntity product = optionalProduct.get();

        if (!product.isState()) {
            return new SupplierMessage("No se puede abastecer un producto inactivo");
        }

        product.setStock(product.getStock() + request.getQuantity());
        productRepository.save(product);

        SupplierProductEntity entity = new SupplierProductEntity();
        entity.setProduct(product);
        entity.setSupplier(optionalS.get());
        entity.setQuantity(request.getQuantity());
        entity.setEntryDate(LocalDateTime.now());

        supplierProductRepository.save(entity);

        return new SupplierMessage("Entrada de almacén registrada correctamente");

        product.setStock(product.getStock() + request.getQuantity());
        productRepository.save(product);

        SupplierProductEntity entity = new SupplierProductEntity();
        entity.setProduct(OptionalP.get());
        entity.setSupplier(OptionalS.get());
        entity.setQuantity(request.getQuantity());
        entity.setEntryDate(LocalDateTime.now());
        supplierProductRepository.save(entity);

        message.setMessage("Entrada de almacén registrada. Stock actualizado correctamente");
        return message;
    }

}