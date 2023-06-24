package com.syedu.service;

import com.syedu.domain.ContentCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.syedu.mapper.ContentCategoryMapper;


import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_content_category】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface ContentCategoryService extends IService<ContentCategory> {


    List<ContentCategory> indexService();

}
