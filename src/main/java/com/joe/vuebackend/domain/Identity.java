package com.joe.vuebackend.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * 身份
 */
@Data
@Table(name = "j_identity")
@Entity
public class Identity extends BaseEntity {


    /**
     * 身分編號
     */
    @Column(name = "code", unique = true)
    private String code;

    /**
     * 身分名稱
     */
    @Column(name = "name")
    private String name;

    /**
     * 身分名稱中文
     */
    @Column(name = "name_zh")
    private String nameZh;

    /**
     * 使用者
     */
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "identity"
    )
    private List<User> userList;

}