package com.joe.vuebackend.utils;

import com.joe.vuebackend.domain.Role;
import com.joe.vuebackend.repository.RoleRepository;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
public class RoleHelper {

    /**
     * 獲取多個角色(權限)物件
     *
     * @param names 角色(權限)名List
     * @return
     */
    public static List<Role> getRoleList(List<String> names) {
        List<Role> target = new ArrayList<>();
        RoleRepository roleRepository = SpringUtils.getBean(RoleRepository.class);
        if (Objects.nonNull(roleRepository)) {
            for (String roleName : names) {
                Optional<Role> optional = roleRepository.findByName(roleName);
                if (optional.isPresent()) {
                    target.add(optional.get());
                }
            }
        }
        return target;
    }

    /**
     * 獲取一個角色(權限)物件
     *
     * @param name 角色(權限)名
     * @return
     */
    public static Role getRole(String name) {
        RoleRepository roleRepository = SpringUtils.getBean(RoleRepository.class);
        if (Objects.nonNull(roleRepository)) {
            Optional<Role> optional = roleRepository.findByName(name);
            if (optional.isPresent()) {
                return optional.get();
            }
        }
        return null;
    }


}
