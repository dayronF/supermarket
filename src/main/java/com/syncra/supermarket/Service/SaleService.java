package com.syncra.supermarket.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.syncra.supermarket.Dto.Sale.SaleRequest;
import com.syncra.supermarket.Dto.Sale.SaleResponse;
import com.syncra.supermarket.Dto.SaleDetail.SaleDetailRequest;
import com.syncra.supermarket.Dto.SaleDetail.SaleDetailResponse;
import com.syncra.supermarket.Entity.EmployeeEntity;
import com.syncra.supermarket.Entity.ProductEntity;
import com.syncra.supermarket.Entity.SaleDetailEntity;
import com.syncra.supermarket.Entity.SaleEntity;
import com.syncra.supermarket.Repository.EmployeeRepository;
import com.syncra.supermarket.Repository.ProductRepository;
import com.syncra.supermarket.Repository.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;

    public SaleResponse createSale(SaleRequest request) {

        EmployeeEntity employee = employeeRepository.findById(request.getEmployeeCc())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        SaleEntity sale = new SaleEntity();
        sale.setDate(LocalDateTime.now());
        sale.setEmployee(employee);

        List<SaleDetailEntity> details = new ArrayList<>();

        double subtotal = 0;

        for (SaleDetailRequest dto : request.getItems()) {

            ProductEntity product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (!product.isState()) {
                throw new RuntimeException("Producto inactivo");
            }

            if (product.getStock() < dto.getQuantity()) {
                throw new RuntimeException("Stock insuficiente");
            }

            product.setStock(product.getStock() - dto.getQuantity());
            productRepository.save(product);

            SaleDetailEntity detail = new SaleDetailEntity();
            detail.setSale(sale);
            detail.setProduct(product);
            detail.setQuantity(dto.getQuantity());
            detail.setPriceUnitary(product.getPrice());

            subtotal += product.getPrice() * dto.getQuantity();

            details.add(detail);
        }

        double iva = subtotal * 0.19;
        double total = subtotal + iva;

        sale.setSubtotal(subtotal);
        sale.setIva(iva);
        sale.setTotal(total);
        sale.setSaleDetails(details);

        SaleEntity saved = saleRepository.save(sale);

        return mapToResponse(saved);
    }

    public List<SaleResponse> getAllSales() {
        return saleRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public SaleResponse getSaleById(int id) {
        SaleEntity sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return mapToResponse(sale);
    }

    public List<SaleResponse> getSalesByEmployee(int cc) {
        return saleRepository.findByEmployeeCC(cc)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private SaleResponse mapToResponse(SaleEntity sale) {

        List<SaleDetailResponse> details = sale.getSaleDetails().stream().map(d -> {
            SaleDetailResponse dto = new SaleDetailResponse();
            dto.setId(d.getId());
            dto.setProductName(d.getProduct().getName());
            dto.setQuantity(d.getQuantity());
            dto.setPriceUnitary(d.getPriceUnitary());
            dto.setSubtotalDetail(d.getQuantity() * d.getPriceUnitary());
            return dto;
        }).toList();

        SaleResponse response = new SaleResponse();
        response.setId(sale.getId());
        response.setDate(sale.getDate());
        response.setEmployeeName(sale.getEmployee().getName());
        response.setSubtotal(sale.getSubtotal());
        response.setIva(sale.getIva());
        response.setTotal(sale.getTotal());
        response.setDetails(details);

        return response;
    }
}