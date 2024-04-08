package com.joe.vuebackend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 角色權限
 */
@Data
@Entity
@Table(name = "j_role")
@ToString(exclude = {"menus"})
public class Role extends BaseEntity implements Serializable {

    /**
     * 英文名稱
     * @see com.joe.vuebackend.constant.RoleType
     */
    @Column(name = "name")
    private String name;

    /**
     * 中文名稱
     * @see com.joe.vuebackend.constant.RoleType
     */
    @Column(name = "name_zh")
    private String nameZh;

    @ManyToMany(
            mappedBy = "roles",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Menu> menus;


    public void addMenus(Menu source){
        if (Objects.isNull(menus)){
            menus = new ArrayList<>();
        }
        menus.add(source);
    }
}

