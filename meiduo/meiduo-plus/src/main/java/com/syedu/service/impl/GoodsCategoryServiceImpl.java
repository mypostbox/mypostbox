package com.syedu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.GoodsCategory;

import com.syedu.domain.Users;
import com.syedu.service.GoodsCategoryService;
import com.syedu.mapper.GoodsCategoryMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
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
    @Autowired
    private PublicKey publicKey;
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
    //获取商品种类goodsCategory
    @Override
    public List<GoodsCategory> findGoodsCategory(String token) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return  this.goodsCategoryMapper.selectList(null);
        }
        return null;
    }
    @Override
    public List<GoodsCategory> findGoodsCategoryOne(String token) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            LambdaQueryWrapper<GoodsCategory> wrapper = new LambdaQueryWrapper<>();
            wrapper.isNull(GoodsCategory::getParentId);
            return  this.goodsCategoryMapper.selectList(wrapper);
        }
        return null;
    }
}




