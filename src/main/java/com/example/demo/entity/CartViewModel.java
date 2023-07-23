package com.example.demo.entity;

import java.math.BigDecimal;

public class CartViewModel {

    private Integer id;
    private Integer idProduct;
    private String name;
    private String image;
    private Integer quantity;
    private BigDecimal price;

    public CartViewModel() {
    }

    public CartViewModel(Integer id, Integer idProduct, String name, String image, Integer quantity, BigDecimal price) {
        this.id = id;
        this.idProduct = idProduct;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
