package com.examly.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.entities.Retailer;

public interface RetailerRepository extends JpaRepository<Retailer,Long> {
    
}
