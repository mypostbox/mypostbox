package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.OrderGoods;
import com.syedu.domain.OrderInfo;
import com.syedu.domain.Sku;
import com.syedu.domain.Users;
import com.syedu.mapper.OrderInfoMapper;
import com.syedu.service.OrderGoodsService;
import com.syedu.mapper.OrderGoodsMapper;
import com.syedu.service.SkuService;
import com.syedu.utils.util.JwtUtils;
import com.syedu.utils.util.OrderNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestConstructor;
import redis.clients.jedis.Jedis;

import java.security.PublicKey;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_order_goods】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class OrderGoodsServiceImpl extends ServiceImpl<OrderGoodsMapper, OrderGoods>
    implements OrderGoodsService{
    @Autowired
    private PublicKey publicKey;
    @Autowired
    private SkuService skuService;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private Jedis jedis;

    @Override
    public Map<String, Object> placeOrder(String token, Map<String, Object> map) throws Exception {
        String orderId = OrderNumberGenerator.generateOrderId();
        Integer totalCount = 0;
        Double totalAmount = 0.0;
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        Integer defaultAddress = Integer.parseInt(map.get("address").toString()) ;
        Integer payMethod = Integer.parseInt(map.get("pay_method").toString()) ;
        this.orderInfoMapper.insert(new OrderInfo().setOrderId(orderId).setAddressId(defaultAddress).setPayMethod(payMethod).setUserId(user.getId()));
        Map<String, Object> allSku = this.skuService.findAllSku(token);
        List<Sku> skus= (List<Sku>) allSku.get("skus");
        Double freight = Double.parseDouble(allSku.get("freight").toString()) ;
        for(Sku sku : skus){
            totalCount += sku.getCount();
            totalAmount += sku.getPrice() * sku.getCount();
            this.orderGoodsMapper.insert(new OrderGoods().setCreateTime(new Date()).setUpdateTime(new Date()).setCount(sku.getCount()).setPrice(sku.getPrice()).setComment("").setScore(0).setIsAnonymous(0).setIsCommented(0).setOrderId(orderId).setSkuId(sku.getId()));
        }
        this.orderInfoMapper.updateById(new OrderInfo().setTotalCount(totalCount).setTotalAmount(totalAmount).setFreight(freight).setStatus(1));
        map.put("order_id",orderId);
        map.put("payment_amount",totalAmount);
        map.put("pay_method",payMethod);
        deleteSkus("cart"+user.getId(),skus);
        return map;
    }

    @Override
    public List<OrderGoods> uncommentGoods(String token, String orderId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
           return this.orderGoodsMapper.findAllByOrderId(orderId);
        }
        return null;
    }

    //删除用户存在redis中的数据
    private void deleteSkus(String key,List<Sku> sku){
        for(Sku s : sku){
            this.jedis.hdel(key,s.getId().toString());
        }
        this.jedis.close();
    }
}




