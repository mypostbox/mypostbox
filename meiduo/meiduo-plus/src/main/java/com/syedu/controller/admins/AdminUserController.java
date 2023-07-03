package com.syedu.controller.admins;

import com.syedu.domain.Users;
import com.syedu.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/7/221:19
 */
@RestController
@RequestMapping("admins")
public class AdminUserController {
    @Autowired
    private UsersService usersService;

    /**
     * 分页查询所有用户信息(或根据关键字查找)
     * @param token
     * @param page
     * @param pageSize
     * @param keyword
     * @return
     * @throws Exception
     */
    @GetMapping("users")
    public Map<String,Object> fnGetData(@RequestHeader("Authorization") String token,
                                        @RequestParam("page") Integer page,
                                        @RequestParam("pagesize") Integer pageSize,
                                        @RequestParam("keyword") String keyword) throws Exception {
       return this.usersService.fnGetData(token, page, pageSize, keyword);
    }

    /**
     * 添加新用户
     * @param token
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("users")
    public Users saveUsers3(@RequestHeader("Authorization") String token,
                             Users user) throws Exception {
        return this.usersService.saveUser(token,user);
    }

}
