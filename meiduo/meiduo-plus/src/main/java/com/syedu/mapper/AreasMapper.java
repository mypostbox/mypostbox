package com.syedu.mapper;

import com.syedu.domain.Areas;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_areas】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.Areas
*/
public interface AreasMapper extends BaseMapper<Areas> {
    @Select("select id,name,parent_id from tb_areas where id = #{id}")
    @Results({
            @Result(property = "id" , column = "id"),
            @Result(property = "name" , column = "name"),
            @Result(property = "child" , column = "id" ,
                    many = @Many(select = "com.syedu.mapper.AreasMapper.findAllChildByParent"))
    })
    Map<String,Object> test(@Param("id") Integer id);

    @Select("select id,name,parent_id from tb_areas where parent_id is null")
    @Results({
            @Result(property = "id" ,column = "id"),
            @Result(property = "child" ,column = "id" ,
                    many = @Many(select = "com.syedu.mapper.AreasMapper.findAllChildByParent"))
    })
    List<Areas> findAllByParentIdIsnull();

    @Select("select id,name,parent_id from tb_areas where parent_id = #{parentId}")
    @Results({
            @Result(property = "id" ,column = "id"),
            @Result(property = "child" ,column = "id" ,
                    many = @Many(select = "com.syedu.mapper.AreasMapper.findAllChildByParent"))
    })
    List<Areas> findAllChildByParent(@Param("parentId") Integer parentId);

    @Select("select * from tb_areas where parent_id is null")
    List<Areas> findAllProvince();
    @Select("select * from tb_areas where parent_id = #{parentId}")
    List<Areas> findChild(@Param("parentId") Integer parentId);
}




