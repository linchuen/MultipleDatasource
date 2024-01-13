package com.cooba.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropagationTestService {
    private final WalletService walletService;
    private final LogService logService;

    @Transactional(rollbackFor = Exception.class)
    public void shouldRollback(long userId, BigDecimal amount) {
        try {
            walletService.update(userId, amount);
            logService.insertLog(userId, BigDecimal.ZERO, amount);

            int i = 1 / 0;
        } catch (Exception e) {
            log.error("要回滾但被catch 吃掉了" + e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void notRollbackForLog(long userId, BigDecimal amount) {
        walletService.update(userId, amount);
        logService.insertLogRequireNew(userId,  BigDecimal.ZERO, amount);
        int i = 1 / 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public void outerNotChange(long userId, BigDecimal amount) {
        walletService.update(userId, amount);
        try {
            logService.insertLogNested(userId,  BigDecimal.ZERO, amount);
        }catch (Exception ignored) {}

    }
}
