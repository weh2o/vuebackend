package com.joe.vuebackend.bean;

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



    public static <t> HttpResult<t> success(){
        return success("成功");
    }
    public static <t> HttpResult<t> success(String msg){
        HttpResult<t> target = new HttpResult<>();
        target.setMsg(msg);
        target.setCode(HttpStatus.OK.value());
        return target;
    }

    public static <t> HttpResult<t> success(t data){
        HttpResult<t> target = new HttpResult<>();
        target.setMsg("成功");
        target.setCode(HttpStatus.OK.value());
        target.setData(data);
        return target;
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
        HttpResult<t> target = new HttpResult<>();
        target.setMsg(msg);
        target.setCode(HttpStatus.BAD_REQUEST.value());
        return target;
    }

    public static <t> HttpResult<t> fail(String msg, t data){
        HttpResult<t> target = new HttpResult<>();
        target.setCode(HttpStatus.BAD_REQUEST.value());
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