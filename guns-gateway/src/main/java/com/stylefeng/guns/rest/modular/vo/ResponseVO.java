package com.stylefeng.guns.rest.modular.vo;

import com.sun.org.apache.regexp.internal.RE;

import javax.xml.ws.Response;

/**
 * 用户模块VO类
 *
 * @author liurui
 * @time 2019-07-21 10:42
 */
public class ResponseVO<T> {

    //报文返回状态【0-成功，1-业务失败，999-系统异常】
    private int status;

    //返回信息
    private String msg;

    //返回数据实体
    private T data;


    //使用单例模式，禁止外部建立实体
    private ResponseVO(){

    }


    /**
     * 成功返回的报文
     * @param t
     * @param <T>
     * @return
     */
    public static<T> ResponseVO success(T t) {

        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(t);

        return responseVO;
    }


    /**
     * 返回成功的msg
     * @param msg
     * @return
     */
    public static ResponseVO success(String msg) {

        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setMsg(msg);

        return responseVO;
    }


    /**
     * 业务失败返回的报文
     * @param msg
     * @return
     */
    public static ResponseVO serviceFail(String msg) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setMsg(msg);

        return responseVO;
    }

    /**
     * 系统异常返回的报文
     * @param msg
     * @return
     */
    public static ResponseVO appFail(String msg) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(999);
        responseVO.setMsg(msg);

        return responseVO;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
