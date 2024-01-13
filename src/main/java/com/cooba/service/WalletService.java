package com.cooba.service;

import com.cooba.datasource.WriteManipulation;
import com.cooba.entity.SimpleWalletEntity;
import com.cooba.mapper.SimpleWalletMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final SimpleWalletMapper simpleWalletMapper;

    @WriteManipulation
    public SimpleWalletEntity selectDataWithWrite(long userId) {
        return simpleWalletMapper.select(userId).orElseThrow();
    }

    public SimpleWalletEntity selectData(long userId) {
        return simpleWalletMapper.select(userId).orElseThrow();
    }

    public void insertRead(long userId, BigDecimal amount) {
        simpleWalletMapper.insertAmount(userId, amount);
    }

    public void updateRead(long userId, BigDecimal amount) {
        simpleWalletMapper.updateAmount(userId, amount);
    }

    @WriteManipulation
    public void insert(long userId, BigDecimal amount) {
        simpleWalletMapper.insertAmount(userId, amount);
    }

    @WriteManipulation
    public void update(long userId, BigDecimal amount) {
        simpleWalletMapper.updateAmount(userId, amount);
    }

    @Transactional
    public void insertTransactional(long userId, BigDecimal amount) {
        simpleWalletMapper.insertAmount(userId, amount);
    }

    @Transactional
    public void updateTransactional(long userId, BigDecimal amount) {
        simpleWalletMapper.updateAmount(userId, amount);
    }
}
