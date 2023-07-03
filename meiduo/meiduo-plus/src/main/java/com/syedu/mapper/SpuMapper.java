package com.syedu.mapper;

import com.syedu.domain.Spu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【tb_spu】的数据库操作Mapper
 * @createDate 2023-06-13 08:45:37
 * @Entity com.syedu.domain.Spu
 */
public interface SpuMapper extends BaseMapper<Spu> {


    @Select("select * from tb_spu where id = #{id}")
    @Results({
            @Result(property = "brand", column = "brand_id",
                    one = @One(select = "com.syedu.mapper.BrandMapper.findAllById")),
            @Result(property = "category1", column = "category1_id",
                    one = @One(select = "com.syedu.mapper.GoodsCategoryMapper.findAllById")),
            @Result(property = "category2", column = "category2_id",
                    one = @One(select = "com.syedu.mapper.GoodsCategoryMapper.findAllById")),
            @Result(property = "category3", column = "category3_id",
                    one = @One(select = "com.syedu.mapper.GoodsCategoryMapper.findAllById")),
    })
    Spu findAllById(@Param("id") Integer id);
    @Select("select * from tb_spu limit #{page} ,#{pageSize}")
    List<Map<String,Object>> findAllSpuByPage(@Param("page") Integer page,@Param("pageSize") Integer pageSize);

    @Select("select * from tb_spu where id = #{id}")
    Spu findSpuById(@Param("id") Integer id);
}




