package com.syedu.mapper;

import com.syedu.domain.SpecificationOption;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_specification_option】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.SpecificationOption
*/
public interface SpecificationOptionMapper extends BaseMapper<SpecificationOption> {



    @Select("select id,value,spec_id from tb_specification_option where spec_id = #{specId}")
    List<SpecificationOption> findAllBySpecId(Integer specId);


    //分页查找options数据
    @Select("select id,value,spec_id,(select name from tb_spu_specification where id = spec_id) as spec from tb_specification_option limit #{page},#{pageSize}")
    List<Map<String,Object>> findAllOptionByPage(@Param("page") Integer page,@Param("pageSize") Integer pageSize);


    //根据optionId获取option
    @Select("select id,value,spec_id from tb_specification_option where id = #{optionId}")
    Map<String,Object> getOption(@Param("optionId") Integer optionId);
}




