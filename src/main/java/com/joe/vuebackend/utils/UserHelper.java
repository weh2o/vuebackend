package com.joe.vuebackend.utils;

import com.joe.vuebackend.domain.User;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

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

    /**
     * 獲取使用者識別碼
     *
     * @return
     */
    public static String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            User user = (User) authentication.getPrincipal();
            return user.getId();
        }
        return null;
    }

}