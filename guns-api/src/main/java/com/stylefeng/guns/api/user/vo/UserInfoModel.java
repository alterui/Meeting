package com.stylefeng.guns.api.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 * @author liurui
 * @time 2019-07-21 07:52
 */
@Data
public class UserInfoModel implements Serializable {

    //uuid
    private Integer uuid;
    //用户名
    private String username;
    //昵称
    private String nickname;
    //邮箱
    private String email;
    //电话
    private String phone;
    //性别
    private int sex;
    //出生日期
    private String birthday;
    //婚姻状态
    private String lifeState;
    //个人简介
    private String biography;
    //住址
    private String address;
    //头像地址
    private String headAddress;
    //用户创建日期
    private long createTime;
    //用户更新日期
    private long updateTime;


}
