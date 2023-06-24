package com.syedu.controller;



import com.syedu.domain.ChannelGroup;
import com.syedu.domain.ContentCategory;
import com.syedu.domain.GoodsCategory;
import com.syedu.mapper.ChannelGroupMapper;
import com.syedu.mapper.ContentCategoryMapper;
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
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private ChannelGroupMapper channelGroupMapper;

    /**
     * 主页数据
     * @return
     */
    @GetMapping("index")
    public List<ContentCategory> indexService() {
        return this.contentCategoryMapper.findFloor1();
    }

    /**
     * 三级目录数据
     * @return
     */
    @GetMapping("menu")
    public List<List<GoodsCategory>> menuService() {
        return this.goodsCategoryService.menuService();
    }

    @GetMapping("test")
    public List<ChannelGroup> menuTest(){
        return this.channelGroupMapper.findAllBy();
    }


}
