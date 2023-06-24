package com.syedu.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * author:Administrator
 * createTime:2023/6/89:42
 */
public class HikariDataSourceFactory implements DataSourceFactory {
    private Properties pro;

    @Override
    public void setProperties(Properties props) {
        this.pro = props;
    }

    @Override
    public DataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig(this.pro);
        return new HikariDataSource(hikariConfig);
    }
}
