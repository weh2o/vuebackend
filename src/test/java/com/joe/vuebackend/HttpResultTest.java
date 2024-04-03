package com.joe.vuebackend;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.vo.StudentVo;
import org.junit.jupiter.api.Test;

public class HttpResultTest {

    @Test
    void fail(){
        HttpResult<User> f1 = HttpResult.fail();
        HttpResult<Student> f2 = HttpResult.fail("我錯了");
        HttpResult<Student> f3 = HttpResult.fail(500, "你知道錯了嗎?");
        HttpResult<StudentVo> f4 = HttpResult.fail("你大錯特錯", new StudentVo());
        HttpResult<User> f5 = HttpResult.fail(403, "你無藥可救了", new User());
        System.out.println();
    }

    @Test
    void success(){
        HttpResult<Object> s1 = HttpResult.success();
        HttpResult<Object> s2 = HttpResult.success("小有成就");
        HttpResult<StudentVo> s3 = HttpResult.success(new StudentVo());
        HttpResult<User> s4 = HttpResult.success("躺著賺", new User());
        System.out.println();

    }
}
