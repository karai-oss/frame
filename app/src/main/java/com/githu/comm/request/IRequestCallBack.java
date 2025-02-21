package com.githu.comm.request;


/**
 * 请求回调对象
 *
 * @author Mr.xie
 * @Date 2025/2/21
 */
public interface IRequestCallBack {


    /**
     * 请求成功的回调
     *
     * @param successCode 封装了请求成功的信息
     */
    public void requestSuccess(RequestCode successCode);


    /**
     * 请求失败的回调
     *
     * @param errorCode 封装了请求失败的信息
     */
    public void requestError(RequestCode errorCode);

}
