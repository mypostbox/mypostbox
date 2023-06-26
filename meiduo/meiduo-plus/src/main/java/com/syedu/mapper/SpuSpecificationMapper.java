package com.syedu.mapper;

import com.syedu.domain.SpuSpecification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_spu_specification】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.SpuSpecification
*/
public interface SpuSpecificationMapper extends BaseMapper<SpuSpecification> {

    @Select("select * from tb_spu_specification where id = #{id}")
    @Results({
            @Result(property = "specificationOptions" , column = "id",
                    many = @Many(select = "com.syedu.mapper.SpecificationOptionMapper.findAllBySpecId"))
    })
    SpuSpecification findAllByIdWithOption(@Param("id") Integer id);
}




