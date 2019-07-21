package com.stylefeng.guns.rest.common;

/**
 * 用户获取当前user的工具类
 *
 * @author liurui
 * @time 2019-07-21 11:20
 */
public class CurrentUser {

    //生成线程绑定的存储空间
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 将用户id放入存储空间
     * @param userId
     */
    public static void saveUserId(String userId) {
        threadLocal.set(userId);

    }

    /**
     * 将用户id将存储空间里面取出
     * @return
     */
    public static String getUserId() {
        return threadLocal.get();
    }
}
