package com.syedu.service;

import com.syedu.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【tb_users】的数据库操作Service
 * @createDate 2023-06-13 08:45:37
 */
public interface UsersService extends IService<Users> {

    Users staffLogin(Users user) throws Exception;

    String token(Users user) throws Exception;

    Users checkUser(Users user) throws Exception;

    Users register(Users user) throws Exception;

    Users findAllUsers(String token) throws Exception;

    Integer modificationPassword(String token,String password) throws Exception;

    Boolean checkPassword(String token,String password) throws Exception;

    Map<String,Object> findAllCountUser(String token)throws Exception;
    Map<String,Object> findAllCreateUser(String token)throws Exception;
    Map<String,Object> findAllLoginUser(String token)throws Exception;
    Map<String,Object> findAllOrderUser(String token)throws Exception;
    List<Map<String, Object>> findAllUserByMonthIncrement(String token)throws Exception;
}
