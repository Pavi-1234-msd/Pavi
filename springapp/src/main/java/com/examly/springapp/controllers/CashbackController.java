package com.examly.springapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entities.Cashback;
import com.examly.springapp.service.CashbackService;

@RestController
@RequestMapping("/api/cashback")
public class CashbackController 
{
    private CashbackService cashbackService;

    public CashbackController(CashbackService cashbackService)
    {
        this.cashbackService=cashbackService;
    }
    @GetMapping
    public List<Cashback> getAllCashbacks()
    {
        return CashbackService.getAllCashbacks();
    }
    @GetMapping("/{id}")
    public Cashback getCashbackById(@PathVariable Long id)
    {
        return cashbackService.getCashbackById(id);
    }
    
}
