package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IdentityRepository
        extends JpaRepository<Identity, String>, JpaSpecificationExecutor<Identity> {

    Optional<Identity> findByName(String name);

}