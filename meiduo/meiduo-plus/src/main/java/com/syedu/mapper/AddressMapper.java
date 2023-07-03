package com.syedu.mapper;

import com.syedu.domain.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_address】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.Address
*/
public interface AddressMapper extends BaseMapper<Address> {
    /**
     * 根据用户id查出用户所有的地址
     * @param userId
     * @return
     */
    @Select("select * from tb_address where user_id = #{userId}")
    @Results({
            @Result(property = "city" , column = "city_id" ,
                    one = @One(select = "com.syedu.mapper.AreasMapper.findNameById")),
            @Result(property = "district" , column = "district_id" ,
                    one = @One(select = "com.syedu.mapper.AreasMapper.findNameById")),
            @Result(property = "province" , column = "province_id" ,
                    one = @One(select = "com.syedu.mapper.AreasMapper.findNameById")),

    })
    List<Address> findAllAddressByUserId(@Param("userId") Integer userId);
}




