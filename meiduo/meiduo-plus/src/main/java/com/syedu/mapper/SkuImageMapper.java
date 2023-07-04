package com.syedu.mapper;

import com.syedu.domain.SkuImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_sku_image】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.SkuImage
*/
public interface SkuImageMapper extends BaseMapper<SkuImage> {

    @Select("select * from tb_sku_image where sku_id = #{skuId}")
    List<SkuImage> findAllBySkuId(@Param("skuId") Integer skuId);
    @Select("select id,image,sku_id 'sku' from tb_sku_image limit #{page} , #{pageSize}")
    List<Map<String,Object>> findAllSkuImageByPage(@Param("page") Integer page,@Param("pageSize") Integer pageSize);
}




