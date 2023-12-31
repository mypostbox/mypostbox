package com.syedu.mapper;

import com.syedu.domain.Sku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_sku】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.Sku
*/
public interface SkuMapper extends BaseMapper<Sku> {
    /**
     * 根据sku_id拿出detail的所有数据
     * @param sku_id
     * @return
     */
    @Select("select id , create_time,update_time,name,caption,price,cost_price,market_price,stock,sales,comments,is_launched,category_id,spu_id,default_image from tb_sku where id = #{id} ")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "name",column = "name"),
            @Result(property = "price",column = "price"),
            @Result(property = "costPrice",column = "cost_price"),
            @Result(property = "marketPrice",column = "market_price"),
            @Result(property = "stock",column = "stock"),
            @Result(property = "sales",column = "sales"),
            @Result(property = "comments",column = "comments"),
            @Result(property = "isLaunched",column = "is_launched"),
            @Result(property = "categoryId",column = "category_id"),
            @Result(property = "spuId",column = "spu_id"),
            @Result(property = "defaultImage",column = "default_image"),
            //封装图片信息
            @Result(property = "skuImages" , column = "id" ,
                    many = @Many(select = "com.syedu.mapper.SkuImageMapper.findAllBySkuId")),
            //商品中间表
            @Result(property = "skuSpecifications" ,column = "id" ,
                    many = @Many(select = "com.syedu.mapper.SkuSpecificationMapper.findAllBySkuId")),
            //spu表信息
            @Result(property = "spu" , column = "spu_id" ,
                    one = @One(select = "com.syedu.mapper.SpuMapper.findAllById")),
    })
    Sku findAllByIdWithSpecificationAndOption(@Param("id") Integer sku_id);
    @Select("select * from tb_sku where id = #{id}")
    Sku findAllByIdSku(@Param("id") Integer id);

    //根据id查找sku，包含skuSpecification信息
    @Select("select * from tb_sku where id = #{skuId}")
    @Results({
            @Result(property = "id" , column = "id"),
            @Result(property = "skuSpecifications" , column = "id" ,
                    many = @Many(select = "com.syedu.mapper.SkuSpecificationMapper.findAllBySkuIds"))
    })
    Sku findSkuWithSkuSpecification(@Param("skuId") Integer skuId);
}




