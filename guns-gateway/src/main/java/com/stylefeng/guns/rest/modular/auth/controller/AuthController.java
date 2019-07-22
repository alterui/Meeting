package com.stylefeng.guns.rest.modular.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.user.UserServiceAPI;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Reference(interfaceClass = UserServiceAPI.class)
    private UserServiceAPI userServiceAPI;

    @RequestMapping(value = "${jwt.auth-path}")
    public ResponseVO<?> createAuthenticationToken(AuthRequest authRequest) {


        boolean validate = true;
        int userId = userServiceAPI.login(authRequest.getUserName(), authRequest.getPassword());

        if (userId == 0) {
            validate = false;
        }

        if (validate) {
            //生成randomKey
            final String randomKey = jwtTokenUtil.getRandomKey();
            //生成token
            final String token = jwtTokenUtil.generateToken("" + userId, randomKey);
            //认证成功，返回成功的报文
            return ResponseVO.success(new AuthResponse(token, randomKey));
        } else {
            return ResponseVO.serviceFail("用户名或者密码错误");
        }

    }
}
