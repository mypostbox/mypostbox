package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.SkuImage;
import com.syedu.domain.Users;
import com.syedu.service.SkuImageService;
import com.syedu.mapper.SkuImageMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

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
}




