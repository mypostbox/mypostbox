package com.syedu.service;

import com.syedu.domain.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_brand】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface BrandService extends IService<Brand> {
    //分页获取brand的数据
    Map<String,Object> findAllBrandByPage(String token,Integer page,Integer pageSize) throws Exception;
    //获取所有的brand
    List<Brand> findAllBrand(String token) throws Exception;

    //添加品牌
    Integer saveBrand(String token, String name, String first_letter, MultipartFile logo) throws Exception;
    //获取品牌信息
    Map<String,Object> findBrand(String token,Integer brandId) throws Exception;
    //修改品牌信息
    Integer updateBrand(String token,Integer brandId,String name,String first_letter,MultipartFile logo) throws Exception;
    //删除品牌
    Integer deleteBrand(String token,Integer brandId) throws Exception;
}
