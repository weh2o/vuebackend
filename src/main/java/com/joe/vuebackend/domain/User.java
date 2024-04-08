package com.joe.vuebackend.domain;

import com.joe.vuebackend.constant.Gender;
import com.joe.vuebackend.convert.GenderConvert;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Objects;

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
     * 使用者名稱
     */
    @Column(name = "name")
    private String name;


    /**
     * 帳號
     */
    @Column(name = "account", unique = true)
    private String account;

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

    /**
     * 地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 上次登入時間
     */
    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 用戶角色(權限)資料
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    foreignKey = @ForeignKey(name = "fk_user_role")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    foreignKey = @ForeignKey(name = "fk_role_user")
            ),
            uniqueConstraints = @UniqueConstraint(
                    name = "uk_user_role",
                    columnNames = {"user_id", "role_id"}
            )
    )
    private List<Role> roles;


    /**
     * 身分
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "identity_id",
            foreignKey = @ForeignKey(name = "fk_user_identity")
    )
    private Identity identity;


    public Integer getAge() {
        if (Objects.nonNull(birth)){
            LocalDate now = LocalDate.now();
            Period period = Period.between(birth, now);
            return period.getYears();
        }
        return null;
    }
}
