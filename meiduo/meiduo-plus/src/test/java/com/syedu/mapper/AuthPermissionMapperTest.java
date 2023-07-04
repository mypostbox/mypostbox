package com.syedu.mapper;

import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/7/419:31
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class AuthPermissionMapperTest {
    @Autowired
    private AuthPermissionMapper authPermissionMapper;

    @Test
    public void test(){

    }
}
