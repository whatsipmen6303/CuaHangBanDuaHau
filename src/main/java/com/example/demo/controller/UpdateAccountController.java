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
@RequestMapping("/update-account")
public class UpdateAccountController {

    @Autowired
    private CustomerService service;

    @Autowired
    private HttpSession session;

    private Customer customer;
    private String message;
    private String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @GetMapping("view")
    public String viewUpdateAccount(){
        return "/account/view-update-account";
    }

    @PostMapping("/update")
    public String updateAccount(Model model,
                                @RequestParam(name = "name") String name,
                                @RequestParam(name = "username") String username){
        if (name.isBlank() || username.isBlank()){
            message = "Hay dien day du thong tin!";
        } else {
            customer = service.getCustomerByUsername(username);
            if (customer != null){
                message = "Email da duoc dang ky!";
            } else if (!username.matches(regexEmail)){
                message = "Email sai dinh dang!";
            } else {
                customer = (Customer) session.getAttribute("customer");
                customer.setName(name);
                customer.setUsername(username);
                service.addOrUpdateCustomer(customer);
                message = "Update thanh cong!";
            }
        }
        model.addAttribute("message",message);
        return "/account/view-update-account";
    }
}
