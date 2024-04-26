package com.joe.vuebackend.service.impl;

import com.joe.vuebackend.bean.DeleteResult;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.PageResult;
import com.joe.vuebackend.bean.StudentInfo;
import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.repository.StudentRepository;
import com.joe.vuebackend.repository.condition.StudentCondition;
import com.joe.vuebackend.repository.spec.StudentSpec;
import com.joe.vuebackend.service.StudentService;
import com.joe.vuebackend.vo.StudentVo;
import com.joe.vuebackend.vo.UserInfo;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Setter(onMethod_ = @Autowired)
    private StudentRepository stuRepository;

    @Override
    public Optional<Student> save(StudentVo vo) {
        Optional<Student> target;
        Student stu = StudentVo.ofStudent(vo);
        // 沒id新增操作，有id修改操作
        if (StringUtils.isEmpty(vo.getId())) {
            target = Optional.of(stuRepository.save(stu));
        } else {
            target = update(stu);
        }
        return target;
    }

    @Override
    public Optional<StudentVo> saveReturnVo(StudentVo vo) {
        StudentVo target = null;
        Optional<Student> optional = save(vo);
        if (optional.isPresent()) {
            target = StudentVo.ofVo(optional.get());
        }
        return Optional.ofNullable(target);
    }

    @Override
    public List<Student> findAll() {
        return stuRepository.findAll();
    }

    @Override
    public PageResult<StudentVo> findAllVo(StudentCondition condition) {
        PageResult<StudentVo> result = new PageResult<>();

        Page<Student> resultPage = stuRepository.findAll(
                StudentSpec.initSpec(condition),
                PageRequest.of(
                        condition.getPage(),
                        condition.getPageSize()
                )
        );
        result.setTotal(resultPage.getTotalElements());
        List<StudentVo> vos = resultPage.getContent()
                .stream().map(StudentVo::ofVo)
                .collect(Collectors.toCollection(LinkedList::new));
        result.setData(vos);
        return result;
    }

    @Override
    public Optional<StudentVo> findOneVo(String id) {
        Optional<StudentVo> target = Optional.empty();
        Optional<Student> optional = stuRepository.findById(id);
        if (optional.isPresent()) {
            StudentVo vo = StudentVo.ofVo(optional.get());
            target = Optional.of(vo);
        }
        return target;
    }

    @Override
    public HttpResult<String> remove(String id) {
        HttpResult<String> result = HttpResult.success();
        stuRepository.deleteById(id);
        Optional<Student> optional = stuRepository.findById(id);
        if (optional.isPresent()) {
            result = HttpResult.fail();
        }
        return result;
    }

    // 此方法被合併在findAllVo()，暫時放置
    @Override
    public PageResult<StudentVo> searchByNameOrNo(StudentCondition condition) {
        PageResult<StudentVo> result = new PageResult<>();
        List<Student> list = stuRepository.findAll(StudentSpec.initSpec(condition));
        result.setTotal((long) list.size());
        List<StudentVo> vos = list.stream().map(StudentVo::ofVo).toList();
        result.setData(vos);
        return result;
    }

    public Optional<Student> update(Student stu) {
        Student target = null;
        Optional<Student> optional = stuRepository.findById(stu.getId());
        if (optional.isPresent()) {
            // 應該可用vo的ofStudent簡化，先放著
            Student dbStu = optional.get();
            dbStu.setName(stu.getName());
            dbStu.setGender(stu.getGender());
            dbStu.setAge(stu.getAge());
            dbStu.setNo(stu.getNo());
            dbStu.setMail(stu.getMail());
            dbStu.setPhone(stu.getPhone());
            dbStu.setBirth(stu.getBirth());
            target = stuRepository.save(dbStu);
        }
        return Optional.ofNullable(target);
    }

    public HttpResult<String> updateInfo(Student stu, UserInfo userInfo) {
        Student newInfo = StudentVo.ofStudent(userInfo);
        stu.setName(newInfo.getName());
        stu.setGender(newInfo.getGender());
        stu.setNo(newInfo.getNo());
        stu.setBirth(newInfo.getBirth());
        stu.setMail(newInfo.getMail());
        stu.setPhone(newInfo.getPhone());
        stu.setAddress(newInfo.getAddress());
        stuRepository.save(stu);
        return HttpResult.success("修改成功");
    }

    @Override
    public HttpResult<DeleteResult<StudentInfo>> removeAllById(List<StudentInfo> infos) {
        DeleteResult<StudentInfo> deleteResult = new DeleteResult<>();
        for (StudentInfo stu : infos) {
            String id = stu.getId();
            boolean exists = stuRepository.existsById(id);
            if (exists) {
                stuRepository.deleteById(id);
                deleteResult.addSuccess(stu);
            } else {
                deleteResult.addFail(stu);
            }
        }

        if (CollectionUtils.isEmpty(deleteResult.getFailList())) {
            return HttpResult.success("刪除成功", deleteResult);
        } else if (CollectionUtils.isEmpty(deleteResult.getSuccessList())) {
            return HttpResult.fail("刪除失敗", deleteResult);
        } else {
            return HttpResult.success("部分刪除成功", deleteResult);
        }
    }
}
