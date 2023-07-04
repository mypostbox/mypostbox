package com.syedu.mapper;

import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/7/414:56
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class SkuImageMapperTest {
    @Autowired
    private SkuImageMapper skuImageMapper;

    @Test
    public void test(){
        List<Map<String, Object>> allSkuImageByPage = this.skuImageMapper.findAllSkuImageByPage(0, 5);
        for(Map<String,Object> map : allSkuImageByPage){
            System.out.println(map);
        }
    }
}
