package com.syedu.mapper;

import com.syedu.domain.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_order_info】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.OrderInfo
*/
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    @Select("select * from tb_order_info where user_id = #{userId} limit #{page} , #{pageSize}")
    @Results({
            @Result(property = "orderId" , column = "order_id"),
            @Result(property = "orderGoods" , column = "order_id",
                    many = @Many(select = "com.syedu.mapper.OrderGoodsMapper.findAllByOrderId"))
    })
    List<OrderInfo> findAllByUserIdWithOrderGoodsAndSku(@Param("page") Integer page, @Param("pageSize") Integer pageSize,@Param("userId") Integer userId);
}




