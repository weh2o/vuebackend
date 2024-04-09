package com.joe.vuebackend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * 課程
 */
@Data
@Table(name = "j_course")
@Entity
@ToString(exclude = {"location", "teacher", "students"})
public class Course extends BaseEntity{

    /**
     * 課程名稱
     */
    @Column(name = "name")
    private String name;

    /**
     * 教師
     */
    @ManyToOne()
    @JoinColumn(
            name = "teacher_id",
            foreignKey = @ForeignKey(name = "fk_course_teacher")
    )
    private Teacher teacher;

    /**
     * 參加學生
     */
    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    foreignKey = @ForeignKey(name = "fk_course_student")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    foreignKey = @ForeignKey(name = "fk_student_course")
            ),
            uniqueConstraints = @UniqueConstraint(
                    name = "uk_course_student",
                    columnNames = {"course_id", "student_id"}
            )
    )
    private List<Student> students;

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
    private LocalDate startDate;

    /**
     * 課程結束日期
     */
    private LocalDate endDate;


    /**
     * 上課開始時間
     */
    private LocalTime startTime;

    /**
     * 上課結束時間
     */
    private LocalTime endTime;

    /**
     * 報名截止日期
     */
    private LocalDate deadline;

    /**
     * 上課地點
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "course_location_id",
            foreignKey = @ForeignKey(name = "fk_course_course_location")
    )
    private CourseLocation location;

}
