package com.syedu.service;

import com.syedu.domain.SkuImage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_sku_image】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface SkuImageService extends IService<SkuImage> {
    Map<String,Object> findAllSkuImageByPage(String token,Integer page,Integer pageSize) throws Exception;
}
