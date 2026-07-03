package com.example.accountbook.dto;

import java.math.BigDecimal;

public class CategoryStatisticsDto {

    private String categoryName;
    private BigDecimal total;

    public CategoryStatisticsDto(){}
    public CategoryStatisticsDto(String categoryName, BigDecimal total) {
        this.categoryName = categoryName;
        this.total = total;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
