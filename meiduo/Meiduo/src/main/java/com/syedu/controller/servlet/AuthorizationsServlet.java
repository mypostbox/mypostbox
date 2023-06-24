package com.syedu.controller.servlet; /**
 * author:Administrator
 * createTime:2023/6/813:58
 */

import com.alibaba.fastjson2.JSON;
import com.syedu.domain.TbUsers;
import com.syedu.mapper.TbUsersMapper;
import com.syedu.utils.JwtUtil;
import com.syedu.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AuthorizationsServlet", value = "/authorizations")
public class AuthorizationsServlet extends HttpServlet {

    private SqlSession session;
    @Override
    public void init() throws ServletException {
       this.session = MybatisUtils.getSqlSession();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        TbUsersMapper mapper = this.session.getMapper(TbUsersMapper.class);
        TbUsers tbUsers = mapper.loginUsername(username, password);
       if(tbUsers != null ){
           //获得一个小时的令牌
           String s = JwtUtil.generateToken(tbUsers.getUsername() + ":" + tbUsers.getId(),1000*60*60);
           Map<String,Object> map = new HashMap<>();
           map.put("username",tbUsers.getUsername());
           map.put("user_id",tbUsers.getId());
           map.put("token",s);
           String s1 = JSON.toJSONString(map);
           out.println(s1);
           response.setStatus(200);
       }else{
           response.setStatus(400);
       }
    }
}
