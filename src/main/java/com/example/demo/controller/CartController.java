package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BillService billService;

    @Autowired
    private BillProductService billProductService;

    @Autowired
    private HttpSession session;

    private List<CartViewModel> listCartViewModel;
    private CartProduct cartProduct;
    private Cart cart;
    private Bill bill;
    private BillProduct billProduct;
    private Product product;
    private Customer customer;
    private String message;

    @GetMapping("/view")
    public String viewCart(Model model){
        Customer customer = (Customer) session.getAttribute("customer");
        listCartViewModel = cartProductService.findCartProductByIdCart(customer.getId());
        BigDecimal totalMoney = cartProductService.totalMoney(listCartViewModel);
        model.addAttribute("listCartViewModel",listCartViewModel);
        model.addAttribute("totalMoney",totalMoney);
        return "/cart/view-cart";
    }

    @GetMapping("/delete")
    public String deleteCartProduct(Model model, @RequestParam(name = "idCartProduct") Integer idCartProduct){
        cartProduct = cartProductService.getCartProductById(idCartProduct);
        cartProductService.deleteCartPorduct(cartProduct);
        return "redirect:/cart/view";
    }

    @GetMapping("/increase")
    public String increaseCartProduct(Model model, @RequestParam(name = "idCartProduct") Integer idCartProduct){
        cartProduct = cartProductService.getCartProductById(idCartProduct);
        int quantity = 0;
        quantity = cartProduct.getQuantity() + 1;
        cartProduct.setQuantity(quantity);
        cartProductService.saveOrUpdateCartProduct(cartProduct);
        message = null;
        return "redirect:/cart/view";
    }

    @GetMapping("/reduce")
    public String reduceCartProduct(Model model, @RequestParam(name = "idCartProduct") Integer idCartProduct){
        cartProduct = cartProductService.getCartProductById(idCartProduct);
        int quantity = 0;
        if (cartProduct.getQuantity() > 1) {
            quantity = cartProduct.getQuantity() - 1;
            cartProduct.setQuantity(quantity);
            cartProductService.saveOrUpdateCartProduct(cartProduct);
        }
        message = null;
        return "redirect:/cart/view";
    }

    @GetMapping("/buy")
    public String buy(Model model){
        customer = (Customer) session.getAttribute("customer");
        listCartViewModel = cartProductService.findCartProductByIdCart(customer.getId());
        BigDecimal totalMoney = cartProductService.totalMoney(listCartViewModel);
        model.addAttribute("listCartViewModel",listCartViewModel);
        model.addAttribute("totalMoney",totalMoney);
        for (CartViewModel o: listCartViewModel){
            product = productService.getOne(o.getIdProduct());
            if (o.getQuantity() > product.getQuantity()){
                message = "So luong con cua san pham: " + product.getName() + " khong du!";
                model.addAttribute("message",message);
                return "/cart/view-cart";
            }
        }

        LocalDate currentDate = LocalDate.now();
        bill = new Bill(null, customer, Date.valueOf(currentDate), null, 2);

        for (CartViewModel o: listCartViewModel){
            product = productService.getOne(o.getIdProduct());

            billProduct = new BillProduct(null, product, bill, o.getQuantity(), product.getPrice());
            billService.saveOrUpdateBill(bill);
            billProductService.saveOrUpdate(billProduct);
            Integer quantityProduct = product.getQuantity() - o.getQuantity();
            product.setQuantity(quantityProduct);
            productService.addOrUpdateProduct(product);
        }
        message = "Thanh toan thanh cong";
        model.addAttribute("message",message);
        return "/cart/view-cart";
    }

    @GetMapping("/haha")
    public String haha(){
        return "";
    }

}
