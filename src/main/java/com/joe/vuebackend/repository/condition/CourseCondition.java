package com.joe.vuebackend.repository.condition;

import lombok.Data;

@Data
public class CourseCondition extends BaseCondition {

    /**
     * 課程名稱或老師名稱
     */
    private String courseNameOrTeacher;
}
