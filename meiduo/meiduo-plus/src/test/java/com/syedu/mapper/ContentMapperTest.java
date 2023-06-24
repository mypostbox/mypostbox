package com.syedu.mapper;

import com.syedu.domain.Content;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/6/1712:34
 */

@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class ContentMapperTest {


    @Autowired
    public ContentMapper contentMapper;

    @Test
    public void test(){
        System.out.println(this.contentMapper);
    }

    @Test
    public void test1(){
        List<Content> contentByCategoryId = this.contentMapper.findContentByCategoryId(1);
        contentByCategoryId.stream().forEach(System.out::println);
    }

}
