package com.syedu.utils;

import com.syedu.utils.util.OrderNumberGenerator;
import org.junit.jupiter.api.Test;

/**
 * author:Administrator
 * createTime:2023/6/2817:13
 */
public class OrderNumberGeneratorTest {

    @Test
    public void test(){
        String s = OrderNumberGenerator.generateOrderId();
        String s1 = OrderNumberGenerator.generateOrderId();
        String s2 = OrderNumberGenerator.generateOrderId();
        String s3 = OrderNumberGenerator.generateOrderId();
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
