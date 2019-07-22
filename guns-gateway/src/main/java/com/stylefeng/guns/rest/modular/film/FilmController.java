package com.stylefeng.guns.rest.modular.film;

import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * film电影模块
 *
 * @author liurui
 * @time 2019-07-21 20:54
 */
@RestController
@RequestMapping("/film/")
public class FilmController {

    /**
     * 获取首页接口
     *
     * API网关：
     *      1.功能聚合【API聚合】
     *   好处：
     *      1.同一时刻，多个接口，一次请求。
     *      2.同一个接口对外暴露，降低了前后端分离开发的难度和复杂度
     *
     */
    @GetMapping("getIndex")
    public ResponseVO getIndex() {

        /**
         * 1.获取banner信息
         * 2.获取正在热映的电影
         * 3.即将上映的电影
         * 4.票房排行榜
         * 5.获取受欢迎的榜单
         * 6.获取前一百榜单
         */



        return null;

    }

}
