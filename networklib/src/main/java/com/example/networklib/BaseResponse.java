package com.example.networklib;

import com.google.gson.JsonObject;

import java.io.Serializable;

public class BaseResponse<T> {
    public String resultCode;
    public String resultMsg;
    public String traceNo;
    public T msg;



    public BaseResponse(String resultCode, String resultMsg, String traceNo, T msg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.traceNo = traceNo;
        this.msg = msg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }
}
