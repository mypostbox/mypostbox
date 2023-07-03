package com.syedu.controller;

import com.syedu.domain.Address;
import com.syedu.domain.OrderGoods;
import com.syedu.service.AddressService;
import com.syedu.service.OrderGoodsService;
import com.syedu.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    /**
     * 用户地址保存
     * @param token
     * @param address
     * @return
     */
    @PostMapping( "addresses")
    public Address saveAddresses(@RequestHeader("Authorization") String token,
                                            @RequestBody Address address) throws Exception {
       return this.addressService.saveAddress(token,address);
    }
    /**
     * 用户地址修改
     */
    @PutMapping("addresses/{addressId}")
    public Address updateAddress(@RequestHeader("Authorization") String token,
                                 @RequestBody Address address,
                                 @PathVariable Integer addressId) throws Exception {
        return this.addressService.updateAddress(token, address, addressId);
    }
    /**
     * 修改保存地址标题
     */
    @PutMapping("addresses/{addressId}/title")
    public Address updateAddressTitle(@RequestHeader("Authorization") String token,
                                      @PathVariable("addressId") Integer addressId,
                                      @RequestBody Map<String,Object> map) throws Exception {
        return this.addressService.updateAddressTitle(token, addressId, map.get("title").toString());
    }
    /**
     *根据订单号获取用户的商品，（给评论页面提供基础数据）
     */
    @GetMapping("orders/{orderId}/uncommentgoods")
    public List<OrderGoods> uncommentGoods(@RequestHeader("Authorization") String token,
                                           @PathVariable("orderId") String orderId) throws Exception {
        return this.orderGoodsService.uncommentGoods(token, orderId);

    }
}
