package com.syedu.mapper;

import com.syedu.domain.SkuSpecification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【tb_sku_specification】的数据库操作Mapper
 * @createDate 2023-06-13 08:45:37
 * @Entity com.syedu.domain.SkuSpecification
 */
public interface SkuSpecificationMapper extends BaseMapper<SkuSpecification> {

    @Select("select id,create_time,update_time,option_id,sku_id,spec_id,spec_name from tb_sku_specification where sku_id = #{skuId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "optionId", column = "option_id"),
            @Result(property = "skuId", column = "sku_id"),
            @Result(property = "specId", column = "spec_id"),
            @Result(property = "specName" ,column = "spec_name"),
            @Result(property = "specificationOptions", column = "spec_id",
                    many = @Many(select = "com.syedu.mapper.SpecificationOptionMapper.findAllBySpecId")),
    })
    List<SkuSpecification> findAllBySkuIdWithOption(@Param("skuId") Integer skuId);
}




