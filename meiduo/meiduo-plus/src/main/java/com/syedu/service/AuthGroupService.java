package com.syedu.service;

import com.syedu.domain.AuthGroup;
import com.baomidou.mybatisplus.extension.service.IService;

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
    //获取（auth_group）的所有数据
    List<Map<String,Object>> findAllGroup(String token) throws Exception;
}
