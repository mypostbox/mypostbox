package com.syedu.service;

import com.syedu.domain.AuthPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【auth_permission】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface AuthPermissionService extends IService<AuthPermission> {
    //分页获取权限数据(auth_permission)
    Map<String,Object> findAllPermissionByPage(String token, Integer page, Integer pageSize) throws Exception;
    //获取所有的权限类型
    List<Map<String,Object>> findAllContentType(String token) throws Exception;
    //添加保存权限
    Integer savePermission(String token,AuthPermission authPermission) throws Exception;
    //根据id获取当前权限
    AuthPermission findPermission(String token,Integer permissionId) throws Exception;
    //根据id修改权限
    Integer updatePermission(String token,Integer permissionId,AuthPermission authPermission) throws Exception;
    //根据id删除权限
    Integer deletePermission(String token,Integer permissionId) throws Exception;
    //获取所有的权限
    List<Map<String,Object>> findAllGroup(String token) throws Exception;
}
