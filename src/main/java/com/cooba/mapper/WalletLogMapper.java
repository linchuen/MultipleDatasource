package com.cooba.mapper;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface WalletLogMapper {
    void select(@Param("userId") long userId);

    void insertLog(@Param("userId") long userId, @Param("beforeAmount") BigDecimal beforeAmount, @Param("amount") BigDecimal amount);
}
