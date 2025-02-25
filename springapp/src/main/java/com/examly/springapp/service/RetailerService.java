package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.Retailer;
import com.examly.springapp.repositories.RetailerRepository;

@Service
public class RetailerService 
{
    @Autowired
    private RetailerRepository retailerRepository;

    public RetailerService(RetailerRepository retailerRepository)
    {
        this.retailerRepository = retailerRepository;
    }

    public List<Retailer> getAllRetailers() 
    {
        return retailerRepository.findAll();
    }
    public Retailer getRetailerById(Long id) 
    {
        return retailerRepository.findById(id).orElse(null);
    }
    public Retailer saveRetailer(Retailer retailer) 
    {
        return retailerRepository.save(retailer);
    }

    public Retailer updateRetailer(Retailer retailer)
    {
        Optional<Retailer> existingRetailer = retailerRepository.findById(retailer.getId());
        if(existingRetailer.isPresent())
        {
            return retailerRepository.save(retailer);
        }
        else
        {
            return null;
        }
    }
    public void deleteRetailer(Long id) 
    {
        retailerRepository.deleteById(id);
    }
}
