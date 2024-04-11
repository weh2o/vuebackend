package com.joe.vuebackend.repository;

import com.joe.vuebackend.domain.CourseLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CourseLocationRepository
        extends JpaRepository<CourseLocation, String>, JpaSpecificationExecutor<CourseLocation> {

    /**
     * 根據英文名獲取地點
     *
     * @param name
     * @return
     */
    Optional<CourseLocation> findByName(String name);

    boolean existsByCode(String code);

}