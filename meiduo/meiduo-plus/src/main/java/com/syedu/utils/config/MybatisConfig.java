package com.syedu.utils.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


import javax.sql.DataSource;
import java.util.Objects;

/**
 * author:Administrator
 * createTime:2023/6/1515:27
 */
@MapperScan(basePackages = {"com.syedu.mapper"})
@PropertySource("classpath:data.properties")
public class MybatisConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource hikariDataSource(){
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
        hikariDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        hikariDataSource.setUsername(environment.getProperty("jdbc.username"));
        hikariDataSource.setPassword(environment.getProperty("jdbc.password"));
        return hikariDataSource;
    }

    @Bean
    public Jedis jedisPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(Objects.requireNonNull(environment.getProperty("jedis.maxTotal"))));
        config.setMaxIdle(Integer.parseInt(Objects.requireNonNull(environment.getProperty("jedis.maxIdle"))));
        JedisPool jedisPool = new JedisPool(config, environment.getProperty("jedis.host"), Integer.parseInt(Objects.requireNonNull(environment.getProperty("jedis.port"))));
        return jedisPool.getResource();
    }

    @Bean
    public MybatisPlusInterceptor  mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
        return mybatisPlusInterceptor;
    }

    @Bean
    public SqlSessionFactory mybatisSqlSessionFactoryBean(DataSource dataSource,MybatisPlusInterceptor mybatisPlusInterceptor) throws Exception {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setPlugins(mybatisPlusInterceptor);
        return mybatisSqlSessionFactoryBean.getObject();
    }

}
