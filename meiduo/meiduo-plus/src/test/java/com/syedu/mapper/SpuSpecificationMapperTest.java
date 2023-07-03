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
 * createTime:2023/7/314:45
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class SpuSpecificationMapperTest {
    @Autowired
    private SpuSpecificationMapper spuSpecificationMapper;

    @Test
    public void test() throws JsonProcessingException {
        List<Map<String, Object>> spuSpecificationBySpuId = this.spuSpecificationMapper.findSpuSpecificationBySpuId(1);
        spuSpecificationBySpuId.stream().forEach(System.out::println);
    }
    @Test
    public void test1() throws JsonProcessingException {
        List<Map<String, Object>> spuSpecificationBySpuId = this.spuSpecificationMapper.findSpuSpecificationBySpuId(1);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(spuSpecificationBySpuId);
        System.out.println(s);
    }

    @Test
    public void test2(){
        List<Map<String, Object>> allSpecsByPage = this.spuSpecificationMapper.findAllSpecsByPage(0, 5);
        for(Map<String,Object> map : allSpecsByPage){
            System.out.println(map);
        }
    }

    @Test
    public void test3(){
        Map<String, Object> spuSpecById = this.spuSpecificationMapper.findSpuSpecById(1);
        System.out.println(spuSpecById);
    }
}
