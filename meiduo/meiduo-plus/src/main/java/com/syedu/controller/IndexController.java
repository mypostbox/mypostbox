package com.syedu.controller;



import com.syedu.domain.ChannelGroup;
import com.syedu.domain.ContentCategory;
import com.syedu.domain.GoodsCategory;
import com.syedu.mapper.ChannelGroupMapper;
import com.syedu.mapper.ContentCategoryMapper;
import com.syedu.service.ChannelGroupService;
import com.syedu.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * author:Administrator
 * createTime:2023/6/1714:34
 */
@RestController
public class IndexController {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;
    @Autowired
    private ChannelGroupService channelGroupService;

    /**
     * 主页数据
     * @return
     */
    @GetMapping("index")
    public List<ContentCategory> indexService() {
        return this.contentCategoryMapper.findFloor1();
    }

    /**
     * 三级目录数据优化
     * @return
     */
    @GetMapping("menu")
    public List<ChannelGroup> menuService(){
      return this.channelGroupService.indexService();
    }


}
