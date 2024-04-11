package com.joe.vuebackend.vo;

import com.joe.vuebackend.domain.Course;
import com.joe.vuebackend.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseVo {

    /**
     * 課程識別碼
     */
    private String id;

    /**
     * 課程名
     */
    private String name;

    /**
     * 老師名稱
     */
    private String teacher;

    /**
     * 老師識別碼
     */
    private String teacherId;

    /**
     * 已報名學生數量
     */
    private Integer count;

    /**
     * 可報名總人數
     */
    private Integer maxCount;

    /**
     * 課程開始 ~ 結束日期
     */
    private String courseDate;

    /**
     * 課程開始日期
     */
    private String startDate;

    /**
     * 課程結束日期
     */
    private String endDate;


    /**
     * 上課開始 ~ 結束時間
     */
    private String courseTime;

    /**
     * 上課開始時間
     */
    private String startTime;

    /**
     * 上課結束時間
     */
    private String endTime;

    /**
     * 報名截止日期
     */
    private String deadline;

    /**
     * 上課地點名稱
     */
    private String location;


    public static CourseVo ofVo(Course source) {
        CourseVo target = new CourseVo();

        // 識別碼
        if (StringUtils.isNotEmpty(source.getId())) {
            target.setId(source.getId());
        }

        // 課程名稱
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }

        // 老師姓名、識別碼
        if (Objects.nonNull(source.getTeacher())) {
            target.setTeacher(source.getTeacher().getName());
            target.setTeacherId(source.getTeacher().getId());
        }

        // 已報名學生數量
        if (CollectionUtils.isNotEmpty(source.getStudents())) {
            target.setCount(source.getStudents().size());
        }

        // 可報名總人數
        if (Objects.nonNull(source.getMaxCount())) {
            target.setMaxCount(source.getMaxCount());
        }

        // 課程時間
        LocalDate startDate = source.getStartDate();
        LocalDate endDate = source.getEndDate();
        if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
            String start = DateUtil.formatToYYYYMMDD(startDate);
            String end = DateUtil.formatToYYYYMMDD(endDate);
            String result = String.format("%s ~ %s", start, end);
            target.setCourseDate(result);
            target.setStartDate(start);
            target.setEndDate(end);
        }

        // 上課時間
        LocalTime startTime = source.getStartTime();
        LocalTime endTime = source.getEndTime();
        if (Objects.nonNull(startTime) && Objects.nonNull(endTime)) {
            String result = String.format("%s ~ %s", startTime, endTime);
            target.setCourseTime(result);
            target.setStartTime(DateUtil.formatToHHMM(startTime));
            target.setEndTime(DateUtil.formatToHHMM(endTime));
        }

        // 截止日期
        if (Objects.nonNull(source.getDeadline())) {
            String deadlineStr = DateUtil.formatToYYYYMMDD(source.getDeadline());
            target.setDeadline(deadlineStr);
        }

        // 上課地點名稱
        if (Objects.nonNull(source.getLocation())) {
            target.setLocation(source.getLocation().getNameZh());
        }

        return target;
    }
}
