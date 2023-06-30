package com.syedu.controller.admin;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/298:50
 */
@RestController
@RequestMapping("admins")
public class LoginController {


    @PostMapping("authorizations")
    public Map<String,Object>  authorization2(@RequestParam("username") String username,
                                             @RequestParam("password") String password){
        System.out.println(username);
        System.out.println(password);
        return null;
    }

}
