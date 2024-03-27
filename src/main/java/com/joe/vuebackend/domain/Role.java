package com.joe.vuebackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色權限
 */
@Data
@Entity
@Table(name = "j_role")
public class Role extends BaseEntity implements Serializable {

    /**
     * 英文名稱
     */
    @Column(name = "name")
    private String name;

    /**
     * 中文名稱
     */
    @Column(name = "name_zh")
    private String nameZh;
}

