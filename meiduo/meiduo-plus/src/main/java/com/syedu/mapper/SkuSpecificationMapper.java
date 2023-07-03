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
 * 中间表
 */
public interface SkuSpecificationMapper extends BaseMapper<SkuSpecification> {
    @Select("select * from tb_sku_specification where sku_id = #{skuId}")
    @Results({
            @Result(property = "spuSpecifications" , column = "spec_id",
                    many = @Many(select = "com.syedu.mapper.SpuSpecificationMapper.findAllByIdWithOption")),
    })
    List<SkuSpecification> findAllBySkuId(@Param("skuId") Integer skuId);
    @Select("select * from tb_sku_specification where sku_id = #{skuId}")
    List<SkuSpecification> findAllBySkuIds(@Param("skuId") Integer skuId);



    @Select("select * from tb_sku_specification where option_id = #{optionId}")
    List<SkuSpecification> findAllByOptionId(@Param("optionId") Integer optionId);

}




