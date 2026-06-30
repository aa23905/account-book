package com.example.accountbook.controller;

import com.example.accountbook.entity.Transaction;
import com.example.accountbook.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> list(@RequestParam(required = false) Long categoryId,
                                  @RequestParam(required = false) String type,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                  LocalDateTime start,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                  LocalDateTime end) {
        if (categoryId != null) {
            return transactionService.findByCategoryId(categoryId);
        }
        if (type != null) {
            return transactionService.findByType(type);
        }
        if (start != null && end != null) {
            return transactionService.findByDateRange(start, end);
        }
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public Transaction add(@RequestBody Transaction transaction) {
        return transactionService.add(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@PathVariable Long id, @RequestBody Transaction transaction) {
        transaction.setId(id);
        transactionService.update(transaction);
        return ResponseEntity.ok(transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}