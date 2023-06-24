package com.syedu.mapper;

import com.syedu.domain.TbContentCategory;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/6/813:32
 */
public interface TbContentCategoryMapper {

    @Select("select * from tb_content_category")
    List<TbContentCategory> getAllContentCategory();
}
