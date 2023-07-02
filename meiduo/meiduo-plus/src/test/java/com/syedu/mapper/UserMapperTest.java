package com.syedu.mapper;

import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/3011:04
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class UserMapperTest {
    @Autowired
    private UsersMapper usersMapper;
    @Test
    public void testFindAllUserCount(){
        Map<String, Object> allUserCount = this.usersMapper.findAllUserCount();
        System.out.println(allUserCount);
    }

    @Test
    public void test(){


    }

    @Test
    public void testFindAllCurrentLoginUser(){
        Map<String, Object> allCurrentLoginUser = this.usersMapper.findAllCurrentLoginUser();
        System.out.println(allCurrentLoginUser);
    }

    @Test
    public void testFindAllOrderUser(){
        Map<String, Object> allUserCount = this.usersMapper.findAllUserCount();
        System.out.println(allUserCount);
    }

    @Test
    public void test2(){
        Map<String, Object> allCurrentCreateUser = this.usersMapper.findAllCurrentCreateUser();
        System.out.println(allCurrentCreateUser);
    }

    @Test
    public void test3(){
        List<Map<String, Object>> allMonthCreateUser = this.usersMapper.findAllMonthCreateUser();
        System.out.println(allMonthCreateUser);
    }
}
