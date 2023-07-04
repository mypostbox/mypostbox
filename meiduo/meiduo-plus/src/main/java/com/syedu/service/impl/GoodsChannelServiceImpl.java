package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.GoodsChannel;
import com.syedu.domain.Users;
import com.syedu.service.GoodsChannelService;
import com.syedu.mapper.GoodsChannelMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_goods_channel】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class GoodsChannelServiceImpl extends ServiceImpl<GoodsChannelMapper, GoodsChannel>
    implements GoodsChannelService{
    @Autowired
    private PublicKey publicKey;
    @Autowired
    private GoodsChannelMapper goodsChannelMapper;

    //分页获取goodsChannel的数据
    @Override
    public Map<String, Object> findAllChannelByPage(String token, Integer page, Integer pageSize) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            map.put("lists",this.goodsChannelMapper.findAllChannelByPage((page-1)*pageSize,pageSize));
            map.put("page",page);
            Long total = this.goodsChannelMapper.selectCount(null);
            map.put("pages",Math.ceil(Double.parseDouble(Long.toString(total))/Double.parseDouble(pageSize.toString())));
            return map;
        }
        return null;
    }
    //添加保存goodsChannel
    @Override
    public Integer saveGoodsChannel(String token, GoodsChannel goodsChannel) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.goodsChannelMapper.insert(goodsChannel);
        }
        return null;
    }
    //根据goodsChannelId找goodsChannel
    @Override
    public Map<String, Object> findGoodsChannel(String token, Integer goodsChannelId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.goodsChannelMapper.findGoodsChannel(goodsChannelId);
        }
        return null;
    }
    //根据goodsChannelId更新goodsChannel
    @Override
    public Integer updateGoodsChannel(String token, Integer goodsChannelId, GoodsChannel goodsChannel) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            goodsChannel.setId(goodsChannelId);
            return this.goodsChannelMapper.updateById(goodsChannel);
        }
        return null;
    }
    //根据goodsChannelId删除goodsChannel
    @Override
    public Integer deleteGoodsChannel(String token, Integer goodsChannelId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            this.goodsChannelMapper.deleteById(goodsChannelId);
        }
        return null;
    }
}




