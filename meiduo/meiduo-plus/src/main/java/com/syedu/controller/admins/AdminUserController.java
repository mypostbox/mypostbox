package com.syedu.controller.admins;

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

    @GetMapping("users")
    public Map<String,Object> fnGetData(@RequestHeader("Authorization") String token,
                                        @RequestParam("page") Integer page,
                                        @RequestParam("pagesize") Integer pageSize,
                                        @RequestParam("keyword") String keyword) throws Exception {
       return this.usersService.fnGetData(token, page, pageSize, keyword);
    }
}
