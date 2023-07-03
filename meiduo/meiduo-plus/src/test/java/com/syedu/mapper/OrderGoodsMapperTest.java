package com.syedu.mapper;

import com.syedu.domain.OrderGoods;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/7/217:18
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class OrderGoodsMapperTest {

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Test
    public void test(){
        List<OrderGoods> allByOrderId = this.orderGoodsMapper.findAllByOrderId("2023062801d573d492124ff3ac40ad7366ca6fe200000001");
        allByOrderId.stream().forEach(System.out::println);
    }
}
