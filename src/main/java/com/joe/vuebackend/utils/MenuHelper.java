package com.joe.vuebackend.utils;

import com.joe.vuebackend.bean.InitMenuBean;
import com.joe.vuebackend.constant.RoleType;
import com.joe.vuebackend.domain.Menu;
import com.joe.vuebackend.domain.Role;
import com.joe.vuebackend.repository.MenuRepository;
import com.joe.vuebackend.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 清單小幫手
 */
@Component
@Slf4j
public class MenuHelper {

    /**
     * 返回所有預設清單
     *
     * @return
     */
    public static List<InitMenuBean> getAllInitMenuBean() {

        List<InitMenuBean> target = new ArrayList<>();
        target.add(InitMenuBean.builder()
                .label("首頁")
                .path("/home")
                .icon("House")
                .sort(0)
                .roleNames(RoleType.getAllText())
                .build());

        target.add(InitMenuBean.builder()
                .label("學生管理")
                .path("/studentManagement")
                .icon("User")
                .sort(1)
                .roleNames(
                        List.of(RoleType.ADMIN.getText(),
                                RoleType.TEACHER.getText()
                        )
                )
                .build());

        target.add(InitMenuBean.builder()
                .label("課程管理")
                .path("/course")
                .icon("Memo")
                .sort(2)
                .roleNames(
                        List.of(RoleType.ADMIN.getText(),
                                RoleType.TEACHER.getText(),
                                RoleType.STUDENT.getText()
                        )
                )
                .build());

        target.add(InitMenuBean.builder()
                .label("公告管理")
                .path("/announcement")
                .icon("Tickets")
                .sort(3)
                .roleNames(List.of(RoleType.ADMIN.getText()))
                .build());

        target.add(InitMenuBean.builder()
                .label("教師管理")
                .path("/teacherManagement")
                .icon("User")
                .sort(1)
                .roleNames(List.of(RoleType.ADMIN.getText()))
                .build());

        return target;
    }


    /**
     * 批量構建清單
     *
     * @param menuBeans
     */
    public static void build(List<InitMenuBean> menuBeans) {
        CRUDLogHelper logHelper = CRUDLogHelper.build("批量構建清單 build()");
        MenuRepository repository = SpringUtils.getBean(MenuRepository.class);
        if (Objects.nonNull(repository) && CollectionUtils.isNotEmpty(menuBeans)) {
            for (InitMenuBean menu : menuBeans) {
                String label = menu.getLabel();
                boolean exists = repository.existsByLabel(label);
                if (!exists) {
                    build(menu.getLabel(),
                            menu.getPath(),
                            menu.getIcon(),
                            menu.getSort(),
                            menu.getRoleNames()
                    );
                } else {
                    logHelper.saveDuplicateFail(label);
                }
            }
        }
    }

    /**
     * 構建清單
     *
     * @param label     清單名
     * @param path      清單地址
     * @param icon      清單圖標
     * @param sort      排序
     * @param roleNames 角色權限名
     */
    public static void build(String label,
                             String path,
                             String icon,
                             Integer sort,
                             List<String> roleNames) {
        CRUDLogHelper logHelper = CRUDLogHelper.build("批量構建清單 build()");
        if (StringUtils.isNotEmpty(label) && StringUtils.isNotEmpty(path)) {
            try {
                MenuRepository menuRepository = SpringUtils.getBean(MenuRepository.class);
                RoleService roleService = SpringUtils.getBean(RoleService.class);
                if (Objects.nonNull(menuRepository) && Objects.nonNull(roleService)) {

                    Menu menu = new Menu();
                    menu.setLabel(label);
                    menu.setPath(path);
                    menu.setIcon(icon);
                    menu.setSort(sort);

                    Menu dbMenu = menuRepository.save(menu);
                    // 角色權限
                    List<Role> roles = roleService.findByNames(roleNames);
                    if (CollectionUtils.isNotEmpty(roles)) {
                        for (Role role : roles) {
                            role.addMenus(dbMenu);
                            dbMenu.addRoles(role);
                        }
                    }
                    menuRepository.save(dbMenu);
                    logHelper.saveSuccess(label);
                }
            } catch (Exception e) {
                logHelper.saveFail(label, e);
            }
        } else {
            log.error("新增失敗，請確認 清單名:{} 與 清單地址:{} 資料是否正確", label, path);
        }
    }
}
