package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdentityRepository extends JpaRepository<Identity, String> {

    Optional<Identity> findByName(String name);

}