package com.syedu.mapper;

import com.syedu.domain.Content;
import com.syedu.domain.ContentCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【tb_content_category】的数据库操作Mapper
 * @createDate 2023-06-13 08:45:37
 * @Entity com.syedu.domain.ContentCategory
 */
public interface ContentCategoryMapper extends BaseMapper<ContentCategory> {


    @Select("select  id,create_time,update_time,name,keyword from tb_content_category where keyword like '%title' or keyword not like '%f%'")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "name", column = "name"),
            @Result(property = "keyword", column = "keyword"),
            @Result(property = "child", column = "keyword",
                    many = @Many(select = "com.syedu.mapper.ContentCategoryMapper.findFloor2",
                            fetchType = FetchType.EAGER)),
            @Result(property = "contents", column = "id",
                    many = @Many(select = "com.syedu.mapper.ContentMapper.findContentByCategoryId",
                            fetchType = FetchType.EAGER))
    })
    List<ContentCategory> findFloor1();


    @Select("SELECT id,create_time,update_time,name,keyword from tb_content_category where keyword like CONCAT(SUBSTR(#{keyword},1,8),'%') and keyword <> #{keyword}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "name", column = "name"),
            @Result(property = "keyword", column = "keyword"),
            @Result(property = "contents", column = "id",
                    many = @Many(select = "com.syedu.mapper.ContentMapper.findContentByCategoryId",
                            fetchType = FetchType.EAGER))
    })
    List<ContentCategory> findFloor2(@Param(value = "keyword") String keyword);


}




