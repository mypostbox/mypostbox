package com.syedu.mapper;

import com.syedu.domain.TbGoodsCategory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/6/811:10
 */
public interface TbGoodsCategoryMapper {

    //第一代goodsCategory
    @Select("select * from tb_goods_category where parent_id is null")
    @Results({
            @Result(property = "child", column = "id",
                    many = @Many(select = "com.syedu.mapper.TbGoodsCategoryMapper.getTbGoodsCategoryByParentId",
                            fetchType = FetchType.EAGER))
    })
    List<TbGoodsCategory> getTbGoodsCategoryByPrentIsNull();

    //根据父id查
    @Select("select * from tb_goods_category where parent_id = #{parent_id }")
    @Results(id = "goodsCategory", value = {
            @Result(property = "child", column = "id",
                    many = @Many(select = "com.syedu.mapper.TbGoodsCategoryMapper.getTbGoodsCategoryByParentId",
                            fetchType = FetchType.EAGER))
    })
    List<TbGoodsCategory> getTbGoodsCategoryByParentId(@Param(value = "parent_id ") Integer parent_id);



    //查询所有的信息
    @Select("select * from tb_goods_category")
    List<TbGoodsCategory> getAllGoodsCategory();
}
