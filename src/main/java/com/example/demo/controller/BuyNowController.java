package com.example.demo.controller;

import com.example.demo.entity.Bill;
import com.example.demo.entity.BillProduct;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.service.BillProductService;
import com.example.demo.service.BillService;
import com.example.demo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequestMapping("/buy-now")
public class BuyNowController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BillService billService;

    @Autowired
    private BillProductService billProductService;

    @Autowired
    private HttpSession session;

    private String regexNumber = "^[1-9]\\d*$";
    private Product product;
    private Bill bill;
    private BillProduct billProduct;
    private String message;
    private Customer customer;

    @GetMapping("/view")
    public String viewBuyNow(Model model, @RequestParam(name = "id") Integer id){
        product = productService.getOne(id);
        model.addAttribute("product",product);
        return "/buy-now/view-buy-now";
    }

    @PostMapping("/buy")
    public String buyNow(Model model, @RequestParam(name = "id") Integer id, @RequestParam(name = "quantityBuy") String quantityBuy){
        product = productService.getOne(id);
        model.addAttribute("product",product);
        if (!quantityBuy.matches(regexNumber)){
            message = "So luong mua phai la 1 so > 0!";
            model.addAttribute("message",message);
            return "/buy-now/view-buy-now";
        } else {
            Integer quantity = Integer.parseInt(quantityBuy);
            if (quantity > product.getQuantity()){
                message = "So luong con khong du!";
                model.addAttribute("message",message);
                return "/buy-now/view-buy-now";
            } else {
                customer = (Customer) session.getAttribute("customer");
                LocalDate currentDate = LocalDate.now();
                bill = new Bill(null,customer, Date.valueOf(currentDate),null,2);
                billProduct = new BillProduct(null,product,bill,quantity,product.getPrice());
                billService.saveOrUpdateBill(bill);
                billProductService.saveOrUpdate(billProduct);

                Integer quantityProduct = product.getQuantity() - quantity;
                product.setQuantity(quantityProduct);
                productService.addOrUpdateProduct(product);

                message = "Mua thanh cong!";
                model.addAttribute("message",message);
            }
        }
        return "/buy-now/view-buy-now";
    }
}
