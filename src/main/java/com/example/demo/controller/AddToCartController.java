package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartProduct;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.service.CartProductService;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/add-to-cart")
public class AddToCartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private CartService cartService;

    @Autowired
    private HttpSession session;

    private Product product;
    private CartProduct cartProduct;
    private Cart cart;
    private String message;

    @GetMapping("/detail")
    public String detailProduct(Model model, @RequestParam(name = "id") Integer id){
        product = productService.getOne(id);
        model.addAttribute("product",product);
        model.addAttribute("message",message);
        return "/buy-product/watch-product";
    }

    @PostMapping("/add")
    public String addToCartProduct(Model model, @RequestParam(name = "id") Integer id, @RequestParam(name = "quantityBuy") Integer quantityBuy){
        int quantity = 0;
        product = productService.getOne(id);
        cart = cartService.findCartByCustomer((Customer) session.getAttribute("customer"));
        cartProduct = cartProductService.getCartProductByProductAndCart(product,cart);
        if (cartProduct != null){
            quantity = cartProduct.getQuantity() + quantityBuy;
            cartProduct.setQuantity(quantity);
            cartProductService.saveOrUpdateCartProduct(cartProduct);
        } else {
            cartProduct = new CartProduct(null,product,cart,quantityBuy,product.getPrice(),null,1);
            cartProductService.saveOrUpdateCartProduct(cartProduct);
        }
        return "redirect:/cart/view";
    }

}
