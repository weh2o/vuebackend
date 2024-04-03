package com.joe.vuebackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "j_student")
public class Student extends User{

    /**
     * 學生證
     */
    @Column(name = "no", unique = true)
    private String no;
}
