package com.syedu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.GoodsCategory;

import com.syedu.service.GoodsCategoryService;
import com.syedu.mapper.GoodsCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Administrator
 * @description 针对表【tb_goods_category】的数据库操作Service实现
 * @createDate 2023-06-13 08:45:37
 */
@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory>
        implements GoodsCategoryService {
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    @Override
    public Map<String, Object> listService(Integer goodsCategoryId) {
        Map<String,Object> map = new HashMap<>();
        GoodsCategory allById = this.goodsCategoryMapper.findAllById(goodsCategoryId);
        map.put("cat3",allById);
        LambdaQueryWrapper<GoodsCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsCategory::getId,allById.getParentId());
        GoodsCategory goodsCategory = this.goodsCategoryMapper.selectOne(wrapper);
        map.put("cat2",goodsCategory);
        wrapper.clear();
        wrapper.eq(GoodsCategory::getId,goodsCategory.getParentId());
        GoodsCategory goodsCategory1 = this.goodsCategoryMapper.selectOne(wrapper);
        map.put("cat1",goodsCategory1);
        return map;
    }
}




