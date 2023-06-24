package com.syedu.controller.test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试页面
 * author:Administrator
 * createTime:2023/6/1420:52
 */
@Controller
public class Favicon {



    public ResponseEntity<Resource> favicon(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.setContentType("image/x-icon");
        ClassPathResource classPathResource = new ClassPathResource("static/images/i.png");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        InputStreamResource inputStreamResource = new InputStreamResource(classPathResource.getInputStream());
        return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
    }
    @RequestMapping(value={"favicon.ico"},method = {RequestMethod.GET})
    public String favicon(){
        return "favicon";
    }
    @RequestMapping(value={"favicon"},method = {RequestMethod.GET})
    public String favicon2(){
        return "favicon";
    }
}
