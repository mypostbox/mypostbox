package com.syedu.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.syedu.domain.Users;
import com.syedu.mapper.UsersMapper;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/1613:55
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class UserServiceTest {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersMapper usersMapper;


    @Test
    public void testToken() throws Exception {
        Users users = new Users();
        Users tom = users.setId(1).setEmail("98999@qq.com").setUsername("tom").setPassword("666888");
        String token = this.usersService.token(tom);
        System.out.println(token);
    }


        @Test
        public void testRegister() throws Exception {
        Users users = new Users();
        users.setUsername("cheng").setPassword("11111111").setMobile("17770884439");
        Users register = this.usersService.register(users);
        System.out.println(register);
    }

    @Test
    public void testFindAllOrderUser() throws Exception {
        Map<String, Object> sss = this.usersService.findAllOrderUser("sss");
        System.out.println(sss);
    }

    @Test
    public void test1() throws Exception {
        List<Map<String, Object>> jsdfo = this.usersService.findAllUserByMonthIncrement("jsdfo");
        System.out.println(jsdfo);
    }

    @Test
    public void test2(){
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Users::getUsername,"%"+"c"+"%");
        List<Users> users = this.usersMapper.selectList(wrapper);
        users.stream().forEach(System.out::println);
    }
}
