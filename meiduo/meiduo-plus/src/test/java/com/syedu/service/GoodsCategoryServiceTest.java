package com.syedu.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syedu.domain.GoodsCategory;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Test
    public void test2(){
        Map<String, Object> stringObjectMap = this.goodsCategoryService.listService(115);
        System.out.println(stringObjectMap);
    }

    @Test
    public void test3() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ0b20iLCJwYXNzd29yZCI6IjExMTExMTExIiwiZW1haWwiOiI5ODk5OUBxcS5jb20iLCJleHAiOjE2ODg5NTU1Nzd9.bmxmGNSHilZdEQzTzlPF7sNr_z1PqXljFIqzlrl5ZrHlu_N_WBr6lrvxmFI20HEpWdH3ZwmDIfIBtV7CPP6c1mblwLeG60tO7IGaknm7xwvs_NwD9NZIsvhVJqUp99M8Izg04YuI0bhcSSgwD_-PgFAWMjUq3gc5F5H9CX_7PDwq84_pstvTuGP637e5QkiFmaQPRuDs9Am3hC-T8Uq4Z8HISeM2NfB4xihC9DWtRYRluiM5NDjilwfl6l1L3oOUJ8TgVBMp3fd5OU0wfP7Y8bIgtbsj92MmSwtrnGfxaWKEk_mFvMf-zrcW9bAPQmB_VMn7wRpysWfaZEvlR5Klow";
        List<GoodsCategory> all = this.goodsCategoryService.findGoodsCategory(token);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(all);
        System.out.println(s);
    }
}
