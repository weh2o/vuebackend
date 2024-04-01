package com.joe.vuebackend.service;

import com.joe.vuebackend.bean.PageResult;
import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.PageData;
import com.joe.vuebackend.vo.StudentVo;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    /**
     * 保存學生資料
     *
     * @param vo
     * @return 學生
     */
    Optional<Student> save(StudentVo vo);

    /**
     * 保存學生資料，返回vo
     *
     * @param vo
     * @return 學生vo
     */
    Optional<StudentVo> saveReturnVo(StudentVo vo);

    List<Student> findAll();

    PageResult<StudentVo> findAllVo(PageData pageData);

    Optional<StudentVo> findOneVo(String id);

    HttpResult<String> remove(String id);
}
