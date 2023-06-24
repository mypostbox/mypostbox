package com.syedu.utils.config;


import com.syedu.utils.keyword.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import redis.clients.jedis.JedisPoolConfig;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * author:Administrator
 * createTime:2023/6/1515:22
 */
@Import({MybatisConfig.class, TransactionConfig.class,ComponentConfig.class,AspectConfig.class})
@Configuration
@ComponentScan(basePackages = {"com.syedu.service"})
public class SpringConfiguration {
    @Bean
    public PublicKey publicKey() throws Exception {
        return RsaUtils.getPublicKey1("pub_key");
    }
    @Bean
    public PrivateKey privateKey() throws Exception {
        return RsaUtils.getPrivateKey1("pri_key");
    }



}
