package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @NotBlank(message = "Name cannot be null")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Price cannot be null")
    @Positive
    @Column(name = "price")
    private BigDecimal price;

    @NotNull(message = "Quantity cannot be null")
    @Positive
    @Column(name = "quantity")
    private Integer quantity;

    @NotNull(message = "Weight cannot be null")
    @DecimalMin(value = "0.1", message = "Weight >= 0.1")
    @Column(name = "weight")
    private BigDecimal weight;

    @NotBlank(message = "Image cannot be null")
    @Column(name = "image")
    private String image;

    @Column(name = "origin")
    private String origin;

    @NotBlank(message = "Description cannot be null")
    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "price_reduced")
    private BigDecimal priceReduced;

}
