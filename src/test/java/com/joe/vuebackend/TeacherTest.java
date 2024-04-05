package com.joe.vuebackend;

import com.joe.vuebackend.domain.Teacher;
import com.joe.vuebackend.domain.TeacherNo;
import com.joe.vuebackend.repository.TeacherNoRepository;
import com.joe.vuebackend.repository.TeacherRepository;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.Optional;

@SpringBootTest
public class TeacherTest {

    @Setter(onMethod_ = @Autowired)
    private TeacherNoRepository teacherNoRepository;

    @Setter(onMethod_ = @Autowired)
    private TeacherRepository teacherRepository;


    private static final String NO1 = "99999999";
    private static final String NO2 = "12345678";


    @Test
    @Commit
    void find(){
        Optional<Teacher> optional = teacherRepository.findByNo_No(NO1);
        System.out.println();
    }

    @Test
    @Commit
    void initTeacher(){
        Teacher teacher = new Teacher();
        teacher.setAccount("test");
        teacher.setPassword("1111");
        Optional<TeacherNo> no = teacherNoRepository.findByNo(NO1);
        Teacher dbT = teacherRepository.save(teacher);
        if (no.isPresent()){
            dbT.setNo(no.get());
        }
        teacherRepository.save(dbT);
    }

    @Test
    @Commit
    void initTeacherNo(){
        TeacherNo no1 = new TeacherNo();
        no1.setNo(NO1);
        no1.setAvailable(Boolean.FALSE);
        teacherNoRepository.save(no1);

        TeacherNo no2 = new TeacherNo();
        no2.setNo(NO2);
        no2.setAvailable(Boolean.TRUE);
        teacherNoRepository.save(no2);
    }

    @Test
    void findAvailable(){
        Optional<TeacherNo> result1 = teacherNoRepository.findByNoAndAvailableIsTrue(NO1);
        Optional<TeacherNo> result2 = teacherNoRepository.findByNoAndAvailableIsTrue(NO2);
        System.out.println();
    }
}