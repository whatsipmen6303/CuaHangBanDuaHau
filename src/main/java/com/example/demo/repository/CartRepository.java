package com.example.demo.repository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findCartByCustomer (Customer customer);
}
