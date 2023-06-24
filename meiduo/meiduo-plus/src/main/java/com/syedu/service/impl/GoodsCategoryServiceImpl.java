package com.syedu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.GoodsCategory;

import com.syedu.service.GoodsCategoryService;
import com.syedu.mapper.GoodsCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public List<List<GoodsCategory>>
    menuService() {
        List<List<GoodsCategory>> list = new ArrayList<>();
        List<GoodsCategory> allByParentIdIsNull = this.goodsCategoryMapper.findAllByParentIdIsNull();
        ArrayList<GoodsCategory> goodsCategories = new ArrayList<>();
        for (int i = 0; i < allByParentIdIsNull.size(); i++) {
            goodsCategories.add(allByParentIdIsNull.get(i));
            if (goodsCategories.size() == 4 && i < 20) {
                list.add(goodsCategories);
                goodsCategories = new ArrayList<>();
            }
            if (goodsCategories.size() == 3 && i > 20) {
                list.add(goodsCategories);
                goodsCategories = new ArrayList<>();
            }
        }
        if (goodsCategories.size() > 0) {
            list.add(goodsCategories);
        }
        for (int i = 0; i < list.size(); i++) {
            List<GoodsCategory> goodsCategories1 = list.get(i);
            for (int y = 1; y < goodsCategories1.size(); y++) {
                GoodsCategory goodsCategory = goodsCategories1.get(0);
                List<GoodsCategory> child = goodsCategory.getChild();
                List<GoodsCategory> child1 = goodsCategories1.get(y).getChild();
                goodsCategory.setChild(Stream.concat(child.stream(), child1.stream()).collect(Collectors.toList()));
            }
        }
        return list;
    }
}




