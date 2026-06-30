package com.example.accountbook.mapper;

import com.example.accountbook.entity.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TransactionMapper {

    int insert(Transaction transaction);

    int update(Transaction transaction);

    int deleteById(Long id);

    Transaction findById(Long id);

    List<Transaction> findAll();

    List<Transaction> findByCategoryId(Long categoryId);

    List<Transaction> findByType(@Param("type") String type);

    List<Transaction> findByDateRange(@Param("start") LocalDateTime start,
                                      @Param("end") LocalDateTime end);
}