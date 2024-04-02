package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.repository.condition.StudentCondition;
import com.joe.vuebackend.repository.spec.StudentSpec;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {

    @Setter(onMethod_ = @Autowired)
    StudentRepository repository;


    @Test
    void findAllVo() {
        // 條件
        StudentCondition condition = new StudentCondition();
        condition.setPage(1);
        condition.setPageSize(5);
        condition.setNameOrNo("2");

        // 分頁
        PageRequest of = PageRequest.of(
                condition.getPage() - 1,
                condition.getPageSize()
        );

        // spec
        StudentSpec spec = StudentSpec.initSpec(condition);
        Page<Student> page = repository.findAll(spec, of);
        List<Student> content = page.getContent();
        System.out.println(page.getTotalElements());
        System.out.println(content);
    }
}