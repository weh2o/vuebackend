package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByName(String name);

    Optional<User> findByAccount(String account);
}