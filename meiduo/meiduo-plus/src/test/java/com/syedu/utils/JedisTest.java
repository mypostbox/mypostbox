package com.syedu.utils;

import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

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

    @Test
    public void test2(){
        Set<String> cart1 = this.jedis.smembers("cart1");
        for(String s : cart1){
            System.out.println(s);
            long cart11 = this.jedis.srem("cart1", s);
            System.out.println(cart11);
        }
    }

    @Test
    public void test3(){
        Set<String> cart1 = this.jedis.smembers("cart1");
        for(String s : cart1){
            System.out.println(s);
        }
    }

    @Test
    public void test4(){
        List<String> user1 = this.jedis.zrange("user1", 0, -1);
        for(String s : user1){
            this.jedis.zrem("user1",s);
        }
        this.jedis.close();
    }

    @Test
    public void test5(){
        Set<String> cart1 = this.jedis.hkeys("cart1");
        for(String s : cart1){
            this.jedis.hdel("cart1",s);
        }
        this.jedis.close();
    }
    @Test
    public void test6(){
        long cart1 = this.jedis.hlen("cart1");
        System.out.println(cart1);
    }


    @AfterAll
    public static void doAfter(){

    }

}
