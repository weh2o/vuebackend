package com.joe.vuebackend.service.impl;

import com.joe.vuebackend.bean.CourseInfo;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.PageResult;
import com.joe.vuebackend.domain.Course;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.repository.CourseRepository;
import com.joe.vuebackend.repository.UserRepository;
import com.joe.vuebackend.repository.condition.CourseCondition;
import com.joe.vuebackend.repository.spec.CourseSpec;
import com.joe.vuebackend.service.CourseService;
import com.joe.vuebackend.utils.CourseHelper;
import com.joe.vuebackend.vo.CourseVo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Setter(onMethod_ = @Autowired)
    private CourseRepository courseRepository;

    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;

    @Override
    public PageResult<CourseVo> findAllPage(CourseCondition condition) {
        PageResult<CourseVo> target = new PageResult<>();
        Page<Course> resultPage = courseRepository.findAll(
                CourseSpec.initSpec(condition),
                PageRequest.of(
                        condition.getPage(),
                        condition.getPageSize()
                )
        );

        target.setTotal(resultPage.getTotalElements());
        List<CourseVo> vos = new LinkedList<>();
        for (Course course : resultPage.getContent()) {
            CourseVo vo = CourseVo.ofVo(course, condition.getUserId());
            vos.add(vo);
        }
        target.setData(vos);
        return target;
    }

    @Override
    public HttpResult<String> save(CourseInfo info) {

        if (StringUtils.isNotEmpty(info.getId())) {
            return update(info);
        } else {
            try {
                Course course = CourseInfo.ofCourse(info);
                Course dbCourse = courseRepository.save(course);
                CourseHelper.infoSetCourseLocation(dbCourse, info);
                CourseHelper.infoSetTeacher(dbCourse, info);
                courseRepository.save(dbCourse);
            } catch (Exception e) {
                log.error(String.format("Course保存失敗: %s", e));
                return HttpResult.fail("新增失敗", e.getMessage());
            }
            return HttpResult.success("新增成功");
        }
    }

    @Override
    public HttpResult<String> remove(String id) {
        courseRepository.deleteById(id);
        boolean exists = courseRepository.existsById(id);
        if (exists) {
            return HttpResult.fail("刪除失敗");
        }
        return HttpResult.success("刪除成功");
    }

    @Override
    public HttpResult<String> update(CourseInfo info) {
        try {
            Optional<Course> optional = courseRepository.findById(info.getId());
            if (optional.isPresent()) {
                Course dbCourse = optional.get();
                Course newCourse = CourseInfo.ofCourse(info);
                // 基本類型複製
                BeanUtils.copyProperties(newCourse, dbCourse, "id", "location", "teacher");
                // 引用類型設置
                CourseHelper.infoSetTeacher(dbCourse, info);
                CourseHelper.infoSetCourseLocation(dbCourse, info);
                courseRepository.save(dbCourse);
                return HttpResult.success("修改成功");
            }
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
        return HttpResult.fail("修改失敗");
    }

    @Override
    public HttpResult<String> signUp(String courseId, String userId) {
        try {
            Optional<Course> courseOptional = courseRepository.findById(courseId);
            Optional<User> userOptional = userRepository.findById(userId);

            if (courseOptional.isEmpty()) {
                throw new RuntimeException("課程資料錯誤");
            }

            if (userOptional.isEmpty()) {
                throw new RuntimeException("使用者資料錯誤");
            }
            Course course = courseOptional.get();
            User user = userOptional.get();
            course.addUser(user);
            user.addCourse(course);
            courseRepository.save(course);
            String msg = String.format("報名 %s 成功", course.getName());
            return HttpResult.success(msg);
        } catch (Exception e) {
            return HttpResult.fail("報名失敗", e.getMessage());
        }
    }
}
