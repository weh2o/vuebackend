package com.joe.vuebackend.utils;


import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

public class BindingResultHelper {

    /**
     * 返回全部錯誤的字串
     * 格式: 錯誤: a錯誤、b錯誤、c錯誤
     *
     * @param bindingResult
     * @return 全部錯誤的字串
     */
    public static String failMsg(BindingResult bindingResult) {
        List<ObjectError> errors = bindingResult.getAllErrors();
        StringBuilder result = new StringBuilder("錯誤: ");
        for (int i = 0; i < errors.size(); i++) {
            String msg = errors.get(i).getDefaultMessage();
            result.append(msg);
            if (i != errors.size() - 1) {
                result.append("、");
            }
        }
        return result.toString();
    }
}
