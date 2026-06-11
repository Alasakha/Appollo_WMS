package com.mgkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.vo.ReportFromOutVo;

import java.util.List;
import java.util.Map;

/**
 * @author : ssy
 * @date: 2024-01-12
 * @Description:
 */
public interface ReportFromOutService extends IService<ReportFromOutVo> {
    Map<String,Object> deliveryLot(List<String> supplierName,  String start_time, String end_time, String type);

    Map<String,Object> qualifiedLot(List<String> supplierName,  String start_time, String end_time, String type);

    Map<String,Object> deliveryQuantity(List<String> supplierName,  String start_time, String end_time, String type);

    Map<String,Object> qualifiedQuantity(List<String> supplierName, String start_time, String end_time, String type);

    List<String> getSupplier();
}
