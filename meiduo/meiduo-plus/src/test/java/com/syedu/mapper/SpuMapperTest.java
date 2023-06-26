package com.syedu.mapper;


import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * author:Administrator
 * createTime:2023/6/2420:53
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class SpuMapperTest {

    @Autowired
    private SpuMapper spuMapper;

    @Test
    public void test(){

    }
}
