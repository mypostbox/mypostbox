package com.syedu.mapper;

import com.syedu.domain.SpecificationOption;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

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
}
