package com.syedu.service;

import com.syedu.domain.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.syedu.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_order_info】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface OrderInfoService extends IService<OrderInfo> {

    Map<String,Object> findAllOrderInfoByUserId(String token, Integer page,Integer pageSize) throws Exception;

    //分页获取订单的数据
    Map<String,Object> findAllOrderByPage(String token,Integer page,Integer pageSize,String keyword) throws Exception;

    //根据id获取订单详情
    Map<String,Object> findOrder(String token,String orderId) throws Exception;

    //修改订单的状态
    Integer updateOrder(String token,String orderId,OrderInfo orderInfo) throws Exception;
}
