package com.joe.vuebackend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    /**
     * 識別碼
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "my-uuid")
    @GenericGenerator(name = "my-uuid", strategy = "uuid2")
    private String id;

    /**
     * 創建時間
     */
    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;

    /**
     * 修改時間
     */
    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;

    /**
     * 備註
     */
    @Column(name = "note", length = 4000)
    private String note;

    /**
     * 是否停用
     */
    @Column(name = "disable")
    private Boolean disable;
}

