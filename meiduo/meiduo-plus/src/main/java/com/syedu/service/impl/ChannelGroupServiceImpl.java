package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.ChannelGroup;
import com.syedu.domain.GoodsCategory;
import com.syedu.domain.GoodsChannel;
import com.syedu.service.ChannelGroupService;
import com.syedu.mapper.ChannelGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* @author Administrator
* @description 针对表【tb_channel_group】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class ChannelGroupServiceImpl extends ServiceImpl<ChannelGroupMapper, ChannelGroup>
    implements ChannelGroupService{
    @Autowired
    private ChannelGroupMapper channelGroupMapper;

    @Override
    public List<ChannelGroup> indexService() {
        List<ChannelGroup> allBy = this.channelGroupMapper.findAllBy();
        for(ChannelGroup channelGroup : allBy) {//手机数码
            List<GoodsChannel> goodsChannels = channelGroup.getGoodsChannels();
            for (int i = 1; i < goodsChannels.size(); i++) {
                GoodsCategory goodsCategory0 = goodsChannels.get(0).getGoodsCategory();
                GoodsCategory goodsCategory = goodsChannels.get(i).getGoodsCategory();
                goodsCategory0.setChild(Stream.concat(goodsCategory0.getChild().stream(), goodsCategory.getChild().stream()).collect(Collectors.toList()));
                goodsCategory.setChild(null);//清除掉多余的数据
            }
        }
        return allBy;
    }
}




