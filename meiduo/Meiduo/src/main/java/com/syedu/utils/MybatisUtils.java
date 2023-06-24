package com.syedu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.time.temporal.Temporal;

/**
 * author:Administrator
 * createTime:2023/6/89:43
 */
public class MybatisUtils {

    private static ThreadLocal<SqlSession> _LOCAL = new ThreadLocal<>() {
        @Override
        protected SqlSession initialValue() {
            try {
                InputStream resourceAsStream = Resources.getResourceAsStream("MybatisConfig.xml");
                SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
                SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
                return build.openSession();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    public static SqlSession getSqlSession(){
        return _LOCAL.get();
    }
}
