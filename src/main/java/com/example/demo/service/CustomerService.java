package com.example.demo.service;

import com.example.demo.entity.Customer;

public interface CustomerService {

    Customer getCustomer(String username, String password);

    Customer addOrUpdateCustomer(Customer customer);

    Customer getCustomerByUsername(String username);
}
