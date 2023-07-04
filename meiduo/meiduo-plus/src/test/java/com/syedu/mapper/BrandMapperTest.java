package com.syedu.mapper;

import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/7/413:59
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class BrandMapperTest {
    @Autowired
    private BrandMapper brandMapper;

    @Test
    public void test(){
        List<Map<String, Object>> allBrandByPage = this.brandMapper.findAllBrandByPage(0, 5);
        for(Map<String,Object> map : allBrandByPage){
            System.out.println(map);
        }
    }
}
