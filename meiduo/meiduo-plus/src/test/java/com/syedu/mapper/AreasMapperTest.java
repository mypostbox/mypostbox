package com.syedu.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.Areas;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2912:56
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class AreasMapperTest {

    @Autowired
    private AreasMapper areasMapper;

    @Test
    public void test() throws JsonProcessingException {
//        List<Areas> allByParentIdIsnull = this.areasMapper.findAllByParentIdIsnull();
//        ObjectMapper mapper = new ObjectMapper();
//        String s = mapper.writeValueAsString(allByParentIdIsnull);
//        System.out.println(s);
    }

    @Test
    public void test2() throws JsonProcessingException {
        List<Areas> allChildByParent = this.areasMapper.findAllChildByParent(110000);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(allChildByParent);
        System.out.println(s);
    }

    @Test
    public void test3() throws JsonProcessingException {
        Map<String, Object> test = this.areasMapper.test(110000);
        ObjectMapper mapper = new ObjectMapper();
       String s = mapper.writeValueAsString(test);
        System.out.println(s);
    }

}
