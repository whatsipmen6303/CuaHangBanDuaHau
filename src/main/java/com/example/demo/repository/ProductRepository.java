package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Page<Product> findAll(Pageable pageable);

    Page<Product> findProductByNameContains(Pageable pageable, String name);

    Page<Product> findProductByNameContainsAndPriceBetween(Pageable pageable, String name, BigDecimal minPrice, BigDecimal maxPrice);

    Page<Product> findProductByPriceBetween(Pageable pageable, BigDecimal minPrice, BigDecimal maxPrice);
    Product getProductById(Integer id);
}
