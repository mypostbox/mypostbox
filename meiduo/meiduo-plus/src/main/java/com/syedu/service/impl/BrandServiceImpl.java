package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.Brand;
import com.syedu.domain.Users;
import com.syedu.service.BrandService;
import com.syedu.mapper.BrandMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.HashMap;
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
}




