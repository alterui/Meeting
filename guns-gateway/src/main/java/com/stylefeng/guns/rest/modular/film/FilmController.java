package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.CatInfoVO;
import com.stylefeng.guns.api.film.vo.SourceInfoVO;
import com.stylefeng.guns.api.film.vo.YearInfoVO;
import com.stylefeng.guns.rest.modular.film.vo.FilmConditionVO;
import com.stylefeng.guns.rest.modular.film.vo.FilmIndexVO;
import com.stylefeng.guns.rest.modular.film.vo.FilmRequestVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * film电影模块
 *
 * @author liurui
 * @time 2019-07-21 20:54
 */
@RestController
@RequestMapping("/film/")
public class FilmController {

    @Reference(interfaceClass = FilmServiceAPI.class)
    private FilmServiceAPI filmServiceAPI;

    private static final String IMG_PRE = "http://img.meetingshop.cn/";


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

        FilmIndexVO filmIndexVO = new FilmIndexVO();

        filmIndexVO.setBanners(filmServiceAPI.getBanners());

        filmIndexVO.setHotFilms(filmServiceAPI.getHotFilms(true, 8));

        filmIndexVO.setSoonFilms(filmServiceAPI.getSoonFilms(true, 8));

        filmIndexVO.setBoxRanking(filmServiceAPI.getBoxRanking());

        filmIndexVO.setExpectRanking(filmServiceAPI.getExpectRanking());

        filmIndexVO.setTop100(filmServiceAPI.getTop());

        return ResponseVO.success(IMG_PRE,filmIndexVO);

    }

    @GetMapping("getConditionList")
    public ResponseVO getConditionList(@RequestParam(name = "catId",required = false,defaultValue = "99") String catId,
                                       @RequestParam(name = "sourceId",required = false,defaultValue = "99") String sourceId,
                                       @RequestParam(name = "yearId", required = false, defaultValue = "99") String yearId) {

        FilmConditionVO filmConditionVO = new FilmConditionVO();

        // 标识位
        boolean flag = false;
        // 类型集合
        List<CatInfoVO> cats = filmServiceAPI.getCatInfo();
        List<CatInfoVO> catResult = new ArrayList<>();
        CatInfoVO cat = null;
        for(CatInfoVO catVO : cats){
            // 判断集合是否存在catId，如果存在，则将对应的实体变成active状态
            // 6
            // 1,2,3,99,4,5 ->
            /*
                优化：【理论上】
                    1、数据层查询按Id进行排序【有序集合 -> 有序数组】
                    2、通过二分法查找
             */
            if(catVO.getCatId().equals("99")){
                cat = catVO;
                continue;
            }
            if(catVO.getCatId().equals(catId)){
                flag = true;
                catVO.setActive(true);
            }else{
                catVO.setActive(false);
            }
            catResult.add(catVO);
        }
        // 如果不存在，则默认将全部变为Active状态
        if(!flag){
            cat.setActive(true);
            catResult.add(cat);
        }else{
            cat.setActive(false);
            catResult.add(cat);
        }


        // 片源集合
        flag=false;
        List<SourceInfoVO> sources = filmServiceAPI.getSourceInfo();
        List<SourceInfoVO> sourceResult = new ArrayList<>();
        SourceInfoVO sourceVO = null;
        for(SourceInfoVO source : sources){
            if(source.getSourceId().equals("99")){
                sourceVO = source;
                continue;
            }
            if(source.getSourceId().equals(catId)){
                flag = true;
                source.setActive(true);
            }else{
                source.setActive(false);
            }
            sourceResult.add(source);
        }
        // 如果不存在，则默认将全部变为Active状态
        if(!flag){
            sourceVO.setActive(true);
            sourceResult.add(sourceVO);
        }else{
            sourceVO.setActive(false);
            sourceResult.add(sourceVO);
        }

        // 年代集合
        flag=false;
        List<YearInfoVO> years = filmServiceAPI.getYearInfo();
        List<YearInfoVO> yearResult = new ArrayList<>();
        YearInfoVO yearVO = null;
        for(YearInfoVO year : years){
            if(year.getYearId().equals("99")){
                yearVO = year;
                continue;
            }
            if(year.getYearId().equals(catId)){
                flag = true;
                year.setActive(true);
            }else{
                year.setActive(false);
            }
            yearResult.add(year);
        }
        // 如果不存在，则默认将全部变为Active状态
        if(!flag){
            yearVO.setActive(true);
            yearResult.add(yearVO);
        }else{
            yearVO.setActive(false);
            yearResult.add(yearVO);
        }

        filmConditionVO.setCatInfo(catResult);
        filmConditionVO.setSourceInfo(sourceResult);
        filmConditionVO.setYearInfo(yearResult);

        return ResponseVO.success(filmConditionVO);
    }


    /**
     * @return
     */
    @GetMapping("getFilms")
    public ResponseVO getFilms(FilmRequestVO filmRequestVO) {

        return null;
    }

    public static void main(String[] args) {

    }



}
