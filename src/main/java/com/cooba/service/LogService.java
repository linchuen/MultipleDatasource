package com.cooba.service;

import com.cooba.datasource.WriteManipulation;
import com.cooba.mapper.WalletLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LogService {
    private final WalletLogMapper walletLogMapper;

    public void insertLog(long userId, BigDecimal beforeAmount, BigDecimal amount) {
        walletLogMapper.insertLog(userId, beforeAmount, amount);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertLogRequireNew(long userId, BigDecimal beforeAmount, BigDecimal amount) {
        walletLogMapper.insertLog(userId, beforeAmount, amount);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insertLogNested(long userId, BigDecimal beforeAmount, BigDecimal amount) {
        walletLogMapper.insertLog(userId, beforeAmount, amount);
        int i = 1 / 0;
    }
}
