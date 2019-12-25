package com.guli.edu.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jason
 * @create 2019-12-08-17:18
 */

@Data
public class R {

    private boolean success;

    private Integer code;

    private String message;

    private Map<String,Object> data = new HashMap<String,Object>();

    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(StateCode.SUCCESS);
        r.setMessage("请求成功");

        return r;
    }

    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(StateCode.ERROR);
        r.setMessage("请求失败");

        return r;
    }

    public R data(Map<String,Object> data) {
        this.setData(data);
        return this;
    }

    public R data(String key, Object value) {

        this.data.put(key, value);
        return this;
    }

    public R success(boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }
}
