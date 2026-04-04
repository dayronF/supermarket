package com.syncra.supermarket.Service;

import com.syncra.supermarket.Dto.Product.ProductRequest;
import com.syncra.supermarket.Dto.Supplier.SupplierMessage;
import com.syncra.supermarket.Dto.Supplier.SupplierRequest;
import com.syncra.supermarket.Dto.Supplier.SupplierResponse;
import com.syncra.supermarket.Entity.ProductEntity;
import com.syncra.supermarket.Entity.SupplierEntity;
import com.syncra.supermarket.Repository.ProductRepository;
import com.syncra.supermarket.Repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    public SupplierMessage createSupplier(SupplierRequest request) {

        if (request.getName() == null || request.getName().isBlank()) {
            return new SupplierMessage("El nombre del proveedor es obligatorio");
        }

        if (request.getNit() == null || request.getNit().isBlank()) {
            return new SupplierMessage("El NIT es obligatorio");
        }

        if (supplierRepository.ExistByNIt(request.getNit())) {
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

        Optional<SupplierEntity> optional = supplierRepository.finByNit(nit);

        if (optional.isEmpty())
            return null;

        SupplierEntity supplier = optional.get();

        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setNit(supplier.getNit());
        response.setName(supplier.getName());

        return response;
    }

    public List<SupplierEntity> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public SupplierMessage updateSupplier(Integer id, SupplierRequest request) {

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
                supplierRepository.ExistByNIt(request.getNit())) {
            return new SupplierMessage("NIT duplicado");
        }

        supplier.setName(request.getName());
        supplier.setNit(request.getNit());

        supplierRepository.save(supplier);

        return new SupplierMessage("Proveedor actualizado");
    }

    public SupplierMessage deleteSupplier(Integer id) {

        Optional<SupplierEntity> optional = supplierRepository.findById(id);

        if (optional.isEmpty()) {
            return new SupplierMessage("Proveedor no encontrado");
        }

        supplierRepository.delete(optional.get());

        return new SupplierMessage("Proveedor eliminado");
    }

    
    public SupplierMessage createProduct(ProductRequest request) {

        if (request.getName() == null || request.getName().isBlank()) {
            return new SupplierMessage("El nombre del producto es obligatorio");
        }

        if (request.getPrice() == null || request.getPrice() <= 0) {
            return new SupplierMessage("El precio debe ser mayor a 0");
        }

        if (request.getStock() == null || request.getStock() < 0) {
            return new SupplierMessage("El stock no puede ser negativo");
        }

        if (request.getBarcode() == null || request.getBarcode().isBlank()) {
            return new SupplierMessage("El código de barras es obligatorio");
        }

        if (productRepository.ExistBarCode(request.getBarcode())) {
            return new SupplierMessage("Código de barras duplicado");
        }

        ProductEntity product = new ProductEntity();
        product.setName(request.getName());
        product.setBarcode(request.getBarcode());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setState(true);

        productRepository.save(product);

        return new SupplierMessage("Producto creado");
    }

    public SupplierMessage getProductByBarcode(String barcode) {

        if (barcode == null || barcode.isBlank()) {
            return new SupplierMessage("El código de barras es obligatorio");
        }

        Optional<ProductEntity> optional = productRepository.findByBarcode(barcode);

        if (optional.isEmpty()) {
            return new SupplierMessage("Producto no encontrado");
        }

        ProductEntity product = optional.get();

        if (!product.isState()) {
            return new SupplierMessage("Producto inactivo");
        }

        return new SupplierMessage("Producto encontrado");
    }

    public List<ProductEntity> getAllActiveProducts() {
        return productRepository.findByStateTrue();
    }

    public SupplierMessage updateProduct(Integer id, ProductRequest request) {

        if (request.getName() == null || request.getName().isBlank()) {
            return new SupplierMessage("El nombre del producto es obligatorio");
        }

        if (request.getPrice() == null || request.getPrice() <= 0) {
            return new SupplierMessage("El precio debe ser mayor a 0");
        }

        if (request.getStock() == null || request.getStock() < 0) {
            return new SupplierMessage("El stock no puede ser negativo");
        }

        Optional<ProductEntity> optional = productRepository.findById(id);

        if (optional.isEmpty()) {
            return new SupplierMessage("Producto no encontrado");
        }

        ProductEntity product = optional.get();

        if (!product.isState()) {
            return new SupplierMessage("Producto inactivo");
        }

        if (!product.getBarcode().equals(request.getBarcode()) &&
                productRepository.ExistBarCode(request.getBarcode())) {
            return new SupplierMessage("Código de barras duplicado");
        }

        product.setName(request.getName());
        product.setBarcode(request.getBarcode());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        productRepository.save(product);

        return new SupplierMessage("Producto actualizado");
    }

    public SupplierMessage deleteProduct(Integer id) {

        Optional<ProductEntity> optional = productRepository.findById(id);

        if (optional.isEmpty()) {
            return new SupplierMessage("Producto no encontrado");
        }

        ProductEntity product = optional.get();

        if (!product.isState()) {
            return new SupplierMessage("Producto ya inactivo");
        }

        product.setState(false);
        productRepository.save(product);

        return new SupplierMessage("Producto desactivado");
    }
}