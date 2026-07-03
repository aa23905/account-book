package com.example.accountbook.controller;

import com.example.accountbook.dto.ApiResponse;
import com.example.accountbook.dto.CategoryDto;
import com.example.accountbook.entity.Category;
import com.example.accountbook.service.CategoryService;
import com.example.accountbook.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final TransactionService transactionService;

    public CategoryController(CategoryService categoryService, TransactionService transactionService) {
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public ApiResponse<List<Category>> list(@RequestParam(required = false) String type) {
        List<Category> categories;
        if (type != null) {
            categories = categoryService.findByType(type);
        } else {
            categories = categoryService.findAll();
        }
        return ApiResponse.success(categories);
    }

    @GetMapping("/{id}")
    public ApiResponse<Category> getById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return ApiResponse.error(404, "Category not found：" + id);
        }
        return ApiResponse.success(category);
    }

    @PostMapping
    public ApiResponse<Category> add(@Valid @RequestBody CategoryDto dto) {
        Category created = categoryService.add(dto);
        return ApiResponse.success(created);
    }

    @PutMapping("/{id}")
    public ApiResponse<Category> update(@PathVariable Long id, @Valid @RequestBody CategoryDto dto) {
        dto.setId(id);
        Category updated = categoryService.update(dto);
        return ApiResponse.success(updated);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return ApiResponse.error(404, "分类不存在: " + id);
        }
        categoryService.delete(id);
        return ApiResponse.success(null);
    }



}