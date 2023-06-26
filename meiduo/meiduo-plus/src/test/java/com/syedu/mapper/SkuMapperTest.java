package com.syedu.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.Sku;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * author:Administrator
 * createTime:2023/6/2021:16
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class SkuMapperTest {


    @Autowired
    private SkuMapper skuMapper;

    @Test
    public void test() throws JsonProcessingException {
        Sku allByIdWithSpecificationAndOption = this.skuMapper.findAllByIdWithSpecificationAndOption(1);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(allByIdWithSpecificationAndOption);
        System.out.println(s);
    }
}
