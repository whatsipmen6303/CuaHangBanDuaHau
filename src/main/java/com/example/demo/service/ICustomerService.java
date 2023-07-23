package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICustomerService implements CustomerService{
    @Autowired
    private CustomerRepository repository;


    @Override
    public Customer getCustomer(String username, String password) {
        return repository.findCustomerByUsernameAndPassword(username,password);
    }

    @Override
    public Customer addOrUpdateCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return repository.findCustomerByUsername(username);
    }
}
