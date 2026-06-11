package com.mgkj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.mapper.ReportFromOutMapper;
import com.mgkj.service.ReportFromOutService;
import com.mgkj.vo.ReportFromOutVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : ssy
 * @date: 2024-01-12
 * @Description:
 */
@Service
public class ReportFromOutServiceImpl extends ServiceImpl<ReportFromOutMapper, ReportFromOutVo> implements ReportFromOutService {

    @Resource
    private ReportFromOutMapper reportFromOutMapper;

    @Override
    public Map<String,Object> deliveryLot(List<String> supplierName,  String start_time, String end_time, String type) {
        List<ReportFromOutVo> deliveryLot = reportFromOutMapper.deliveryLot(supplierName,  start_time, end_time, type);
        Map<String,Object> deliveryLotMap = new HashMap();
        deliveryLotMap.put("deliveryLotInfo",deliveryLot);
        return deliveryLotMap;
    }

    @Override
    public Map<String,Object> qualifiedLot(List<String> supplierName, String start_time, String end_time, String type) {
        List<ReportFromOutVo> qualifiedLot = reportFromOutMapper.qualifiedLot(supplierName,start_time,end_time,type);
        Map<String,Object> qualifiedLotMap = new HashMap();
        qualifiedLotMap.put("qualifiedLotInfo",qualifiedLot);
        return qualifiedLotMap;
    }

    @Override
    public Map<String,Object> deliveryQuantity(List<String> supplierName, String start_time, String end_time, String type) {
        List<ReportFromOutVo> deliveryQuantity = reportFromOutMapper.deliveryQuantity(supplierName,start_time,end_time,type);
        Map<String,Object> deliveryQuantityMap = new HashMap();
        deliveryQuantityMap.put("deliveryQuantityInfo",deliveryQuantity);
        return deliveryQuantityMap;
    }

    @Override
    public Map<String,Object> qualifiedQuantity(List<String> supplierName, String start_time, String end_time, String type) {
        List<ReportFromOutVo> qualifiedQuantity = reportFromOutMapper.qualifiedQuantity(supplierName,start_time,end_time,type);
        Map<String,Object> qualifiedQuantityMap = new HashMap();
        qualifiedQuantityMap.put("deliveryLotInfo",qualifiedQuantity);
        return qualifiedQuantityMap;
    }

    @Override
    public List<String> getSupplier() {
        return reportFromOutMapper.getSupplier();
    }

}
