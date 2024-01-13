package com.cooba.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
@Component
public class DataSourceAspect {
    @Before("execution(* com.cooba.service.*.*(..))")
    public void setDataSource(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("方法:{}", signature.getName());

        WriteManipulation writeManipulation = signature.getMethod().getAnnotation(WriteManipulation.class);
        if (writeManipulation != null) {
            DataSourceContextHolder.set(DynamicDataSourceEnum.MASTER);
            log.info("切换到{}", DynamicDataSourceEnum.MASTER);
            return;
        }

        Transactional transactional = signature.getMethod().getAnnotation(Transactional.class);
        if (transactional != null && !transactional.readOnly()) {
            DataSourceContextHolder.set(DynamicDataSourceEnum.MASTER);
            log.info("切换到{}", DynamicDataSourceEnum.MASTER);
            return;
        }

        DataSourceContextHolder.set(DynamicDataSourceEnum.SLAVE);
        log.info("切换到{}", DynamicDataSourceEnum.SLAVE);
    }

    @After("execution(* com.cooba.service.*.*(..))")
    public void clearDataSource() {
        DataSourceContextHolder.clear();
    }
}