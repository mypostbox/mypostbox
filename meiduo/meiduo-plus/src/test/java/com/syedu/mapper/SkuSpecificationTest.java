package com.syedu.mapper;

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
    public void test(){
        List<SkuSpecification> allBySkuIdWithOption = this.mapper.findAllBySkuIdWithOption(1);
        allBySkuIdWithOption.stream().forEach(System.out::println);
    }
}
