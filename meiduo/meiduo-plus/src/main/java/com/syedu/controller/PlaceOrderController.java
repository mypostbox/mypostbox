package com.syedu.controller;

import com.syedu.service.AddressService;
import com.syedu.service.OrderGoodsService;
import com.syedu.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2719:06
 */
@RestController
public class PlaceOrderController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private SkuService skuService;
    @Autowired
    private OrderGoodsService orderGoodsService;
    //获取用户地址信息
    @GetMapping("addresses")
    public Map<String,Object> findAllAddresses(@RequestHeader("Authorization") String token) throws Exception {
        return this.addressService.findAllAddress(token);
    }
    /**
     * 设置默认地址
     */
    @PutMapping("addresses/{addressId}/status")
    public Integer addressStatus(@RequestHeader("Authorization") String token,
                                 @PathVariable("addressId") Integer addressId) throws Exception {
        return this.addressService.addressStatus(token,addressId);
    }
    //获取用户商品信息
    @GetMapping("orders/settlement")
    public Map<String,Object> findAllSku(@RequestHeader("Authorization") String token) throws Exception{
        return this.skuService.findAllSku(token);
    }
    //用户下订单
    @PostMapping("orders")
    public Map<String,Object> placeOrder(@RequestHeader("Authorization") String token,
                                         @RequestBody Map<String,Object> map) throws Exception{
        return this.orderGoodsService.placeOrder(token,map);
    }
}
