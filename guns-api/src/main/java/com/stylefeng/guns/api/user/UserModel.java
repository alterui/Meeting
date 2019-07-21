package com.stylefeng.guns.api.user;

import java.io.Serializable;

/**
 * 用户注册信息
 * @author liurui
 * @time 2019-07-21 07:50
 */
public class UserModel implements Serializable {
    //用户名
    private String username;
    //密码
    private String password;
    //邮箱
    private String email;
    //电话
    private String phone;
    //地址
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
