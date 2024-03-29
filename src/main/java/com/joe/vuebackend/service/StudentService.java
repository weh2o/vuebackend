package com.joe.vuebackend.service;

import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.vo.StudentVo;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    /**
     * 添加學生
     * @param studentVo
     * @return
     */
    Optional<Student> addStudent(StudentVo studentVo);

    List<Student> findAll();

    List<StudentVo> findAllVo();
}
