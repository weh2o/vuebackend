package com.joe.vuebackend;

import com.joe.vuebackend.constant.CourseLocationType;
import com.joe.vuebackend.domain.CourseLocation;
import com.joe.vuebackend.repository.CourseLocationRepository;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseLocationTest {

    @Setter(onMethod_ = @Autowired)
    private CourseLocationRepository locationRepository;

    @Test
    void test(){
        CourseLocation location = new CourseLocation();
        location.setCode(CourseLocationType.LIBRARY.getCode());
        location.setName(CourseLocationType.LIBRARY.getName());
        location.setNameZh(CourseLocationType.LIBRARY.getNameZh());
        location.setAddress("我也不知道");
        locationRepository.save(location);
    }
}
