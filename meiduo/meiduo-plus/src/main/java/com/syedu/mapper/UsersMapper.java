package com.syedu.mapper;

import com.syedu.domain.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_users】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.Users
*/
public interface UsersMapper extends BaseMapper<Users> {
    /**
     * 管理员登入
     * @param username
     * @param password
     * @return
     */
    @Select("select * from tb_users where username = #{username} and password = #{password} and is_staff = 1 and is_superuser = 1")
    Users staffLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 获取所有用户的数量
     * @return
     */
    @Select("select count(id) from tb_users")
    @Results({
            @Result(property = "count" , column = "count(id)")
    })
    Map<String,Object> findAllUserCount();

    /**
     * 获取日增用户数量
     * @return
     */
    @Select("select count(id) as count from tb_users where datediff(date(date_joined),curdate()) = 0 group by date(date_joined)")
    @Results({
            @Result(property = "count" , column = "count(id)")
    })
    Map<String,Object> findAllCurrentCreateUser();


    @Select("select count(date_format(date_joined,'%Y年%m月%d日')) as count , date_format(date_joined,'%Y年%m月%d日') as date from tb_users where date(date_joined) between date_sub(curdate(),interval 1 month) and curdate() group by date_format(date_joined,'%Y年%m月%d日')")
    @Results({
            @Result(property = "count" , column = "count"),
            @Result(property = "date" , column = "date")
    })
    List<Map<String,Object>> findAllMonthCreateUser();

    /**
     * 获取日活用户
     * @return
     */
    @Select("select count(id) from tb_users where datediff(date(last_login),curdate()) = 0 group by date(last_login)")
    @Results({
            @Result(property = "count" , column = "count(id)")
    })
    Map<String,Object> findAllCurrentLoginUser();
}



