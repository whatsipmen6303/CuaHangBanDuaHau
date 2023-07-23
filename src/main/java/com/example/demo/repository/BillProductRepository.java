package com.example.demo.repository;

import com.example.demo.entity.Bill;
import com.example.demo.entity.BillProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillProductRepository extends JpaRepository<BillProduct, Integer> {

    List<BillProduct> findAllByBill(Bill bill);
}
