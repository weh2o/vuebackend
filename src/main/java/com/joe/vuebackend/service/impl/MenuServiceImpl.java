package com.joe.vuebackend.service.impl;

import com.joe.vuebackend.domain.Menu;
import com.joe.vuebackend.repository.MenuRepository;
import com.joe.vuebackend.repository.spec.MenuSpec;
import com.joe.vuebackend.service.MenuService;
import com.joe.vuebackend.vo.MenuVo;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Setter(onMethod_ = @Autowired)
    private MenuRepository menuRepository;

    @Override
    public List<Menu> getMenuByRole(List<String> roles) {
        MenuSpec.MenuCondition condition = new MenuSpec.MenuCondition();
        condition.setRoles(roles);
        return menuRepository.findAll(MenuSpec.initSpec(condition));
    }

    @Override
    public List<MenuVo> getMenuVoByRole(List<String> roles) {
        List<Menu> menus = getMenuByRole(roles);
        if (CollectionUtils.isNotEmpty(menus)) {
            return menus.stream().map(MenuVo::of)
                    .sorted(Comparator.comparing(MenuVo::getSort)).toList();
        }
        return null;
    }
}
