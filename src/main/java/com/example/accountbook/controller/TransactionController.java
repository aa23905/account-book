package com.example.accountbook.controller;

import com.example.accountbook.dto.ApiResponse;
import com.example.accountbook.dto.CategoryStatisticsDto;
import com.example.accountbook.dto.TransactionDto;
import com.example.accountbook.entity.Transaction;
import com.example.accountbook.service.TransactionService;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ApiResponse<List<Transaction>> list(@RequestParam(required = false) Long categoryId,
                                               @RequestParam(required = false) String type,
                                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                               LocalDateTime start,
                                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                               LocalDateTime end) {
        List<Transaction> transactions;
        if (categoryId != null) {
            transactions = transactionService.findByCategoryId(categoryId);
        } else if (type != null) {
            transactions = transactionService.findByType(type);
        } else if (start != null && end != null) {
            transactions = transactionService.findByDateRange(start, end);
        } else {
            transactions = transactionService.findAll();
        }
        return ApiResponse.success(transactions);
    }

    @GetMapping("/{id}")
    public ApiResponse<Transaction> getById(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            return ApiResponse.error(404, "Transaction not found：" + id);
        }
        return ApiResponse.success(transaction);
    }

    @PostMapping
    public ApiResponse<Transaction> add(@Valid @RequestBody TransactionDto dto) {
        Transaction created = transactionService.add(dto);
        return ApiResponse.success(created);
    }

    @PutMapping("/{id}")
    public ApiResponse<Transaction> update(@PathVariable Long id, @Valid @RequestBody TransactionDto dto) {
        dto.setId(id);
        Transaction transaction = transactionService.update(dto);
        return ApiResponse.success(transaction);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            return ApiResponse.error(404, "Transaction not found：" + id);
        }
        transactionService.delete(id);
        return ApiResponse.success(null);
    }

    @GetMapping("/summary/monthly-expense")
    public ApiResponse<Double> monthlyExpense() {
        Double expense = transactionService.getMonthlyExpense();
        return ApiResponse.success(expense);
    }


    @GetMapping("/summary/by-category")
    public ApiResponse<List<CategoryStatisticsDto>> expenseByCategory() {
        List<CategoryStatisticsDto> Stats = transactionService.getExpenseByCategory();
        return ApiResponse.success(Stats);

    }
    @GetMapping("/page")
    public ApiResponse<PageInfo<Transaction>> page(
            @RequestParam(defaultValue = "1")int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false)Long categoryId) {
        PageInfo<Transaction> pageInfo = transactionService.findPageByCategory(page,size,categoryId);
        return ApiResponse.success(pageInfo);

    }

}