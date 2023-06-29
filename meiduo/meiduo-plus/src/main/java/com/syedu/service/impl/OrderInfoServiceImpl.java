package com.syedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.OrderInfo;
import com.syedu.domain.Users;
import com.syedu.service.OrderInfoService;
import com.syedu.mapper.OrderInfoMapper;
import com.syedu.utils.keyword.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.HashMap;
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
    public Map<String, Object> findAllOrderInfoByUserId(String token, Map<String, Object> map) throws Exception {
        Map<String,Object> maps = new HashMap<>();
        Page<OrderInfo> page = new Page<>(Integer.parseInt(map.get("page").toString()),Integer.parseInt(map.get("page_size").toString()));
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        LambdaQueryWrapper<OrderInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderInfo::getUserId,user.getId());
        this.orderInfoMapper.selectPage(page,wrapper);

        maps.put("count",page.getTotal());
        maps.put("results",page.getRecords());
        return map;
    }
}




