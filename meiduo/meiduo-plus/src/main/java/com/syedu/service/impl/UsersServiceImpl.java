package com.syedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.Users;
import com.syedu.service.UsersService;
import com.syedu.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syedu.utils.keyword.JwtUtils;
import redis.clients.jedis.Jedis;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
* @author Administrator
* @description 针对表【tb_users】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

    @Autowired
    private PrivateKey privateKey;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private PublicKey publicKey;


    /**
     * 根据用户和密码校验用户是否存在
     * @param user
     * @return Users
     * @throws Exception
     */
    @Override
    public Users checkUser(Users user) throws Exception {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername,user.getUsername());
        wrapper.eq(Users::getPassword,user.getPassword());
        Users users = this.usersMapper.selectOne(wrapper);
        if(users != null){
            String token = JwtUtils.generateToken(users, privateKey, 7 * 24 * 60);
            users.setToken(token);
            return users;
        }
        return null;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public Users register(Users user) throws Exception {
        user.setFirstName(user.getUsername());
        user.setLastName(user.getUsername());
        user.setDateJoined(new Date());
        user.setLastLogin(new Date());
        int insert = this.usersMapper.insert(user);
        System.out.println(insert);
        LambdaQueryWrapper<Users> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Users::getUsername,user.getUsername());
        wrapper1.eq(Users::getPassword,user.getPassword());
        Users users = this.usersMapper.selectOne(wrapper1);
        users.setToken(JwtUtils.generateToken(users,privateKey,7 * 24 * 60));
        return users;
    }

    //获取用户的详细信息
    @Override
    public Users findAllUsers(String token) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token,this.publicKey);
        return this.usersMapper.selectById(user.getId());
    }

    @Override
    public Integer modificationPassword(String token, String  password) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        Users users = this.usersMapper.selectById(user.getId());
        users.setPassword(password);
        return this.usersMapper.updateById(users);
    }

    @Override
    public Boolean checkPassword(String token, String password) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        Users users = this.usersMapper.selectById(user.getId());
        if(users.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    /**
     * 此方法已改，无法在使用，只做浏览
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public String token(Users user) throws Exception{
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getId,user.getId());
        wrapper.eq(Users::getUsername,user.getUsername());
        wrapper.eq(Users::getPassword,user.getPassword());
        wrapper.eq(Users::getEmail,user.getEmail());
        Users users = this.usersMapper.selectOne(wrapper);
        if(users == null){
            return null;
        }
        return JwtUtils.generateToken(users, privateKey, 7 * 24 * 60);
    }
}




