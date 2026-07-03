package com.example.accountbook.service;

import com.example.accountbook.dto.CategoryDto;
import com.example.accountbook.entity.Category;
import com.example.accountbook.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public Category add(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setType(dto.getType());
        category.setDescription(dto.getDescription());

        categoryMapper.insert(category);

        return category;
    }

    public Category update(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setType(dto.getType());
        categoryMapper.update(category);
        return category;
    }

    public void delete(Long id) {
        categoryMapper.deleteById(id);
    }

    public Category findById(Long id) {
        return categoryMapper.findById(id);
    }

    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    public List<Category> findByType(String type) {
        return categoryMapper.findByType(type);
    }
}