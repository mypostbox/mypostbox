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

    Integer delCart(String token,String skuId) throws Exception;

    Integer updateCart(String token,String skuId,String count) throws Exception;

    Sku findSkuDetailById(Integer id);

    //list数据
    Map<String,Object> listService(Integer goodsCategoryId);
}
