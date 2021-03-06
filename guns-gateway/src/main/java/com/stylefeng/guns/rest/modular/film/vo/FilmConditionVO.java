package com.stylefeng.guns.rest.modular.film.vo;

import com.stylefeng.guns.api.film.vo.CatInfoVO;
import com.stylefeng.guns.api.film.vo.SourceInfoVO;
import com.stylefeng.guns.api.film.vo.YearInfoVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liurui
 * @time 2019-07-23 11:05
 */
@Data
public class FilmConditionVO implements Serializable {

    private List<CatInfoVO> catInfo;

    private List<SourceInfoVO> sourceInfo;

    private List<YearInfoVO> yearInfo;
}
