package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.Content;
import com.syedu.service.ContentService;
import com.syedu.mapper.ContentMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【tb_content】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content>
    implements ContentService{

}




