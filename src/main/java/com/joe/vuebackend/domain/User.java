package com.joe.vuebackend.domain;

import com.joe.vuebackend.constant.Gender;
import com.joe.vuebackend.convert.GenderConvert;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 帳號
 */
@Data
@Entity
@Table(name = "j_user")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    /**
     * 用戶名
     */
    @Column(name = "name")
    private String name;

    /**
     * 密碼
     */
    @Column(name = "password")
    private String password;

    /**
     * 性別
     */
    @Column(name = "gender")
    @Convert(converter = GenderConvert.class)
    private Gender gender;

    /**
     * 生日
     */
    @Column(name = "birth")
    private LocalDate birth;

    /**
     * 年紀
     */
    @Column(name = "age")
    private Integer age;

    /**
     * 電話
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 信箱
     */
    @Column(name = "mail")
    private String mail;

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
