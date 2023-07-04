package com.syedu.controller.admins;

import com.syedu.domain.OrderInfo;
import com.syedu.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/7/415:23
 */
@RestController
@RequestMapping("admins/orders")
public class AdminOrderController {
    @Autowired
    private OrderInfoService orderInfoService;
    //分页获取订单的数据
    @GetMapping("")
    Map<String,Object> findAllOrderByPage(@RequestHeader("Authorization") String token,
                                          @RequestParam("page") Integer page,
                                          @RequestParam("pagesize") Integer pageSize,
                                          @RequestParam("keyword") String keyword) throws Exception {
        return this.orderInfoService.findAllOrderByPage(token, page, pageSize, keyword);
    }
    //根据id获取订单详情
    @GetMapping("{orderId}")
    Map<String,Object> findOrder(@RequestHeader("Authorization") String token,
                                 @PathVariable("orderId") String orderId) throws Exception {
        return this.orderInfoService.findOrder(token, orderId);
    }

    //修改订单的状态
    @PutMapping("{orderId}/status")
    Integer updateOrder(@RequestHeader("Authorization") String token,
                        @PathVariable("orderId") String orderId,
                        @RequestBody OrderInfo orderInfo) throws Exception {
        return this.orderInfoService.updateOrder(token, orderId, orderInfo);
    }
}
