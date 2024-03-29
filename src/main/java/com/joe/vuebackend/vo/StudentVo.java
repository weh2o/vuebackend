package com.joe.vuebackend.vo;

import com.joe.vuebackend.constant.Gender;
import com.joe.vuebackend.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 用於接收前端學生資料的物件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVo {

    private String id;
    private String name;
    private String age;
    private String sex;
    private String no;
    private String phone;
    private String mail;
    private String birth;


    public static StudentVo ofVo(Student source) {
        val target = new StudentVo();

        // 姓名
        if (StringUtils.isNotEmpty(source.getId())) {
            target.setId(source.getId());
        }

        // 姓名
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }
        // 年紀
        if (ObjectUtils.isNotEmpty(source.getAge())) {
            target.setAge(String.valueOf(source.getAge()));
        }
        // 性別
        if (ObjectUtils.isNotEmpty(source.getGender())) {
            target.setSex(source.getGender().getText());
        }
        // 學生證
        if (StringUtils.isNotEmpty(source.getNo())) {
            target.setNo(source.getNo());
        }
        // 生日
        if (ObjectUtils.isNotEmpty(source.getBirth())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formatBirth = formatter.format(source.getBirth());
            target.setBirth(formatBirth);
        }
        // 電話
        if (StringUtils.isNotEmpty(source.getPhone())) {
            target.setPhone(source.getPhone());
        }
        //信箱
        if (StringUtils.isNotEmpty(source.getMail())) {
            target.setMail(source.getMail());
        }
        return target;
    }

    public static Student ofStudent(StudentVo source) {
        Student target = new Student();
        // 姓名
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }

        // 年紀
        if (StringUtils.isNotEmpty(source.getAge())) {
            target.setAge(Integer.valueOf(source.getAge()));
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

        // 學生證
        if (StringUtils.isNotEmpty(source.getNo())) {
            target.setNo(source.getNo());
        }

        // 生日
        if (StringUtils.isNotEmpty(source.getBirth())) {
            String sourceBirth = source.getBirth();
            LocalDateTime userBirth = LocalDateTime.parse(sourceBirth, DateTimeFormatter.ISO_DATE_TIME);
            target.setBirth(userBirth);
        }

        // 電話
        if (StringUtils.isNotEmpty(source.getPhone())) {
            target.setPhone(source.getPhone());
        }
        //信箱
        if (StringUtils.isNotEmpty(source.getMail())) {
            target.setMail(source.getMail());
        }
        return target;
    }
}
