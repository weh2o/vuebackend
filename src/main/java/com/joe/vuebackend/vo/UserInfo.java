package com.joe.vuebackend.vo;

import com.joe.vuebackend.constant.Gender;
import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.domain.Teacher;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.utils.RoleHelper;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 展示到前端的使用者資料
 */
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

    /**
     * 上次登入時間
     */
    private String lastLoginTime;

    /**
     * 學生證 or 教師證
     */
    private String no;

    /**
     * 地址
     */
    private String address;

    /**
     * 身分
     */
    private String identity;

    /**
     * 角色權限
     */
    private String roles;

    private String token;

    private String oldPassword;

    private String newPassword;


    /**
     * 轉換 識別碼、姓名、身分
     *
     * @param source
     * @return
     */
    public static UserInfo of(User source) {
        UserInfo target = new UserInfo();
        // 識別碼
        if (StringUtils.isNotEmpty(source.getId())) {
            target.setId(source.getId());
        }
        // 姓名
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }
        // 身分
        if (Objects.nonNull(source.getIdentity())) {
            target.setIdentity(source.getIdentity().getCode());
        }
        // 角色權限
        if (CollectionUtils.isNotEmpty(source.getRoles())) {
            String strRoles = RoleHelper.ofJoinName(source.getRoles());
            target.setRoles(strRoles);
        }
        return target;
    }

    /**
     * 轉換全部
     *
     * @param source
     * @return
     */
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

        if (ObjectUtils.isNotEmpty(source.getLastLoginTime())) {
            LocalDateTime sourceLastLoginTime = source.getLastLoginTime();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            String formatDateTime = timeFormatter.format(sourceLastLoginTime);
            target.setLastLoginTime(formatDateTime);
        }

        if (StringUtils.isNotEmpty(source.getAddress())) {
            target.setAddress(source.getAddress());
        }

        return target;
    }

    /**
     * 設定不同身分獨有的資料
     *
     * @param userInfo 使用者資料
     * @param source   資料庫中原始的使用者
     */
    public static void setSpecial(UserInfo userInfo, User source) {
        if (source instanceof Student stu) {
            userInfo.setNo(stu.getNo());
        } else if (source instanceof Teacher teacher) {
            userInfo.setNo(teacher.getNo().getNo());
        }
    }

    public static User ofUser(UserInfo source) {
        User target = new User();

        // 識別碼
        if (StringUtils.isNotEmpty(source.getId())) {
            target.setId(source.getId());
        }

        // 姓名
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }

        // 年紀
        if (Objects.nonNull(source.getAge())) {
            target.setAge(source.getAge());
        }

        // 性別
        if (StringUtils.isNotEmpty(source.getSex())) {
            for (Gender gender : Gender.values()) {
                // 判斷哪個資料跟和stu裡的一樣，將其返回
                if (gender.getCode().equals(source.getSex())) {
                    target.setGender(gender);
                }
            }
        }

        // 生日
        if (StringUtils.isNotEmpty(source.getBirth())) {
            String sourceBirth = source.getBirth();
            LocalDate stuBirth;
            if (sourceBirth.length() != 10) {
                ZonedDateTime taiwanTime = Instant.parse(sourceBirth).atZone(ZoneId.of("Asia/Taipei"));
                stuBirth = taiwanTime.toLocalDate();
            } else {
                stuBirth = LocalDate.parse(sourceBirth);
            }
            target.setBirth(stuBirth);
        }

        // 電話
        if (StringUtils.isNotEmpty(source.getPhone())) {
            target.setPhone(source.getPhone());
        }
        // 信箱
        if (StringUtils.isNotEmpty(source.getMail())) {
            target.setMail(source.getMail());
        }

        // 地址
        if (StringUtils.isNotEmpty(source.getAddress())) {
            target.setAddress(source.getAddress());
        }
        return target;
    }

}
