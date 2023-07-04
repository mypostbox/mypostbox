package com.syedu.service;

import com.syedu.domain.ChannelGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_channel_group】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface ChannelGroupService extends IService<ChannelGroup> {

    List<ChannelGroup> indexService();

    //获取goodsChannel的group的所有类型
    List<ChannelGroup> findAllChannelGroup(String token) throws Exception;
}
