package com.joe.vuebackend;

import com.joe.vuebackend.constant.CourseLocationType;
import com.joe.vuebackend.domain.Course;
import com.joe.vuebackend.domain.CourseLocation;
import com.joe.vuebackend.domain.Teacher;
import com.joe.vuebackend.repository.CourseLocationRepository;
import com.joe.vuebackend.repository.CourseRepository;
import com.joe.vuebackend.repository.TeacherRepository;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CourseTest {

    @Setter(onMethod_ = @Autowired)
    private CourseRepository courseRepository;

    @Setter(onMethod_ = @Autowired)
    private CourseLocationRepository locationRepository;

    @Setter(onMethod_ = @Autowired)
    private TeacherRepository teacherRepository;

    @Test
    void test() {
        Course course = new Course();
        course.setName("如何不被老婆發現私房錢");
        course.setDeadline(LocalDate.of(2030, 1, 1));
        course.setMaxCount(30);
        course.setStartDate(LocalDate.now());
        course.setEndDate(LocalDate.of(2030, 1, 1));
        course.setStartTime(LocalTime.of(14, 0));
        course.setEndTime(LocalTime.of(15, 0));
        Course dbCourse = courseRepository.save(course);

        List<Teacher> teacherList = teacherRepository.findByName("超級老師");
        dbCourse.setTeacher(teacherList.get(0));

        Optional<CourseLocation> locationOptional = locationRepository.findByName(CourseLocationType.LIBRARY.getName());
        if (locationOptional.isPresent()) {
            dbCourse.setLocation(locationOptional.get());
        }
        courseRepository.save(dbCourse);
    }
}
