package com.syedu.service;

import com.syedu.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Administrator
 * @description 针对表【tb_users】的数据库操作Service
 * @createDate 2023-06-13 08:45:37
 */
public interface UsersService extends IService<Users> {

    String token(Users user) throws Exception;

    Users checkUser(Users user) throws Exception;

    Users register(Users user) throws Exception;

    Users findAllUsers(String token) throws Exception;
}
