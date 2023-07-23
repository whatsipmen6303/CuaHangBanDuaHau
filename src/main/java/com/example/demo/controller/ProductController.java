package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    private Page<Product> listProduct;
    private String message;
    private String regexNumber = "^-?\\d+$";

    @GetMapping("/view")
    public String viewProduct(Model model, @RequestParam(defaultValue = "0",name = "num")Integer num){
        Pageable pageable = PageRequest.of(num,5);
        listProduct = service.getAllProduct(pageable);
        model.addAttribute("listProduct",listProduct);
        return "/product/view-product";
    }

    @GetMapping("/view-add")
    public String viewAddProduct(){
        return "/product/add-product";
    }

    @PostMapping("/add")
    public String addProduct(Model model, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "/product/add-product";
        } else {
            product.setPriceReduced(new BigDecimal(0.0));
            service.addOrUpdateProduct(product);
            message = "Them thanh cong!";
        }
        model.addAttribute("message",message);
        return "/product/add-product";
    }

    @GetMapping("/delete")
    public String deleteProduct(Model model, @RequestParam(name = "id") Integer id, @RequestParam(defaultValue = "0",name = "num")Integer num){
        service.deleteProduct(id);
        return "redirect:/product/view";
    }

    @GetMapping("/detail")
    public String detailProduct(Model model,@RequestParam(name = "id") Integer id){
        Product product = service.getOne(id);
        model.addAttribute("product",product);
        return "/product/detail-product";
    }

    @PostMapping("/update")
    public String updateProduct(Model model, @Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                @RequestParam(name = "id") Integer id){

        if (bindingResult.hasErrors()) {
            return "/product/detail-product";
        } else {
            product.setId(id);
            service.addOrUpdateProduct(product);
            message = "Cap nhat thanh cong!";
        }
        model.addAttribute("message",message);
        return "/product/detail-product";
    }

    @GetMapping("/search")
    public String searchProduct(Model model,
                                @RequestParam(defaultValue = "0",name = "num")Integer num,
                                @RequestParam(name = "nameSearch") String nameSearch,
                                @RequestParam(name = "minPrice") String minPrice,
                                @RequestParam(name = "maxPrice") String maxPrice){
        Pageable pageable = PageRequest.of(num,5);
        BigDecimal minPriceParse = null;
        BigDecimal maxPriceParse = null;

        if (nameSearch.equals("") & minPrice.equals("") & maxPrice.equals("")){
            listProduct = service.getAllProduct(pageable);
            model.addAttribute("listProduct",listProduct);
            return "/product/view-product";
        }

        if (minPrice.isBlank() || maxPrice.isBlank()){
            minPriceParse = new BigDecimal(0);
            maxPriceParse = new BigDecimal(999999999);
        } else if (!minPrice.matches(regexNumber) || !maxPrice.matches(regexNumber)){
            message = "Min price va max price phai la so nguyen";
            return "redirect:/product/view";
        }

        if (!nameSearch.isBlank()){
            if (minPrice.isBlank() & maxPrice.isBlank()){
                //search theo name
                listProduct = service.getAllProductByName(pageable,nameSearch);
            } else if (maxPrice.isBlank()){
                //search theo name va min price
                listProduct = service.getAllProductByNameAndPrice(pageable,nameSearch,BigDecimal.valueOf(Long.parseLong(minPrice)),maxPriceParse);
            } else if (minPrice.isBlank()){
                //search theo name va max price
                listProduct = service.getAllProductByNameAndPrice(pageable,nameSearch,minPriceParse,BigDecimal.valueOf(Long.parseLong(maxPrice)));
            } else {
                //search theo name va min price + max price
                listProduct = service.getAllProductByNameAndPrice(pageable,nameSearch,BigDecimal.valueOf(Long.parseLong(minPrice)),BigDecimal.valueOf(Long.parseLong(maxPrice)));
            }
        } else {
            if (minPrice.isBlank() & maxPrice.isBlank()){
                //search
                listProduct = service.getAllProductByPrice(pageable,minPriceParse,maxPriceParse);
            } else if (maxPrice.isBlank()){
                //search min price
                listProduct = service.getAllProductByPrice(pageable,BigDecimal.valueOf(Long.parseLong(minPrice)),maxPriceParse);
            } else if (minPrice.isBlank()){
                //search max price
                listProduct = service.getAllProductByPrice(pageable,minPriceParse,BigDecimal.valueOf(Long.parseLong(maxPrice)));
            } else {
                //search min price + max price
                listProduct = service.getAllProductByPrice(pageable,BigDecimal.valueOf(Long.parseLong(minPrice)),BigDecimal.valueOf(Long.parseLong(maxPrice)));
            }
        }

        model.addAttribute("listProduct",listProduct);
        model.addAttribute("message",message);
        return "/product/view-product";
    }
}
