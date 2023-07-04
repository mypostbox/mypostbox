package com.syedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.SpuSpecification;
import com.syedu.domain.Users;
import com.syedu.service.SpuSpecificationService;
import com.syedu.mapper.SpuSpecificationMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_spu_specification】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class SpuSpecificationServiceImpl extends ServiceImpl<SpuSpecificationMapper, SpuSpecification>
    implements SpuSpecificationService{
    @Autowired
    private PublicKey publicKey;
    @Autowired
    private SpuSpecificationMapper spuSpecificationMapper;
    //根据spuId获取SpuSpecification(包含options)
    @Override
    public List<Map<String,Object>> findSpuSpecificationBySpuId(String token, Integer spuId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
           return this.spuSpecificationMapper.findSpuSpecificationBySpuId(spuId);
        }
        return null;
    }
    //分页查找specs数据
    @Override
    public Map<String, Object> findAllSpecsByPage(String token, Integer page, Integer pageSize) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            List<Map<String, Object>> allSpecsByPage = this.spuSpecificationMapper.findAllSpecsByPage((page - 1) * pageSize, pageSize);
            map.put("lists",allSpecsByPage);
            map.put("page",page);
            Long total = this.spuSpecificationMapper.selectCount(null);
            map.put("pages",Math.ceil(Double.parseDouble(Long.toString(total))/Double.parseDouble(pageSize.toString())));
            return map;
        }
        return null;
    }
    //根据specsId获取specs
    @Override
    public Map<String, Object> findSpuSpecById(String token, Integer specId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.spuSpecificationMapper.findSpuSpecById(specId);
        }
        return null;
    }
    //添加保存数据SpuSpec
    @Override
    public Integer saveSpuSpec(String token,SpuSpecification spuSpecification) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
           return this.spuSpecificationMapper.insert(spuSpecification);
        }
        return null;
    }
    //根据specId修改spec
    @Override
    public Integer updateSpuSpec(String token, Integer specId, SpuSpecification spuSpecification) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            spuSpecification.setId(specId);
           return this.spuSpecificationMapper.updateById(spuSpecification);
        }
        return null;
    }
    //根据specId删除spec
    @Override
    public Integer deleteSpuSpec(String token, Integer specId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.spuSpecificationMapper.deleteById(specId);
        }
        return null;
    }
    //获取options的类型
    @Override
    public List<SpuSpecification> findAllSpuSpec(String token) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
          return  this.spuSpecificationMapper.selectList(null);
        }
        return null;
    }

}




