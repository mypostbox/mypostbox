package com.syedu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2610:37
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class ContentCategoryServiceTest {

    @Autowired
    private ContentCategoryService contentCategoryService;


    @Test
    public void test() throws JsonProcessingException {
        Map<String, Object> stringObjectMap = this.contentCategoryService.indexService();
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(stringObjectMap);
        System.out.println(s);
    }
}
