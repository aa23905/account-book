package com.example.accountbook.service;

import com.example.accountbook.dto.CategoryDto;
import com.example.accountbook.entity.Category;
import com.example.accountbook.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public Category add(CategoryDto dto) {
        log.info("新增分类: name={}, type={}", dto.getName(), dto.getType());
        Category category = new Category();
        category.setName(dto.getName());
        category.setType(dto.getType());
        category.setDescription(dto.getDescription());

        categoryMapper.insert(category);

        return category;
    }

    public Category update(CategoryDto dto) {
        log.info("更新分类: id={}, name={}", dto.getId(), dto.getName());
        Category category = categoryMapper.findById(dto.getId());
        category.setName(dto.getName());
        category.setType(dto.getType());
        category.setDescription(dto.getDescription());
        categoryMapper.update(category);
        return category;
    }

    public void delete(Long id) {
        log.warn("删除分类: id={}", id);
        categoryMapper.deleteById(id);
    }

    public Category findById(Long id) {
        log.info("查询分类: id={}", id);
        return categoryMapper.findById(id);
    }

    public List<Category> findAll() {
        log.info("查询所有分类");
        return categoryMapper.findAll();
    }

    public List<Category> findByType(String type) {
        log.info("按类型查询分类: type={}", type);
        return categoryMapper.findByType(type);
    }
}