package com.joe.vuebackend.vo;

import com.joe.vuebackend.domain.Course;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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
    private Integer count = 0;

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

    /**
     * 是否報名
     * <p>0 尚未報名</p>
     * <p>1 已報名</p>
     */
    private String isSignUp = "0";

    /**
     * 是否為課程創建者
     * <p>0 不是</p>
     * <p>1 是</p>
     */
    private String isSelf = "0";

    public static CourseVo ofVo(Course source) {
        return ofVo(source, null);
    }

    public static CourseVo ofVo(Course source, String userId) {
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
        if (CollectionUtils.isNotEmpty(source.getUsers())) {
            target.setCount(source.getUsers().size());
        }

        // 可報名總人數
        if (Objects.nonNull(source.getMaxCount())) {
            target.setMaxCount(source.getMaxCount());
        }

        // 課程時間
        LocalDate startDate = source.getStartDate();
        LocalDate endDate = source.getEndDate();
        if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
            String start = DateUtil.formatLocalDate(startDate);
            String end = DateUtil.formatLocalDate(endDate);
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
            target.setStartTime(DateUtil.formatLocalTime(startTime));
            target.setEndTime(DateUtil.formatLocalTime(endTime));
        }

        // 截止日期
        if (Objects.nonNull(source.getDeadline())) {
            String deadlineStr = DateUtil.formatLocalDate(source.getDeadline());
            target.setDeadline(deadlineStr);
        }

        // 上課地點名稱
        if (Objects.nonNull(source.getLocation())) {
            target.setLocation(source.getLocation().getNameZh());
        }

        // 有傳入使用者識別碼的話
        if (StringUtils.isNotEmpty(userId)) {

            // 是否為課程創建者
            if (Objects.nonNull(source.getTeacher())) {
                if (source.getTeacher().getId().equals(userId)) {
                    target.setIsSelf("1");
                }
            }

            // 是否已報名
            if (CollectionUtils.isNotEmpty(source.getUsers())) {
                List<User> courseUsers = source.getUsers();
                for (User courseUser : courseUsers) {
                    if (courseUser.getId().equals(userId)) {
                        target.setIsSignUp("1");
                        break;
                    }
                }
            }
        }

        return target;
    }
}
