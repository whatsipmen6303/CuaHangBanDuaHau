package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EmailSenderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {

    @Autowired
    private CustomerService service;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private HttpSession session;

    private Customer customer;
    private String message;
    private String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private String sendCode;
    private Integer status;

    @GetMapping("/view")
    public String viewForgotPassword(){
        return "/login/view-forgot-password";
    }

    @PostMapping("/get-code")
    public String getCode(Model model, @RequestParam(name = "username") String username){
        if (username.isBlank()){
            message = "Email trong!";
        } else {
            customer = service.getCustomerByUsername(username);
            if (!username.matches(regexEmail)){
                message = "Email sai dinh dang!";
            } else if(customer == null){
                message = "Email chua duoc dang ky!";
            } else {
                sendCode = String.format("%06d", new Random().nextInt(1000000));
                emailSenderService.sendSimpleEmail(username,"Code reset password",sendCode);
                session.setAttribute("email",username);
                message = "Code da gui thanh cong. Hay nhap code de reset password!";
                status = 1;
            }
        }
        model.addAttribute("status",status);
        model.addAttribute("message",message);
        return "/login/view-forgot-password";
    }

    @PostMapping("/submit")
    public String submitCode(Model model, @RequestParam(name = "code") String code){
        status = 1;
        if (code.isBlank()){
            message = "Yeu cau nhap code!";
        } else {
            if (!code.equals(sendCode)){
                message = "Code nhap bi sai. Yeu cau check lai gmail!";
            } else {
                message = "Code chinh xac!";
                status = 2;
            }
        }
        model.addAttribute("status",status);
        model.addAttribute("message",message);
        return "/login/view-forgot-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(Model model){
        customer = service.getCustomerByUsername((String) session.getAttribute("email"));
        customer.setPassword(String.valueOf(1));
        service.addOrUpdateCustomer(customer);
        message = "Reset password thanh cong!";
        model.addAttribute("message",message);
        return "/login/view-forgot-password";
    }
}
