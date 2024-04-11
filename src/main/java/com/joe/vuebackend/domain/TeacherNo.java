package com.joe.vuebackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

/**
 * 教師證
 */
@Entity
@Table(name = "j_teacher_no")
@Data
@ToString(exclude = {"teacher"})
public class TeacherNo extends BaseEntity {

    /**
     * 教師證編號
     */
    @Column(name = "no", length = 8, unique = true)
    private String no;

    /**
     * 教師
     */
    @OneToOne(mappedBy = "no")
    private Teacher teacher;

    /**
     * 是否註冊
     *
     * @return true 尚未註冊, false 已經註冊
     */
    @Column(name = "available")
    private Boolean available;
}
