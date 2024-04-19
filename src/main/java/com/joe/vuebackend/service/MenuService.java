package com.joe.vuebackend.service;

import com.joe.vuebackend.domain.Menu;
import com.joe.vuebackend.vo.MenuVo;

import java.util.List;

public interface MenuService {


    List<Menu> getMenuByRole(List<String> roles);

    List<MenuVo> getMenuVoByRole(List<String> roles);
}
