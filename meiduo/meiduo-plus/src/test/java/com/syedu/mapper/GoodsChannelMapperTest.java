package com.syedu.mapper;

import com.syedu.domain.GoodsChannel;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2114:56
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class GoodsChannelMapperTest {
    @Autowired
    private GoodsChannelMapper mapper;


    @Test
    public void test(){
        List<GoodsChannel> allBySequence = this.mapper.findAllByGroupId(1);
        allBySequence.stream().forEach(System.out::println);
    }

    @Test
    public void test2(){

    }
}
