package com.joe.vuebackend.service.impl;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.domain.Teacher;
import com.joe.vuebackend.repository.TeacherRepository;
import com.joe.vuebackend.repository.condition.TeacherCondition;
import com.joe.vuebackend.repository.spec.TeacherSpec;
import com.joe.vuebackend.service.TeacherService;
import com.joe.vuebackend.vo.TeacherVo;
import com.joe.vuebackend.vo.UserInfo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Setter(onMethod_ = @Autowired)
    private TeacherRepository teacherRepository;

    public HttpResult<String> updateInfo(Teacher teacher, UserInfo userInfo){
        Teacher newInfo = TeacherVo.ofTeacher(userInfo);
        teacher.setName(newInfo.getName());
        teacher.setGender(newInfo.getGender());
        teacher.setBirth(newInfo.getBirth());
        teacher.setMail(newInfo.getMail());
        teacher.setPhone(newInfo.getPhone());
        teacher.setAddress(newInfo.getAddress());
        teacherRepository.save(teacher);
        return HttpResult.success("修改成功");
    }

    @Override
    public HttpResult<List<TeacherVo>> findAll(TeacherCondition condition) {
        TeacherSpec spec = TeacherSpec.initSpec(condition);
        List<Teacher> list = teacherRepository.findAll(spec);
        List<TeacherVo> vos = list.stream().map(TeacherVo::ofVo).toList();
        return HttpResult.success(vos);
    }
}
