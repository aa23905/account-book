package com.example.accountbook.service;

import com.example.accountbook.dto.CategoryStatisticsDTO;
import com.example.accountbook.dto.TransactionDTO;
import com.example.accountbook.entity.Transaction;
import com.example.accountbook.mapper.TransactionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class TransactionService {

    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Transactional
    public Transaction add(TransactionDTO dto) {
        log.info("新增交易: categoryId={}, amount={}, type={}",
                dto.getCategoryId(), dto.getAmount(), dto.getType());
        Transaction transaction = new Transaction();
        transaction.setCategoryId(dto.getCategoryId());
        transaction.setAmount(dto.getAmount());
        transaction.setType(dto.getType());
        transaction.setDescription(dto.getDescription());
        transaction.setTransactionDate(dto.getTransactionDate());
        transactionMapper.insert(transaction);
        return transaction;
    }

    @Transactional
    public Transaction update(TransactionDTO dto) {
        log.info("更新交易: id={}", dto.getId());
        Transaction transaction = transactionMapper.findById(dto.getId());
        transaction.setCategoryId(dto.getCategoryId());
        transaction.setAmount(dto.getAmount());
        transaction.setType(dto.getType());
        transaction.setDescription(dto.getDescription());
        transaction.setTransactionDate(dto.getTransactionDate());
        transactionMapper.update(transaction);
        return transaction;
    }

    @Transactional
    public void delete(Long id) {
        log.warn("删除交易: id={}", id);
        transactionMapper.deleteById(id);
    }

    public Transaction findById(Long id) {
        log.info("查询交易: id={}", id);
        return transactionMapper.findById(id);
    }

    public List<Transaction> findAll() {
        log.info("查询所有交易记录");
        return transactionMapper.findAll();
    }

    public List<Transaction> findByCategoryId(Long categoryId) {
        log.info("按分类查询交易: categoryId={}", categoryId);
        return transactionMapper.findByCategoryId(categoryId);
    }

    public List<Transaction> findByType(String type) {
        log.info("按类型查询交易: type={}", type);
        return transactionMapper.findByType(type);
    }

    public List<Transaction> findByDateRange(LocalDateTime start, LocalDateTime end) {
        log.info("按日期范围查询交易: {} ~ {}", start, end);
        return transactionMapper.findByDateRange(start, end);
    }

    public Double getMonthlyExpense() {
        log.info("查询本月总支出");
        return transactionMapper.sumMonthlyExpense();
    }

    public PageInfo<Transaction> findPage(int pageNum, int pageSize) {
        log.info("分页查询交易: page={}, size={}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<Transaction> list = transactionMapper.findAll();
        PageInfo<Transaction> pageInfo = new PageInfo<>(list);
        return pageInfo;

    }

    public List<CategoryStatisticsDTO> getExpenseByCategory() {
        log.info("查询本月各分类支出统计");
        List<CategoryStatisticsDTO> list = transactionMapper.sumByCategory();
        return list;
    }


}