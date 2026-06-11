package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.vo.ReportFromOutVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : ssy
 * @date: 2024-01-12
 * @Description:
 */
@Mapper
public interface ReportFromOutMapper extends BaseMapper<ReportFromOutVo> {

    @DS("dscsys")
    List<ReportFromOutVo> deliveryLot(List<String> supplierName, String start_time, String end_time, String type);


    @DS("dscsys")
    List<ReportFromOutVo> qualifiedLot(List<String> supplierName,  String start_time, String end_time, String type);


    @DS("dscsys")
    List<ReportFromOutVo> deliveryQuantity(List<String> supplierName,  String start_time, String end_time, String type);


    @DS("dscsys")
    List<ReportFromOutVo> qualifiedQuantity(List<String> supplierName, String start_time, String end_time, String type);

    @DS("dscsys")
    List<String> getSupplier();
}
