package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepository
        extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {

    boolean existsByName(String name);
}