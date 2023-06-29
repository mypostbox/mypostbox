package com.syedu.controller;



import com.syedu.domain.ChannelGroup;
import com.syedu.domain.ContentCategory;
import com.syedu.mapper.ContentCategoryMapper;
import com.syedu.service.ChannelGroupService;
import com.syedu.service.ContentCategoryService;
import com.syedu.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * author:Administrator
 * createTime:2023/6/1714:34
 */
@RestController
public class IndexController {

    @Autowired
    private ChannelGroupService channelGroupService;
    @Autowired
    private ContentCategoryService contentCategoryService;


    /**
     * 主数据优化
     */
    @GetMapping("index")
    public Map<String,Object> indexService2(){
        Map<String, Object> map = this.contentCategoryService.indexService();
        map.put("menu",this.channelGroupService.indexService());
        return map;
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
