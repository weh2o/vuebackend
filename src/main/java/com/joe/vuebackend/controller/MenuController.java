package com.joe.vuebackend.controller;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.domain.Menu;
import com.joe.vuebackend.service.MenuService;
import com.joe.vuebackend.vo.MenuVo;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

/**
 * 清單
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Setter(onMethod_ = @Autowired)
    private MenuService menuService;

    @GetMapping("/{roles}")
    public HttpResult<List<MenuVo>> getAll(@PathVariable(name = "roles") String rolesStr){
        List<Menu> menus = menuService.getMenuByRole(rolesStr);
        if (CollectionUtils.isNotEmpty(menus)){
            List<MenuVo> vos = menus.stream().map(MenuVo::of)
                    .sorted(Comparator.comparing(MenuVo::getSort)).toList();
            return HttpResult.success(vos);
        }
        return HttpResult.fail("取得清單失敗");
    }
}
