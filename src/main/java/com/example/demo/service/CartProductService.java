package com.example.demo.service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartProduct;
import com.example.demo.entity.CartViewModel;
import com.example.demo.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface CartProductService {
    List<CartViewModel> findCartProductByIdCart(Integer idCustomer);

    BigDecimal totalMoney(List<CartViewModel> listCartViewModel);

    CartProduct getCartProductByProductAndCart(Product product, Cart cart);

    CartProduct getCartProductById(Integer id);

    CartProduct saveOrUpdateCartProduct(CartProduct cartProduct);

    void deleteCartPorduct(CartProduct cartProduct);
}
