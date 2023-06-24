package com.syedu.mapper;

import com.syedu.domain.TbUsers;
import com.syedu.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/810:12
 */
public class TbUsersMapperTest {

    private SqlSession session = MybatisUtils.getSqlSession();


    @Test
    public void test(){
        System.out.println(this.session);
    }

    @Test
    public void test1(){
        TbUsersMapper mapper = this.session.getMapper(TbUsersMapper.class);
        Integer register = mapper.register("123456", "cheng1", "cheng", "cheng", "17770884433", LocalDateTime.now().toString());
        this.session.commit(register > 2);
    }

    @Test
    public void test2(){
        System.out.println(LocalDateTime.now());
    }

    @Test
    public void test3(){
        TbUsersMapper mapper = this.session.getMapper(TbUsersMapper.class);
        TbUsers cheng = mapper.checkName("cheng2");
        System.out.println(cheng);
    }

    @Test
    public void test4(){
        TbUsersMapper mapper = this.session.getMapper(TbUsersMapper.class);
        TbUsers cheng = mapper.loginUsername("cheng", "123456");
        System.out.println(cheng);
    }

    @Test
    public void testLoginMobile(){
        TbUsersMapper mapper = this.session.getMapper(TbUsersMapper.class);
        TbUsers cheng = mapper.loginMobile("cheng", "17770884439");
        System.out.println(cheng);
    }
}
