package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.user.UserAPI;
import org.springframework.stereotype.Component;

/**
 * @author liurui
 * @time 2019-07-20 19:06
 */

@Component
@Service(interfaceClass = UserAPI.class)
public class UserImpl implements UserAPI {
    @Override
    public boolean login(String username, String password) {
        System.out.println("This is user service" + username + "," + password);
        return false;
    }
}
