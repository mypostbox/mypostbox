package com.syedu.service;

import com.syedu.domain.GoodsCategory;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import java.util.ArrayList;
import java.util.List;

/**
 * author:Administrator
 * createTime:2023/6/1720:35
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class GoodsCategoryServiceTest {
    @Autowired
    private GoodsCategoryService goodsCategoryService;


    @Test
    public void test() {
        List<List<GoodsCategory>> lists = this.goodsCategoryService.menuService();
        lists.stream().forEach(System.out::println);
    }


    @Test
    public void test1() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            arr.add(i);
        }
        ArrayList<List<Integer>> lists = new ArrayList<>();
        ArrayList<Integer> x = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            x.add(arr.get(i));

            if (x.size() == 3) {
                lists.add(x);
                x = new ArrayList<>();
            }
        }

        lists.add(x);


        lists.stream().forEach(System.out::println);

    }
}
