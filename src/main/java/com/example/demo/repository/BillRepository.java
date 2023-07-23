package com.example.demo.repository;

import com.example.demo.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    Page<Bill> findAll(Pageable pageable);

    Bill findBillById(Integer id);
}
