package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.Cashback;
import com.examly.springapp.repositories.CashbackRepository;

@Service
public class CashbackService 
{

    @Autowired
    private CashbackRepository cashbackRepository;
    public CashbackService(CashbackRepository cashbackRepository)
    {
        this.cashbackRepository = cashbackRepository;
    }

    public List<Cashback> getAllCashbacks() 
    {
        return cashbackRepository.findAll();
    }
    
     public Cashback getCashbackById(Long id) 
    {
        return cashbackRepository.findById(id).orElse(null);
    }

    public Cashback saveCashback(Cashback cashback) 
    {
        return cashbackRepository.save(cashback);
    }

    public Cashback updateCashback(Cashback cashback)
    {
        Optional<Cashback> existingCashback = cashbackRepository.findById(cashback.getId());
        if(existingCashback.isPresent())
        {
            return cashbackRepository.save(cashback);
        }
        else
        {
            return null;
        }
    }

    public void deleteCashback(Long id) 
    {
        cashbackRepository.deleteById(id);
    }
}
