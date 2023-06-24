package com.syedu.utils.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * author:Administrator
 * createTime:2023/6/1515:24
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.syedu.controller"})
public class SpringmvcConfiguration {

    @Bean
    public ViewResolver internalResourceViewResolver(){
       return new InternalResourceViewResolver("/WEB-INF/pages/",".jsp");
    }
}

