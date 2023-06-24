package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.ContentCategory;
import com.syedu.service.ContentCategoryService;
import com.syedu.mapper.ContentCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<ContentCategory> indexService() {
        return this.contentCategoryMapper.findFloor1();
    }
}




