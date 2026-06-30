package com.example.accountbook.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Category {
    private Long id;
    private String name;
    private String type;          // INCOME / EXPENSE
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}