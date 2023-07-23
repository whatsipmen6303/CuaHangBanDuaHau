package com.example.demo.service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Customer;

public interface CartService {

    Cart findCartByCustomer(Customer customer);

    Cart saveOrUpdateCart(Cart cart);
}
