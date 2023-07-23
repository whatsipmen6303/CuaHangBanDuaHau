package com.example.demo.service;

import com.example.demo.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillService {

    Page<Bill> findAll(Pageable pageable);

    Bill findBillById(Integer id);

    Bill saveOrUpdateBill(Bill bill);
}
