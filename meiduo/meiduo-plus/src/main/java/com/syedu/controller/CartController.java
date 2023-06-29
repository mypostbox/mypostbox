package com.syedu.controller;

import com.syedu.domain.Sku;
import com.syedu.service.SkuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2010:30
 */

@RestController
public class CartController {

    @Autowired
    private SkuService skuService;

    /**
     * 根据令牌获取用户购物车的信息
     * @param token
     * @return
     * @throws Exception
     */
    @GetMapping("cart")
    public List<Sku> getCart(@RequestHeader(value = "Authorization") String token) throws Exception {
        return this.skuService.findCart(token);
    }

    /**
     * 删除购物车的商品
     * @param token
     * @param map
     * @return
     * @throws Exception
     */
    @DeleteMapping("cart")
    public Integer delCart(@RequestHeader("Authorization") String token,
                           @RequestBody Map<String,Integer> map) throws Exception {
        return this.skuService.delCart(token, map);
    }
    /**
     * 更新购物车商品的信息
     */
    @PutMapping("cart")
    public  Sku updateCart(@RequestHeader("Authorization") String token,
                           @RequestBody Map<String,Object > map)throws Exception{
        return this.skuService.updateCart(token,map);
    }

    @PutMapping("cart/selection/")
    public Boolean updateCart2(@RequestHeader("Authorization") String token,
                              @RequestBody Map<String,Object> map) throws Exception {
        return this.skuService.selectedAll(token,map);
    }

}
