package com.joe.vuebackend.service;

import com.joe.vuebackend.domain.Role;

import java.util.List;

public interface RoleService {

    /**
     * 根據角色權限名找權限物件
     * @param names
     * @return
     */
    List<Role> findByNames(List<String> names);
}
