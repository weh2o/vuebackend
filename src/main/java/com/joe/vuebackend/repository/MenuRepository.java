package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuRepository
        extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {

}