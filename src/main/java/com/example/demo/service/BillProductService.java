package com.example.demo.service;

import com.example.demo.entity.Bill;
import com.example.demo.entity.BillProduct;

import java.math.BigDecimal;
import java.util.List;

public interface BillProductService {

    List<BillProduct> findAll(Bill bill);

    BigDecimal totalMoney(List<BillProduct> listBillProduct);

    BillProduct saveOrUpdate(BillProduct billProduct);
}
