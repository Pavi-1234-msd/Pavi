package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.Transaction;
import com.examly.springapp.repositories.TransactionRepository;

@Service
public class TransactionService 
{

@Autowired
private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository)
    {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() 
    {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) 
    {
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction saveTransaction(Transaction transaction) 
    {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Transaction transaction)
    {
        Optional<Transaction> existingTransaction = transactionRepository.findById(transaction.getId());
        if(existingTransaction.isPresent())
        {
            return transactionRepository.save(transaction);
        }
        else
        {
            return null;
        }
    }

    public void deleteTransaction(Long id) 
    {
        transactionRepository.deleteById(id);
    }
}
