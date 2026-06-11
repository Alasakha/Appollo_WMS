package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.CMSMD;
import com.mgkj.entity.CMSMV;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/18/23:29
 * @Description:    工作中心信息单头档 Mapper
 */
@Mapper
public interface CMSMDMapper extends BaseMapper<CMSMD> {
    /**
     * 根据 工作中心编号 查询工作中心信息详情
     * @param MD001
     * @return
     */
    @DS("dscsys")
    @Select("select * from CMSMD where MD001 = #{MD001}")
    CMSMD getByMD001(String MD001);
}


