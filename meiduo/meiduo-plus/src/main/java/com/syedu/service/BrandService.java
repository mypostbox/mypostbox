package com.syedu.service;

import com.syedu.domain.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_brand】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface BrandService extends IService<Brand> {
    //分页获取brand的数据
    Map<String,Object> findAllBrandByPage(String token,Integer page,Integer pageSize) throws Exception;
}
