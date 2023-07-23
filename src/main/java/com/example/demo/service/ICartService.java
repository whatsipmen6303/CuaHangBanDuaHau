package com.example.demo.service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICartService implements CartService{

    @Autowired
    private CartRepository repository;

    @Override
    public Cart findCartByCustomer(Customer customer) {
        return repository.findCartByCustomer(customer);
    }

    @Override
    public Cart saveOrUpdateCart(Cart cart) {
        return repository.save(cart);
    }
}
