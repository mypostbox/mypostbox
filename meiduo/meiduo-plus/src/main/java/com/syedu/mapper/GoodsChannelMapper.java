package com.syedu.mapper;

import com.syedu.domain.GoodsChannel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_goods_channel】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.GoodsChannel
*/
public interface GoodsChannelMapper extends BaseMapper<GoodsChannel> {

    @Select("select id,create_time,update_time,sequence,url,group_id,category_id from tb_goods_channel where group_id = #{id} order by sequence")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "sequence",column = "sequence"),
            @Result(property = "url",column = "url"),
            @Result(property = "groupId",column = "group_id"),
            @Result(property = "categoryId",column = "category_id"),
            @Result(property = "goodsCategory",column = "category_id",
                    one = @One(select = "com.syedu.mapper.GoodsCategoryMapper.findAllByIdWithChild")),
    })
    List<GoodsChannel> findAllByGroupId(@Param("id") Integer id);


    //分页获取goodsChannel的数据d
    @Select("select \n" +
            "id,\n" +
            "sequence,\n" +
            "group_id,\n" +
            "(select name from tb_channel_group where id = group_id) 'group',\n" +
            "category_id,\n" +
            "(select name from tb_goods_category where id = category_id) 'category',\n" +
            "url\n" +
            "from tb_goods_channel\n" +
            "limit #{page} , #{pageSize}")
    List<Map<String,Object>> findAllChannelByPage(@Param("page") Integer page,@Param("pageSize") Integer pageSize);

    //根据goodsChannelId找goodsChannel
    @Select("select * from tb_goods_channel where id = #{goodsChannelId}")
    Map<String,Object> findGoodsChannel(@Param("goodsChannelId") Integer goodsChannelId);
}




