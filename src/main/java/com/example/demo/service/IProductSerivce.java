package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class IProductSerivce implements ProductService{
    @Autowired
    private ProductRepository repository;

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Product> getAllProductByName(Pageable pageable, String name) {
        return repository.findProductByNameContains(pageable,name);
    }

    @Override
    public Page<Product> getAllProductByNameAndPrice(Pageable pageable, String name, BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.findProductByNameContainsAndPriceBetween(pageable,name,minPrice,maxPrice);
    }

    @Override
    public Page<Product> getAllProductByPrice(Pageable pageable, BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.findProductByPriceBetween(pageable,minPrice,maxPrice);
    }

    @Override
    public Product addOrUpdateProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product getOne(Integer id) {
        return repository.getProductById(id);
    }

    @Override
    public void deleteProduct(Integer id) {
        repository.deleteById(id);
    }
}
