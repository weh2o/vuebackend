package com.joe.vuebackend.service.impl;

import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.repository.StudentRepository;
import com.joe.vuebackend.service.StudentService;
import com.joe.vuebackend.vo.StudentVo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Setter(onMethod_ = @Autowired)
    private StudentRepository studentRepository;

    @Override
    public Optional<Student> addStudent(StudentVo studentVo) {
        Student stu = StudentVo.ofStudent(studentVo);
        return Optional.of(studentRepository.save(stu));
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<StudentVo> findAllVo() {
        List<Student> list = studentRepository.findAll();
        return list.stream().map(StudentVo::ofVo).toList();
    }
}
