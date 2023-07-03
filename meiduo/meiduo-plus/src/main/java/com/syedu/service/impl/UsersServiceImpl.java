package com.syedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.Users;
import com.syedu.mapper.OrderInfoMapper;
import com.syedu.service.UsersService;
import com.syedu.mapper.UsersMapper;
import com.syedu.utils.util.CurrentDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syedu.utils.util.JwtUtils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private PublicKey publicKey;
    /**
     * 用户登入
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
            users.setLastLogin(new Date());
            this.usersMapper.updateById(users);
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

    @Override
    public Map<String, Object> findAllCountUser(String token) throws Exception {
        return this.usersMapper.findAllUserCount();
    }
    @Override
    public Map<String, Object> findAllCreateUser(String token) throws Exception {
        return this.usersMapper.findAllCurrentCreateUser();
    }
    @Override
    public Map<String, Object> findAllLoginUser(String token) throws Exception {
        return this.usersMapper.findAllCurrentLoginUser();
    }

    @Override
    public Map<String, Object> findAllOrderUser(String token) throws Exception {
       return this.orderInfoMapper.findAllOrderUser();
    }
    /**
     * 获取月增用户
     * @param token
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> findAllUserByMonthIncrement(String token) throws Exception {
        return this.usersMapper.findAllMonthCreateUser();
    }
    /**
     * 管理员登录
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public Users staffLogin(Users user) throws Exception {
        Users users = this.usersMapper.staffLogin(user.getUsername(), user.getPassword());
        if(users != null){
            String token = JwtUtils.generateToken(users, this.privateKey, 7 * 24 * 60);
            users.setToken(token);
            return users;
        }
        return null;
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

    /**
     * 分页查询所有用户信息(或根据关键字查找)
     * @param token
     * @param page
     * @param pageSize
     * @param keyword
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> fnGetData(String token, Integer page, Integer pageSize, String keyword) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            Page<Users> usersPage = new Page<>(page,pageSize);
            if(keyword == null || keyword == "") {
                this.usersMapper.selectPage(usersPage, null);
                map.put("lists", usersPage.getRecords());
                map.put("page", page);
                map.put("pages", Math.ceil(Double.parseDouble(Long.toString(usersPage.getTotal())) / Double.parseDouble(pageSize.toString())));
            }else{
                LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
                wrapper.like(Users::getUsername,"%"+keyword+"%");
                this.usersMapper.selectPage(usersPage,wrapper);
                map.put("lists",usersPage.getRecords());
                map.put("page",page);
                map.put("pages",Math.ceil(Double.parseDouble(Long.toString(usersPage.getTotal())) / Double.parseDouble(pageSize.toString())));
            }
            return map;
        }
        return null;
    }

    /**
     * 保存用户
     * @param token
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public Users saveUser(String token, Users user) throws Exception {
        Users users = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(users.getId() != null){
            System.out.println(user);
            user.setFirstName(user.getUsername());
            user.setLastName(user.getUsername());
            this.usersMapper.insert(user);
            return user;
        }
        return null;
    }
}




