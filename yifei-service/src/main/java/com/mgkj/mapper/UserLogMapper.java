package com.mgkj.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.UserLog;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LiangPandeng
 * @since 2024-04-07
 */
@DS("demo")
public interface UserLogMapper extends BaseMapper<UserLog> {

}
