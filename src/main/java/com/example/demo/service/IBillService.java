package com.example.demo.service;

import com.example.demo.entity.Bill;
import com.example.demo.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IBillService implements BillService{

    @Autowired
    private BillRepository repository;

    @Override
    public Page<Bill> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Bill findBillById(Integer id) {
        return repository.findBillById(id);
    }

    @Override
    public Bill saveOrUpdateBill(Bill bill) {
        return repository.save(bill);
    }
}
