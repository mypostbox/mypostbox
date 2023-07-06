package com.syedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.AuthPermission;
import com.syedu.domain.Users;
import com.syedu.service.AuthPermissionService;
import com.syedu.mapper.AuthPermissionMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【auth_permission】的数据库操作Service实现
* @createDate 2023-06-13 08:45:36
*/
@Service
public class AuthPermissionServiceImpl extends ServiceImpl<AuthPermissionMapper, AuthPermission>
    implements AuthPermissionService{
    @Autowired
    private PublicKey publicKey;
    @Autowired
    private AuthPermissionMapper authPermissionMapper;
    //分页获取权限数据(auth_permission)
    @Override
    public Map<String, Object> findAllPermissionByPage(String token, Integer page, Integer pageSize) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            map.put("lists",this.authPermissionMapper.findAllPermissionByPage((page-1)*pageSize, pageSize));
            map.put("page",page);
            Long total = this.authPermissionMapper.selectCount(null);
            map.put("pages",Math.ceil(Double.parseDouble(Long.toString(total))/Double.parseDouble(pageSize.toString())));
            return map;
        }
        return null;
    }
    //获取所有的权限类型
    @Override
    public List<Map<String,Object>> findAllContentType(String token) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
           return this.authPermissionMapper.findAllContentType();
        }
        return null;
    }
    //添加保存权限
    @Override
    public Integer savePermission(String token, AuthPermission authPermission) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            System.out.println(authPermission);
            return this.authPermissionMapper.insert(authPermission);
        }
        return null;
    }
    //根据id获取当前权限
    @Override
    public AuthPermission findPermission(String token, Integer permissionId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            System.out.println(permissionId);
            return this.authPermissionMapper.selectById(permissionId);
        }
        return null;
    }
    //根据id修改权限
    @Override
    public Integer updatePermission(String token, Integer permissionId, AuthPermission authPermission) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            authPermission.setId(permissionId);
            System.out.println(authPermission);
            return this.authPermissionMapper.updateById(authPermission);
        }
        return null;
    }
    //根据id删除权限
    @Override
    public Integer deletePermission(String token, Integer permissionId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
           return this.authPermissionMapper.deleteById(permissionId);
        }
        return null;
    }

    //获取所有的权限
    @Override
    public List<Map<String, Object>> findAllGroup(String token) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
           return this.authPermissionMapper.findAllGroup();
        }
        return null;
    }
}




