package com.joe.vuebackend.utils;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Data
public class UserHelper {

    /**
     * 密碼加密
     *
     * @param source 尚未加密的密碼
     * @return
     */
    public static String passwordEncryption(String source) {

        if (StringUtils.isNotEmpty(source)) {
            return DigestUtils.md5DigestAsHex(source.getBytes(StandardCharsets.UTF_8));
        }
        return "";
    }

}