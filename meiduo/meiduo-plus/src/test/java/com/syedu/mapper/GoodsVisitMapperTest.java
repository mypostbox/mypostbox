package com.syedu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.GoodsVisit;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/7/219:31
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class GoodsVisitMapperTest {

    @Autowired
    private GoodsVisitMapper goodsVisitMapper;

    @Test
    public void test(){
        this.goodsVisitMapper.InsertGoodsVisit(1,4);
    }

    @Test
    public void test1(){
        LambdaQueryWrapper<GoodsVisit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsVisit::getCategoryId,2);
        boolean exists = this.goodsVisitMapper.exists(wrapper);
        System.out.println(exists);
    }

    @Test
    public void test2() throws JsonProcessingException {
        List<Map<String, Object>> allGoodsDayViews = this.goodsVisitMapper.findAllGoodsDayViews();
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(allGoodsDayViews);
        System.out.println(s);
    }
}
