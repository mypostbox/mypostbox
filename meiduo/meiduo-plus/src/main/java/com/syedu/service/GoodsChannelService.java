package com.syedu.service;

import com.syedu.domain.GoodsChannel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_goods_channel】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface GoodsChannelService extends IService<GoodsChannel> {
    //分页获取goodsChannel的数据
    Map<String,Object> findAllChannelByPage(String token, Integer page, Integer pageSize) throws Exception;
    //添加保存goodsChannel
    Integer saveGoodsChannel(String token,GoodsChannel goodsChannel) throws Exception;
    //根据goodsChannelId找goodsChannel
    Map<String,Object> findGoodsChannel(String token,Integer goodsChannelId) throws Exception;
    //根据goodsChannelId更新goodsChannel
    Integer updateGoodsChannel(String token,Integer goodsChannelId,GoodsChannel goodsChannel) throws Exception;
    //根据goodsChannelId删除goodsChannel
    Integer deleteGoodsChannel(String token,Integer goodsChannelId) throws Exception;
}
