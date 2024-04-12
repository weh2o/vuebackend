package com.joe.vuebackend.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 打印控制台日誌小幫手
 */
@Data
@Component
@Slf4j
public class CRUDLogHelper {

    /**
     * 標題
     */
    private String title = "";

    public CRUDLogHelper() {
    }

    public CRUDLogHelper(String methodName) {
        this.title = methodName;
    }

    /**
     * 構建打印控制台日誌小幫手
     *
     * @param title 標題
     * @return 打印控制台日誌小幫手
     */
    public static CRUDLogHelper build(String title) {
        return new CRUDLogHelper(title);
    }

    /**
     * 新增 xx 成功
     *
     * @param text 具體內容
     * @return 完整日誌字串
     */
    public String saveSuccess(String text) {
        String msg = String.format("%s ，新增 %s 成功", title, text);
        log.info(msg);
        return msg;
    }

    /**
     * 新增 xx 失敗
     *
     * @param text 具體內容
     * @return 完整日誌字串
     */
    public String saveFail(String text) {
        return saveFail(text, null);
    }

    /**
     * 新增 xx 失敗
     *
     * @param text 日誌字串
     * @param e    異常錯誤
     * @return 完整日誌字串
     */
    public String saveFail(String text, Exception e) {
        String msg = String.format("%s ，新增 %s 失敗:", title, text);
        if (Objects.nonNull(e)) {
            log.error(msg, e);
        } else {
            log.error(msg);
        }
        return msg;
    }


    /**
     * 新增 xx 失敗，該筆資料已存在資料庫中
     *
     * @param text 具體內容
     * @return 完整日誌字串
     */
    public String saveDuplicateFail(String text) {
        String msg = String.format("%s ，新增 %s 失敗，該筆資料已存在資料庫中:", title, text);
        log.warn(msg);
        return msg;
    }
}
