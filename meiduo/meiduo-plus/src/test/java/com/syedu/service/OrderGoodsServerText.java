package com.syedu.service;

import com.syedu.domain.OrderGoods;
import com.syedu.mapper.OrderGoodsMapper;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;

/**
 * author:Administrator
 * createTime:2023/6/2819:15
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class OrderGoodsServerText {

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;


    @Test
    public void test(){
        this.orderGoodsMapper.insert(new OrderGoods().setCreateTime(new Date()).setUpdateTime(new Date()).setCount(5).setPrice(6.6).setComment("").setScore(0).setIsAnonymous(0).setIsCommented(0).setOrderId("666666").setSkuId(1));

    }

}
