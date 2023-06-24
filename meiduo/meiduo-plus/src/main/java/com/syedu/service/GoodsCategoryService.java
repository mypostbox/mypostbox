package com.syedu.service;

import com.syedu.domain.GoodsCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_goods_category】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface GoodsCategoryService extends IService<GoodsCategory> {

    List<List<GoodsCategory>> menuService();
}
