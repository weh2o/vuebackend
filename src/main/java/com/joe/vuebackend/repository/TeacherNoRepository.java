package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.Teacher;
import com.joe.vuebackend.domain.TeacherNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TeacherNoRepository
        extends JpaRepository<TeacherNo, String>, JpaSpecificationExecutor<TeacherNo> {

    Optional<TeacherNo> findByNo(String no);

    Optional<TeacherNo> findByNoAndAvailableIsTrue(String no);

    boolean existsByNo(String no);

}