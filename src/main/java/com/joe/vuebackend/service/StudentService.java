package com.joe.vuebackend.service;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.PageResult;
import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.repository.condition.StudentCondition;
import com.joe.vuebackend.vo.StudentVo;
import com.joe.vuebackend.vo.UserInfo;

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

    PageResult<StudentVo> findAllVo(StudentCondition condition);

    Optional<StudentVo> findOneVo(String id);

    HttpResult<String> remove(String id);

    PageResult<StudentVo> searchByNameOrNo(StudentCondition condition);

    /**
     * 修改學生基本資料
     *
     * @param stu      被修改的學生
     * @param userInfo 新的資本資料
     * @return
     */
    HttpResult<String> updateInfo(Student stu, UserInfo userInfo);

}
