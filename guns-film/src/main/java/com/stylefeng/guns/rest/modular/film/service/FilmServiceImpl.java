package com.stylefeng.guns.rest.modular.film.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.BannerVO;
import com.stylefeng.guns.api.film.vo.FilmInfo;
import com.stylefeng.guns.api.film.vo.FilmVO;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.common.persistence.dao.MoocBannerTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MoocFilmTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocBannerT;
import com.stylefeng.guns.rest.common.persistence.model.MoocFilmT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Wrapper;
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

        Page<MoocFilmT> page = new Page<>(1, num);
        EntityWrapper<MoocFilmT> wrapper = new EntityWrapper<>();
        wrapper.eq("film_status", "1");

        //表明在首页展示
        if (isLimit) {
            //获取在首页展示的所有热映电影的信息
            List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, wrapper);
            List<FilmInfo> filmInfoList = getFilmInfoList(moocFilmTS);
            filmVO.setFilmInfo(filmInfoList);
            filmVO.setFilmNum(moocFilmTS.size());
        }


        return filmVO;
    }

    @Override
    public FilmVO getSoonFilms(boolean isLimit, int num) {
        return null;
    }

    @Override
    public List<FilmInfo> getBoxRanking() {

        return null;
    }

    @Override
    public List<FilmInfo> getExpectRanking() {
        return null;
    }

    @Override
    public List<FilmInfo> getTop() {
        return null;
    }
}
