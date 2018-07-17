package com.huawei.bean;

import java.io.Serializable;

public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = 1421937580611606987L;

    private Integer code;

    private String message = "";

    private T data;

    public ResponseData() {
        super();
    }

    public ResponseData(T data) {
        super();
        this.data = data;
        this.code = 1000;
    }

    public ResponseData(Integer errorCode, String message) {
        super();
        this.code = errorCode;
        this.message = message;
    }

    public ResponseData(Integer errorCode, String message, T data) {
        super();
        this.code = errorCode;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseData setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseData setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseData setData(T data) {
        this.data = data;
        return this;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
