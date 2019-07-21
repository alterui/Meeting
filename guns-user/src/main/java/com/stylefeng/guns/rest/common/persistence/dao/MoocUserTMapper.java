package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MoocUserT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author liurui
 * @since 2019-07-21
 */

@Repository
public interface MoocUserTMapper extends BaseMapper<MoocUserT> {

}
