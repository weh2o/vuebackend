package com.joe.vuebackend.repository.condition;

import lombok.Data;

@Data
public class StudentCondition extends BaseCondition{

    /**
     * 姓名或學生證
     */
    protected String nameOrNo;
}
