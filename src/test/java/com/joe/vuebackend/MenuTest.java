package com.joe.vuebackend;

import com.joe.vuebackend.constant.RoleType;
import com.joe.vuebackend.repository.MenuRepository;
import com.joe.vuebackend.repository.RoleRepository;
import com.joe.vuebackend.service.MenuService;
import com.joe.vuebackend.service.RoleService;
import com.joe.vuebackend.utils.MenuHelper;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
public class MenuTest {

    @Setter(onMethod_ = @Autowired)
    private MenuRepository menuRepository;

    @Setter(onMethod_ = @Autowired)
    private RoleRepository roleRepository;

    @Setter(onMethod_ = @Autowired)
    private RoleService roleService;

    @Setter(onMethod_ = @Autowired)
    private MenuService menuService;


    @Test
    void get() {
        List<String> allText = RoleType.getAllText();
        System.out.println(allText);
    }

    @Test
    @Commit
    void initMenu() {
        MenuHelper.build(
                "首頁",
                "/home",
                "House",
                0,
                RoleType.getAllText()
        );

        MenuHelper.build(
                "學生管理",
                "/studentManagement",
                "User",
                1,
                List.of(RoleType.ADMIN.getText(),
                        RoleType.TEACHER.getText()
                )
        );

        MenuHelper.build(
                "課程管理",
                "/course",
                "Memo",
                2,
                List.of(RoleType.ADMIN.getText(),
                        RoleType.TEACHER.getText(),
                        RoleType.STUDENT.getText()
                )
        );
    }
}
