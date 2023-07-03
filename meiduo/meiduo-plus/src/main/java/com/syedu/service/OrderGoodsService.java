package com.syedu.service;

import com.syedu.domain.OrderGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_order_goods】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface OrderGoodsService extends IService<OrderGoods> {
    //用户下单
    Map<String,Object> placeOrder(String token,Map<String,Object> map) throws Exception;


    List<OrderGoods> uncommentGoods(String token, String orderId) throws Exception;

}
