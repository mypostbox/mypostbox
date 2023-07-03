package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.GoodsVisit;
import com.syedu.domain.Users;
import com.syedu.service.GoodsVisitService;
import com.syedu.mapper.GoodsVisitMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_goods_visit】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class GoodsVisitServiceImpl extends ServiceImpl<GoodsVisitMapper, GoodsVisit>
    implements GoodsVisitService{
    @Autowired
    private GoodsVisitMapper goodsVisitMapper;
    @Autowired
    private PublicKey publicKey;

    @Override
    public List<Map<String, Object>> findAllGoodsDayViews(String token) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
           return this.goodsVisitMapper.findAllGoodsDayViews();
        }
        return null;
    }
}




