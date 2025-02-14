package com.joe.vuebackend.exception.teacherNo;

import lombok.Data;

/**
 * 教師編號重複異常
 */
@Data
public class TeacherNoDuplicateException extends RuntimeException{

    /**
     * 構造方法
     * @param no 重複的教師證編號
     */
    public TeacherNoDuplicateException(String no) {
        super(no);
    }
}
