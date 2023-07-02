package com.syedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.OrderInfo;
import com.syedu.domain.Users;
import com.syedu.service.OrderInfoService;
import com.syedu.mapper.OrderInfoMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_order_info】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
    implements OrderInfoService{
    @Autowired
    private PublicKey publicKey;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Override
    public Map<String, Object> findAllOrderInfoByUserId(String token, Integer page,Integer pageSize) throws Exception {
        Map<String,Object> maps = new HashMap<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        List<OrderInfo> orderInfos = this.orderInfoMapper.findAllByUserIdWithOrderGoodsAndSku((page - 1) * pageSize, pageSize, user.getId());
        maps.put("results",orderInfos);
        LambdaQueryWrapper<OrderInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderInfo::getUserId,user.getId());
        maps.put("count",this.orderInfoMapper.selectCount(wrapper));
        return maps;
    }
}




