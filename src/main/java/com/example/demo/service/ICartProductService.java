package com.example.demo.service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartProduct;
import com.example.demo.entity.CartViewModel;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ICartProductService implements CartProductService{

    @Autowired
    private CartProductRepository repository;

    @Override
    public List<CartViewModel> findCartProductByIdCart(Integer idCustomer) {
        return repository.findCartViewModel(idCustomer);
    }

    @Override
    public BigDecimal totalMoney(List<CartViewModel> listCartViewModel){
        BigDecimal sum = new BigDecimal("0");
        for (CartViewModel o: listCartViewModel){
            sum = sum.add(o.getPrice().multiply(new BigDecimal(o.getQuantity())));
        }
        return sum;
    }

    @Override
    public CartProduct getCartProductByProductAndCart(Product product, Cart cart) {
        return repository.findCartProductByProductAndCart(product,cart);
    }

    @Override
    public CartProduct getCartProductById(Integer id) {
        return repository.findCartProductById(id);
    }

    @Override
    public CartProduct saveOrUpdateCartProduct(CartProduct cartProduct) {
        return repository.save(cartProduct);
    }

    @Override
    public void deleteCartPorduct(CartProduct cartProduct) {
        repository.delete(cartProduct);
    }


}
