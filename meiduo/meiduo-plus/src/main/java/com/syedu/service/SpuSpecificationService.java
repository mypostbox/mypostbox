package com.syedu.service;

import com.syedu.domain.SpuSpecification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_spu_specification】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface SpuSpecificationService extends IService<SpuSpecification> {
    //根据spuId获取SpuSpecification(包含options)
    List<Map<String,Object>> findSpuSpecificationBySpuId(String token, Integer spuId) throws Exception;

    //分页查找specs数据
    Map<String,Object> findAllSpecsByPage(String token,Integer page,Integer pageSize) throws Exception;

    //根据specsId获取specs
    Map<String,Object> findSpuSpecById(String token,Integer specId) throws Exception;
    //添加保存数据SpuSpec
    Integer saveSpuSpec(String token,SpuSpecification spuSpecification) throws Exception;
    //根据specId修改spec
    Integer updateSpuSpec(String token,Integer specId,SpuSpecification spuSpecification) throws Exception;
    //根据specId删除spec
    Integer deleteSpuSpec(String token,Integer specId) throws Exception;

    //获取options的类型
    List<SpuSpecification> findAllSpuSpec(String token) throws Exception;
}
