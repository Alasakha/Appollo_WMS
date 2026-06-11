package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.Admmg;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LiangPandeng
 * @since 2024-06-04
 */
@DS("demo")
@Mapper
public interface AdmmgMapper extends BaseMapper<Admmg> {

}
