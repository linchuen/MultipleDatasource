package com.cooba.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SimpleWalletEntity {
    private long userId;
    private BigDecimal amount;
    private LocalDateTime updatedTime;
}
