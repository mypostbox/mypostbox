package com.syedu.mapper;

import com.syedu.domain.ChannelGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_channel_group】的数据库操作Mapper
* @createDate 2023-06-13 08:45:37
* @Entity com.syedu.domain.ChannelGroup
*/
public interface ChannelGroupMapper extends BaseMapper<ChannelGroup> {

    @Select("select id,create_time,update_time,name from tb_channel_group")
    @Results({
            @Result(property = "id" ,column = "id"),
            @Result(property = "createTime" ,column = "create_time"),
            @Result(property = "updateTime" ,column = "update_time"),
            @Result(property = "name" ,column = "name"),
            @Result(property = "goodsChannels" ,column = "id",
                    many = @Many(select = "com.syedu.mapper.GoodsChannelMapper.findAllByGroupId")),
    })
   List<ChannelGroup> findAllBy();
}




