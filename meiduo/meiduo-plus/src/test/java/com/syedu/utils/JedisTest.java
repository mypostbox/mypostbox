package com.syedu.utils;

import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import redis.clients.jedis.Jedis;

/**
 * author:Administrator
 * createTime:2023/6/2011:01
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class JedisTest {

    @Autowired
    private Jedis jedis;
    @Test
    public void test(){
        System.out.println(jedis);
    }


    @Test
    public void test1(){
        Jedis jedis1 = new Jedis();
        System.out.println(jedis1);
    }


}
