package com.syedu.mapper;

import com.syedu.domain.ContentCategory;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/6/1712:47
 */

@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class ContentCategoryMapperTest {


    @Autowired
    private ContentCategoryMapper contentCategoryMapper;



    @Test
    public void test2(){
        System.out.println(this.contentCategoryMapper);
    }

    @Test
    public void test(){
        List<ContentCategory> floor1 = this.contentCategoryMapper.findFloor1();
        floor1.stream().forEach(System.out::println);
    }

    @Test
    public void test1(){
        List<ContentCategory> index1fTitle = this.contentCategoryMapper.findFloor2("index_1f_title");
        index1fTitle.stream().forEach(System.out::println);
    }


}
