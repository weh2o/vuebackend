package com.joe.vuebackend.validation;

import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.repository.StudentRepository;
import com.joe.vuebackend.vo.StudentVo;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;


@Component
public class StudentValidation implements Validator {

    @Setter(onMethod_ = @Autowired)
    private StudentRepository stuRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return StudentVo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentVo main = (StudentVo) target;
        validateNo(main, errors);
    }

    // 學生證驗證: 判斷是否重複
    public void validateNo(StudentVo main, Errors errors) {
        String no = main.getNo();
        if (StringUtils.isNotEmpty(no)) {
            Optional<Student> optional = stuRepository.findByNo(no);
            if (optional.isPresent()) {
                errors.rejectValue("no", "duplicate-no", "學生證重複");
            }
        }

    }
}