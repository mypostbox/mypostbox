package com.syedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.*;
import com.syedu.mapper.*;
import com.syedu.service.UsersUserPermissionsService;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_users_user_permissions】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class UsersUserPermissionsServiceImpl extends ServiceImpl<UsersUserPermissionsMapper, UsersUserPermissions>
    implements UsersUserPermissionsService{
    @Autowired
    private PublicKey publicKey;
    @Autowired
    private UsersUserPermissionsMapper usersUserPermissionsMapper;
    @Autowired
    private UsersGroupsMapper usersGroupsMapper;
    @Autowired
    private UsersMapper usersMapper;
    //分页获取管理员的信息
    @Override
    public Map<String, Object> findAllAdminByPage(String token, Integer page, Integer pageSize) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            map.put("lists",this.usersUserPermissionsMapper.findAllAdminByPage((page-1)*pageSize,pageSize));
            map.put("page",page);
            Integer total = this.usersUserPermissionsMapper.findAdminTotal().size();
            map.put("pages",Math.ceil(Double.parseDouble(total.toString())/Double.parseDouble(pageSize.toString())));
            return map;
        }
        return null;
    }
    //添加管理员
    @Override
    @Transactional
    public Integer saveAdmin(String token, MyUserBody myUserBody) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            this.usersMapper.insert(new Users().setUsername(myUserBody.getUsername()).setFirstName(myUserBody.getUsername()).setLastName(myUserBody.getUsername()).setPassword(myUserBody.getPassword()).setMobile(myUserBody.getMobile()).setEmail(myUserBody.getEmail()).setIsStaff(1));
            Users users = this.usersMapper.selectOne(new LambdaQueryWrapper<Users>().eq(Users::getUsername, myUserBody.getUsername()).eq(Users::getPassword, myUserBody.getPassword()));
            for(Integer groupId : myUserBody.getGroups()){
                this.usersGroupsMapper.insert(new UsersGroups().setUserId(users.getId()).setGroupId(groupId));
            }
            for(Integer permissions : myUserBody.getUser_permissions()){
                this.usersUserPermissionsMapper.insert(new UsersUserPermissions().setUserId(users.getId()).setPermissionId(permissions));
            }
            return 200;
        }
        return null;
    }
    //根据id获取管理员信息
    @Override
    public MyUserBody getAdmin(String token, Integer userId) throws Exception {
        MyUserBody myUserBody = new MyUserBody();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            Users users = this.usersMapper.selectById(userId);
            myUserBody.setUsername(users.getUsername()).setPassword(users.getPassword()).setMobile(users.getMobile()).setEmail(users.getEmail());
            myUserBody.setUser_permissions(this.usersUserPermissionsMapper.findAllPermissionByUserId(userId));
            myUserBody.setGroups(this.usersGroupsMapper.findAllGroupByUserId(userId));
            return myUserBody;
        }
        return null;
    }
    //根据id修改管理员信息
    @Override
    @Transactional
    public Integer updateAdmin(String token, Integer userId, MyUserBody myUserBody) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            this.usersMapper.updateById(new Users().setId(userId).setUsername(myUserBody.getUsername()).setPassword(myUserBody.getPassword()).setMobile(myUserBody.getMobile()).setEmail(myUserBody.getEmail()));
            this.usersGroupsMapper.delete(new LambdaQueryWrapper<UsersGroups>().eq(UsersGroups::getUserId,userId));
            this.usersUserPermissionsMapper.delete(new LambdaQueryWrapper<UsersUserPermissions>().eq(UsersUserPermissions::getUserId,userId));
            for(Integer groupId : myUserBody.getGroups()){
                this.usersGroupsMapper.insert(new UsersGroups().setUserId(userId).setGroupId(groupId));
            }
            for(Integer permissionId : myUserBody.getUser_permissions()){
                this.usersUserPermissionsMapper.insert(new UsersUserPermissions().setUserId(userId).setPermissionId(permissionId));
            }
            return 200;
        }
        return null;
    }
    //根据id删除管理员
    @Override
    @Transactional
    public Integer deleteAdmin(String token, Integer userId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
          this.usersGroupsMapper.delete(new LambdaQueryWrapper<UsersGroups>().eq(UsersGroups::getUserId,userId));
          this.usersUserPermissionsMapper.delete(new LambdaQueryWrapper<UsersUserPermissions>().eq(UsersUserPermissions::getUserId,userId));
          this.usersMapper.deleteById(userId);
          return 200;
        }
        return null;
    }
}




