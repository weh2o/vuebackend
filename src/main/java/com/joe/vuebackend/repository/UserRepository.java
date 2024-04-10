package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.Menu;
import com.joe.vuebackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    Optional<User> findByName(String name);

    Optional<User> findByAccount(String account);

    boolean existsByName(String name);
}