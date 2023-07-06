package com.syedu.mapper;

import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/7/513:47
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class UsersUserPermissionsMapperTest {
    @Autowired
    private UsersUserPermissionsMapper usersUserPermissionsMapper;

    @Test
    public void test(){
        List<Integer> adminTotal = this.usersUserPermissionsMapper.findAdminTotal();
        System.out.println(adminTotal);
    }
}
