package com.syedu.utils;

import com.syedu.utils.config.SpringConfiguration;
import com.syedu.utils.util.CurrentDateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * author:Administrator
 * createTime:2023/6/3012:17
 */

public class CurrentDateUtilsTest {

    @Test
    public void test(){
        String currentDate = CurrentDateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
        System.out.println(currentDate);
    }

    @Test
    public void test2(){
        String currentDate = CurrentDateUtils.getCurrentDate("yyyy-MM");
        System.out.println(currentDate);
    }
    @Test
    public void test1(){
        List<Integer> integers = List.of(1, 3, 4, 5, 1, 3);
        Stream<Integer> distinct = integers.stream().distinct();
        distinct.forEach(System.out::println);
    }

    @Test
    public void test3(){
        LocalDate currentLocalDate = CurrentDateUtils.getCurrentLocalDate("yyyy-MM-dd");
        System.out.println(currentLocalDate);
    }

    @Test
    public void test4(){
        LocalDate currentLocalDate = CurrentDateUtils.getCurrentLocalDate("yyyy-MM-dd");
        System.out.println(currentLocalDate);
        LocalDate localDate = currentLocalDate.minusMonths(1);
        System.out.println(localDate);
    }
}
