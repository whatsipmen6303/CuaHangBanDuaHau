package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CustomerService service;
    private Customer customer;
    private String message;

    @Autowired
    private HttpSession session;

    @GetMapping("/view")
    public String viewLogin(){
        return "/login/view-login";
    }

    @PostMapping("/login")
    public String Login(Model model, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password){
        customer = service.getCustomer(username,password);

        if (username.isBlank() || password.isBlank()){
            message = "Username va password khong duoc trong!";
            model.addAttribute("message",message);
            return "/login/view-login";
        }

        if (customer == null){
            message = "Username hoac password khong dung!";
            model.addAttribute("message",message);
        } else {
            session.setAttribute("customer",customer);
            return "redirect:/home-page";
        }

        return "/login/view-login";
    }
}
