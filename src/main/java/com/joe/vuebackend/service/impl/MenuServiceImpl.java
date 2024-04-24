package com.joe.vuebackend.service.impl;

import com.joe.vuebackend.domain.Menu;
import com.joe.vuebackend.repository.MenuRepository;
import com.joe.vuebackend.repository.spec.MenuSpec;
import com.joe.vuebackend.service.MenuService;
import com.joe.vuebackend.utils.MenuHelper;
import com.joe.vuebackend.vo.MenuVo;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
            // 查詢最上層的菜單
            Map<Menu, List<Menu>> parentMap = menus.stream().collect(Collectors.groupingBy(
                    m -> {
                        // 沒上層 = 第一層
                        if (Objects.isNull(m.getParent())) {
                            return m;
                        }
                        // 上層不為空，且上層的上層是空【代表最頂層】
                        else if (Objects.isNull(m.getParent().getParent())
                        ) {
                            return m.getParent();
                        }
                        // 到這代表是下層。做禁用標記
                        Menu menu = new Menu();
                        menu.setDisable(true);
                        return menu;
                    }));

            List<MenuVo> vos = new ArrayList<>();
            // 根據上層菜單構建樹形結構
            for (Map.Entry<Menu, List<Menu>> entry : parentMap.entrySet()) {
                Menu parent = entry.getKey();
                // 沒有標記的才是上層元素
                if (Objects.isNull(parent.getDisable())){
                    List<MenuVo> childrenVos = MenuHelper.buildTree(menus, parent.getId());
                    MenuVo parentVo = MenuVo.of(parent);
                    parentVo.setChildren(childrenVos);
                    vos.add(parentVo);
                }
            }
            return vos.stream().sorted(Comparator.comparing(MenuVo::getSort)).toList();
        }
        return null;
    }
}
