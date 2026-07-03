package com.example.accountbook.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryDto {
    private Long id;
    @NotBlank(message = "分类名称不能为空")
    private String name;
    @NotBlank(message = "分类类型不能为空")
    private String type;
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
