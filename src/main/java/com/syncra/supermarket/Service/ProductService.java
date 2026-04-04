package com.syncra.supermarket.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.syncra.supermarket.Dto.Product.ProductMessage;
import com.syncra.supermarket.Dto.Product.ProductRequest;
import com.syncra.supermarket.Dto.Product.ProductResponse;
import com.syncra.supermarket.Entity.CategoryEntity;
import com.syncra.supermarket.Entity.ProductEntity;
import com.syncra.supermarket.Repository.CategoryRepository;
import com.syncra.supermarket.Repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductMessage CreateProduct(ProductRequest producto) {
        ProductMessage message = new ProductMessage(null);

        if (productRepository.existsByBarcode(producto.getBarcode())) {
            message.setMessage("El codigo de barras ya esta registrado en otro producto el cual es " + producto.getName());
            return message;
        }

        Optional<CategoryEntity> categoria = categoryRepository.findById(producto.getCategoryId());
        if (categoria.isEmpty()) {
            message.setMessage("Categoria no encontrada por id");
            return message;

        }

        ProductEntity Producto = new ProductEntity();
        Producto.setName(producto.getName());
        Producto.setBarcode(producto.getBarcode());
        Producto.setPrice(producto.getPrice());
        Producto.setStock(producto.getStock());
        Producto.setState(true);
        Producto.setCategory(categoria.get());

        productRepository.save(Producto);
        message.setMessage("Producto" + Producto.getName() + "Creado Exitosamente");

        return message;

    }

    public List<ProductResponse> ListProdcut() {

        List<ProductEntity> productos = productRepository.findByStateTrue();
        List<ProductResponse> responses = new ArrayList<>();

        for (ProductEntity product : productos) {

            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setBarcode(product.getBarcode());
            productResponse.setPrice(product.getPrice());
            productResponse.setStock(product.getStock());
            productResponse.setState(product.isState());
            productResponse.setCategoryName(product.getCategory().getName());
            responses.add(productResponse);
        }

        return responses;
    }

    public ProductResponse SeacrhId(int id) {
        ProductMessage message = new ProductMessage(null);

        Optional<ProductEntity> prodOptional = productRepository.findById(id);
        if (prodOptional.isEmpty()) {
            return null;
        }

        ProductEntity produc = prodOptional.get();

        if (!produc.isState()) {
            return null;
        }

        ProductResponse response = new ProductResponse();
        response.setId(produc.getId());
        response.setBarcode(produc.getBarcode());
        response.setPrice(produc.getPrice());
        response.setStock(produc.getStock());
        response.setCategoryName(produc.getCategory().getName());

        message.setMessage(response.toString());
        return response;
    }

    public ProductMessage Update(int id, ProductRequest productRequest) {
        ProductMessage message = new ProductMessage(null);

        Optional<ProductEntity> opcion = productRepository.findById(id);
        Optional<ProductEntity> ExistBarCode = productRepository.findByBarcode(productRequest.getBarcode());

        if (opcion.isEmpty()) {
            message.setMessage("Producto no encontrado");
            return message;
        }

        if (ExistBarCode.isPresent() && ExistBarCode.get().getId() != id) {
            message.setMessage("Ya existe un producto con el código de barras: " + productRequest.getName());
            return message;
        }

        ProductEntity productEntity = opcion.get();
        productEntity.setBarcode(productRequest.getBarcode());
        productEntity.setName(productRequest.getName());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setStock(productRequest.getStock());

        Optional<CategoryEntity> categoria = categoryRepository.findById(productRequest.getCategoryId());
        if (categoria.isEmpty()) {
            message.setMessage("Categoría no encontrada, no se puede actualizar");
            return message;
        }
        productEntity.setCategory(categoria.get());

        productRepository.save(productEntity);
        message.setMessage("Producto Actualizado correctamente");
        return message;
    }

    public ProductMessage DeleteProduct(int id) {
        ProductMessage message = new ProductMessage(null);

        Optional<ProductEntity> opcion = productRepository.findById(id);

        if (opcion.isEmpty()) {
            message.setMessage("Producto no encontrado");
            return message;
        }

        ProductEntity productEntity = opcion.get();
        productEntity.setState(false);

        productRepository.save(productEntity);
        message.setMessage("Producto eliminado Exitosamente");

        return message;
    }

}
