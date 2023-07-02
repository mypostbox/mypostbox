package com.syedu.utils.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * author:Administrator
 * createTime:2023/6/3012:04
 */
public class CurrentDateUtils {

    /**
     * 根据具体的参数放回具体的时间格式
     * @param format
     * @return
     */
    public static String getCurrentDate(String format){
        if(format.length() < 12){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
            LocalDate now = LocalDate.now();
            return dateTimeFormatter.format(now);
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dateTimeFormatter.format(now);
    }

    public static LocalDate getCurrentLocalDate(String format){
        return LocalDate.parse(getCurrentDate(format),DateTimeFormatter.ofPattern(format));
    }

    public static LocalDateTime getCurrentLocalDateTime(String format){
        return LocalDateTime.parse(getCurrentDate(format),DateTimeFormatter.ofPattern(format));
    }

}
