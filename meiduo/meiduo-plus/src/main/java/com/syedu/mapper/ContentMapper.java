package com.syedu.mapper;

import com.syedu.domain.Content;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【tb_content】的数据库操作Mapper
 * @createDate 2023-06-13 08:45:37
 * @Entity com.syedu.domain.Content
 */
public interface ContentMapper extends BaseMapper<Content> {

    @Select("select * from tb_content where category_id = #{categoryId} and status = 1")
    List<Content> findContentByCategoryId(@Param(value = "categoryId") Integer categoryId);


}




