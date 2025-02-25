package com.examly.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examly.springapp.entities.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction,Long>{
    
}
