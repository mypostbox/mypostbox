package com.syedu.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.ChannelGroup;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/6/2114:48
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class ChannelGroupMapperTest {

    @Autowired
    private ChannelGroupMapper mapper;


    @Test
    public void test() throws JsonProcessingException {
        List<ChannelGroup> allBy = this.mapper.findAllBy();
        allBy.stream().forEach(System.out::println);
        System.out.println("--------------- 5");
        ObjectMapper mapper1 = new ObjectMapper();
        String s = mapper1.writeValueAsString(allBy);
        System.out.println(s);
    }

}
