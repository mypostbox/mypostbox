package com.syedu.service.impl;

import com.alipay.api.domain.Image;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.Sku;
import com.syedu.domain.SkuImage;
import com.syedu.domain.Users;
import com.syedu.service.SkuImageService;
import com.syedu.mapper.SkuImageMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
* @author Administrator
* @description 针对表【tb_sku_image】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class SkuImageServiceImpl extends ServiceImpl<SkuImageMapper, SkuImage>
    implements SkuImageService{
    @Autowired
    private PublicKey publicKey;
    @Autowired
    private SkuImageMapper skuImageMapper;

    private static final Properties pro;

    static{
        pro = new Properties();
        try {
            pro.load(SkuImageServiceImpl.class.getClassLoader().getResourceAsStream("nginx.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String createImage(MultipartFile multipartFile){
        String path = pro.getProperty("nginxPath");
        String serverPath = pro.getProperty("nginxService");
        String filename = multipartFile.getOriginalFilename();
        try {
            multipartFile.transferTo(new File(path + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverPath + filename;
    }

    @Override
    public Map<String, Object> findAllSkuImageByPage(String token, Integer page, Integer pageSize) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            map.put("lists",this.skuImageMapper.findAllSkuImageByPage((page-1)*pageSize,pageSize));
            map.put("page",page);
            Long total = this.skuImageMapper.selectCount(null);
            map.put("pages",Math.ceil(Double.parseDouble(Long.toString(total))/Double.parseDouble(pageSize.toString())));
            return map;
        }
        return null;
    }
    //保存图片(spu)
    @Override
    public Map<String, Object> saveImage(String token, MultipartFile image) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            Map<String,Object> map = new HashMap<>();
            String path = pro.getProperty("nginxPath");
            String serverPath = pro.getProperty("nginxService");
            String filename = image.getOriginalFilename();
            image.transferTo(new File(path,filename));
            map.put("img_url",serverPath+filename);
            return map;
        }
        return null;
    }


    //保存图片(Sku)
    @Override
    @Transactional
    public Integer saveImage(String token, Integer skuId,MultipartFile image) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            String image1 = SkuImageServiceImpl.createImage(image);
            this.skuImageMapper.insert(new SkuImage().setSkuId(skuId).setImage(image1));
            return 200;
        }
        return null;
    }
    //根据id获取图片详细信息
    @Override
    public SkuImage findImage(String token, Integer imagesId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.skuImageMapper.selectById(imagesId);
        }
        return null;
    }
    //修改照片
    @Override
    @Transactional
    public Integer updateImage(String token, Integer imagesId, Integer skuId, MultipartFile image) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            String image1 = SkuImageServiceImpl.createImage(image);
            this.skuImageMapper.updateById(new SkuImage().setImage(image1).setId(imagesId).setSkuId(skuId));
            return 200;
        }
        return null;
    }
    //删除照片
    @Override
    public Integer deleteImage(String token, Integer imagesId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.skuImageMapper.deleteById(imagesId);
        }
        return null;
    }
}




