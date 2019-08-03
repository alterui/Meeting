package com.stylefeng.guns.rest.modular.film.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.*;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liurui
 * @time 2019-07-22 15:14
 */

@Component
@Service(interfaceClass = FilmServiceAPI.class)
public class FilmServiceImpl implements FilmServiceAPI {

    @Autowired
    private MoocBannerTMapper moocBannerTMapper;

    @Autowired
    private MoocFilmTMapper moocFilmTMapper;


    @Autowired
    private MoocCatDictTMapper moocCatDictTMapper;

    @Autowired
    private MoocSourceDictTMapper moocSourceDictTMapper;

    @Autowired
    private MoocYearDictTMapper moocYearDictTMapper;


    /**
     * 获取banners的业务逻辑
     * @return
     */
    @Override
    public List<BannerVO> getBanners() {

        //得到所有的banner
        List<MoocBannerT> moocBannerTS = moocBannerTMapper.selectList(null);
        //用于盛放赋值后的结果集
        List<BannerVO> result = new ArrayList<>();

        //遍历为BannerVO赋值
        for (MoocBannerT moocBannerT : moocBannerTS) {
            BannerVO bannerVO = new BannerVO();
            bannerVO.setBannerId(moocBannerT.getUuid() + "");
            bannerVO.setBannerUrl(moocBannerT.getBannerUrl());
            bannerVO.setBannerAddress(moocBannerT.getBannerAddress());
            result.add(bannerVO);

        }
        return result;
    }


    /**
     * 即将上映和热映都会用到的公共填充方法
     * @param moocFilmTS
     * @return
     */
    public List<FilmInfo> getFilmInfoList(List<MoocFilmT> moocFilmTS) {
        List<FilmInfo> filmInfoList = new ArrayList<>();
        for (MoocFilmT moocFilmT : moocFilmTS) {
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setShowTime(DateUtil.getDay(moocFilmT.getFilmTime()));
            filmInfo.setScore(moocFilmT.getFilmScore());
            filmInfo.setImgAddress(moocFilmT.getImgAddress());
            filmInfo.setFilmType(moocFilmT.getFilmType());
            filmInfo.setFilmScore(moocFilmT.getFilmScore());
            filmInfo.setFilmName(moocFilmT.getFilmName());
            filmInfo.setFilmId(moocFilmT.getUuid() + "");
            filmInfo.setExpectNum(moocFilmT.getFilmPresalenum());
            filmInfo.setBoxNum(moocFilmT.getFilmBoxOffice());


            //添加到List集合中
            filmInfoList.add(filmInfo);
        }

        return filmInfoList;
    }

    /**
     * 获取热映影片
     * @param isLimit 是否展示所有的开关
     * @param num 展示的数量
     * @return
     */
    @Override
    public FilmVO getHotFilms(boolean isLimit, int num) {
        FilmVO filmVO = new FilmVO();

        //表明从第一页开始，一页展示num个
        Page<MoocFilmT> page = new Page<>(1, num);

        //设置根据条件进行分页
        EntityWrapper<MoocFilmT> wrapper = new EntityWrapper<>();
        //影片状态,1-正在热映，2-即将上映，3-经典影片
        wrapper.eq("film_status", "1");

        //表明在首页展示
        getFilmVO(isLimit, filmVO, page, wrapper);


        return filmVO;
    }

    /**
     * 获取即将上映的影片
     * @param isLimit 是否展示所有的开关
     * @param num 展示的数量
     * @return
     */
    @Override
    public FilmVO getSoonFilms(boolean isLimit, int num) {
        FilmVO filmVO = new FilmVO();

        //表明从第一页开始，一页展示num个
        Page<MoocFilmT> page = new Page<>(1, num);

        //设置根据条件进行分页
        EntityWrapper<MoocFilmT> wrapper = new EntityWrapper<>();
        //影片状态,1-正在热映，2-即将上映，3-经典影片
        wrapper.eq("film_status", "2");
        getFilmVO(isLimit, filmVO, page, wrapper);

        return filmVO;
    }

    /**
     * 提取的公共方法
     * @param isLimit
     * @param filmVO
     * @param page
     * @param wrapper
     */
    private void getFilmVO(boolean isLimit, FilmVO filmVO, Page<MoocFilmT> page, EntityWrapper<MoocFilmT> wrapper) {
        //表明在首页展示
        if (isLimit) {
            //获取在首页展示的所有热映电影的信息
            List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, wrapper);
            List<FilmInfo> filmInfoList = getFilmInfoList(moocFilmTS);
            filmVO.setFilmInfo(filmInfoList);
            filmVO.setFilmNum(moocFilmTS.size());
        }
    }

    /**
     * 票房排行榜
     * @return
     */
    @Override
    public List<FilmInfo> getBoxRanking() {
        //影片状态,1-正在热映，2-即将上映，3-经典影片
        Page<MoocFilmT> page = new Page<>(1, 10,"film_box_office");

        List<FilmInfo> filmInfoList = getFilmInfos(page, 1);

        return filmInfoList;
    }

    /**
     * 最受期待的排行榜
     * @return
     */
    @Override
    public List<FilmInfo> getExpectRanking() {
        //影片状态,1-正在热映，2-即将上映，3-经典影片
        Page<MoocFilmT> page = new Page<>(1, 10,"film_preSaleNum");

        List<FilmInfo> filmInfoList = getFilmInfos(page, 2);

        return filmInfoList;
    }

    /**
     * 正在上映评分最高的排行榜
     * @return
     */
    @Override
    public List<FilmInfo> getTop() {
        //影片状态,1-正在热映，2-即将上映，3-经典影片
        Page<MoocFilmT> page = new Page<>(1, 10,"film_score");

        List<FilmInfo> filmInfoList = getFilmInfos(page, 1);

        return filmInfoList;
    }

    /**
     * 提出公共类
     * @param page
     * @param i
     * @return
     */
    private List<FilmInfo> getFilmInfos(Page<MoocFilmT> page, int i) {
        EntityWrapper<MoocFilmT> wrapper = new EntityWrapper<>();
        wrapper.eq("film_status", i);

        List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, wrapper);
        return getFilmInfoList(moocFilmTS);
    }


    /**
     * 获取类型信息
     * @return
     */
    @Override
    public List<CatInfoVO> getCatInfo() {
        List<CatInfoVO> catInfoVOList = new ArrayList<>();
        List<MoocCatDictT> moocCatDictTS = moocCatDictTMapper.selectList(null);
        for (MoocCatDictT moocCatDictT : moocCatDictTS) {
            CatInfoVO catInfoVO = new CatInfoVO();
            catInfoVO.setCatName(moocCatDictT.getShowName());
            catInfoVO.setCatId(moocCatDictT.getUuid() + "");
            catInfoVOList.add(catInfoVO);

        }
        return catInfoVOList;
    }

    /**
     * 获取片源信息
     * @return
     */
    @Override
    public List<SourceInfoVO> getSourceInfo() {
        List<SourceInfoVO> sourceInfoVOList = new ArrayList<>();

        List<MoocSourceDictT> moocSourceDictTS = moocSourceDictTMapper.selectList(null);
        for (MoocSourceDictT moocSourceDictT : moocSourceDictTS) {
            SourceInfoVO sourceInfoVO = new SourceInfoVO();
            sourceInfoVO.setSourceId(moocSourceDictT.getUuid() + "");
            sourceInfoVO.setSourceName(moocSourceDictT.getShowName());
            sourceInfoVOList.add(sourceInfoVO);
        }
        return sourceInfoVOList;
    }

    /**
     * 获取年代信息
     * @return
     */
    @Override
    public List<YearInfoVO> getYearInfo() {

        List<YearInfoVO> yearInfoVOList = new ArrayList<>();
        List<MoocYearDictT> moocYearDictTS = moocYearDictTMapper.selectList(null);
        for (MoocYearDictT moocYearDictT : moocYearDictTS) {
            YearInfoVO yearInfoVO = new YearInfoVO();
            yearInfoVO.setYearName(moocYearDictT.getShowName());
            yearInfoVO.setYearId(moocYearDictT.getUuid()+"");
            yearInfoVOList.add(yearInfoVO);

        }
        return yearInfoVOList;
    }
}
