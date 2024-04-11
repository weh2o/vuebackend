package com.joe.vuebackend.repository.condition;

import lombok.Data;

@Data
public class TeacherCondition extends BaseCondition{

    /**
     * 姓名或教師證
     */
    protected String nameOrNo;
}
