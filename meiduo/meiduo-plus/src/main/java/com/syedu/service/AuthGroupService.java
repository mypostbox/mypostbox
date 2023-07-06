package com.syedu.service;

import com.syedu.domain.AuthGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.syedu.domain.MyGroupBody;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【auth_group】的数据库操作Service
* @createDate 2023-06-13 08:45:36
*/
public interface AuthGroupService extends IService<AuthGroup> {
    //分页获取（auth_group）中的数据
    Map<String,Object> findAllGroupByPage(String token,Integer page,Integer pageSize) throws Exception;
    //添加角色
    Integer saveGroup(String token, MyGroupBody permissions) throws Exception;
    //根据id获取角色的名称和权限
    MyGroupBody findGroup(String token,Integer groupId) throws Exception;
    //根据id修改角色
    Integer updateGroup(String token,Integer groupId,MyGroupBody permissions) throws Exception;
    //根据id删除角色
    Integer deleteGroup(String token,Integer groupId) throws Exception;


    //获取所有的用户组
    List<AuthGroup> findAllAuthGroup(String token) throws Exception;
}
