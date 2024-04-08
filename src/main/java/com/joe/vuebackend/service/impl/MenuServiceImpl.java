package com.joe.vuebackend.service.impl;

import com.joe.vuebackend.domain.Menu;
import com.joe.vuebackend.repository.MenuRepository;
import com.joe.vuebackend.repository.spec.MenuSpec;
import com.joe.vuebackend.service.MenuService;
import com.joe.vuebackend.utils.RoleHelper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Setter(onMethod_ = @Autowired)
    private MenuRepository menuRepository;

    @Override
    public List<Menu> getMenuByRole(String rolesStr) {
        List<String> roles = RoleHelper.splitRolesStr(rolesStr);
        MenuSpec.MenuCondition condition = new MenuSpec.MenuCondition();
        condition.setRoles(roles);
        return menuRepository.findAll(MenuSpec.initSpec(condition));
    }
}
