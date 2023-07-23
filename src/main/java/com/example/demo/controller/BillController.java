package com.example.demo.controller;

import com.example.demo.entity.Bill;
import com.example.demo.entity.BillProduct;
import com.example.demo.service.BillProductService;
import com.example.demo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillProductService billProductService;

    private Page<Bill> listBill;
    private Bill bill;
    private List<BillProduct> listBillProduct;
    private BigDecimal totalMoney;

    @GetMapping("/view")
    public String viewBill(Model model, @RequestParam(defaultValue = "0",name = "num")Integer num){
        Pageable pageable = PageRequest.of(num,5);
        listBill = billService.findAll(pageable);
        model.addAttribute("listBill",listBill);
        return "/bill/view-bill";
    }

    @GetMapping("/detail")
    public String detailBill(Model model, @RequestParam(name  = "id") Integer id){
        bill = billService.findBillById(id);
        listBillProduct = billProductService.findAll(bill);
        totalMoney = billProductService.totalMoney(listBillProduct);
        model.addAttribute("bill",bill);
        model.addAttribute("listBillProduct",listBillProduct);
        model.addAttribute("totalMoney",totalMoney);
        return "/bill/detail-bill";
    }

    @GetMapping("/update-status")
    public String updateStatusBill(Model model, @RequestParam(name  = "id") Integer id){
        bill = billService.findBillById(id);
        LocalDate currentDate = LocalDate.now();
        Date date = Date.valueOf(currentDate);
        bill.setStatus(1);
        bill.setDateOfPayment(date);
        billService.saveOrUpdateBill(bill);
        return "redirect:/bill/view";
    }
}
