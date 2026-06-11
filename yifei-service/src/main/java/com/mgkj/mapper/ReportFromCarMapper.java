package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.vo.ReportFromCarVo;
import com.mgkj.vo.ReportFromOutVo;
import com.mgkj.vo.ReportVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : ssy
 * @date: 2024-01-12
 * @Description:
 */
@Mapper
public interface ReportFromCarMapper extends BaseMapper<ReportFromCarVo> {

    @DS("dscsys")
    List<ReportVo> inputQuantity1(String workCenter, String start_time, String end_time, String type);

    @DS("dscsys")
    List<ReportVo> inputQuantity2(String workCenter,  String start_time, String end_time, String type);

    @DS("dscsys")
    List<ReportVo> inputQuantity3(String start_time, String end_time, String type);

    @DS("dscsys")
    List<ReportVo> inputQuantity4(String start_time, String end_time, String type);

    @DS("dscsys")
    List<ReportVo> outputQuantity1(String workCenter, String start_time, String end_time, String type);

    @DS("dscsys")
    List<ReportVo> outputQuantity2(String workCenter, String start_time, String end_time, String type);

    @DS("dscsys")
    List<ReportVo> outputQuantityByFG(String start_time, String end_time, String type);

    @DS("dscsys")
    List<ReportVo> outputQuantity3(String start_time, String end_time, String type);

    @DS("dscsys")
    List<ReportVo> outputQuantity4(String start_time, String end_time,String type);

    @DS("dscsys")
    List<ReportVo> scrapQuantity(String workCenter, String start_time, String end_time,String type);

    @DS("dscsys")
    List<String> getWorkCenter();
}
