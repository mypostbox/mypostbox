package com.syedu.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2420:53
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class SpuMapperTest {

    @Autowired
    private SpuMapper spuMapper;

    @Test
    public void test() throws JsonProcessingException {
        List<Map<String, Object>> allSpuByPage = this.spuMapper.findAllSpuByPage(0, 5);

        for(Map<String,Object> map : allSpuByPage){
            System.out.println(map);
            System.out.println("=================================");
        }
    }

    @Test
    public void test1(){
        Long aLong = this.spuMapper.selectCount(null);
        System.out.println(aLong);
    }
}
