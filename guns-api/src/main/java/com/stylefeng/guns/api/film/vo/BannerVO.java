package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liurui
 * @time 2019-07-22 11:00
 */

@Data
public class BannerVO implements Serializable {

    private String bannerId;
    private String bannerAddress;
    private String bannerUrl;

}
