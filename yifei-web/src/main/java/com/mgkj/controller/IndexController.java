package com.mgkj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgkj.service.*;
import com.mgkj.vo.VYSXJ;
import com.mgkj.vo.*;
import com.mgkj.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.x509.SerialNumber;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : ssy
 * @date: 2023-12-19
 * @Description:
 */
@RestController
@RequestMapping("/api/index")
@Api(tags = "首页")
public class IndexController {

    @Autowired
    private COPTHService copthService;

    @Autowired
    private COPTDService coptdService;

    @Autowired
    private COPTJService coptjService;

    @Autowired
    private MoctaService moctaService;

    @Autowired
    private VYSXJService vysxjService;

    @Autowired
    private VYFXJService vyfxjService;

    @Autowired
    private OutPutService outPutService;

    @Autowired
    private CapitalFlowService capitalFlowService;

    @Autowired
    private ReceivableService receivableService;

    @ApiOperation("查询销售金额")
    @GetMapping("/getSales")
    public Result getSales(){
        Map<String,List<SalesVo>> salesVos = copthService.getSales();
        return Result.ok(salesVos);
    }

    @ApiOperation("查询销退金额")
    @GetMapping("/getRefund")
    public Result getRefund(){
        Map<String,List<RefundVo>> refundVos = coptjService.getRefund();
        return Result.ok(refundVos);
    }

    @ApiOperation("查询本月销售毛利")
    @GetMapping("/getSalesMarginByNowMonth")
    public Result getSalesMargin(){
        Map<String, List<SalesMarginVo>> salesMarginByNowMonth = copthService.getSalesMarginByNowMonth();
        return Result.ok(salesMarginByNowMonth);
    }

    @ApiOperation("查询今年销售毛利")
    @GetMapping("/getSalesMarginByNowYear")
    public Result getSalesMarginByNowYear(){
        Map<String,List<SalesMarginVo>> salesMarginByNowYear = copthService.getSalesMarginByNowYear();
        return Result.ok(salesMarginByNowYear);
    }

    @ApiOperation("查询销售毛利详细信息")
    @PostMapping("/getSalesMarginInfo")
    public Result getSalesMarginInfo(@RequestParam Integer month){
        List<SalesMarginInfoVo> getSalesMarginInfo = copthService.getSalesMarginInfo(month);
        return Result.ok(getSalesMarginInfo);
    }


    @ApiOperation("当天-工单逾期数量")
    @GetMapping("getOverdueNum")
    public Result getOverdueNum(){
        Integer num = moctaService.getOverdueNum();
        return Result.ok(num);
    }

    @ApiOperation("工单逾期概述")
    @GetMapping("getOverdueInfo")
    public Result getOverdueInfo(){
        List<WorkOrderVo> list = moctaService.getOverdueInfo();
        return Result.ok(list);
    }

    @ApiOperation("工单逾期详细")
    @PostMapping("/getOverdueDetailsByWorkCenter")
    public Result getOverdueDetails(@RequestParam("workCenter") String workCenter,
                                    @RequestParam("page") Integer page,
                                    @RequestParam("pageSize") Integer pageSize){
        Page<WorkOrderVo> page1 = new Page<>(page, pageSize);
        IPage<WorkOrderVo> workOrderPage = moctaService.getOverdueDetailsByWorkCenter(page1, workCenter);
        String number = moctaService.getOverdueCount(workCenter);
        List<WorkOrderVo> workOrderList = workOrderPage.getRecords();
        for (WorkOrderVo workOrderVo : workOrderList) {
            workOrderVo.setNumber(number);
        }
        return Result.ok(workOrderList);
    }

    @ApiOperation("根据单别单号查询工单详细信息")
    @GetMapping("/getOverdueDetailsBySingleOrderNumber/{workCenter}/{single}/{singleNumber}")
    public Result getOverdueDetailsInfo(@PathVariable String workCenter,
                                        @PathVariable String single,
                                        @PathVariable String singleNumber){
        List<WorkOrderOvertimeVo> workOrderVos = moctaService.getOverdueDetailsInfo(workCenter,single,singleNumber);
        return Result.ok(workOrderVos);
    }

    @ApiOperation("应收总金额")
    @GetMapping("receivableTotal")
    public Result receivableTotal(){
        String number = vysxjService.receivableTotal();
        return Result.ok(number);
    }

    @ApiOperation("应收总金额明细")
    @GetMapping("receivableTotalInfo")
    public Result receivableTotalInfo(){
        List<VYSXJ> vysxjs = vysxjService.receivableTotalInfo();
        return Result.ok(vysxjs);
    }

    @ApiOperation("应付总金额")
    @GetMapping("payableTotal")
    public Result payableTotal(){
        String number = vyfxjService.payableTotal();
        return Result.ok(number);
    }

    @ApiOperation("应付总金额明细")
    @GetMapping("payableTotalInfo")
    public Result payableTotalInfo(){
        List<VYFXJ> vyfxjs = vyfxjService.payableTotalInfo();
        return Result.ok(vyfxjs);
    }

    @ApiOperation("获取当月资金流动信息")
    @GetMapping("/getCapitalFlowInfo")
    public Result getCapitalFlowInfo(){
        return Result.ok(capitalFlowService.getCapitalFlowInfo());
    }

    @ApiOperation("获取当月资金总流动")
    @GetMapping("/getSumCapitalFlow")
    public Result getSumCapitalFlow(){
        return Result.ok(capitalFlowService.getSumCapitalFlow());
    }

    @ApiOperation("获得逾期订单数量")
    @GetMapping("/getNumberOfOverdueOrders")
    public Result getNumberOfOverdueOrders(){
        return Result.ok(coptdService.getNumberOfOverdueOrders());
    }

    @ApiOperation("工单超期未完工")
    @GetMapping("/getWorkOrderOverdue/{page}/{pageSize}")
    public Result getWorkOrderOverdue(@PathVariable("page") Integer page,
                                      @PathVariable("pageSize") Integer pageSize){
        Page<SalesOrderVo> page1= new Page<>(page, pageSize);
        IPage<SalesOrderVo> salesOrderVoIPage = coptdService.getWorkOrderOverdue(page1);
        List<SalesOrderVo> salesOrderVoList = salesOrderVoIPage.getRecords();
        return Result.ok(salesOrderVoList);
    }

    @ApiOperation("工单超期未完工详细信息")
    @GetMapping("/getWorkOrderOverdueInfo/{orderNumber}/{orderSingle}/{orderSerialNumber}")
    public Result getWorkOrderOverdueInfo(@PathVariable String orderNumber,
                                          @PathVariable String orderSingle,
                                          @PathVariable String orderSerialNumber){
        return Result.ok(coptdService.getWorkOrderOverdueInfo(orderSingle,orderNumber,orderSerialNumber));
    }


    @ApiOperation("获取产量条数")
    @GetMapping("/getOutPutCount")
    public Result getOutPutCount(){
        return Result.ok(outPutService.getOutPutCount());
    }

    @ApiOperation("获取所有工作中心")
    @GetMapping("getAllWorkShop")
    public Result getAllWorkShop(){
        List<OutPutVo> getAllWorkShop = outPutService.getAllWorkShop();
        return Result.ok(getAllWorkShop);
    }

    @ApiOperation("产量")
    @GetMapping("/getOutPut/{type}")
    public Result getOutPut(@PathVariable("type") Integer type,
                            @RequestParam(required = false) String workShop){
        List<OutPutVo> outPutVoList = outPutService.getOutPut(type,workShop);
        return Result.ok(outPutVoList);
    }

    @ApiOperation("产量详细信息")
    @GetMapping("/getOutPutInfo/{page}/{pageSize}/{warehousingDate}/{type}/{workShop}")
    public Result getOutPutInfo(@PathVariable("page") Integer page,
                                @PathVariable("pageSize") Integer pageSize,
                                @PathVariable("warehousingDate") String warehousingDate,
                                @PathVariable("type") Integer type,
                                @PathVariable("workShop") String workShop){
        List<OutPutVo> outputVoList = outPutService.getOutPutInfo(page,pageSize,warehousingDate,type,workShop);
        Integer count = outPutService.getCount(warehousingDate,type,workShop);
        Map<String,Object> map = new HashMap<>();
        map.put("count",count);
        map.put("outputVoList",outputVoList);
        return Result.ok(map);
    }

    @ApiOperation("应收未收")
    @GetMapping("/getReceivable/{page}/{pageSize}")
    public Result getReceivable(@PathVariable("page") Integer page,
                                @PathVariable("pageSize") Integer pageSize){
        Map<String,Object> map = new HashMap<>();
        Page<ReceivableVo> page1 = new Page<>(page, pageSize);
        IPage<ReceivableVo> receivable = receivableService.getReceivable(page1);
        List<ReceivableVo> receivableVoList = receivable.getRecords();
        long total = receivable.getTotal();
        map.put("count",total);
        map.put("receivableVoList",receivableVoList);
        return Result.ok(map);
    }

    @ApiOperation("应收未收信息")
    @GetMapping("/getReceivableInfo/{single}/{singleNumber}")
    public Result getReceivableInfo(@PathVariable String single,
                                    @PathVariable String singleNumber){
        return Result.ok(receivableService.getReceivableInfo(single,singleNumber));
    }
}
