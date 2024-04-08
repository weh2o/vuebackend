package com.joe.vuebackend.service;

import com.joe.vuebackend.domain.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 根據拼接後的rolesStr獲取清單
     *
     * @param rolesStr
     * @return
     */
    List<Menu> getMenuByRole(String rolesStr);
}
