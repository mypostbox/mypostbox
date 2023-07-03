package com.syedu.mapper;

import com.syedu.domain.GoodsCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【tb_goods_category】的数据库操作Mapper
 * @createDate 2023-06-13 08:45:37
 * @Entity com.syedu.domain.GoodsCategory
 */
public interface GoodsCategoryMapper extends BaseMapper<GoodsCategory> {

    @Select("select id,create_time,update_time,name,parent_id from tb_goods_category where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "name", column = "name"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "child", column = "id",
                    many = @Many(select = "com.syedu.mapper.GoodsCategoryMapper.findAllByParentId",
                            fetchType = FetchType.EAGER))
    })
    GoodsCategory findAllByIdWithChild(@Param("id") Integer id);

    @Select("select  id,create_time,update_time,name,parent_id from tb_goods_category where parent_id = #{parentId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "name", column = "name"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "child", column = "id",
                    many = @Many(select = "com.syedu.mapper.GoodsCategoryMapper.findAllByParentId",
                            fetchType = FetchType.EAGER))
    })
    List<GoodsCategory> findAllByParentId(@Param(value = "parentId") Integer parentId);

    /**
     * 根据id找到单个GoodsCategory
     * @param id
     * @return
     */
    @Select("select * from tb_goods_category where id = #{id}")
    GoodsCategory findAllById(@Param("id") Integer id);
    /**
     * 根据id找到单个GoodsCategory的名字
     */
    @Select("select name from tb_goods_category where id = #{id}")
    String findNameById(@Param("id") Integer id);

}




