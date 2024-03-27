package com.joe.vuebackend.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 響應結果物件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpResult<t> implements Serializable {

    /**
     * 響應碼
     */
    private Integer code;

    /**
     * 響應消息
     */
    private String msg;

    /**
     * 響應物件
     */
    private t data;


    public static <t> HttpResult<t> success(String msg, t data){
        HttpResult<t> target = new HttpResult<>();
        target.setCode(HttpStatus.OK.value());
        target.setMsg(msg);
        target.setData(data);
        return target;
    }

    public static <t> HttpResult<t> error(String msg, t data){
        HttpResult<t> target = new HttpResult<>();
        target.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        target.setMsg(msg);
        target.setData(data);
        return target;
    }
}