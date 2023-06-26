package com.syedu.mapper;

import com.syedu.domain.SpecificationOption;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_specification_option】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.SpecificationOption
*/
public interface SpecificationOptionMapper extends BaseMapper<SpecificationOption> {



    @Select("select * from tb_specification_option where spec_id = #{specId}")
    List<SpecificationOption> findAllBySpecId(Integer specId);
}




