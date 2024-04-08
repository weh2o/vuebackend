package com.joe.vuebackend.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 清單
 */
@Entity
@Table(name = "j_menu")
@Data
public class Menu extends BaseEntity {

    /**
     * 英文名稱
     */
    private String name;

    /**
     * 中文名稱
     */
    private String label;

    /**
     * 地址
     */
    private String path;

    /**
     * 完整地址
     */
    private String url;

    /**
     * 圖標
     */
    private String icon;

    /**
     * 上層清單
     */
    @ManyToOne
    @JoinColumn(
            name = "parent_id",
            foreignKey = @ForeignKey(name = "fk_menu_parent")
    )
    private Menu parent;

    /**
     * 下層清單
     */
    @OneToMany(mappedBy = "parent")
    private List<Menu> children;

    /**
     * 角色權限
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "menu_role",
            joinColumns = @JoinColumn(
                    name = "menu_id",
                    foreignKey = @ForeignKey(name = "fk_menu_role")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    foreignKey = @ForeignKey(name = "fk_role_menu")
            ),
            uniqueConstraints = @UniqueConstraint(
                    name = "uk_menu_role",
                    columnNames = {"menu_id", "role_id"}
            )
    )
    private List<Role> roles;

    public void addRoles(Role source){
        if (Objects.isNull(roles)){
            roles = new ArrayList<>();
        }
        roles.add(source);
    }
}
