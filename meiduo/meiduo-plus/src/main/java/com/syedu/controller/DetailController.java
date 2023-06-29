package com.syedu.controller;

import com.syedu.domain.Sku;
import com.syedu.mapper.SkuMapper;
import com.syedu.service.SkuService;
import com.syedu.service.SkuSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * author:Administrator
 * createTime:2023/6/2020:50
 */
@RestController
public class DetailController {

    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private SkuSpecificationService skuSpecificationService;
    @Autowired
    private SkuService skuService;
    //初始化加载detail数据
    @GetMapping("detail/{id}")
    public Sku detailService(@PathVariable("id") Integer id){
        return this.skuMapper.findAllByIdWithSpecificationAndOption(id);
    }
    //切换detail数据
    @GetMapping("toggle/{arr}")
    public Sku toggleService(@PathVariable("arr") String arr){
        System.out.println(arr);
        Sku sku = this.skuSpecificationService.detailToggleService(arr);
        if(sku == null) return null;
        return sku;
    }

    @GetMapping("/sku/hot/")
    public List<Sku> findHot(){
        return this.skuService.fundHot();
    }

}
