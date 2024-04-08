package com.joe.vuebackend.utils;

import com.joe.vuebackend.domain.Menu;
import com.joe.vuebackend.domain.Role;
import com.joe.vuebackend.repository.MenuRepository;
import com.joe.vuebackend.service.RoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class MenuHelper {


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
        if (StringUtils.isNotEmpty(label) && StringUtils.isNotEmpty(path)) {
            Menu menu = new Menu();
            menu.setLabel(label);
            menu.setPath(path);
            menu.setIcon(icon);
            menu.setSort(sort);
            MenuRepository menuRepository = SpringUtils.getBean(MenuRepository.class);
            RoleService roleService = SpringUtils.getBean(RoleService.class);
            if (Objects.nonNull(menuRepository) && Objects.nonNull(roleService)) {
                Menu dbMenu = menuRepository.save(menu);
                List<Role> roles = roleService.findByNames(roleNames);
                if (CollectionUtils.isNotEmpty(roles)) {
                    for (Role role : roles) {
                        role.addMenus(dbMenu);
                        dbMenu.addRoles(role);
                    }
                }
                menuRepository.save(dbMenu);
            }
        }
    }
}
