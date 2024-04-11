package com.joe.vuebackend.utils;

import com.joe.vuebackend.bean.CourseInfo;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.constant.CourseLocationType;
import com.joe.vuebackend.domain.Course;
import com.joe.vuebackend.domain.CourseLocation;
import com.joe.vuebackend.domain.Teacher;
import com.joe.vuebackend.repository.CourseLocationRepository;
import com.joe.vuebackend.repository.TeacherRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class CourseHelper {

    /**
     * 將 CourseInfo的地點設置給Course
     * <br/>
     * 如果地點不存在資料庫，則創建新的地點
     *
     * @param target 目標
     * @param source 來源
     * @return 是否成功設置
     */
    public static HttpResult<String> infoSetCourseLocation(Course target, CourseInfo source) {

        CourseLocationRepository locationRepository = SpringUtils.getBean(CourseLocationRepository.class);
        String locationId = source.getLocation();

        // 資料相同
        if (Objects.nonNull(target.getLocation())){
            String targetId = target.getLocation().getId();
            if (targetId.equals(locationId)){
                return HttpResult.fail("地點資料一樣，無需修改");
            }
        }


        // 資料不同
        if (Objects.nonNull(locationRepository)) {
            // 非資料庫內的地點
            if (CourseLocationType.OTHER.getName().equals(locationId)
                    && StringUtils.isNotEmpty(source.getOtherLocation())
            ) {
                CourseLocation newLocation = new CourseLocation();
                newLocation.setNameZh(source.getOtherLocation());
                newLocation.addCourseList(target);
                CourseLocation dbLocation = locationRepository.save(newLocation);
                target.setLocation(dbLocation);
                return HttpResult.success("設置成功，並添加新地點");
            } else {   //資料庫內已存在的地點
                Optional<CourseLocation> optional = locationRepository.findById(locationId);
                if (optional.isPresent()) {
                    CourseLocation oldDbLocation = optional.get();
                    oldDbLocation.addCourseList(target);
                    target.setLocation(oldDbLocation);
                    return HttpResult.success("設置成功");
                }
                return HttpResult.fail("設置失敗，地點資料錯誤");
            }
        }
        return HttpResult.fail("設置失敗");
    }

    /**
     * 將 CourseInfo的老師資料設置給Course
     *
     * @param target 目標
     * @param source 來源
     * @return
     */
    public static HttpResult<String> infoSetTeacher(Course target, CourseInfo source) {
        TeacherRepository teacherRepository = SpringUtils.getBean(TeacherRepository.class);
        if (Objects.nonNull(teacherRepository)) {

            // 資料相同
            if (Objects.nonNull(target.getTeacher())){
                String targetId = target.getTeacher().getId();
                if (targetId.equals(source.getTeacherId())){
                    return HttpResult.fail("老師資料一樣，無須設置");
                }
            }

            // 資料不同
            Optional<Teacher> optional = teacherRepository.findById(source.getTeacherId());
            if (optional.isPresent()) {
                Teacher teacher = optional.get();
                teacher.addCourseList(target);
                target.setTeacher(teacher);
                return HttpResult.success("設置成功");
            }
            return HttpResult.fail("設置失敗，老師資料錯誤");
        }
        return HttpResult.fail("設置失敗");
    }
}
