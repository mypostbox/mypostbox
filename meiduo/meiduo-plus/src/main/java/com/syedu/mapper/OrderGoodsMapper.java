package com.syedu.mapper;

import com.syedu.domain.OrderGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_order_goods】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.OrderGoods
*/
public interface OrderGoodsMapper extends BaseMapper<OrderGoods> {

    @Select("select * from tb_order_goods where order_id = #{orderId}")
    @Results({
            @Result(property = "sku" , column = "sku_id" ,
                    one = @One(select = "com.syedu.mapper.SkuMapper.findAllByIdSku"))
    })
    List<OrderGoods> findAllByOrderId(@Param("orderId") String orderId);
}




