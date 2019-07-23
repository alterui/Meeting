package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liurui
 * @time 2019-07-23 10:10
 */
@Data
public class YearInfoVO implements Serializable {
    private String yearId;
    private String yearName;
    private boolean isActive;
}
