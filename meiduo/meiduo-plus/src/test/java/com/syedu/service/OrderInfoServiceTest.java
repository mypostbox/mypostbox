package com.syedu.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.OrderInfo;
import com.syedu.mapper.OrderInfoMapper;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2912:15
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class OrderInfoServiceTest {
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    private String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ0b20iLCJwYXNzd29yZCI6IjY2Njg4OCIsImVtYWlsIjoiOTg5OTlAcXEuY29tIiwiZXhwIjoxNjg4NjA5NTIzfQ.fC6auHOjq1o25weG9DQrhQNDDDFlRNg2x3VK_BJcvdXokLmPQ7faKELejalOh9c_1M-Huv58VrVSoYaRTMAqViDsSF9sdlx7oW2VOT-5iYgOIsZ9P2Z8fjztT_e1_-5eSvkp19ttxTZ_BorP8pP42mEKW5Yu70ArRYj8lRukxq02wsQ-og3jf1hNWshNyelf9HkI9mDKiSyPD6avpc3uKNkC8J9HzKkOrfXH13KJto0bxO0IbWLDcerbcZZ_UV581S1pUCPinK3R4S_HfIhRHcp8qCrKcVpNilnM56XVKEzjqaQquq5--rt1_WCaj_cwWqWlChzLuhzOyqIgMJDi0A";
    @Test
    public void test() throws Exception {
        Map<String, Object> allOrderInfoByUserId = this.orderInfoService.findAllOrderInfoByUserId(token, 1, 5);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(allOrderInfoByUserId);
        System.out.println(s);
    }

    @Test
    public void test1(){
        this.orderInfoMapper.insert(new OrderInfo().setOrderId("20230702").setAddressId(1).setPayMethod(2).setUserId(1));
    }
}
