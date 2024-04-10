package com.joe.vuebackend.bean;

import com.joe.vuebackend.domain.Course;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CourseInfo {

    /**
     * 課程名
     */
    private String name;

    /**
     * 可參加人數
     */
    private String maxCount;

    /**
     * 報名截止日期
     */
    private String deadline;

    /**
     * 課程開始日期
     */
    private String startDate;

    /**
     * 課程結束日期
     */
    private String endDate;

    /**
     * 上課開始時間
     */
    private String startTime;

    /**
     * 上課結束時間
     */
    private String endTime;

    /**
     * 地點
     */
    private String location;

    /**
     * 地點
     */
    private String otherLocation;

    /**
     * 老師
     */
    private String teacher;

    public static Course ofCourse(CourseInfo source) {
        Course target = new Course();

        // 課程名
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }

        // 可參數人數
        if (StringUtils.isNotEmpty(source.getMaxCount())) {
            target.setMaxCount(Integer.valueOf(source.getMaxCount()));
        }

        // 報名截止日期
        if (StringUtils.isNotEmpty(source.getDeadline())) {
            target.setDeadline(LocalDate.parse(source.getDeadline()));
        }

        // 課程開始日期
        if (StringUtils.isNotEmpty(source.getStartDate())) {
            target.setStartDate(LocalDate.parse(source.getStartDate()));
        }

        // 課程結束日期
        if (StringUtils.isNotEmpty(source.getEndDate())) {
            target.setEndDate(LocalDate.parse(source.getEndDate()));
        }

        // 上課開始時間
        if (StringUtils.isNotEmpty(source.getStartTime())) {
            target.setStartTime(LocalTime.parse(source.getStartTime()));
        }

        // 上課結束時間
        if (StringUtils.isNotEmpty(source.getEndTime())) {
            target.setEndTime(LocalTime.parse(source.getEndTime()));
        }

        // 地點
//        if (StringUtils.isNotEmpty(source.getLocation())) {
//            CourseLocationHelper.infoSetCourse(target, source);
//        }


        return target;
    }
}
