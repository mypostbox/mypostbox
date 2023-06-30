package com.syedu.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.syedu.domain.Areas;
import com.syedu.mapper.AreasMapper;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/6/2920:24
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class AreasServiceTest {
    @Autowired
    private AreasService areasService;
    @Autowired
    private AreasMapper areasMapper;
    @Test
    public void test(){
        List<Areas> areas = this.areasMapper.selectList(new LambdaQueryWrapper<Areas>().eq(Areas::getParentId, null));
        System.out.println(areas);
    }

    @Test
    public void test1(){
        LambdaQueryWrapper<Areas> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Areas::getParentId,null);
        List<Areas> areas = this.areasMapper.selectList(wrapper);
        System.out.println(areas);
    }

}
