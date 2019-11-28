package com.tiger.btp.framework.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * BaseMapper 基于 MP 删减
 * <p>
 * * @see com.baomidou.mybatisplus.core.mapper.BaseMapper
 */
@Mapper
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {
}
