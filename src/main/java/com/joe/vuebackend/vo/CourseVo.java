package com.joe.vuebackend.vo;

import com.joe.vuebackend.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 老師
     */
    private String teacher;

    /**
     * 已參加學生數量
     */
    private Integer studentCount;

    /**
     * 可參加最大學生人數
     */
    private Integer maxCount;

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
     * 報名截止日期
     */
    private String deadline;

    /**
     * 上課地點
     */
    private String location;


    public static CourseVo ofVo(Course source) {
        CourseVo target = new CourseVo();


        return target;
    }
}
