package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TeacherRepository
        extends JpaRepository<Teacher, String>, JpaSpecificationExecutor<Teacher> {

    Optional<Teacher> findByNo_No(String no);

    Optional<Teacher> findByAccount(String account);
}