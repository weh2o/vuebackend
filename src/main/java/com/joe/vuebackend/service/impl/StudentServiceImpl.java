package com.joe.vuebackend.service.impl;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.PageData;
import com.joe.vuebackend.bean.PageResult;
import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.repository.StudentRepository;
import com.joe.vuebackend.service.StudentService;
import com.joe.vuebackend.vo.StudentVo;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Setter(onMethod_ = @Autowired)
    private StudentRepository studentRepository;

    @Override
    public Optional<Student> save(StudentVo vo) {
        Optional<Student> target;
        Student stu = StudentVo.ofStudent(vo);
        // 沒id新增操作，有id修改操作
        if (StringUtils.isEmpty(vo.getId())) {
            target = Optional.of(studentRepository.save(stu));
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
        return studentRepository.findAll();
    }

    @Override
    public PageResult<StudentVo> findAllVo(PageData pageData) {
        PageResult<StudentVo> result = new PageResult<>();
        long count = studentRepository.count();
        // 默認排序
        Sort sort = Sort.by(Sort.Direction.ASC, "age");

        // 定製排序
        if (StringUtils.isNotEmpty(pageData.getProp()) &&
                StringUtils.isNotEmpty(pageData.getOrder())
        ) {
            String order = pageData.getOrder().substring(0, 3);
            Sort.Direction direction;
            if ("asc".equals(order)) {
                direction = Sort.Direction.ASC;
            } else {
                direction = Sort.Direction.DESC;
            }
            String prop = pageData.getProp();
            // 性別特別處理
            if ("sex".equals(prop)) {
                prop = "gender";
            }
            sort = Sort.by(direction, prop);
        }

        Page<Student> resultPage = studentRepository.findAll(
                PageRequest.of(
                        pageData.getPage() - 1,
                        pageData.getPageSize(),
                        sort
                )
        );
        result.setTotal(count);
        List<StudentVo> list = resultPage.getContent().stream().map(StudentVo::ofVo).toList();
        result.setData(list);
        return result;
    }

    @Override
    public Optional<StudentVo> findOneVo(String id) {
        Optional<StudentVo> target = Optional.empty();
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            StudentVo vo = StudentVo.ofVo(optional.get());
            target = Optional.of(vo);
        }
        return target;
    }

    @Override
    public HttpResult<String> remove(String id) {
        HttpResult<String> result = HttpResult.success();
        studentRepository.deleteById(id);
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            result = HttpResult.fail();
        }
        return result;
    }

    public Optional<Student> update(Student stu) {
        Student target = null;
        Optional<Student> optional = studentRepository.findById(stu.getId());
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
            target = studentRepository.save(dbStu);
        }
        return Optional.ofNullable(target);
    }

}
