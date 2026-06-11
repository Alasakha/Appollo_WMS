package com.mgkj.common.utils;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static String getNow() {
        LocalDateTime now = LocalDateTime.now();
        // 创建一个SimpleDateFormat对象来格式化日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dateString = now.format(formatter);
        return dateString;
    }
    public static String getToday(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String todayString=now.format(formatter);
        return todayString;
    }

    public static String dateTime(){
        LocalDateTime now = LocalDateTime.now();
        // 创建一个SimpleDateFormat对象来格式化日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS");
        String dateString = now.format(formatter);
        return dateString;
    }

    public static LocalDateTime dateTime2(){
        LocalDateTime now = LocalDateTime.now();
        // 创建一个SimpleDateFormat对象来格式化日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS");
        String dateString = now.format(formatter);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        return dateTime;
    }

    public static LocalDateTime dateStringTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime dateTime;
        // 解析日期
        LocalDate parse = LocalDate.parse(date, formatter);
        dateTime = LocalDateTime.of(parse, LocalTime.MIN);
        return dateTime;
    }

    public static String dateTimeNumber(){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
        return  dateFormat.format(new Date());
    }
}
