package com.syedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.syedu.domain.Users;
import com.syedu.mapper.UsersMapper;
import com.syedu.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * author:Administrator
 * createTime:2023/6/1613:48
 */
@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersMapper usersMapper;


    /**
     * 用户登入
     * @param user
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("authorizations")
    public Users authorizations(Users user, HttpServletResponse response) throws Exception {
        return this.usersService.checkUser(user);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("users")
    public Users users(@RequestBody Users user) throws Exception {
        return this.usersService.register(user);
    }

    /**
     * 用户姓名查重
     */
    @GetMapping("usernames/{username}/count/")
    public Long usernames(@PathVariable(value = "username") String username){
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername,username);
        return this.usersMapper.selectCount(wrapper);
    }
    /**
     * 用户电话号码查重
     */
    @GetMapping("mobiles/{mobile}/count/")
    public Long mobile(@PathVariable(value = "mobile") String mobile){
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getMobile,mobile);
        return this.usersMapper.selectCount(wrapper);
    }



    /**
     * 此方法已被替换，无法在使用，只做浏览
     *
     * @param users
     * @param response
     * @return
     * @throws Exception
     */
    public String generateToken(Users users, HttpServletResponse response) throws Exception {
        String token = this.usersService.token(users);
        if (token == null) {
            return "用户名或密码错误!!";
        }
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
        return token;
    }



}
