package com.example.accountbook.dto;

import java.math.BigDecimal;

public class CategoryStatisticsDTO {

    private String categoryName;
    private BigDecimal total;

    public CategoryStatisticsDTO(){}
    public CategoryStatisticsDTO(String categoryName, BigDecimal total) {
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
