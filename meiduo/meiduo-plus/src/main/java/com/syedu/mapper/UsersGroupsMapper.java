package com.syedu.mapper;

import com.syedu.domain.UsersGroups;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_users_groups】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.UsersGroups
*/
public interface UsersGroupsMapper extends BaseMapper<UsersGroups> {
    //根据用户id获取角色id
    @Select("select group_id from tb_users_groups where user_id = #{userId}")
    List<Integer> findAllGroupByUserId(@Param("userId") Integer userId);
}




