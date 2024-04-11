package com.joe.vuebackend.service.impl;

import com.joe.vuebackend.bean.CourseInfo;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.PageResult;
import com.joe.vuebackend.domain.Course;
import com.joe.vuebackend.repository.CourseRepository;
import com.joe.vuebackend.repository.condition.CourseCondition;
import com.joe.vuebackend.repository.spec.CourseSpec;
import com.joe.vuebackend.service.CourseService;
import com.joe.vuebackend.utils.CourseHelper;
import com.joe.vuebackend.vo.CourseVo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Setter(onMethod_ = @Autowired)
    private CourseRepository courseRepository;

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
        List<CourseVo> vos = resultPage.getContent()
                .stream().map(CourseVo::ofVo)
                .collect(Collectors.toCollection(LinkedList::new));
        target.setData(vos);
        return target;
    }

    @Override
    public HttpResult<String> save(CourseInfo info) {
        try {
            Course course = CourseInfo.ofCourse(info);
            Course dbCourse = courseRepository.save(course);
            CourseHelper.infoSetCourse(dbCourse, info);
            CourseHelper.infoSetTeacher(dbCourse, info);
            courseRepository.save(dbCourse);
        } catch (Exception e) {
            log.error(String.format("Course保存失敗: %s", e.getMessage()));
            return HttpResult.success("添加失敗", e.getMessage());
        }
        return HttpResult.success("添加成功");
    }

    @Override
    public HttpResult<String> remove(String id) {
        courseRepository.deleteById(id);
        boolean exists = courseRepository.existsById(id);
        if (exists){
            return HttpResult.fail("刪除失敗");
        }
        return HttpResult.success("刪除成功");
    }
}
