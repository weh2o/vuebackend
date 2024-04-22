package com.joe.vuebackend.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Component
public class DateUtil {

    /**
     * 返回範圍內隨機的日期物件
     *
     * @param startDate 開始日期
     * @param endDate   結束日期
     * @return 日期物件
     */
    public static LocalDate getRangeLocalDate(LocalDate startDate, LocalDate endDate) {

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        Random random = new Random();
        long randomDays = random.nextInt((int) daysBetween);

        return startDate.plusDays(randomDays);
    }

    /**
     * 格式化日期 yyyy-MM-dd HH:mm:ss (24小時制)
     *
     * @param localDateTime
     * @return
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 格式化日期 yyyy-MM-dd
     *
     * @param localDate
     * @return
     */
    public static String formatLocalDate(LocalDate localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTimeFormatter.format(localDate);
    }

    /**
     * 格式化日期 HH:mm (24小時制)
     *
     * @param localTime
     * @return
     */
    public static String formatLocalTime(LocalTime localTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTimeFormatter.format(localTime);
    }

    /**
     * 解析 yyyy-MM-dd HH:mm:ss 格式日期
     * <br/>
     *
     * @param strDate yyyy-MM-dd HH:mm:ss 格式日期
     * @return
     */
    public static LocalDateTime parseToLocalDateTime(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(strDate, formatter);
    }
}
