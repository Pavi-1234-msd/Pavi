package com.examly.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entities.Cashback;
@Repository
public interface CashbackRepository extends JpaRepository<Cashback,Long>{
    
}
