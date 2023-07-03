package com.syedu.controller.admins;

import com.syedu.domain.Users;
import com.syedu.service.GoodsVisitService;
import com.syedu.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/298:50
 */
@RestController
@RequestMapping("admins")
public class AdminFrontController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private GoodsVisitService goodsVisitService;
    /**
     * 管理员登入
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("authorizations")
    public Users checkUsers(Users user) throws Exception {
        return  this.usersService.staffLogin(user);
    }
    /**
     * 获取所有用户的数量
     * @return
     */
    @GetMapping("statistical/total_count")
    public Map<String,Object> findAllUserCount(@RequestHeader("Authorization") String token) throws Exception{
        return this.usersService.findAllCountUser(token);
    }
    /**
     * 获取日增用户
     */
    @GetMapping("statistical/day_increment")
    public Map<String,Object> findAllUserByDateJoined(@RequestHeader("Authorization") String token) throws Exception{
        return this.usersService.findAllCreateUser(token);
    }
    /**
     * 获取日活人数
     * @return
     */
    @GetMapping("statistical/day_active")
    public Map<String,Object> findAllUserByLastLogin(@RequestHeader("Authorization") String token) throws Exception{
        return this.usersService.findAllLoginUser(token);
    }
    /**
     * 获取日下单人数
     */
    @GetMapping("statistical/day_orders")
    public Map<String,Object> findAllUserByOrder(@RequestHeader("Authorization") String token) throws Exception{
        return this.usersService.findAllOrderUser(token);
    }
    /**
     * 获取月增用户
     * @param token
     * @return
     */
    @GetMapping("statistical/month_increment")
    public List<Map<String,Object>> findAllUserByMonthIncrement(@RequestHeader("Authorization") String token) throws Exception {
       return this.usersService.findAllUserByMonthIncrement(token);
    }

    /**
     * 获取商品访问数量
     * @param token
     * @return
     * @throws Exception
     */
    @GetMapping("statistical/goods_day_views")
    public List<Map<String,Object>> findAllGoodsDayViews(@RequestHeader("Authorization") String token)throws Exception{
       return this.goodsVisitService.findAllGoodsDayViews(token);
    }
}
