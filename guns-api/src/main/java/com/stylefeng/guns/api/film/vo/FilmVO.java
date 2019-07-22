package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 正在热映和即将上映的影片
 * @author liurui
 * @time 2019-07-22 11:10
 */
@Data
public class FilmVO implements Serializable {
    private int filmNum;
    private List<FilmInfo> filmInfo;

}
