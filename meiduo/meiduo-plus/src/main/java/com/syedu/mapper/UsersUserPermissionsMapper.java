package com.syedu.mapper;

import com.syedu.domain.UsersUserPermissions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_users_user_permissions】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.UsersUserPermissions
*/
public interface UsersUserPermissionsMapper extends BaseMapper<UsersUserPermissions> {
    //分页获取管理员的信息
    @Select("select distinct(tab2.id),tab2.username,tab2.email,tab2.mobile\n" +
            "from tb_users_user_permissions `tab1`\n" +
            "left join tb_users `tab2`\n" +
            "on tab2.id = tab1.user_id\n" +
            "limit #{page} , #{pageSize}")
    List<Map<String,Object>> findAllAdminByPage(@Param("page") Integer page,@Param("pageSize") Integer pageSize);

    //获取所有管理员的id
    @Select("select distinct(user_id) from tb_users_user_permissions")
    List<Integer> findAdminTotal();

    //根据用户id获取权限id
    @Select("select permission_id from tb_users_user_permissions where user_id = #{userId}")
    List<Integer> findAllPermissionByUserId(@Param("userId") Integer userId);
}




