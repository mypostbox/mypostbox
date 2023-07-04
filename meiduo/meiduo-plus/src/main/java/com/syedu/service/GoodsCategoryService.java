package com.syedu.service;

import com.syedu.domain.GoodsCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_goods_category】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface GoodsCategoryService extends IService<GoodsCategory> {
    /**
     * 根据三级id查出三层数据
     * @param goodsCategoryId
     * @return
     */
    Map<String,Object> listService(Integer goodsCategoryId);

    /**
     * 获取所有商品种类goodsCategory
     * @param token
     * @return
     * @throws Exception
     */
    List<GoodsCategory> findGoodsCategory(String token) throws Exception;
    //获取一级goodsCategory
    List<GoodsCategory> findGoodsCategoryOne(String token) throws Exception;
}
