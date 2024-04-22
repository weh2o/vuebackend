package com.joe.vuebackend.utils;

import com.joe.vuebackend.constant.URLConstant;

import java.util.ArrayList;
import java.util.List;

public class URLHelper {

    /**
     * 排除驗證的url清單
     *
     * @return
     */
    public static List<String> getExcludeUrl() {
        List<String> excludeURL = new ArrayList<>();
        excludeURL.add(URLConstant.LOGIN);
        excludeURL.add(URLConstant.LOGOUT);
        excludeURL.add(URLConstant.REGISTER);
        return excludeURL;
    }
}
