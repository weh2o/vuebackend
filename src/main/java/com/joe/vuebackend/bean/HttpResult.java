package com.joe.vuebackend.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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



    public static <t> HttpResult<t> success(){
        return success("成功");
    }
    public static <t> HttpResult<t> success(String msg){
        return success(msg, null);
    }

    public static <t> HttpResult<t> success(t data){
        return success("成功", data);
    }

    public static <t> HttpResult<t> success(String msg, t data){
        HttpResult<t> target = new HttpResult<>();
        target.setCode(HttpStatus.OK.value());
        target.setMsg(msg);
        target.setData(data);
        return target;
    }

    public static <t> HttpResult<t> fail(){
        return fail("失敗");
    }

    public static <t> HttpResult<t> fail(String msg){
        return fail(msg, null);
    }

    public static <t> HttpResult<t> fail(Integer status, String msg){
        return fail(status, msg, null);
    }

    public static <t> HttpResult<t> fail(String msg, t data){
        return fail(null, msg, data);
    }
    public static <t> HttpResult<t> fail(Integer status, String msg, t data){
        HttpResult<t> target = new HttpResult<>();

        if (ObjectUtils.isNotEmpty(status)){
            target.setCode(status);
        }else {
            target.setCode(HttpStatus.BAD_REQUEST.value());
        }

        if (StringUtils.isNotEmpty(msg)){
            target.setMsg(msg);
        }

        if (ObjectUtils.isNotEmpty(data)){
            target.setData(data);
        }
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