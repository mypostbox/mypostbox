package com.syedu.controller;

import com.syedu.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2820:32
 */
@RestController
public class UserCenterOrderController {

    @Autowired
    private OrderInfoService orderInfoService;
    @GetMapping("orders")
    public Map<String,Object> findAllOrderInfoByUserId(@RequestHeader("Authorization") String token,
                                                       @RequestBody Map<String,Object> map) throws Exception{
        return this.orderInfoService.findAllOrderInfoByUserId(token,map);
    }
}
