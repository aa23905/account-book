package com.example.accountbook.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transaction {
    private Long id;
    private Long categoryId;
    private String categoryName;
    private BigDecimal amount;
    private String type;          // INCOME / EXPENSE
    private String description;
    private LocalDateTime transactionDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
