package com.syedu.utils.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.ResourceTransactionManager;

import javax.sql.DataSource;

/**
 * author:Administrator
 * createTime:2023/6/1613:30
 */
@EnableTransactionManagement
public class TransactionConfig {

    @Bean
    public ResourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
