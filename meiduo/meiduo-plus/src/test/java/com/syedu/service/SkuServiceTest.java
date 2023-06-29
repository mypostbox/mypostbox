package com.syedu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.Sku;
import com.syedu.utils.config.SpringConfiguration;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2011:29
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class SkuServiceTest {



    @Autowired
    private SkuService skuService;


    @Test
    public void test(){
        Sku skuDetailById = this.skuService.findSkuDetailById(1);
        System.out.println(skuDetailById);
    }

    @Test
    public void test1() throws JsonProcessingException {
        Map<String, Object> map = this.skuService.listService(115, 1, 5, "-create_time");
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(map);
        System.out.println(s);

    }

    @Test
    public void testFindHot() throws JsonProcessingException {
        List<Sku> skus = this.skuService.fundHot();
        String s = new ObjectMapper().writeValueAsString(skus);
        System.out.println(s);
    }

}
