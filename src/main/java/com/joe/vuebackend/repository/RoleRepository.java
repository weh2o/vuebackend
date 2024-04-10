package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * 角色權限Repository
 */
public interface RoleRepository
        extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

    /**
     * 根據英文名找角色權限
     *
     * @param name 角色權限英文名
     * @return
     */
    Optional<Role> findByName(String name);

    /**
     * 根據英文名判斷是否存在於資料庫中
     *
     * @param name 角色權限英文名
     * @return
     */
    boolean existsByName(String name);

}
