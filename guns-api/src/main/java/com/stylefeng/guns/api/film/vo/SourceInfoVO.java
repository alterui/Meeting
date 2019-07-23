package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liurui
 * @time 2019-07-23 10:09
 */
@Data
public class SourceInfoVO implements Serializable {
    private String sourceId;
    private String sourceName;
    private boolean isActive;
}
