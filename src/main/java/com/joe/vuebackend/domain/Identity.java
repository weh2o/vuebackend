package com.joe.vuebackend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

/**
 * 身份
 */
@Data
@Table(name = "j_identity")
@Entity
@ToString(exclude = "userList")
public class Identity extends BaseEntity {


    /**
     * 身分編號
     * @see com.joe.vuebackend.constant.IdentityType
     */
    @Column(name = "code", unique = true)
    private String code;

    /**
     * 身分名稱
     * @see com.joe.vuebackend.constant.IdentityType
     */
    @Column(name = "name")
    private String name;

    /**
     * 身分名稱中文
     * @see com.joe.vuebackend.constant.IdentityType
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

    /**
     * 添加屬於該身分的使用者
     *
     * @param user
     */
    public void addUserList(User user) {
        if (Objects.nonNull(userList)) {
            userList.add(user);
        }
    }
}