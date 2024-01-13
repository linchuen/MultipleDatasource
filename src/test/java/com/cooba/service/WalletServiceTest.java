package com.cooba.service;

import com.cooba.entity.SimpleWalletEntity;
import com.cooba.mapper.SimpleWalletMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional
@SpringBootTest
class WalletServiceTest {
    @Autowired
    WalletService walletService;

    @Test
    void selectData() {
        SimpleWalletEntity simpleWallet = walletService.selectData(1);
        System.out.println(simpleWallet);
        Assertions.assertNotNull(simpleWallet);
    }

    @Test
    void insertRead() {
        Assertions.assertThrows(TransientDataAccessResourceException.class,
                () -> walletService.insertRead(7, BigDecimal.valueOf(10)));
    }

    @Test
    void updateRead() {
        Assertions.assertThrows(TransientDataAccessResourceException.class,
                () -> walletService.updateRead(2, BigDecimal.valueOf(20)));
    }

    @Test
    void insert() {
        walletService.insert(8, BigDecimal.valueOf(10));

        SimpleWalletEntity simpleWallet = walletService.selectDataWithWrite(8);
        System.out.println(simpleWallet);
        Assertions.assertNotNull(simpleWallet);
    }

    @Test
    void update() {
        walletService.update(3, BigDecimal.valueOf(10));

        SimpleWalletEntity simpleWallet = walletService.selectDataWithWrite(3);
        System.out.println(simpleWallet);
        Assertions.assertNotNull(simpleWallet);
    }

    @Test
    void insertTransactional() {
        walletService.insertTransactional(9, BigDecimal.valueOf(10));

        SimpleWalletEntity simpleWallet = walletService.selectDataWithWrite(9);
        System.out.println(simpleWallet);
        Assertions.assertNotNull(simpleWallet);
    }

    @Test
    void updateTransactional() {
        walletService.updateTransactional(4, BigDecimal.valueOf(10));

        SimpleWalletEntity simpleWallet = walletService.selectDataWithWrite(4);
        System.out.println(simpleWallet);
        Assertions.assertNotNull(simpleWallet);
    }
}