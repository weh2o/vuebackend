package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IdentityRepository
        extends JpaRepository<Identity, String>, JpaSpecificationExecutor<Identity> {

    /**
     * 根據英文名找身分
     *
     * @param name 身分英文名
     * @return
     */
    Optional<Identity> findByName(String name);

    /**
     * 根據英文名判斷是否存在於資料庫中
     *
     * @param name 身分英文名
     * @return
     */
    boolean existsByName(String name);

}