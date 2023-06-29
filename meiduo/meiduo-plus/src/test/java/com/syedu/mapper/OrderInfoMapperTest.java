package com.syedu.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.OrderInfo;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

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
}
