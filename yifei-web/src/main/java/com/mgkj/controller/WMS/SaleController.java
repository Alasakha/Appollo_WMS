//package com.mgkj.controller.WMS;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mgkj.annotation.Log;
//import com.mgkj.common.result.JsonResponseUtil;
//import com.mgkj.common.result.Result;
//import com.mgkj.dto.*;
//import com.mgkj.service.SaleService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * @author yyyjcg
// * @date 2024/4/15
// * @Description
// */
//@RestController
//@Api(tags = "销售管理相关接口")
//@RequestMapping("/api")
//public class SaleController {
//    @Autowired
//    private SaleService saleService;
//
//    @ApiOperation("出货下架--简洁查询")
//    @PostMapping("/ListSaleOrderOutStock")
//    public Result ListSaleOrderOutStock(@RequestBody ListSaleOrderOutStockDto dto){
//        return saleService.ListSaleOrderOutStock(dto);
//    }
//
//    @ApiOperation("出货下架--详细查询")
//    @GetMapping("/ListSaleOrderOutStockDetail/{docNo}")
//    public Result ListSaleOrderOutStockDetail(@PathVariable("docNo") String docNo) {
//        return saleService.ListSaleOrderOutStockDetail(docNo);
//    }
//
//    @ApiOperation("出货下架--提交-维护销售订单(已审核)->维护销货单(未审核)->->维护销货单(已审核)")
//    @PostMapping("/SaleOrderOutStock")
//    @Log("出货下架--提交-维护销售订单(已审核)->维护销货单(未审核)->->维护销货单(已审核)")
//    public Result SaleOrderOutStock(@RequestBody List<SaleOrderOutDto> list){
////        try {
//        //同步中间表
//        MiddleReturnDto middleReturnDto = saleService.SaleOrderOutStockInsertMiddleTable(list);
//        //提交
//        JSONObject jsonObject = saleService.SaleOrderOutStockSubmit(middleReturnDto);
//        return JsonResponseUtil.handleJsonResponse(jsonObject);
////        }catch (Exception e){
////            return Result.fail().message("添加失败");
////        }
//    }
//
//    //TODO 恢复xml注释代码
//    @ApiOperation("通知下架--简洁查询")
//    @PostMapping("/ListNoticeOutStock")
//    public Result ListNoticeOutStock(@RequestBody ListNoticeOutStockDto dto) {
//        return saleService.ListNoticeOutStock(dto);
//    }
//
//    //TODO 恢复xml注释代码
//    @ApiOperation("通知下架--详细查询")
//    @GetMapping("/ListNoticeOutStockDetail/{docNo}")
//    public Result ListNoticeOutStockDetail(@PathVariable("docNo") String docNo) {
//        return saleService.ListNoticeOutStockDetail(docNo);
//    }
//
//    @ApiOperation("通知下架--提交-维护销售订单(已审核)->维护销货单(已审核)->->生成维护销货出库单(已审核)")
//    @PostMapping("/SaleOutStock")
//    @Log("通知下架--提交-维护销售订单(已审核)->维护销货单(已审核)->->生成维护销货出库单(已审核)")
//    public Result SaleOutStock(@RequestBody List<SaleOutStockDto> list){
//        try {
//        //同步中间表
//        MiddleReturnDto middleReturnDto = saleService.SaleOutStockInsertMiddleTable(list);
//        //提交
//        JSONObject jsonObject = saleService.SaleOutStockSubmit(middleReturnDto);
////        return Result.ok(jsonObject).message("添加成功");
////            return handleJsonResponse(jsonObject);
//            return JsonResponseUtil.handleJsonResponse(jsonObject);
//        }catch (Exception e){
//            return Result.fail().message("添加失败");
//        }
//    }
//
//
//
//    @ApiOperation("销售仓退--简洁查询")
//    @PostMapping("/ListSaleReturn")
//    public Result ListSaleReturn(@RequestBody ListSaleReturnDto dto) {
//        return saleService.ListSaleReturn(dto);
//    }
//
//    @ApiOperation("销售仓退--详细查询")
//    @GetMapping("/ListSaleReturnDetail/{docNo}")
//    public Result ListSaleReturnDetail(@PathVariable("docNo") String docNo){
//        return saleService.ListSaleReturnDetail(docNo);
//    }
//
//    @ApiOperation("销售仓退--提交-维护销售订单(已审核)->维护销货单(已审核)->维护销货出库单(已审核)->维护销退单(已审核)->->生成维护销退入库单(已审核)/app销售退货")
//    @PostMapping("/SaleReturnSubmit")
//    @Log("销售仓退--提交-维护销售订单(已审核)->维护销货单(已审核)->维护销货出库单(已审核)->维护销退单(已审核)->->生成维护销退入库单(已审核)/app销售退货")
//    public Result saleReturnSubmit(@RequestBody List<SaleReturnSubmitDto> list){
//        try {
//            //同步中间表
//            MiddleReturnDto middleReturnDto = saleService.SaleReturnInsertMiddleTable(list);
//            //提交
//            JSONObject jsonObject = saleService.SaleReturnSubmit(middleReturnDto);
//            return Result.ok(jsonObject).message("添加成功");
//        }catch (Exception e){
//            return Result.fail().message("添加失败");
//        }
//    }
//
////TODO:寄售调拨
//
//
//}
//
