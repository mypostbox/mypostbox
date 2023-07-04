package com.syedu.service;

import com.syedu.domain.SpecificationOption;
import com.baomidou.mybatisplus.extension.service.IService;
import com.syedu.domain.SpuSpecification;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_specification_option】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface SpecificationOptionService extends IService<SpecificationOption> {
    //分页查找options数据
    Map<String,Object> findAllOptionByPage(String token, Integer page, Integer pageSize) throws Exception;
    //添加保存options
    Integer saveOption(String token, SpecificationOption specificationOption) throws Exception;
    //根据optionId获取option
    Map<String,Object> getOption(String token,Integer optionId) throws Exception;
    //根据optionsId修改option
    Integer updateOption(String token,Integer optionId,SpecificationOption specificationOption) throws Exception;
    //根据optionsId删除option
    Integer deleteOption(String token,Integer optionId) throws Exception;
}
