package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ProductService {
    Page<Product> getAllProduct(Pageable pageable);

    Page<Product> getAllProductByName(Pageable pageable, String name);

    Page<Product> getAllProductByNameAndPrice(Pageable pageable, String name, BigDecimal minPrice, BigDecimal maxPrice);

    Page<Product> getAllProductByPrice(Pageable pageable, BigDecimal minPrice, BigDecimal maxPrice);

    Product addOrUpdateProduct(Product product);

    Product getOne(Integer id);

    void deleteProduct(Integer id);
}
