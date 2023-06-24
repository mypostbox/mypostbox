package com.syedu.controller;

import com.syedu.domain.Sku;
import com.syedu.mapper.SkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:Administrator
 * createTime:2023/6/2020:50
 */
@RestController
public class DetailController {

    @Autowired
    private SkuMapper skuMapper;

    @GetMapping("detail/{id}")
    public Sku test(@PathVariable("id") Integer id){
        return this.skuMapper.findAllByIdWithSpecificationAndOption(id);
    }


}
