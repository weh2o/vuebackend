package com.joe.vuebackend;

import com.joe.vuebackend.constant.CourseLocationType;
import com.joe.vuebackend.domain.CourseLocation;
import com.joe.vuebackend.repository.CourseLocationRepository;
import com.joe.vuebackend.utils.CRUDLogHelper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Slf4j
public class CourseLocationTest {

    @Setter(onMethod_ = @Autowired)
    private CourseLocationRepository locationRepository;


    /**
     * 初始化所有地點
     */
    @Test
    @Commit
    void initAll() {
        CRUDLogHelper logHelper = CRUDLogHelper.build("初始化所有地點 initAll()");
        for (CourseLocationType type : CourseLocationType.values()) {
            String code = type.getCode();
            String name = type.getName();
            boolean exists = locationRepository.existsByCode(code);
            if (!exists) {
                try {
                    CourseLocation location = new CourseLocation();
                    location.setCode(code);
                    location.setName(name);
                    location.setNameZh(type.getNameZh());
                    locationRepository.save(location);
                    logHelper.saveSuccess(name);
                } catch (Exception e) {
                    logHelper.saveFail(name, e);
                }
            } else {
                logHelper.saveDuplicateFail(name);
            }
        }
    }

    @Test
    void test() {
        CourseLocation location = new CourseLocation();
        location.setCode(CourseLocationType.LIBRARY.getCode());
        location.setName(CourseLocationType.LIBRARY.getName());
        location.setNameZh(CourseLocationType.LIBRARY.getNameZh());
        location.setAddress("我也不知道");
        locationRepository.save(location);
    }
}
