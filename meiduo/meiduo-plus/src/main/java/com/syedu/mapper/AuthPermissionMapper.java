package com.syedu.mapper;

import com.syedu.domain.AuthPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【auth_permission】的数据库操作Mapper
* @createDate 2023-06-13 08:45:36
* @Entity com.syedu.domain.AuthPermission
*/
public interface AuthPermissionMapper extends BaseMapper<AuthPermission> {
    //分页获取权限数据(auth_permission)
    @Select("select * from auth_permission limit #{page} , #{pageSize}")
    List<Map<String,Object>> findAllPermissionByPage(@Param("page") Integer page,@Param("pageSize") Integer pageSize);
    @Select("select distinct(content_type_id) 'type' from auth_permission")
    @Results({
            @Result(property = "id" , column = "type"),
            @Result(property = "name" , column = "type")
    })
    List<Map<String,Object>> findAllContentType();
}




