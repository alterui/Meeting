package com.stylefeng.guns.api.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册信息
 * @author liurui
 * @time 2019-07-21 07:50
 */

@Data
public class UserModel implements Serializable {
    //用户名
    private String user_nameing;
    //密码
    private String userPwd;
    //邮箱
    private String email;
    //电话
    private String userPhone;
    //地址
    private String address;


}
