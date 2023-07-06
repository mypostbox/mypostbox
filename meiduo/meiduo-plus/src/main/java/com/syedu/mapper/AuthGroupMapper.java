package com.syedu.mapper;

import com.syedu.domain.AuthGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【auth_group】的数据库操作Mapper
* @createDate 2023-06-13 08:45:36
* @Entity com.syedu.domain.AuthGroup
*/
public interface AuthGroupMapper extends BaseMapper<AuthGroup> {
    //分页获取（auth_group）中的数据
    @Select("select * from auth_group limit #{page} , #{pageSize}")
    List<Map<String,Object>> findAllGroupByPage(@Param("page") Integer page,@Param("pageSize") Integer pageSize);

    //根据用户id获取该用户的所有角色(未使用)
    @Select("select * from auth_group where id in(select group_id from tb_users_groups where user_id = #{userId})")
    List<AuthGroup> findAllAuthGroupByUserId(@Param("userId") Integer userId);
}




