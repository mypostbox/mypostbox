package com.syedu.mapper;

import com.syedu.domain.SpuSpecification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_spu_specification】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.SpuSpecification
*/
public interface SpuSpecificationMapper extends BaseMapper<SpuSpecification> {
    //根据自己的id获取spuSpecification并填入SpecificationOption
    @Select("select * from tb_spu_specification where id = #{id}")
    @Results({
            @Result(property = "specificationOptions" , column = "id",
                    many = @Many(select = "com.syedu.mapper.SpecificationOptionMapper.findAllBySpecId"))
    })
    SpuSpecification findAllByIdWithOption(@Param("id") Integer id);

    @Select("select id,name,spu_id from tb_spu_specification where spu_id=#{spuId}")
    @Results({
            @Result(property = "id" , column = "id"),
            @Result(property = "name" , column = "name"),
            @Result(property = "spuId" , column = "spu_id"),
            @Result(property = "options" , column = "id" ,
                    many = @Many(select = "com.syedu.mapper.SpecificationOptionMapper.findAllBySpecId"))
    })
    List<Map<String,Object>> findSpuSpecificationBySpuId(@Param("spuId") Integer spuId);

    //分页查找specs数据
    @Select("select id,name,spu_id, (select name from tb_spu where id = spu_id) as spu from tb_spu_specification limit #{page} , #{pageSize}")
    @Results({
            @Result(property = "spu_id" , column = "spu_id"),
            @Result(property = "spu" , column = "spu")
    })
    List<Map<String,Object>> findAllSpecsByPage(@Param("page") Integer page,@Param("pageSize") Integer pageSize);
    //根据specsId获取specs
    @Select("select name,spu_id from tb_spu_specification where id = #{specId}")
    Map<String,Object> findSpuSpecById(@Param("specId") Integer specId);

}




