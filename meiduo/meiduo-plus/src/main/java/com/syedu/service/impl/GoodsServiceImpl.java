package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.Goods;
import com.syedu.service.GoodsService;
import com.syedu.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【goods】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

}




