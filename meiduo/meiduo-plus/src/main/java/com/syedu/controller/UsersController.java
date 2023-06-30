package com.syedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.syedu.domain.Areas;
import com.syedu.domain.Sku;
import com.syedu.domain.Users;
import com.syedu.mapper.AreasMapper;
import com.syedu.mapper.UsersMapper;
import com.syedu.service.OrderInfoService;
import com.syedu.service.SkuService;
import com.syedu.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/1613:48
 */
@RestController
public class UsersController {
    @Autowired
    private AreasMapper areasMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UsersService usersService;
    @Autowired
    private SkuService skuService;
    @Autowired
    private OrderInfoService orderInfoService;


    /**
     * 用户登入
     * @param user
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("authorizations")
    public Users authorizations(Users user, HttpServletResponse response) throws Exception {
        return this.usersService.checkUser(user);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("users")
    public Users users(@RequestBody Users user) throws Exception {
        return this.usersService.register(user);
    }

    /**
     * 用户姓名查重
     */
    @GetMapping("usernames/{username}/count/")
    public Long usernames(@PathVariable(value = "username") String username){
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername,username);
        return this.usersMapper.selectCount(wrapper);
    }
    /**
     * 用户电话号码查重
     */
    @GetMapping("mobiles/{mobile}/count/")
    public Long mobile(@PathVariable(value = "mobile") String mobile){
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getMobile,mobile);
        return this.usersMapper.selectCount(wrapper);
    }



    /**
     * 此方法已被替换，无法在使用，只做浏览
     *
     * @param users
     * @param response
     * @return
     * @throws Exception
     */
    public String generateToken(Users users, HttpServletResponse response) throws Exception {
        String token = this.usersService.token(users);
        if (token == null) {
            return "用户名或密码错误!!";
        }
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
        return token;
    }


    //保存用户浏览记录
    @PostMapping("browse_histories")
    public void browseHistories1(@RequestBody Map<String,Integer> map,
                                @RequestHeader("Authorization") String token) throws Exception {
      this.skuService.browseHistories1(map.get("sku_id"),token);
    }
    //获取用户的浏览记录
    @GetMapping("browse_histories")
    public List<Sku> browseHistories2(@RequestHeader("Authorization") String token) throws Exception{
       return this.skuService.browseHistories2(token);
    }
    //获取用户的详细信息
    @GetMapping("user")
    public Users findAllUsers(@RequestHeader("Authorization") String token) throws Exception {
       return this.usersService.findAllUsers(token);
    }

    /**
     * 获取订单信息
     * @param token 用户验证信息
     * @param page 当前页数
     * @param pageSize 最大页数
     * @return
     * @throws Exception
     */
    @GetMapping("orders")
    public Map<String,Object> findAllOrderInfoByUserId(@RequestHeader("Authorization") String token,
                                                       @RequestParam("page") Integer page,
                                                       @RequestParam("page_size") Integer pageSize) throws Exception{
        return this.orderInfoService.findAllOrderInfoByUserId(token,page,pageSize);
    }

    /**
     * 用户修改密码
     * @param token
     * @param password
     * @return
     * @throws Exception
     */
    @PostMapping("modification")
    public Integer modificationPassword(@RequestHeader("Authorization") String token,
                                        @RequestParam("password") String password) throws Exception {
        return this.usersService.modificationPassword(token,password);
    }
    @GetMapping("checkPassword")
    public Boolean checkPassword(@RequestHeader("Authorization") String token,
                                 @RequestParam("password") String password) throws Exception{
        return this.usersService.checkPassword(token,password);
    }

    /**
     * 获取省份信息
     * @return
     */
    @GetMapping("areas")
    public List<Areas> getProvince(){
        return this.areasMapper.findAllProvince();
    }

    @GetMapping("areas/{parentId}")
    public List<Areas> findChild(@PathVariable("parentId") Integer parentId){
        return this.areasMapper.findChild(parentId);
    }


}
