package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 类型信息的VO
 *
 * @author liurui
 * @time 2019-07-22 20:17
 */
@Data
public class CatInfoVO implements Serializable {
    private String catId;
    private String catName;
    private boolean isActive;

}
