package com.joe.vuebackend.vo;

import com.joe.vuebackend.domain.CourseLocation;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class CourseLocationVo {


    /**
     * 識別碼
     */
    private String id;

    /**
     * 地點編號
     */
    private String code;

    /**
     * 地點名稱(英文)
     */
    private String name;

    /**
     * 地點名稱(中文)
     */
    private String nameZh;

    /**
     * 詳細地址
     */
    private String address;

    public static CourseLocationVo of(CourseLocation source) {
        CourseLocationVo target = new CourseLocationVo();

        // 識別碼
        if (StringUtils.isNotEmpty(source.getId())) {
            target.setId(source.getId());
        }

        // 地點編號
        if (StringUtils.isNotEmpty(source.getCode())) {
            target.setCode(source.getCode());
        }

        // 地點名稱(英文)
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }

        // 地點名稱(中文)
        if (StringUtils.isNotEmpty(source.getNameZh())) {
            target.setNameZh(source.getNameZh());
        }

        // 詳細地址
        if (StringUtils.isNotEmpty(source.getAddress())) {
            target.setAddress(source.getAddress());
        }

        return target;
    }
}
