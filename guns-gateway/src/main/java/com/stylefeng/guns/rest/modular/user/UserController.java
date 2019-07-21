package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.UserInfoModel;
import com.stylefeng.guns.api.user.UserModel;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * gateway中的用户控制层
 *
 * @author liurui
 * @time 2019-07-21 14:46
 */
@RequestMapping("/user/")
@RestController
public class UserController {

    @Reference
    private UserAPI userAPI;

    /**
     * 用户注册接口
     * @param userModel
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ResponseVO register(UserModel userModel) {
        if (userModel.getUsername() == null || userModel.getUsername().trim().length() == 0) {
            return ResponseVO.serviceFail("用户名不能为空");
        }


        if (userModel.getPassword() == null || userModel.getPassword().trim().length() == 0) {
            return ResponseVO.serviceFail("密码不能为空");
        }


        boolean isSuccess = userAPI.register(userModel);
        if (isSuccess) {
            return ResponseVO.success("注册成功");
        } else {

            return ResponseVO.serviceFail("注册失败");
        }
    }


    /**
     * 检测用户名是否存在
     * @param username
     * @return
     */
    @PostMapping("check")
    public ResponseVO check(String username) {
        if (username != null && username.trim().length() > 0) {
            boolean isUsable = userAPI.checkUsername(username);
            if (isUsable) {
                return ResponseVO.success("用户名不存在");
            } else {
                return ResponseVO.serviceFail("用户名已存在");
            }
        } else {
            return ResponseVO.serviceFail("用户名不能不空");
        }
    }


    /**
     * 用户登出
     * @return
     */
    @RequestMapping("logout")
    public ResponseVO logout() {
        return ResponseVO.success("用户退出成功");
    }


    /**
     * 查询用户信息
     * @return
     */
    @RequestMapping("getUserInfo")
    public ResponseVO getUserInfo() {

        //得到当前用户userId
        String userId = CurrentUser.getUserId();
        if (userId != null && userId.trim().length() > 0) {
            UserInfoModel userInfo = userAPI.getUserInfo(Integer.parseInt(userId));
            if (userInfo != null) {
                return ResponseVO.success(userInfo);
            } else {
                return ResponseVO.serviceFail("用户信息查询失败");
            }
        } else {
            return ResponseVO.serviceFail("查询失败，用户尚未登录");
        }

    }

    /**
     * 修改个人信息
     *
     * @param userInfoModel
     * @return
     */
    @PostMapping("updateUserInfo")
    public ResponseVO updateUserInfo(UserInfoModel userInfoModel) {
        String userId = CurrentUser.getUserId();

        if (userId != null && userId.trim().length() > 0) {
            if (Integer.parseInt(userId) != userInfoModel.getUuid()) {
                return ResponseVO.serviceFail("请修改您个人的用户信息");
            }

            UserInfoModel infoModel = userAPI.updateUserInfo(userInfoModel);


            if (infoModel != null) {
                return ResponseVO.success(infoModel);
            } else {
                return ResponseVO.serviceFail("修改用户信息失败");
            }
        } else {
            return ResponseVO.serviceFail("请登录后再修改个人信息");
        }
    }

}
