package com.cooba.service;

import com.cooba.entity.SimpleWalletEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final WalletService walletService;
    private final LogService logService;

    @Transactional(rollbackFor = Exception.class)
    public void increaseWalletAmount(long userId, BigDecimal amount) {
        SimpleWalletEntity simpleWallet;
        try {
            simpleWallet = walletService.selectData(userId);
        } catch (Exception e) {
            walletService.insert(userId, amount);
            logService.insertLog(userId, BigDecimal.ZERO, amount);
            return;
        }
        BigDecimal walletAmount = simpleWallet.getAmount();
        BigDecimal afterAmount = walletAmount.add(amount);
        walletService.update(userId, afterAmount);
        logService.insertLog(userId, walletAmount, afterAmount);
    }

    @Transactional(rollbackFor = Exception.class)
    public void decreaseWalletAmount(long userId, BigDecimal amount) throws Exception {
        SimpleWalletEntity simpleWallet = walletService.selectData(userId);
        BigDecimal walletAmount = simpleWallet.getAmount();
        BigDecimal afterAmount = walletAmount.subtract(amount);
        if (afterAmount.compareTo(BigDecimal.ZERO) < 0) throw new Exception("less than zero");

        walletService.update(userId, afterAmount);
        logService.insertLog(userId, walletAmount, afterAmount);
    }

}
