package com.joe.vuebackend.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 上課地點
 */
@Data
@Entity
@Table(name = "j_course_location")
public class CourseLocation extends BaseEntity{

    /**
     * 地點編號
     */
    private String code;

    /**
     * 地點名稱(英文)
     */
    private String name;

    /**
     * 地點名稱(中文)
     */
    private String nameZh;

    /**
     * 詳細地址
     */
    private String address;

    /**
     * 課程
     */
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Course> courseList;

    public void addCourseList(Course course){
        if (Objects.isNull(courseList)){
            courseList = new ArrayList<>();
        }
        if (Objects.nonNull(course)){
            courseList.add(course);
        }
    }
}
