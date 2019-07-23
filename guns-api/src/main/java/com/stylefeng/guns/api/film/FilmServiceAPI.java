package com.stylefeng.guns.api.film;

import com.stylefeng.guns.api.film.vo.*;

import java.util.List;

/**
 * 影片API接口
 *
 * @author liurui
 * @time 2019-07-22 11:37
 */
public interface FilmServiceAPI {

    /**
     * 获取banners
     *
     * qus：为什么不用List集合
     * @return
     */
    List<BannerVO> getBanners();

    /**
     * 获取热映影片
     * tip:由于热映影片展示不同的地方，有的地方显示其中的几个，有的地方显示所有，因此设置个开关。
     * @param isLimit 是否展示所有的开关
     * @param num 展示的数量
     * @return
     */
    FilmVO getHotFilms(boolean isLimit, int num);


    /**
     * 获取即将上映影片
     * tip:由于热映影片展示不同的地方，有的地方显示其中的几个，有的地方显示所有，因此设置个开关。
     * @param isLimit 是否展示所有的开关
     * @param num 展示的数量
     * @return
     */
    FilmVO getSoonFilms(boolean isLimit, int num);

    /**
     * 票房排行榜
     * @return
     */
    List<FilmInfo> getBoxRanking();

    /**
     * 受欢迎的榜单
     * @return
     */
    List<FilmInfo> getExpectRanking();

    /**
     * 获取top100
     * @return
     */
    List<FilmInfo> getTop();

    /**
     * 获取类型信心
     * @return
     */
    List<CatInfoVO> getCatInfo();

    /**
     * 获取片源信息
     * @return
     */
    List<SourceInfoVO> getSourceInfo();

    /**
     * 获取年代信息
     * @return
     */
    List<YearInfoVO> getYearInfo();






}
