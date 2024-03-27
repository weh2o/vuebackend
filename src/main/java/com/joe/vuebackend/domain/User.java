package com.joe.vuebackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 帳號
 */
@Data
@Entity
@Table(name = "j_user")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    /**
     * 用戶名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密碼
     */
    @Column(name = "password")
    private String password;

//    /**
//     * 用戶角色(權限)資料
//     */
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "user_role",
//            joinColumns = @JoinColumn(
//                    name = "user_id",
//                    foreignKey = @ForeignKey(name = "fk_user_role")
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id",
//                    foreignKey = @ForeignKey(name = "fk_role_user")
//            ),
//            uniqueConstraints = @UniqueConstraint(
//                    name = "uk_user_role",
//                    columnNames = {"user_id", "role_id"}
//            )
//    )
//    private List<Role> roles;


}
