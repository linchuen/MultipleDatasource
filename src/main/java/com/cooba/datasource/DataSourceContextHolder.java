package com.cooba.datasource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceContextHolder {
    private static final ThreadLocal<DynamicDataSourceEnum> context = new ThreadLocal<>();

    public static void set(DynamicDataSourceEnum dataSource) {
        log.info("設定至{}", dataSource.toString());
        context.set(dataSource);
    }

    public static DynamicDataSourceEnum get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}
