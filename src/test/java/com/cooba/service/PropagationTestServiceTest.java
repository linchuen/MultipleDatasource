package com.cooba.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class PropagationTestServiceTest {
    @Autowired
    PropagationTestService propagationService;

    @Test
    void shouldRollback() {
        propagationService.shouldRollback(2, BigDecimal.valueOf(100));
    }

    @Test
    void notRollbackForLog() {
        propagationService.notRollbackForLog(3, BigDecimal.valueOf(100));
    }

    @Test
    void outerNotChange() {
        propagationService.outerNotChange(3, BigDecimal.valueOf(200));
    }
}