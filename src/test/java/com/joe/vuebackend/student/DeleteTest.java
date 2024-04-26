package com.joe.vuebackend.student;

import com.joe.vuebackend.bean.DeleteResult;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.StudentInfo;
import com.joe.vuebackend.service.StudentService;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;

@SpringBootTest
public class DeleteTest {

    @Setter(onMethod_ = @Autowired)
    private StudentService studentService;


    @Test
    @Commit
    void successAndFailDelete() {
        ArrayList<StudentInfo> infos = new ArrayList<>();
        StudentInfo s1 = new StudentInfo();
        s1.setId("5397e43c-b7f1-4fe3-99b1-ab4914eabecb");
        infos.add(s1);

        StudentInfo s2 = new StudentInfo();
        s2.setId("1002");
        infos.add(s2);

        HttpResult<DeleteResult<StudentInfo>> result = studentService.removeAllById(infos);
        System.out.println();
    }

    @Test
    @Commit
    void FailDelete() {
        ArrayList<StudentInfo> infos = new ArrayList<>();
        StudentInfo s1 = new StudentInfo();
        s1.setId("1001");
        infos.add(s1);

        StudentInfo s2 = new StudentInfo();
        s2.setId("1002");
        infos.add(s2);

        HttpResult<DeleteResult<StudentInfo>> result = studentService.removeAllById(infos);
        System.out.println();
    }
}
