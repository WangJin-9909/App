package com.example.networklib;

import com.google.gson.JsonObject;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public BaseResponse() {
    }

    public String getCode() {
        return this.code == null ? "" : this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg == null ? "" : this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data == null ? (T)new JsonObject() : this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
