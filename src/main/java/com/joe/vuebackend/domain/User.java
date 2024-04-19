package com.joe.vuebackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joe.vuebackend.constant.Gender;
import com.joe.vuebackend.convert.GenderConvert;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

/**
 * 帳號
 */
@Data
@Entity
@Table(name = "j_user")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {

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

    /**
     * 課程
     */
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Course> courseList;

    /**
     * 是否啟動
     */
    @Column(name = "enable")
    private boolean enable = true;

    /**
     * 帳號是否沒過期
     */
    @Column(name = "accountNonExpired")
    private boolean accountNonExpired = true;

    /**
     * 帳號是否沒被鎖定
     */
    @Column(name = "accountNonLocked")
    private boolean accountNonLocked = true;

    /**
     * 密碼是否沒過期
     */
    @Column(name = "credentialsNonExpired")
    private boolean credentialsNonExpired = true;


    public Integer getAge() {
        if (Objects.nonNull(birth)) {
            LocalDate now = LocalDate.now();
            Period period = Period.between(birth, now);
            return period.getYears();
        }
        return null;
    }

    /**
     * 添加課程
     *
     * @param source 課程
     */
    public void addCourse(Course source) {
        if (Objects.isNull(courseList)) {
            courseList = new ArrayList<>();
        }
        if (Objects.nonNull(source)) {
            courseList.add(source);
        }
    }

    /**
     * 獲取角色英文名稱List
     *
     * @return
     */
    public List<String> getRolesName() {
        if (CollectionUtils.isNotEmpty(roles)) {
            return roles.stream().map(Role::getName).toList();
        }
        return null;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            String name = role.getName();
            // 角色
            SimpleGrantedAuthority roleAuthority = new SimpleGrantedAuthority("ROLE_" + name);
            authorities.add(roleAuthority);
        });
        return authorities;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

}
