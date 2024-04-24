package com.joe.vuebackend;

import com.joe.vuebackend.bean.InitMenuBean;
import com.joe.vuebackend.bean.initbean.InitMenuList;
import com.joe.vuebackend.constant.RoleType;
import com.joe.vuebackend.domain.Menu;
import com.joe.vuebackend.repository.MenuRepository;
import com.joe.vuebackend.repository.RoleRepository;
import com.joe.vuebackend.service.MenuService;
import com.joe.vuebackend.service.RoleService;
import com.joe.vuebackend.utils.MenuHelper;
import com.joe.vuebackend.vo.MenuVo;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    @Commit
    @Transactional
    void initMenuPlus() {
        Map<String, List<InitMenuBean>> allMenu = InitMenuList.getAll();
        for (Map.Entry<String, List<InitMenuBean>> entry : allMenu.entrySet()) {
            List<InitMenuBean> beanList = entry.getValue();
            MenuHelper.buildTreeMenu(beanList);
        }
    }

    // 返回給前端樹形菜單
    @Test
    void treeTest() {
        // 全部菜單
        List<String> condition = Collections.singletonList(RoleType.ADMIN.getText());
        List<Menu> menus = menuService.getMenuByRole(condition);

        // 查詢最上層的菜單
        Map<Menu, List<Menu>> parentMap = menus.stream().collect(Collectors.groupingBy(
                m -> {
                    // 沒上層 = 第一層
                    if (Objects.isNull(m.getParent())) {
                        return m;
                    }
                    // 上層是不為空，且上層的上層是空【代表最頂層】
                    else if (Objects.isNull(m.getParent().getParent())
                    ) {
                        return m.getParent();
                    }
                    // 到這代表是下層。做禁用標記
                    Menu menu = new Menu();
                    menu.setDisable(true);
                    return menu;
                }));

        List<MenuVo> Vos = new ArrayList<>();
        for (Map.Entry<Menu, List<Menu>> entry : parentMap.entrySet()) {
            Menu parent = entry.getKey();
            // 沒有標記的才是上層元素
            if (Objects.isNull(parent.getDisable())) {
                List<MenuVo> childrenVos = MenuHelper.buildTree(menus, parent.getId());
                MenuVo parentVo = MenuVo.of(parent);
                parentVo.setChildren(childrenVos);
                Vos.add(parentVo);
            }
        }
        System.out.println();
    }
}
