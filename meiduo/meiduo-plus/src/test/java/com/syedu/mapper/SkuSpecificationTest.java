package com.syedu.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.SkuSpecification;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/6/2021:30
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class SkuSpecificationTest {

    @Autowired
    private SkuSpecificationMapper mapper;

    @Test
    public void test() throws JsonProcessingException {
        List<SkuSpecification> allBySkuId = this.mapper.findAllBySkuId(1);
        ObjectMapper mapper1 = new ObjectMapper();
        String s = mapper1.writeValueAsString(allBySkuId);
        System.out.println(s);
    }
}
