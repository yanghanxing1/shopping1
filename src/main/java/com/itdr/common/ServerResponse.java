package com.itdr.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * 服务端返回到前端的高复用的相应对象
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保证序列化json的时候，如果是null的对象，key也会消失
public class ServerResponse<T> implements Serializable {

    private int status;//返回到前端的状态码
    private T data;//返回给前端的数据
    private String msg;//当status！=0时，封装了错误信息

    private ServerResponse() {

    }

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;

    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }


    /**
     * 调用接口成功时回调
     */
    public static ServerResponse createServerResponseBySuccess() {
        return new ServerResponse(ResponseCode.SUCESS);
    }

    public static <T> ServerResponse createServerResponseBySuccess(T data) {
        return new ServerResponse(ResponseCode.SUCESS, data);
    }

    public static <T> ServerResponse createServerResponseBySuccess(T data, String msg) {
        return new ServerResponse(ResponseCode.SUCESS, data, msg);
    }


    /**
     * 接口调用失败时回调
     */

    public static ServerResponse createServerResponseByError() {
        return new ServerResponse(ResponseCode.ERROR);
    }

    public static ServerResponse createServerResponseByError(String msg) {
        return new ServerResponse(ResponseCode.ERROR, msg);
    }

    public static ServerResponse createServerResponseByError(int status) {
        return new ServerResponse(status);
    }

    public static ServerResponse createServerResponseByError(int status, String msg) {
        return new ServerResponse(status, msg);
    }


    /**
     * 判断接口是否正确返回
     * status==0
     */
    @JsonIgnore
    //让该方法不在json序列化接口当中
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCESS;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
