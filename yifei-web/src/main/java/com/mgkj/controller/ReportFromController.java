package com.mgkj.controller;

import com.mgkj.common.result.Result;
import com.mgkj.entity.SFCTE;
import com.mgkj.service.ReportFromCarService;
import com.mgkj.service.ReportFromOutService;
import com.mgkj.service.SFCTEService;
import com.mgkj.vo.ReportVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : ssy
 * @date: 2024-01-11
 * @Description:
 */
@Api(tags = "报工报表")
@RestController
@RequestMapping("/api/report")
public class ReportFromController {

    @Autowired
    private SFCTEService sfcteService;

    @Autowired
    private ReportFromOutService reportFromOutService;

    @Autowired
    private ReportFromCarService reportFromCarService;

    @ApiOperation("获取所有供应商")
    @PostMapping("getSupplier")
    public Result getSupplier(){
        List<String> getSupplier = reportFromOutService.getSupplier();
        return Result.ok(getSupplier);
    }

    @ApiOperation("获取所有工作中心")
    @PostMapping("getWorkCenter")
    public Result getWorkCenter(){
        List<String> getWorkCenter = reportFromCarService.getWorkCenter();
        return Result.ok(getWorkCenter);
    }

    @ApiOperation("报工品质")
    @PostMapping("qualityFrom")
    public Result qualityFrom(@RequestParam(required = false) String UDF06,
                              @RequestParam(required = false) String start_time,
                              @RequestParam(required = false) String end_time,
                              @RequestParam(required = false) String type) {
//        List<SFCTE> sfcteList = sfcteService.qualityFrom(page,pageSize,UDF06,start_time,end_time);
//        int count = sfcteService.qualityFromCount(UDF06,start_time,end_time);
        Map<String, List<ReportVo>> sumUp = sfcteService.qualityFromUp(UDF06,start_time,end_time,type);
        Map<String, List<ReportVo>> downSum = sfcteService.qualityFromDown(UDF06,start_time,end_time,type);
        Map<String,Map<String, List<ReportVo>>> qualityFromMap = new HashMap<>();
//        qualityFromMap.put("total",count);
//        qualityFromMap.put("SFCTE",sfcteList);
        qualityFromMap.put("upSum",sumUp);
        qualityFromMap.put("downSum",downSum);
        return Result.ok(qualityFromMap);
    }

    @ApiOperation("报工品质测温")
    @PostMapping("qualityFromTemp")
    public Result qualityFromTemp(@RequestParam(required = false) String UDF06,
                                  @RequestParam(required = false) String start_time,
                                  @RequestParam(required = false) String end_time,
                                  @RequestParam(required = false) String type) {
//        List<SFCTE> sfcteList = sfcteService.qualityFromTemp(UDF06,page,pageSize,start_time,end_time);
//        int count = sfcteService.qualityFromTempCount(UDF06,start_time,end_time);
        Map<String, List<ReportVo>> sumUp = sfcteService.qualityFromTempUp(UDF06,start_time,end_time,type);
        Map<String, List<ReportVo>> sumDown = sfcteService.qualityFromTempDown(UDF06,start_time,end_time,type);
        Map<String, Map<String, List<ReportVo>>> qualityFromTempMap = new HashMap<>();
//        qualityFromTempMap.put("total",count);
//        qualityFromTempMap.put("SFCTE",sfcteList);
        qualityFromTempMap.put("upSum",sumUp);
        qualityFromTempMap.put("downSum",sumDown);
//        Map<String,Integer> tempSum = sfcteService.qualityFromTempSum(UDF06,qualityFromTempMap);
//        qualityFromTempMap.put("tempSum",tempSum);
        return Result.ok(qualityFromTempMap);
    }

    @ApiOperation("报工品质(外检类)")
    @PostMapping("qualityFromOut")
    public Result qualityFromOut(@RequestParam(required = false) List<String> supplierName,
                                 @RequestParam(required = false) String start_time,
                                 @RequestParam(required = false) String end_time,
                                 @RequestParam(required = false) String type){
        Map<String,Object> deliveryLot = reportFromOutService.deliveryLot(supplierName,start_time,end_time,type);
        Map<String,Object> qualifiedLot = reportFromOutService.qualifiedLot(supplierName,start_time,end_time,type);
        Map<String,Object> deliveryQuantity = reportFromOutService.deliveryQuantity(supplierName,start_time,end_time,type);
        Map<String,Object> qualifiedQuantity = reportFromOutService.qualifiedQuantity(supplierName,start_time,end_time,type);
        Map<String,Map<String,Object>> map = new HashMap<>();
        map.put("deliveryLot",deliveryLot);
        map.put("qualifiedLot",qualifiedLot);
        map.put("deliveryQuantity",deliveryQuantity);
        map.put("qualifiedQuantity",qualifiedQuantity);
        return Result.ok(map);
    }

    @ApiOperation("报工品质(车间类)")
    @PostMapping("qualityFromCar")
    public Result qualityFromCar(@RequestParam(required = false) String workCenter,
                                 @RequestParam(required = false) String start_time,
                                 @RequestParam(required = false) String end_time,
                                 @RequestParam(required = false) String type){
        Map<String,List<ReportVo>> inputQuantity = reportFromCarService.inputQuantity(workCenter,start_time,end_time,type);
        Map<String,List<ReportVo>> outputQuantity = reportFromCarService.outputQuantity(workCenter,start_time,end_time,type);
        Map<String,List<ReportVo>> scrapQuantity = reportFromCarService.scrapQuantity(workCenter,start_time,end_time,type);
        Map<String,Map<String,List<ReportVo>>> map = new HashMap<>();
        map.put("inputCount",inputQuantity);
        map.put("outputCount",outputQuantity);
        map.put("scrapQuantity",scrapQuantity);
//        Map<String,List<ReportVo>> difference = reportFromCarService.difference(workCenter,map);
//        map.put("difference",difference);
        return Result.ok(map);
    }
}
