package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Customer;
import com.example.demo.service.CartService;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private CustomerService service;

    @Autowired
    private CartService cartService;

    private String message;
    private Customer customer;
    private String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @GetMapping("/view")
    public String viewRegister(Model model){
        return "/login/view-register";
    }

    @PostMapping("/register")
    public String register(Model model,
                           @RequestParam(name = "name") String name,
                           @RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password,
                           @RequestParam(name = "confirmPassword") String confirmPassword){
        if (name.isBlank() || username.isBlank() || password.isBlank() || confirmPassword.isBlank()){
            message = "Hay dien day du thong tin!";
        } else {
            customer = service.getCustomerByUsername(username);
            if (customer != null){
                message = "Email da duoc dang ky!";
            } else if (!username.matches(regexEmail)){
                message = "Email sai dinh dang!";
            } else if (!password.equals(confirmPassword)){
                message = "Password va re-enter password khong trung khop!";
            } else {
                customer = new Customer(null,name,username,password);
                Cart cart = new Cart(null,customer,1);
                service.addOrUpdateCustomer(customer);
                cartService.saveOrUpdateCart(cart);
                message = "Dang ky thanh cong!";
            }
        }

        model.addAttribute("message",message);
        return "/login/view-register";
    }
}
