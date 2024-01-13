package com.cooba.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WalletLogEntity {
    private long id;
    private long userId;
    private BigDecimal beforeAmount;
    private BigDecimal amount;
    private LocalDateTime createdTime;
}
