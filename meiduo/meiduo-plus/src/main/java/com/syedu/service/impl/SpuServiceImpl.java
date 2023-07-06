package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.Spu;
import com.syedu.domain.Users;
import com.syedu.service.SpuService;
import com.syedu.mapper.SpuMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_spu】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu>
    implements SpuService{
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private PublicKey publicKey;
    @Override
    public List<Spu> findSpu(String token) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.spuMapper.selectList(null);
        }
        return null;
    }
    //分页查找spu数据
    @Override
    public Map<String, Object> findAllSpuByPage(String token,Integer page,Integer pageSize) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            List<Map<String, Object>> allSpuByPage = this.spuMapper.findAllSpuByPage((page - 1) * pageSize, pageSize);
            map.put("lists",allSpuByPage);
            map.put("page",page);
            Long total = this.spuMapper.selectCount(null);
            map.put("pages",Math.ceil(Double.parseDouble(Long.toString(total))/Double.parseDouble(pageSize.toString())));
            return map;
        }
        return null;
    }
    //根据id获取spu信息
    @Override
    public Map<String, Object> findSpuById(String token, Integer spuId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.spuMapper.findSpuObjectById(spuId);
        }
        return null;
    }
    //保存spu
    @Override
    public Integer saveSpu(String token, Spu spu) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
           return this.spuMapper.insert(spu);
        }
        return null;
    }
    //修改spu
    @Override
    public Integer updateSpu(String token, Integer spuId, Spu spu) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            spu.setId(spuId);
            return this.spuMapper.updateById(spu);
        }
        return null;
    }
    //删除spu
    @Override
    public Integer deleteSpu(String token, Integer spuId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.spuMapper.deleteById(spuId);
        }
        return null;
    }
}




