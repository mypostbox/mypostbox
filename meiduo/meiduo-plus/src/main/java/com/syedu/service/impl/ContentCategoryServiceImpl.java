package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.ContentCategory;
import com.syedu.service.ContentCategoryService;
import com.syedu.mapper.ContentCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_content_category】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class ContentCategoryServiceImpl extends ServiceImpl<ContentCategoryMapper, ContentCategory>
    implements ContentCategoryService{

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Override
    public Map<String, Object> indexService() {
        Map<String,Object> map = new HashMap<>();
        List<ContentCategory> list = new ArrayList<>();
        List<ContentCategory> floor1 = this.contentCategoryMapper.findFloor1();
        for(ContentCategory contentCategory : floor1){
            switch(contentCategory.getName()){
                case "轮播图" : map.put("slide",contentCategory); break;
                case "快讯" : map.put("news",contentCategory); break;
                case "页头广告" : map.put("headNews",contentCategory); break;
                default:list.add(threeSum(contentCategory));break;
            }
        }
        map.put("model",list);
        return map;
    }
    /**
     * 加工contentCategory数据,将tab层用list提升一个等级
     * @param contentCategory
     * @return
     */
    private ContentCategory threeSum(ContentCategory contentCategory){
        List<ContentCategory> child = contentCategory.getChild();
        ContentCategory bbb = new ContentCategory();
        List<ContentCategory> contentCategoryChild = new ArrayList<>();
        List<ContentCategory> childb = new ArrayList<>();
        for(ContentCategory contentCategory1 : child){
            String substring = contentCategory1.getName().substring(2);
            switch(substring){
                case "Logo" : contentCategoryChild.add(contentCategory1); break;
                case "频道" : contentCategoryChild.add(contentCategory1); break;
                case "标签" : contentCategoryChild.add(contentCategory1) ; break;
                default:childb.add(contentCategory1); break;
            }
        }
        bbb.setChild(childb);
        bbb.setId(childb.get(0).getId());
        contentCategoryChild.add(bbb);
        contentCategory.setChild(contentCategoryChild);
        return contentCategory;
    }
}




