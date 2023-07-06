package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.Brand;
import com.syedu.domain.Users;
import com.syedu.service.BrandService;
import com.syedu.mapper.BrandMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_brand】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand>
    implements BrandService{
    @Autowired
    private PublicKey publicKey;
    @Autowired
    private BrandMapper brandMapper;
    //分页获取brand的数据
    @Override
    public Map<String, Object> findAllBrandByPage(String token,Integer page,Integer pageSize) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            map.put("lists",this.brandMapper.findAllBrandByPage((page-1)*pageSize,pageSize));
            map.put("page",page);
            Long total = this.brandMapper.selectCount(null);
            map.put("pages",Math.ceil(Double.parseDouble(Long.toString(total))/Double.parseDouble(pageSize.toString())));
            return map;
        }
        return null;
    }
    //获取所有的brand
    @Override
    public List<Brand> findAllBrand(String token) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.brandMapper.selectList(null);
        }
        return null;
    }
    //添加品牌
    @Override
    @Transactional
    public Integer saveBrand(String token, String name, String first_letter, MultipartFile logo) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            String logoString = SkuImageServiceImpl.createImage(logo);
            this.brandMapper.insert(new Brand().setName(name).setLogo(logoString).setFirstLetter(first_letter));
            return 200;
        }
        return null;
    }
    //获取品牌信息
    @Override
    public Map<String, Object> findBrand(String token, Integer brandId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.brandMapper.findBrand(brandId);
        }
        return null;
    }
    //修改品牌信息
    @Override
    @Transactional
    public Integer updateBrand(String token, Integer brandId, String name, String first_letter, MultipartFile logo) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            String image = SkuImageServiceImpl.createImage(logo);
            this.brandMapper.updateById(new Brand().setId(brandId).setName(name).setFirstLetter(first_letter).setLogo(image));
            return 200;
        }
        return null;
    }
    //删除品牌
    @Override
    public Integer deleteBrand(String token, Integer brandId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
           return this.brandMapper.deleteById(brandId);
        }
        return null;
    }
}




