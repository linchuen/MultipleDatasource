package com.cooba.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void increaseWalletAmount() {
        userService.increaseWalletAmount(100, BigDecimal.valueOf(100));
    }

    @Test
    void decreaseWalletAmount() throws Exception {
        userService.decreaseWalletAmount(100, BigDecimal.valueOf(100));
    }


}