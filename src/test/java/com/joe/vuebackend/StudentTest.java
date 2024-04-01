package com.joe.vuebackend;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.PageResult;
import com.joe.vuebackend.constant.Gender;
import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.repository.StudentRepository;
import com.joe.vuebackend.repository.condition.StudentCondition;
import com.joe.vuebackend.service.StudentService;
import com.joe.vuebackend.vo.StudentVo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Slf4j
public class StudentTest {

    @Setter(onMethod_ = @Autowired)
    private StudentRepository repository;

    @Setter(onMethod_ = @Autowired)
    private StudentService service;

    @Test
    void add() {
        Student target = new Student();
        target.setName("新之助");
        target.setAge(6);
        target.setNo("3345678");
        target.setGender(Gender.BOY);
        target.setBirth(LocalDate.now());
        target.setPhone("0987123456");
        target.setMail("newJJ@gmail.com");
        repository.save(target);

        Student target2 = new Student();
        target2.setName("美牙");
        target2.setAge(25);
        target2.setNo("6597132");
        target2.setGender(Gender.GIRL);
        target2.setBirth(LocalDate.now());
        target2.setPhone("0987777666");
        target2.setMail("beauti@gmail.com");
        repository.save(target2);
    }

    @Test
    void ofStuTest() {
        StudentVo vo = new StudentVo();
        vo.setBirth("2024-03-11");
        Student of = StudentVo.ofStudent(vo);
        log.info("結果: {}", of.getBirth());
    }

    @Test
    void remove() {
        HttpResult<String> remove = service.remove("056a8da8-f714-46bc-917b-8e07cd2890d3");
        System.out.println(remove);
    }


    @Test
    void initData() {
        for (int i = 0; i < 50; i++) {
            Student target = new Student();
            target.setName("機器人" + i);
            target.setAge(i);
            target.setNo(10000 + i + "");
            if (i % 2 == 0) {
                target.setGender(Gender.BOY);
            } else {
                target.setGender(Gender.GIRL);
            }

            target.setBirth(LocalDate.now());
            target.setPhone("098710000" + i);
            target.setMail(i + "newJJ@gmail.com");
            repository.save(target);
        }
    }

    @Test
    void pageTest() {
        StudentCondition condition = new StudentCondition();
        condition.setPage(1);
        condition.setPageSize(5);
        condition.setOrder("desc");
        condition.setProp("sex");
        PageResult<StudentVo> result = service.findAllVo(condition);
        log.info("總數: {}", result.getTotal());
        System.out.println(result);
    }

    @Test
    void findByNameOrNo() {
        List<Student> result = repository.findByNameOrNo("機器人0", "10003");
        System.out.println();
    }

    @Test
    void other() {

    }
}
