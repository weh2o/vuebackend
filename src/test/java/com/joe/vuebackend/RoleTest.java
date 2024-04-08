package com.joe.vuebackend;

import com.joe.vuebackend.constant.RoleType;
import com.joe.vuebackend.domain.Role;
import com.joe.vuebackend.service.RoleService;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RoleTest {

    @Setter(onMethod_ = @Autowired)
    private RoleService roleService;

    @Test
    @Transactional(readOnly = true)
    void findByNames(){
        ArrayList<String> list = new ArrayList<>();
        list.add(RoleType.ADMIN.getText());
        list.add(RoleType.TEACHER.getText());
        List<Role> result = roleService.findByNames(list);
        System.out.println(result);
    }

    @Test
    void findAll(){

    }
}
