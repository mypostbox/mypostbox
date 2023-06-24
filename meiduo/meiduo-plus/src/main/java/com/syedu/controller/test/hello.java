package com.syedu.controller.test;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syedu.domain.Content;
import com.syedu.domain.SysException;
import com.syedu.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 测试专用接口
 * author:Administrator
 * createTime:2023/6/1419:27
 */
@Controller
@RequestMapping("hello")
public class hello {



    @Autowired
    private ContentMapper contentMapper;

    /**
     * 视图解析器测试
     * @return
     */
    @RequestMapping("test")
    public String test() {
        return "success" ;
    }

    /**
     * 分页测试
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("page")
    @ResponseBody
    public List<Content> showPage(@RequestParam(value = "page") Long page, @RequestParam(value = "pageSize") Long pageSize) {
        System.out.println(page);
        System.out.println(pageSize);
        Page<Content> contentPage = new Page<>(page, pageSize);
        this.contentMapper.selectPage(contentPage, null);
        return contentPage.getRecords();
    }

    /**
     * 异常捕捉器测试
     * @return
     * @throws SysException
     */
    @RequestMapping("error")
    public String tests() throws SysException{
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException("test", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "success";
    }



}
