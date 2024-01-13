package com.cooba.config;

import com.cooba.datasource.DynamicDataSource;
import com.cooba.datasource.DynamicDataSourceEnum;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan("com.cooba.mapper")
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public HikariConfig masterConfig() {
        return new HikariConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public HikariConfig slaveConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource masterDataSource(HikariConfig masterConfig) {
        return  new HikariDataSource(masterConfig);
    }

    @Bean
    public DataSource slaveDataSource(HikariConfig slaveConfig) {
        return new HikariDataSource(slaveConfig);
    }

    @Bean
    public DynamicDataSource dynamicDataSource(DataSource masterDataSource,
                                       DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceEnum.MASTER, masterDataSource);
        targetDataSources.put(DynamicDataSourceEnum.SLAVE, slaveDataSource);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sessionFactory(DataSource dynamicDataSource) throws Exception {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);
        configuration.setCacheEnabled(false);

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/*Mapper.xml"));
        bean.setDataSource(dynamicDataSource);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public DataSourceTransactionManager dataSourceTx(DataSource dynamicDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dynamicDataSource);
        return dataSourceTransactionManager;
    }
}