
package com.mgkj.common.utils;



import cn.hutool.core.date.DateUtil;
import com.mgkj.exception.CustomException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class TimeUtils {
    public static String getNowTime(){

        // 创建日期格式化对象，指定输出格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        // 获取当前时间
        Date currentDate = new Date();

        // 格式化日期时间
        String formattedDate = sdf.format(currentDate);

        return formattedDate;

    }

    public static String getHzViewTime(){

        // 创建日期格式化对象，指定输出格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        // 获取当前时间
        Date currentDate = new Date();

        // 格式化日期时间
        String formattedDate = sdf.format(currentDate);

        return formattedDate;

    }

    public static String getFullNowTime(){

        // 创建日期格式化对象，指定输出格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 获取当前时间
        Date currentDate = new Date();

        // 格式化日期时间
        String formattedDate = sdf.format(currentDate);

        return formattedDate;

    }

    public static String HZStdTime(){

        // 创建日期格式化对象，指定输出格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        // 获取当前时间
        Date currentDate = new Date();

        // 格式化日期时间
        String formattedDate = sdf.format(currentDate);

        return formattedDate;

    }

    /**
     * 判断 年/月/日 的格式是否正确
     * @param str
     * @return
     */
    public static boolean isValidDate(String str) {

        // 定义可能的日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/M/d");
        // 尝试解析日期字符串
        try {
            LocalDate date = LocalDate.parse(str, formatter);

            // 检查日期是否合法
            if (date.getDayOfMonth() != Integer.parseInt(str.split("/")[2])) {
                throw new DateTimeParseException("Invalid day of month", str, 0);
            }
//            System.out.println(str + " 的格式正确");
            return true;
        } catch (DateTimeParseException e) {
//            System.out.println(str + " 的格式不正确");
            return false;
        }
    }

    public static String stringToHZStdTime(String date) {
        if(isValidDate(date)) {
            Date date1 = DateUtil.parse(date);
            return DateUtil.format(date1, "yyyyMMdd");
        }else{
            throw new CustomException("500", "日期格式不正确");
        }
    }

    /**
     * 将指定日期减去/增加一定天数
     * @param y
     * @param m
     * @param d
     * @param days
     * @return
     */
    public static String addOrSubtract(int y, int m, int d, int days) {
        // 指定一个日期
        LocalDate specifiedDate = LocalDate.of(y, m, d); // 例如指定为 2024-07-17

        // 假设要加的天数或减去的天数
        int daysToAddOrSubtract = days; // 可以为负数，表示减去天数

        // 加上或减去指定天数后的日期
        LocalDate manipulatedDate = specifiedDate.plusDays(daysToAddOrSubtract);

        // 格式化日期为 "YYYYMMDD" 格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = manipulatedDate.format(formatter);

        // 输出结果
        return formattedDate;
    }

    /**
     * 将指定日期减去/增加一定天数
     */
    public static String addOrSubtract(String hzStdTime, int days) {
        // 输出结果
        return addOrSubtract(Integer.parseInt(hzStdTime.substring(0, 4)), Integer.parseInt(hzStdTime.substring(4, 6)),
                Integer.parseInt(hzStdTime.substring(6, 8)), days);
    }

    public static Long getSecondsDifference(String t1, String t2) {
        // 创建两个时间点
        LocalDateTime time1 = LocalDateTime.of(Integer.parseInt(t1.substring(0, 4)), Integer.parseInt(t1.substring(4, 6)), Integer.parseInt(t1.substring(6, 8)),
                Integer.parseInt(t1.substring(8, 10)), Integer.parseInt(t1.substring(10, 12)), Integer.parseInt(t1.substring(12, 14)));
        LocalDateTime time2 = LocalDateTime.of(Integer.parseInt(t2.substring(0, 4)), Integer.parseInt(t2.substring(4, 6)), Integer.parseInt(t2.substring(6, 8)),
                Integer.parseInt(t2.substring(8, 10)), Integer.parseInt(t2.substring(10, 12)), Integer.parseInt(t2.substring(12, 14)));
        // 计算时间差
        Duration duration = Duration.between(time2, time1);
        // 输出相差的秒数
        long seconds = duration.getSeconds();
//        System.out.println("相差秒数: " + seconds);
        return seconds;
    }

}
