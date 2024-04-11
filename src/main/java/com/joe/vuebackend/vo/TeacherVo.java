package com.joe.vuebackend.vo;

import com.joe.vuebackend.constant.Gender;
import com.joe.vuebackend.domain.Teacher;
import com.joe.vuebackend.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherVo {

    private String id;
    private String name;
    private String age;

    /**
     * 性別
     * <br/>
     * 使用code屬性
     *
     * @see Gender
     */
    private String sex;
    private String no;
    private String phone;
    private String mail;
    private String birth;
    private String address;


    public static TeacherVo ofVo(Teacher source) {
        TeacherVo target = new TeacherVo();

        // 識別碼
        if (StringUtils.isNotEmpty(source.getId())) {
            target.setId(source.getId());
        }

        // 姓名
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }

        // 性別
        if (Objects.nonNull(source.getGender())) {
            Gender gender = source.getGender();
            target.setSex(gender.getCode());
        }

        // 生日、年紀
        if (Objects.nonNull(source.getBirth())) {
            LocalDate localDate = source.getBirth();
            target.setBirth(DateUtil.formatToYYYYMMDD(localDate));
            // 年紀
            target.setAge(String.valueOf(source.getAge()));
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

    public static Teacher ofTeacher(UserInfo source) {
        Teacher target = new Teacher();
        // 識別碼
        if (StringUtils.isNotEmpty(source.getId())) {
            target.setId(source.getId());
        }

        // 姓名
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
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

        // 教師證
//        if (StringUtils.isNotEmpty(source.getNo())) {
//
//        }

        // 生日、年紀
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
            // 年紀
            target.setAge(target.getAge());
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
