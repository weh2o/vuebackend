package com.joe.vuebackend.utils;

import com.joe.vuebackend.bean.InitMenuBean;
import com.joe.vuebackend.domain.Menu;
import com.joe.vuebackend.domain.Role;
import com.joe.vuebackend.repository.MenuRepository;
import com.joe.vuebackend.service.RoleService;
import com.joe.vuebackend.vo.MenuVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 菜單小幫手
 */
@Component
@Slf4j
public class MenuHelper {

    /**
     * 構建樹形結構的菜單
     *
     * @param beanList 構建菜單的資料
     */
    public static void buildTreeMenu(List<InitMenuBean> beanList) {
        if (CollectionUtils.isNotEmpty(beanList)) {
            CRUDLogHelper logHelper = CRUDLogHelper.build("構建樹形結構菜單 buildTreeMenu()");
            String label = "";
            try {
                MenuRepository menuRepository = SpringUtils.getBean(MenuRepository.class);
                RoleService roleService = SpringUtils.getBean(RoleService.class);
                if (Objects.nonNull(menuRepository) && Objects.nonNull(roleService)) {
                    Menu parentMenu = null;
                    // 獲取上層菜單
                    List<InitMenuBean> parentList = beanList.stream().filter(m -> StringUtils.isEmpty(m.getParent())).toList();
                    if (CollectionUtils.isNotEmpty(parentList)) {
                        InitMenuBean bean = parentList.get(0);
                        Optional<Menu> optional = menuRepository.findByPath(bean.getPath());
                        if (optional.isPresent()) {
                            parentMenu = optional.get();
                        }
                    }
                    // 構建
                    for (InitMenuBean bean : beanList) {
                        Menu menu = new Menu();
                        label = bean.getLabel();
                        menu.setLabel(label);
                        menu.setPath(bean.getPath());
                        menu.setIcon(bean.getIcon());
                        Integer sort = bean.getSort();
                        if (Objects.isNull(sort)) {
                            sort = 99;
                        }
                        menu.setSort(sort);
                        // 如果資料庫已存在，跳過本次構建過程
                        boolean exists = menuRepository.existsByLabel(label);
                        if (exists) {
                            logHelper.saveDuplicateFail(label);
                            continue;
                        }
                        Menu dbMenu = menuRepository.save(menu);
                        // 如果是第一層菜單
                        if (StringUtils.isEmpty(bean.getParent())) {
                            parentMenu = dbMenu;
                        } else {
                            dbMenu.setParent(parentMenu);
                        }
                        // 角色權限
                        if (CollectionUtils.isNotEmpty(bean.getRoleNames())) {
                            List<Role> roles = roleService.findByNames(bean.getRoleNames());
                            if (CollectionUtils.isNotEmpty(roles)) {
                                for (Role role : roles) {
                                    role.addMenus(dbMenu);
                                    dbMenu.addRoles(role);
                                }
                            }
                            menuRepository.save(dbMenu);
                        }
                        logHelper.saveSuccess(label);
                    }
                }
            } catch (Exception e) {
                logHelper.saveFail(label, e);
            }
        } else {
            log.error("新增失敗，構建菜單用的資料為null");
        }
    }

    /**
     * 批量構建菜單
     * <br/>
     * 請改用 buildTreeMenu() 功能比較強大
     *
     * @param menuBeans 構建菜單的資料
     */
    @Deprecated
    public static void build(List<InitMenuBean> menuBeans) {
        CRUDLogHelper logHelper = CRUDLogHelper.build("批量構建菜單 build()");
        MenuRepository repository = SpringUtils.getBean(MenuRepository.class);
        if (Objects.nonNull(repository) && CollectionUtils.isNotEmpty(menuBeans)) {
            for (InitMenuBean menu : menuBeans) {
                String label = menu.getLabel();
                boolean exists = repository.existsByLabel(label);
                if (!exists) {
                    build(menu);
                } else {
                    logHelper.saveDuplicateFail(label);
                }
            }
        }
    }

    /**
     * 構建菜單
     *
     * @param initMenu 構建菜單的資料
     */
    @Deprecated
    public static void build(InitMenuBean initMenu) {
        String label = initMenu.getLabel();
        String path = initMenu.getPath();
        String icon = initMenu.getIcon();
        Integer sort = initMenu.getSort();
        List<String> roleNames = initMenu.getRoleNames();

        CRUDLogHelper logHelper = CRUDLogHelper.build("構建菜單 build()");
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
            log.error("新增失敗，請確認 菜單名:{} 與 菜單地址:{} 資料是否正確", label, path);
        }
    }

    /**
     * 匹配下層菜單並轉換成Vo
     * <br/>
     * 適合特定角色權限的菜單構建
     *
     * @param allMenu  菜單資料
     * @param parentId 上層識別碼
     * @return 塞滿下層菜單的Vo
     */
    public static List<MenuVo> buildTree(List<Menu> allMenu, String parentId) {
        List<MenuVo> childrenVos = new ArrayList<>();
        // 1.遍歷所有資料
        for (Menu childrenMenu : allMenu) {
            // 2.判斷menu的parentId是否與傳進來的parentId相同
            Menu parent = childrenMenu.getParent();
            if (Objects.nonNull(parent) && parent.getId().equals(parentId)) {
                // 3.如果相同轉換成VO添加進結果
                childrenVos.add(MenuVo.of(childrenMenu));
            }
        }
        // 4.遞迴childrenList，因為子節點本身可能還有下層
        if (CollectionUtils.isNotEmpty(childrenVos)) {
            for (MenuVo childrenItem : childrenVos) {
                // 遞迴調用本身方法
                List<MenuVo> grandchildrenList = buildTree(allMenu, childrenItem.getId());
                childrenItem.setChildren(grandchildrenList);
            }
        }
        return childrenVos;
    }

    /**
     * 創建樹形結構MenuVo資料
     * <br/>
     * 注意: 會把所有下層資料轉換，所以「不適合」特定角色權限的菜單構建
     *
     * @param source 菜單
     * @return 樹形結構MenuVo
     */
    public static MenuVo buildTree(Menu source) {
        List<MenuVo> childrenVos = new ArrayList<>();
        // 情況1: 有小孩
        if (CollectionUtils.isNotEmpty(source.getChildren())) {
            for (Menu child : source.getChildren()) {
                // 情況1-1: 有孫子，使用遞迴往下挖掘，最後把結果轉成Vo放入List
                if (CollectionUtils.isNotEmpty(child.getChildren())) {
                    MenuVo menuVo = buildTree(source);
                    childrenVos.add(menuVo);
                } else {  // // 情況1-2: 無孫子，轉成Vo放入List
                    childrenVos.add(MenuVo.of(child));
                }
            }
        } // 情況2: 無小孩。 轉成Vo
        MenuVo target = MenuVo.of(source);
        target.setChildren(childrenVos);
        return target;
    }
}
