package com.mgkj.controller.WMS;


import com.alibaba.fastjson.JSONObject;
import com.mgkj.annotation.Log;
import com.mgkj.common.result.JsonResponseUtil;
import com.mgkj.common.result.Result;
import com.mgkj.dto.MiddleReturnDto;
import com.mgkj.dto.PassiveOutStorageDto;
import com.mgkj.dto.PassiveToStorageDto;
import com.mgkj.dto.PassiveTransferDocSubmitDto;
import com.mgkj.service.MoService;
import com.mgkj.service.PassiveService;

import com.mgkj.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2023/12/26
 * @Description
 */
@Slf4j
@RestController
@Api(tags = "无源管理相关接口")
@RequestMapping("/api/Passive")
public class PassiveController {
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PassiveService passiveService;

    @Autowired
    private MoService moService;
//
//    @Autowired
//    private PrintLineService printLineService;

    @ApiOperation("无源入库/杂收-提交")
    @PostMapping("/PassiveToStorageGenerateDoc")
    @Log("无源入库/杂收-提交")
    public Result PassiveToStorageGenerateDoc(@RequestBody List<PassiveToStorageDto> list) {
        try {
            //同步中间表
            MiddleReturnDto middleReturnDto = passiveService.PassiveToStorageInsertMiddleTable(list);
            //提交e10
            JSONObject jsonObject = passiveService.PassiveToStorageSubmit(middleReturnDto);
            return JsonResponseUtil.handleJsonResponse(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().message("提交失败");
        }
    }

    @ApiOperation("无源出库/杂发-提交")
    @Log("无源出库/杂发-提交")
    @PostMapping("/PassiveOutStorageGenerateDoc")
    public Result PassiveOutStorageGenerateDoc(@RequestBody List<PassiveOutStorageDto> list) {
        try {
            //同步中间表
            MiddleReturnDto middleReturnDto = passiveService.PassiveOutStorageInsertMiddleTable(list);
            //提交e10
            JSONObject result = passiveService.PassiveOutStorageSubmit(middleReturnDto);
            System.out.println(result.toJSONString());
            String resultDocNo = result.getJSONObject("payload")
                    .getJSONObject("std_data")
                    .getJSONObject("parameter")
                    .getJSONArray("als_app_request_result")
                    .getJSONObject(0)
                    .getString("doc_no");
            return Result.ok(resultDocNo).message("提交成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().message("提交失败");
        }
    }

    @ApiOperation("无源调拨-提交")
    @PostMapping("/PassiveTransferDocSubmit")
    @Log("无源调拨-提交")
    public Result PassiveTransferDocSubmit(@RequestBody List<PassiveTransferDocSubmitDto> list) {
        try {
            //同步中间表
            MiddleReturnDto middleReturnDto = passiveService.PassiveTransferInsertMiddleTable(list);
            //提交e10
            JSONObject result = passiveService.PassiveTransferSubmit(middleReturnDto);
            System.out.println(result.toJSONString());
            String resultDocNo = result.getJSONObject("payload")
                    .getJSONObject("std_data")
                    .getJSONObject("parameter")
                    .getJSONArray("als_app_request_result")
                    .getJSONObject(0)
                    .getString("doc_no");
            return Result.ok(resultDocNo).message("提交成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().message("提交失败");
        }
    }
}
