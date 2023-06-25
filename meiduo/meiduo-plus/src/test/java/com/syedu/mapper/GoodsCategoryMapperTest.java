package com.syedu.mapper;

import com.syedu.domain.GoodsCategory;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/6/1719:51
 */

@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class GoodsCategoryMapperTest {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;


    @Test
    public void test(){
        List<GoodsCategory> allByParentIdIsNull = this.goodsCategoryMapper.findAllByParentIdIsNull();
        allByParentIdIsNull.stream().forEach(System.out::println);
    }

    @Test
    public void test1(){
        GoodsCategory allById = this.goodsCategoryMapper.findAllById(1);
        System.out.println(allById);
    }

}
