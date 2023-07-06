package com.syedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.AuthGroup;
import com.syedu.domain.AuthGroupPermissions;
import com.syedu.domain.MyGroupBody;
import com.syedu.domain.Users;
import com.syedu.mapper.AuthGroupPermissionsMapper;
import com.syedu.service.AuthGroupService;
import com.syedu.mapper.AuthGroupMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private AuthGroupPermissionsMapper authGroupPermissionsMapper;
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
    //添加角色
    @Override
    @Transactional
    public Integer saveGroup(String token, MyGroupBody permissions) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            System.out.println(permissions);
            AuthGroup authGroup = new AuthGroup();
            authGroup.setName(permissions.getName());
            this.authGroupMapper.insert(authGroup);
            AuthGroup authGroup1 = this.authGroupMapper.selectOne(new LambdaQueryWrapper<AuthGroup>().eq(AuthGroup::getName, permissions.getName()));
            for(Integer permissionId :  permissions.getPermissions()){
                AuthGroupPermissions authGroupPermissions = new AuthGroupPermissions();
                authGroupPermissions.setGroupId(authGroup1.getId());
                authGroupPermissions.setPermissionId(permissionId);
                this.authGroupPermissionsMapper.insert(authGroupPermissions);
            }
            return 200;
        }
        return null;
    }
    //根据id获取角色的名称和权限
    @Override
    public MyGroupBody findGroup(String token, Integer groupId) throws Exception {
        MyGroupBody myGroupBody = new MyGroupBody();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            myGroupBody.setName(this.authGroupMapper.selectById(groupId).getName());
            myGroupBody.setPermissions(this.authGroupPermissionsMapper.selectList(new LambdaQueryWrapper<AuthGroupPermissions>().eq(AuthGroupPermissions::getGroupId, groupId)).stream().map(AuthGroupPermissions::getPermissionId).collect(Collectors.toList()));
            return myGroupBody;
        }
        return null;
    }
    //根据id修改角色
    @Override
    @Transactional
    public Integer updateGroup(String token, Integer groupId, MyGroupBody permissions) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            this.authGroupMapper.updateById(new AuthGroup(groupId,permissions.getName()));
            this.authGroupPermissionsMapper.delete(new LambdaQueryWrapper<AuthGroupPermissions>().eq(AuthGroupPermissions::getGroupId,groupId));
            for(Integer permissionId : permissions.getPermissions()){
                this.authGroupPermissionsMapper.insert(new AuthGroupPermissions().setGroupId(groupId).setPermissionId(permissionId));
            }
            return 200;
        }
        return null;
    }
    //根据id删除角色
    @Override
    public Integer deleteGroup(String token, Integer groupId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            this.authGroupPermissionsMapper.delete(new LambdaQueryWrapper<AuthGroupPermissions>().eq(AuthGroupPermissions::getGroupId,groupId));
            this.authGroupMapper.deleteById(groupId);
            return 200;
        }
        return null;
    }
    //获取所有的用户组
    @Override
    public List<AuthGroup> findAllAuthGroup(String token) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
           return this.authGroupMapper.selectList(null);
        }
        return null;
    }
}




