package com.syedu.utils.component;

import com.syedu.domain.SysException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author:Administrator
 * createTime:2023/6/1710:43
 */

public class SysExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        SysException e = null;
        if(ex instanceof SysException){
            e = (SysException) ex;
        }else{
            e = new SysException("系统维护中",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(e.getStatus());
        modelAndView.addObject("errorMsg",e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
