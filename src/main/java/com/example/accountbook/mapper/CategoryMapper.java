package com.example.accountbook.mapper;

import com.example.accountbook.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CategoryMapper {

    int insert(Category category);

    int update(Category category);

    int deleteById(Long id);

    Category findById(Long id);

    List<Category> findAll();

    List<Category> findByType(String type);
}