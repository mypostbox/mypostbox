package com.syedu.service;

import com.syedu.domain.MyUserBody;
import com.syedu.domain.UsersUserPermissions;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_users_user_permissions】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface UsersUserPermissionsService extends IService<UsersUserPermissions> {

    //分页获取管理员的信息
    Map<String,Object> findAllAdminByPage(String token, Integer page, Integer pageSize) throws Exception;
    //添加管理员
    Integer saveAdmin(String token, MyUserBody myUserBody) throws Exception;
    //根据id获取管理员信息
    MyUserBody getAdmin(String token, Integer userId) throws Exception;
    //根据id修改管理员信息
    Integer updateAdmin(String token,Integer userId, MyUserBody myUserBody) throws Exception;
    //根据id删除管理员
    Integer deleteAdmin(String token,Integer userId) throws Exception;
}
