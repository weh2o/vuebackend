package com.joe.vuebackend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "j_teacher")
@ToString(callSuper = true)
public class Teacher extends User{

    /**
     * 教師證
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "teacher_no_id",
            foreignKey = @ForeignKey(name = "fk_teacher_teacher_no")
    )
    private TeacherNo no;

    /**
     * 課程
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
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
