package com.joe.vuebackend.vo;

import com.joe.vuebackend.domain.User;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class UserInfo {

    /**
     * 識別碼
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性別
     */
    private String sex;

    /**
     * 生日
     */
    private String birth;

    /**
     * 年紀
     */
    private Integer age;

    /**
     * 電話
     */
    private String phone;

    /**
     * 信箱
     */
    private String mail;

    private String token;


    public static UserInfo of(User source) {
        UserInfo target = new UserInfo();
        if (StringUtils.isNotEmpty(source.getId())) {
            target.setId(source.getId());
        }
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }
        return target;
    }

    public static UserInfo ofAll(User source) {
        UserInfo target = of(source);

        if (ObjectUtils.isNotEmpty(source.getGender())) {
            target.setSex(source.getGender().getCode());
        }

        if (ObjectUtils.isNotEmpty(source.getBirth())) {
            LocalDate localDate = source.getBirth();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formatDate = timeFormatter.format(localDate);
            target.setBirth(formatDate);
        }

        if (ObjectUtils.isNotEmpty(source.getAge())) {
            target.setAge(source.getAge());
        }

        if (StringUtils.isNotEmpty(source.getPhone())) {
            target.setPhone(source.getPhone());
        }

        if (StringUtils.isNotEmpty(source.getMail())) {
            target.setMail(source.getMail());
        }
        return target;
    }

}
