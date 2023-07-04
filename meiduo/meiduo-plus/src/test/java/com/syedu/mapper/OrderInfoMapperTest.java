package com.syedu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.OrderInfo;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2821:14
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class OrderInfoMapperTest {

    @Autowired
    private OrderInfoMapper orderInfoMapper;



    @Test
    public void test() throws JsonProcessingException {
        List<OrderInfo> allByUserIdWithOrderGoodsAndSku = this.orderInfoMapper.findAllByUserIdWithOrderGoodsAndSku(0, 5, 1);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(allByUserIdWithOrderGoodsAndSku);
        System.out.println(s);
    }

    @Test
    public void test1(){
        LambdaQueryWrapper<OrderInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderInfo::getUserId,1);
        Long aLong = this.orderInfoMapper.selectCount(wrapper);
        System.out.println(aLong);
    }

    @Test
    public void testFindAllOrderUser(){
        Map<String, Object> allOrderUser = this.orderInfoMapper.findAllOrderUser();
        System.out.println(allOrderUser);
    }

    @Test
    public void test3(){
        List<Map<String, Object>> allOrderByPage = this.orderInfoMapper.findAllOrderByPage(0, 5, "2019");
        for(Map<String,Object> map : allOrderByPage){
            System.out.println(map);
        }
    }

    @Test
    public void test4() throws JsonProcessingException {
        Map<String, Object> order = this.orderInfoMapper.findOrder("2023062801d573d492124ff3ac40ad7366ca6fe200000001");
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(order);
        System.out.println(s);
    }
}
