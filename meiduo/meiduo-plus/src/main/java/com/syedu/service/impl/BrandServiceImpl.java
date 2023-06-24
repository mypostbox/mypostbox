package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.Brand;
import com.syedu.service.BrandService;
import com.syedu.mapper.BrandMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【tb_brand】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand>
    implements BrandService{

}




