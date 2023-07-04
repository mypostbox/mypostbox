package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.AuthGroup;
import com.syedu.domain.Users;
import com.syedu.service.AuthGroupService;
import com.syedu.mapper.AuthGroupMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【auth_group】的数据库操作Service实现
* @createDate 2023-06-13 08:45:36
*/
@Service
public class AuthGroupServiceImpl extends ServiceImpl<AuthGroupMapper, AuthGroup>
    implements AuthGroupService{
    @Autowired
    private PublicKey publicKey;
    @Autowired
    private AuthGroupMapper authGroupMapper;
    //分页获取（auth_group）中的数据
    @Override
    public Map<String, Object> findAllGroupByPage(String token, Integer page, Integer pageSize) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            map.put("lists",this.authGroupMapper.findAllGroupByPage((page-1)*pageSize,pageSize));
            map.put("page",page);
            Long total = this.authGroupMapper.selectCount(null);
            map.put("pages",Math.ceil(Double.parseDouble(Long.toString(total))/Double.parseDouble(pageSize.toString())));
            return map;
        }
        return null;
    }
    //获取（auth_group）的所有数据
    @Override
    public List<Map<String,Object>> findAllGroup(String token) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.authGroupMapper.findAllGroup();
        }
        return null;
    }
}




