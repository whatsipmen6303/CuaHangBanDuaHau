package com.example.demo.service;

import com.example.demo.entity.Bill;
import com.example.demo.entity.BillProduct;
import com.example.demo.repository.BillProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IBillProductService implements BillProductService{

    @Autowired
    private BillProductRepository repository;

    @Override
    public List<BillProduct> findAll(Bill bill) {
        return repository.findAllByBill(bill);
    }

    @Override
    public BigDecimal totalMoney(List<BillProduct> listBillProduct) {
        BigDecimal sum = new BigDecimal("0");
        for (BillProduct o: listBillProduct){
            sum = sum.add(o.getPrice().multiply(new BigDecimal(o.getQuantity())));
        }
        return sum;
    }

    @Override
    public BillProduct saveOrUpdate(BillProduct billProduct) {
        return repository.save(billProduct);
    }


}
