package com.example.accountbook.service;

import com.example.accountbook.entity.Transaction;
import com.example.accountbook.mapper.TransactionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Transactional
    public Transaction add(Transaction transaction) {
        transactionMapper.insert(transaction);
        return transaction;
    }

    @Transactional
    public Transaction update(Transaction transaction) {
        transactionMapper.update(transaction);
        return transaction;
    }

    @Transactional
    public void delete(Long id) {
        transactionMapper.deleteById(id);
    }

    public Transaction findById(Long id) {
        return transactionMapper.findById(id);
    }

    public List<Transaction> findAll() {
        return transactionMapper.findAll();
    }

    public List<Transaction> findByCategoryId(Long categoryId) {
        return transactionMapper.findByCategoryId(categoryId);
    }

    public List<Transaction> findByType(String type) {
        return transactionMapper.findByType(type);
    }

    public List<Transaction> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return transactionMapper.findByDateRange(start, end);
    }
}