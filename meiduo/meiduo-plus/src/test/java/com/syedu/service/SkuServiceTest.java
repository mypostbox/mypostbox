package com.syedu.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.GoodsVisit;
import com.syedu.domain.Sku;
import com.syedu.mapper.GoodsVisitMapper;
import com.syedu.mapper.SkuMapper;
import com.syedu.service.impl.SkuServiceImpl;
import com.syedu.utils.config.SpringConfiguration;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * author:Administrator
 * createTime:2023/6/2011:29
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class SkuServiceTest {


@Autowired
private SkuMapper skuMapper;
    @Autowired
    private SkuService skuService;
    @Autowired
    private GoodsVisitMapper goodsVisitMapper;


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
    @Test
    public void testInsertVisit() throws ParseException {

    }

    @Test
    public void test3(){
        LambdaQueryWrapper<GoodsVisit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsVisit::getCategoryId,157);
        List<GoodsVisit> goodsVisits = this.goodsVisitMapper.selectList(wrapper);
        System.out.println(goodsVisits);
    }
    @Test
    public void test2(){
        LocalDate parse = LocalDate.parse("2023-07-02");
        System.out.println(parse);
    }
    @Test
    public void test4(){
        Double x = Double.parseDouble(Long.toString(17));
        Double y = Double.parseDouble(Integer.toString(10));
        System.out.println(x);
        System.out.println(y);
        System.out.println(x/y);
        System.out.println(Math.ceil(x/y));

    }

    @Test
    public void test5(){
        Sku sku = new Sku().setName("1111").setSpuId(1).setCaption("11111").setCategoryId(1).setPrice(1000.0).setCostPrice(1000.0).setMarketPrice(1000.0).setStock(1).setIsLaunched(1).setSpecs(null);
        this.skuMapper.insert(sku);
    }

}
