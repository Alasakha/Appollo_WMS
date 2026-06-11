package com.mgkj.controller.WMS;


import com.mgkj.common.result.Result;
import com.mgkj.dto.PrintBarcodeDocCjmDto;
import com.mgkj.dto.PrintBarcodeDocDto;
import com.mgkj.service.BmBarcodeDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mgkj.annotation.Log;
import java.util.List;

@RestController
@Api(tags = "标签列印相关接口")
@RequestMapping("/api/barcode")
public class PrintBarcodeController {


    @Autowired
    private BmBarcodeDetailService bmBarcodeDetailService;


    /**
     * 工单列印-插入标签信息-根据工单号
     * @param list
     * @return
     */
    @ApiOperation("插入标签信息-根据工单号")
    @PostMapping("/insertPrintBarcodeByJobNo")
    @Log("插入标签信息-根据工单号")
    public Result insertPrintBarcodeByJobNo(@RequestBody List<PrintBarcodeDocDto> list){
        return bmBarcodeDetailService.insertPrintBarcodeByJobNo(list);
    }

    @ApiOperation("插入标签信息-根据工单号（ABL车架码）")
    @PostMapping("/insertPrintBarcodeByCjm")
    @Log("插入标签信息-根据工单号（ABL车架码）")
    public Result insertPrintBarcodeByByCjm(@RequestBody List<PrintBarcodeDocCjmDto> list){

        return bmBarcodeDetailService.insertPrintBarcodeByCjm(list);
    }


}
