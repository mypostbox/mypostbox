package com.syedu.utils.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * author:Administrator
 * createTime:2023/6/2817:09
 */
public class OrderNumberGenerator {

    private static int dailyOrderCount;

    public static synchronized String generateOrderId(){
        ++dailyOrderCount;
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String orderId = UUID.randomUUID().toString().replaceAll("-","");
        String countStr = String.format("%08d",dailyOrderCount);
        return date + orderId + countStr;
    }
}
