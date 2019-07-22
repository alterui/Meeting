package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.user.UserServiceAPI;
import com.stylefeng.guns.api.user.vo.UserInfoModel;
import com.stylefeng.guns.api.user.vo.UserModel;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.persistence.dao.MoocUserTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocUserT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liurui
 * @time 2019-07-20 19:06
 */

@Component
@Service(interfaceClass = UserServiceAPI.class)
public class UserServiceImpl implements UserServiceAPI {


    @Autowired
    private MoocUserTMapper moocUserTMapper;

    @Override
    public int login(String username, String password) {

        MoocUserT moocUserT = new MoocUserT();

        moocUserT.setUserName(username);

        MoocUserT result = moocUserTMapper.selectOne(moocUserT);

        if (result != null && result.getUuid() > 0) {
            if (result.getUserPwd().equals(MD5Util.encrypt(password))) {
                return result.getUuid();
            }
        }

        return 0;

    }

    @Override
    public boolean register(UserModel userModel) {

        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(userModel.getUsername());
        moocUserT.setUserPwd(MD5Util.encrypt(userModel.getPassword()));
        moocUserT.setEmail(userModel.getEmail());
        moocUserT.setUserPhone(userModel.getPhone());
        moocUserT.setAddress(userModel.getAddress());

        Integer count = moocUserTMapper.insert(moocUserT);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkUsername(String username) {

        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(username);

        //表明数据库存在这个用户名
        MoocUserT result = moocUserTMapper.selectOne(moocUserT);
        if (result != null && result.getUuid() > 0) {
            //用户名不可用，返回false
            return false;
        }
        return true;
    }

    @Override
    public UserInfoModel getUserInfo(int uuid) {

        MoocUserT moocUserT = moocUserTMapper.selectById(uuid);
        UserInfoModel userInfoModel = do2UserInfo(moocUserT);
        return userInfoModel;
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        // 将传入的参数转换为DO 【MoocUserT】
        MoocUserT moocUserT = new MoocUserT();

        moocUserT.setUuid(userInfoModel.getUuid());

        moocUserT.setNickName(userInfoModel.getNickname());
        moocUserT.setLifeState(Integer.parseInt(userInfoModel.getLifeState()));
        moocUserT.setBirthday(userInfoModel.getBirthday());
        moocUserT.setBiography(userInfoModel.getBiography());
        moocUserT.setBeginTime(null);
        moocUserT.setHeadUrl(userInfoModel.getHeadAddress());
        moocUserT.setEmail(userInfoModel.getEmail());
        moocUserT.setAddress(userInfoModel.getAddress());
        moocUserT.setUserPhone(userInfoModel.getPhone());
        moocUserT.setUserSex(userInfoModel.getSex());
        moocUserT.setUpdateTime(null);

        // DO存入数据库
        Integer integer = moocUserTMapper.updateById(moocUserT);
       // System.out.println(integer);
        if(integer>0){
            // 将数据从数据库中读取出来
            UserInfoModel userInfo = getUserInfo(moocUserT.getUuid());
            // 将结果返回给前端
            return userInfo;
        }else{
            return userInfoModel;
        }
    }


    /**
     * 提取封装UserInfoModel
     * @param moocUserT
     * @return
     */
    private UserInfoModel do2UserInfo(MoocUserT moocUserT) {
        UserInfoModel userInfoModel = new UserInfoModel();

        userInfoModel.setUsername(moocUserT.getUserName());
        userInfoModel.setUpdateTime(moocUserT.getUpdateTime().getTime());
        userInfoModel.setSex(moocUserT.getUserSex());
        userInfoModel.setPhone(moocUserT.getUserPhone());
        userInfoModel.setNickname(moocUserT.getNickName());
        userInfoModel.setLifeState("" + moocUserT.getLifeState());
        userInfoModel.setHeadAddress(moocUserT.getHeadUrl());
        userInfoModel.setEmail(moocUserT.getEmail());
        userInfoModel.setCreateTime(moocUserT.getBeginTime().getTime());
        userInfoModel.setBirthday(moocUserT.getBirthday());
        userInfoModel.setBiography(moocUserT.getBiography());
        userInfoModel.setAddress(moocUserT.getAddress());
        userInfoModel.setUuid(moocUserT.getUuid());

        return userInfoModel;
    }
}
