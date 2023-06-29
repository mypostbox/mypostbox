package com.syedu.service;

import com.syedu.domain.Sku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_sku】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface SkuService extends IService<Sku> {

    List<Sku> findCart(String token) throws Exception;

    Integer delCart(String token,Map<String,Integer> map) throws Exception;

    Sku updateCart(String token,Map<String,Object> map) throws Exception;

    Boolean selectedAll(String token,Map<String,Object> map) throws Exception;


    //获取detail页面数据
    Sku findSkuDetailById(Integer id);
    //list页面数据
    Map<String,Object> listService(Integer cat,
                                   Integer page,
                                   Integer pageSize,
                                   String ordering);

    //获取热销数据
    List<Sku> fundHot();

    //添加用户浏览记录
    void browseHistories1(Integer skuId,String token) throws Exception;
    //获取用户的浏览记录
    List<Sku> browseHistories2(String token) throws Exception;

    //获取用户结算商品
    Map<String,Object> findAllSku(String token) throws Exception;

}
