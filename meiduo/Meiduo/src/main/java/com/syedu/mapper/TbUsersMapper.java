package com.syedu.mapper;

import com.syedu.domain.TbUsers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/810:03
 */
public interface TbUsersMapper {


    //插入成功放回1
    @Insert("insert into tb_users(password,username,first_name,last_name,mobile,date_joined)  values(#{password},#{username},#{first_name},#{last_name},#{mobile},#{date_joined})")
    Integer register(@Param(value = "password") String password, @Param(value = "username") String username , @Param(value = "first_name") String first_name, @Param(value = "last_name") String last_name, @Param(value = "mobile") String mobile, @Param(value = "date_joined")String date_joined);

    //没查到返回null
    @Select("select username from tb_users where username = #{username}")
    TbUsers checkName(String username);

    //用户名登入
    @Select("select id,username from tb_users where username = #{username} and password = #{password}")
    TbUsers loginUsername(@Param(value = "username") String username,@Param(value = "password")String password);

    //手机号登入
    @Select("select id,username from tb_users where username = #{username} and mobile = #{mobile}")
    TbUsers loginMobile(@Param(value = "username") String username , @Param(value = "mobile") String mobile);
}
