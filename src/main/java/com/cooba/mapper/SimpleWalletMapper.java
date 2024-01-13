package com.cooba.mapper;

import com.cooba.entity.SimpleWalletEntity;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface SimpleWalletMapper {
    Optional<SimpleWalletEntity> select(long userId);

    void insertAmount(@Param("userId") long userId, @Param("amount") BigDecimal amount);

    void updateAmount(@Param("userId") long userId, @Param("amount") BigDecimal amount);
}
