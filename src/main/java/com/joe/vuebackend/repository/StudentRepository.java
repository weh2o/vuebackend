package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository
        extends JpaRepository<Student, String>, JpaSpecificationExecutor<Student> {
}
