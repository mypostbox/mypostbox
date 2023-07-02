package com.syedu.mapper;

import com.syedu.domain.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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
    /**
     * 获取今日的订单的用户id
     * @return
     */
    @Select("select distinct(user_id) from tb_order_info where datediff(date(create_time),curdate()) = 0 group by user_id")
    @Results({
            @Result(property = "count" , column = "user_id")
    })
    Map<String,Object> findAllOrderUser();
}




