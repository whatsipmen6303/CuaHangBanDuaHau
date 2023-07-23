package com.example.demo.repository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartProduct;
import com.example.demo.entity.CartViewModel;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {

    @Query("""
    select new com.example.demo.entity.CartViewModel
    (c.Id,c.product.Id,c.product.name,c.product.image,c.quantity,c.price)
    from CartProduct c where c.cart.customer.Id = :idCustomer
    """)
    List<CartViewModel> findCartViewModel(@Param("idCustomer") Integer idCustomer);


    CartProduct findCartProductByProductAndCart(Product product, Cart cart);

    CartProduct findCartProductById(Integer id);
}
