package com.joe.vuebackend.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
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

}
