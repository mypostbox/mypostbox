package com.syedu.mapper;

import com.alibaba.fastjson2.JSON;
import com.syedu.domain.TbGoodsCategory;
import com.syedu.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.FileChooserUI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author:Administrator
 * createTime:2023/6/811:19
 */
public class TbGoodsCategoryTest {

    private SqlSession session = MybatisUtils.getSqlSession();


    @Test
    public void test(){
        System.out.println(this.session);
    }


    @Test
    public void testGetTbGoodsCategoryById(){
        TbGoodsCategoryMapper mapper = this.session.getMapper(TbGoodsCategoryMapper.class);


    }

    @Test
    public void testGetTbGoodsCategoryByParentIsNull(){
        TbGoodsCategoryMapper mapper = this.session.getMapper(TbGoodsCategoryMapper.class);
        List<TbGoodsCategory> tbGoodsCategoryByPrentIsNull = mapper.getTbGoodsCategoryByPrentIsNull();
        tbGoodsCategoryByPrentIsNull.stream().forEach(System.out::println);
    }

    @Test
    public void test5(){
        TbGoodsCategoryMapper mapper = this.session.getMapper(TbGoodsCategoryMapper.class);


    }

    @Test
    public void test6(){
        TbGoodsCategoryMapper mapper = this.session.getMapper(TbGoodsCategoryMapper.class);
        List<TbGoodsCategory> tbGoodsCategoryByPrentIsNull = mapper.getTbGoodsCategoryByPrentIsNull();
        tbGoodsCategoryByPrentIsNull.stream().forEach(System.out::println);
    }

    @Test
    public void test7(){
        TbGoodsCategoryMapper mapper = this.session.getMapper(TbGoodsCategoryMapper.class);
        List<TbGoodsCategory> allGoodsCategory = mapper.getAllGoodsCategory();
        allGoodsCategory.stream().forEach(System.out::println);
    }
    @Test
    public void Test(){
        TbGoodsCategoryMapper mapper = this.session.getMapper(TbGoodsCategoryMapper.class);
        List<TbGoodsCategory> allGoodsCategory = mapper.getAllGoodsCategory();
        List<TbGoodsCategory> collect = allGoodsCategory.stream().filter(goods -> goods.getParentId() == null).peek(goods->goods.setChild(fun(allGoodsCategory,goods))).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }

    private List<TbGoodsCategory> fun(List<TbGoodsCategory> goodsCategory,TbGoodsCategory goods){
       return goodsCategory.stream().filter(good->good.getParentId() == goods.getId()).peek(good->good.setChild(fun(goodsCategory,good))).collect(Collectors.toList());
    }


    @Test
    public void test8(){
        TbGoodsCategoryMapper mapper = this.session.getMapper(TbGoodsCategoryMapper.class);
        List<TbGoodsCategory> tbGoodsCategoryByPrentIsNull = mapper.getTbGoodsCategoryByPrentIsNull();
        tbGoodsCategoryByPrentIsNull.stream().forEach(System.out::println);
    }
}
