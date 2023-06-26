package com.syedu.service;

import com.syedu.domain.ChannelGroup;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2415:52
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class ChannelGroupServiceTest {

    @Autowired
    private ChannelGroupService channelGroupService;


    @Test
    public void test(){

    }
}
