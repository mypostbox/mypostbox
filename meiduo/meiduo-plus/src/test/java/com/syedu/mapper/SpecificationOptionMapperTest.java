package com.syedu.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.SpecificationOption;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2021:45
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class SpecificationOptionMapperTest {


    @Autowired
    private SpecificationOptionMapper mapper;


    @Test
    public void test(){
        List<SpecificationOption> allBySpecId = this.mapper.findAllBySpecId(1);
        allBySpecId.stream().forEach(System.out::println);
    }

    @Test
    public void test1() throws JsonProcessingException {
        List<Map<String, Object>> allOptionByPage = this.mapper.findAllOptionByPage(0, 10);
        ObjectMapper mapper1 = new ObjectMapper();
        String s = mapper1.writeValueAsString(allOptionByPage);
        System.out.println(s);
    }

    @Test
    public void test2(){
        Map<String, Object> option = this.mapper.getOption(1);
        System.out.println(option);
    }
}
