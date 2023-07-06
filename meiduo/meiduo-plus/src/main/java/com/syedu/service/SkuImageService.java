package com.syedu.service;

import com.syedu.domain.SkuImage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_sku_image】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface SkuImageService extends IService<SkuImage> {
    Map<String,Object> findAllSkuImageByPage(String token,Integer page,Integer pageSize) throws Exception;

    //保存图片(spu)
    Map<String,Object> saveImage(String token, MultipartFile image) throws Exception;

    //保存图片(sku)
    Integer saveImage(String token,Integer skuId,MultipartFile image) throws Exception;
    //根据id获取图片详细信息
    SkuImage findImage(String token,Integer imagesId) throws Exception;
    //修改照片
    Integer updateImage(String token,Integer imagesId,Integer skuId,MultipartFile image) throws Exception;
    //删除照片
    Integer deleteImage(String token,Integer imagesId) throws Exception;
}
