package com.syedu.mapper;

import com.syedu.domain.OrderGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【tb_order_goods】的数据库操作Mapper
 * @createDate 2023-06-13 08:45:37
 * @Entity com.syedu.domain.OrderGoods
 */
public interface OrderGoodsMapper extends BaseMapper<OrderGoods> {

    @Select("select * from tb_order_goods where order_id = #{orderId}")
    @Results({
            @Result(property = "skuId", column = "sku_id"),
            @Result(property = "sku", column = "sku_id",
                    one = @One(select = "com.syedu.mapper.SkuMapper.findAllByIdSku"))
    })
    List<OrderGoods> findAllByOrderId(@Param("orderId") String orderId);


    //根据订单号获取商品
    @Select("select  tb_sku.id,tb_sku.name,tb_sku.default_image,tb_order_goods.price,tb_order_goods.count\n" +
            "from tb_order_goods \n" +
            "join tb_sku on tb_sku.id = tb_order_goods.sku_id\n" +
            "where tb_order_goods.order_id = #{orderId}")
    List<Map<String,Object>> findSkuByOrderId(@Param("orderId") String orderId);
}




