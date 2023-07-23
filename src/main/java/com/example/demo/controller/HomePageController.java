package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home-page")
public class HomePageController {

    @Autowired
    private ProductService service;

    @Autowired
    private HttpSession session;
    private String message;
    private Page<Product> listProduct;

    @GetMapping
    public String viewHomePage(Model model,@RequestParam(defaultValue = "0",name = "num")Integer num){
        Pageable pageable = PageRequest.of(num,4);
        listProduct = service.getAllProduct(pageable);
//        message = "Chao mung den voi cua hang dua hau!";
        model.addAttribute("message",message);
        model.addAttribute("listProduct",listProduct);
        return "/index";
    }

    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "/login/view-login";
    }
}
