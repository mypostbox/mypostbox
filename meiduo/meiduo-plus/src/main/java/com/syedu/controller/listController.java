package com.syedu.controller;

import com.syedu.domain.Sku;
import com.syedu.service.GoodsCategoryService;
import com.syedu.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2519:14
 */
@RestController
@RequestMapping("categories")
public class listController {

    @Autowired
    private SkuService skuService;

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 给list页面提供数据
     * @param goodsCategoryId
     * @return
     */
    @GetMapping("{goodsCategoryId}/skus/")
    public Map<String,Object> listServiceStu(@PathVariable("goodsCategoryId") Integer goodsCategoryId) {
        System.out.println(goodsCategoryId);//155
       return this.skuService.listService(goodsCategoryId);
    }

    /**
     * 给list页面提供三级数据
     * @param goodsCategoryId
     */
    @GetMapping("{goodsCategoryId}")
    public Map<String,Object> listServiceGoodsCategory(@PathVariable("goodsCategoryId") Integer goodsCategoryId){
        return this.goodsCategoryService.listService(goodsCategoryId);
    }
}
