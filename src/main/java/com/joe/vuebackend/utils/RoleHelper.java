package com.joe.vuebackend.utils;

import com.joe.vuebackend.domain.Role;
import com.joe.vuebackend.repository.RoleRepository;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

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

    /**
     * 將Role轉換成英文名List
     *
     * @param source RoleList
     * @return 角色權限英文名List
     */
    public static List<String> ofName(List<Role> source) {
        if (CollectionUtils.isNotEmpty(source)) {
            return source.stream().map(Role::getName).toList();
        }
        return null;
    }

    /**
     * 將角色權限物件List轉換成一個字串
     *
     * @param source
     * @return
     */
    public static String ofJoinName(List<Role> source) {
        if (CollectionUtils.isNotEmpty(source)) {
            List<String> list = source.stream().map(Role::getName).toList();
            return StringUtils.join(list, "-");
        }
        return null;
    }

    /**
     * 將角色權限字串List轉換成一個字串
     *
     * @param source
     * @return
     */
    public static String ofJoinNameWithString(List<String> source) {
        if (CollectionUtils.isNotEmpty(source)) {
            return StringUtils.join(source, "-");
        }
        return null;
    }

    /**
     * 將角色權限字串拆解成List
     *
     * @param source
     * @return
     */
    public static List<String> splitRolesStr(String source) {
        if (StringUtils.isNotEmpty(source)) {
            String[] split = StringUtils.split(source, "-");
            return Arrays.asList(split);
        }
        return null;
    }
}
