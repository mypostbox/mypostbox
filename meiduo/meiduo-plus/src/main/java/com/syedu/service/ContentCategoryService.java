package com.syedu.service;

import com.syedu.domain.ContentCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.syedu.mapper.ContentCategoryMapper;


import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_content_category】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface ContentCategoryService extends IService<ContentCategory> {

    Map<String,Object> indexService();
}
