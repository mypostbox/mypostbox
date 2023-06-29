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
     *  给list页面提供数据
     * @param cat 类型id
     * @param page 当前页码
     * @param pageSize 页码大小
     * @param ordering 排序规则
     * @return 放回查的到sku数据和sku的总数量()
     */
    @GetMapping("{cat}/skus")
    public Map<String,Object> getSkuByCat(@PathVariable("cat") Integer cat,
                                          @RequestParam("page") Integer page,
                                          @RequestParam("page_size") Integer pageSize,
                                          @RequestParam("ordering") String ordering){
        return this.skuService.listService(cat,page,pageSize,ordering);
    }

    /**
     * 给list页面提供三级数据
     * @param goodsCategoryId 第三级类型id
     * @return 三级数据
     */
    @GetMapping("{goodsCategoryId}")
    public Map<String,Object> listServiceGoodsCategory(@PathVariable("goodsCategoryId") Integer goodsCategoryId){
        return this.goodsCategoryService.listService(goodsCategoryId);
    }
}
