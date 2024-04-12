package com.joe.vuebackend.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
