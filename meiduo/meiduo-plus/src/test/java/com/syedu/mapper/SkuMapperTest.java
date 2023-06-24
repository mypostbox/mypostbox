package com.syedu.mapper;

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
    public void test(){
        Sku allByIdWithSpecificationAndOption = this.skuMapper.findAllByIdWithSpecificationAndOption(1);
        System.out.println(allByIdWithSpecificationAndOption);
    }
}
