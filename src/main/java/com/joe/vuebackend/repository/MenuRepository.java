package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MenuRepository
        extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {

    /**
     * 根據中文名稱判斷是否存在資料庫
     *
     * @param Label 中文名稱
     * @return
     */
    boolean existsByLabel(String Label);


    /**
     * 根據路徑查詢
     *
     * @param path 路徑
     * @return 菜單
     */
    Optional<Menu> findByPath(String path);

}