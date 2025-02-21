package com.githu.comm.request;


/**
 * @author Mr.xie
 * @Date 2025/2/21
 */
public class RequestCode {


    public int code;

    public String msg;


    public String tag ;

    Object data;

    RequestCode(int code, String msg, String tag , Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.tag = tag;
    }

    RequestCode() {
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
