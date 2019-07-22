package com.stylefeng.guns.api.user;

import com.stylefeng.guns.api.user.vo.UserInfoModel;
import com.stylefeng.guns.api.user.vo.UserModel;

/**
 * @author liurui
 * @time 2019-07-20 19:05
 */
public interface UserServiceAPI {


    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    int login(String username, String password);


    /**
     * 注册
     * @param userModel
     * @return
     */
    boolean register(UserModel userModel);

    /**
     * 检测用户名是否存在
     * @param username
     * @return
     */
    boolean checkUsername(String username);

    /**
     * 得到用户信息
     * @param uuid
     * @return
     */
    UserInfoModel getUserInfo(int uuid);

    /**
     * 修改用户信息
     * @param userInfoModel
     * @return
     */
    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);










}
