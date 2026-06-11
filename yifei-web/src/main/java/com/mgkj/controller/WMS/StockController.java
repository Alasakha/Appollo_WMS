package com.mgkj.controller.WMS;

import com.alibaba.fastjson.JSONObject;
import com.mgkj.annotation.Log;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.service.StockService;
import com.mgkj.vo.OtherInStockDetailVo;
import com.mgkj.vo.OtherOutStockDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/4/7
 * @Description
 */

@Slf4j
@RestController
@Api(tags = "库存管理相关接口")
@RequestMapping("/api")
@CrossOrigin
public class StockController {
    @Autowired
    private StockService stockService;

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);


    @ApiOperation("库存查询")
    @PostMapping("/ListStock")
    @Transactional
    public Result getStock(@RequestBody ListStockDto dto){
        return stockService.ListStock(dto);
    }

    @ApiOperation("其他入库--简洁查询")
    @PostMapping("/ListOtherInStock")
    public Result ListOtherInStock(@RequestBody OtherInStockDto dto){
        return stockService.ListOtherInStock(dto);
    }

    @ApiOperation("其他入库--查询详情")
    @GetMapping("/OtherInStockDetail/{docNo}")
    public Result<List<OtherInStockDetailVo>> OtherInStockDetail(@PathVariable("docNo") String docNo){
        return stockService.OtherInStockDetail(docNo);
    }

    @ApiOperation("其他入库--提交")
    @Log("其他入库--提交")
    @PostMapping("/OtherInStockSubmit")
    public Result OtherInStockSubmit(@RequestBody List<OtherInstockSubmitDto> list){
        try {
            //同步中间表
            MiddleReturnDto middleReturnDto = stockService.insertMiddleTable(list);
            //提交
            JSONObject jsonObject = stockService.OtherInStockSubmit(middleReturnDto);
            return Result.ok(jsonObject).message("添加成功");
        }catch (Exception e){
            return Result.fail().message("添加失败");
        }
    }

    @ApiOperation("其他出库--简洁查询")
    @PostMapping("/ListOtherOutStock")
    @Transactional
    public Result ListOtherOutStock(@RequestBody OtherOutStockDto dto){
        return stockService.ListOtherOutStock(dto);
    }

    @ApiOperation("其他出库--查询详情")
    @GetMapping("/OtherOutStockDetail/{docNo}")
    @Transactional
    public Result<List<OtherOutStockDetailVo>> OtherOutStockDetail(@PathVariable("docNo") String docNo){
        return stockService.OtherOutStockDetail(docNo);
    }

    @ApiOperation("其他出库--提交")
    @PostMapping("/OtherOutStockSubmit")
    @Log("其他出库--提交")
    public Result OtherOutStockSubmit(@RequestBody List<OtherOutStockSubmitDto> list){
        try {
            //同步中间表
            MiddleReturnDto middleReturnDto = stockService.OtherOutStockInsertMiddleTable(list);
            //提交
            JSONObject jsonObject = stockService.OtherOutStockSubmit(middleReturnDto);
            return Result.ok(jsonObject).message("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail().message("添加失败");
        }
    }

    @ApiOperation("库存调拨--简洁查询")
    @PostMapping("/ListTransferDoc")
    @Transactional
    public Result ListTransferDoc(@RequestBody ListTransferDocDto dto){
        return stockService.ListTransferDoc(dto);
    }

    @ApiOperation("库存调拨--查询详情")
    @PostMapping("/ListTransferDocDetail/{docNo}")
    @Transactional
    public Result ListTransferDocDetail(@PathVariable("docNo") String docNo){
        return stockService.ListTransferDocDetail(docNo);
    }

    @ApiOperation("库存调拨--提交")
    @PostMapping("/ListTransferDocSubmit")
    @Log("库存调拨--提交")
    public Result ListTransferDocSubmit(@RequestBody List<TransferDocSubmitDto> list){
        try{
            MiddleReturnDto middleReturnDto = stockService.insertTransferDocMiddleTable(list);
//            return Result.ok(middleReturnDto);
            JSONObject jsonObject = stockService.ListTransferDocSubmit(middleReturnDto);
            return Result.ok(jsonObject);
        }catch (Exception e){
            return Result.fail().message(e.getMessage());
        }
    }

//    @GetMapping ("/TestI")
//    public Result TestI(@PathVariable("id") Integer id){
//        DateTime dateTime = DateUtil.parse("2023-04-07 00:00:00");
//        System.out.println("插入同步成功");
//        return Result.ok();
//    }
//
//    @GetMapping ("/TestU")
//    public Result TestU(){
//        DateTime dateTime = DateUtil.parse("2023-04-07 00:00:00");
//        System.out.println("更新同步成功");
//        return Result.ok();
//    }
//
//    @GetMapping ("/TestD")
//    public Result TestD(){
//        DateTime dateTime = DateUtil.parse("2023-04-07 00:00:00");
//        System.out.println("删除同步成功");
//        return Result.ok();
//    }
}
