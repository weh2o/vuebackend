package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student, String>, JpaSpecificationExecutor<Student> {

    List<Student> findByNameOrNo(String name, String no);

    Optional<Student> findByName(String name);
}
