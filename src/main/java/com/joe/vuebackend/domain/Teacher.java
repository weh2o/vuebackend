package com.joe.vuebackend.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "j_teacher")
public class Teacher extends User{

    /**
     * 教師證
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "teacher_no_id",
            foreignKey = @ForeignKey(name = "fk_teacher_teacher_no")
    )
    private TeacherNo no;
}