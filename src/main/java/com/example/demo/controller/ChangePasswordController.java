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
@RequestMapping("/change-password")
public class ChangePasswordController {

    @Autowired
    private CustomerService service;

    @Autowired
    private HttpSession session;

    private String message;
    private Customer customer;

    @GetMapping("/view")
    public String viewChangePassword(){
        return "/account/view-change-password";
    }

    @PostMapping("/change")
    public String changePassword(Model model,
                                 @RequestParam(name = "oldPassword") String oldPassword,
                                 @RequestParam(name = "newPassword") String newPassword,
                                 @RequestParam(name = "confirmPassword") String confirmPassword){
        if (oldPassword.isBlank() || newPassword.isBlank() || confirmPassword.isBlank()){
            message = "Hay dien day du thong tin!";
        } else {
            customer = (Customer) session.getAttribute("customer");
            if (!oldPassword.equals(customer.getPassword())){
                message = "Old password khong dung!";
            } else if (!newPassword.equals(confirmPassword)){
                message = "New password va re-enter password khong trung khop!";
            } else {
                customer.setPassword(newPassword);
                service.addOrUpdateCustomer(customer);
                message = "Doi mat khau thanh cong!";
            }
        }
        model.addAttribute("message",message);
        return "/account/view-change-password";
    }

}
