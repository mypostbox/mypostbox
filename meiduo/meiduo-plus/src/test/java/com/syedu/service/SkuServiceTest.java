package com.syedu.service;

import com.syedu.domain.Sku;
import com.syedu.utils.config.SpringConfiguration;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * author:Administrator
 * createTime:2023/6/2011:29
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class SkuServiceTest {



    @Autowired
    private SkuService skuService;


    @Test
    public void test(){
        Sku skuDetailById = this.skuService.findSkuDetailById(1);
        System.out.println(skuDetailById);
    }

}
