package com.syedu.mapper;

import com.syedu.domain.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_brand】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.Brand
*/
public interface BrandMapper extends BaseMapper<Brand> {
    @Select("select * from tb_brand where id = #{id}")
    List<Brand> findAllById(@Param("id") Integer id);
}




