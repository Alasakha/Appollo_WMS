package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.CMSME;
import com.mgkj.vo.SfctaMoctaVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/18/16:56
 * @Description:    部门信息档   Mapper
 */
@Mapper
public interface CMSMEMapper extends BaseMapper<CMSME> {
    /**
     * 根据 部门编号 获取部门信息详情
     * @param ME001
     * @return
     */
    @DS("dscsys")
    @Select("select * from CMSME where ME001 = #{ME001}")
    CMSME getByME001(String ME001);

    @DS("dscsys")
    @Select("select ME001,ME002 from CMSME")
    List<CMSME> getAll();

}
