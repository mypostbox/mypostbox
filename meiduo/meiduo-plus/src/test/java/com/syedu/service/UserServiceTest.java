package com.syedu.service;

import com.syedu.domain.Users;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * author:Administrator
 * createTime:2023/6/1613:55
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class UserServiceTest {
    @Autowired
    private UsersService usersService;


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
}
