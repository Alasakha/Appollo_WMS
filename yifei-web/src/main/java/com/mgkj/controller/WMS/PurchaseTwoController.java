package com.mgkj.controller.WMS;


import com.alibaba.fastjson.JSONObject;
import com.mgkj.annotation.Log;
import com.mgkj.common.result.Result;
import com.mgkj.dto.MiddleReturnDto;
import com.mgkj.dto.PurchaseToStorageDto;;
import com.mgkj.service.PurchaseService;
import com.mgkj.service.PurchaseTwoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author yyyjcg
 * @date 2023/12/26
 * @Description
 */
@Slf4j
@RestController
@Api(tags = "采购管理2相关接口")
@RequestMapping("/api")
public class PurchaseTwoController {
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseTwoService purchaseTwoService;
//    @Autowired
//    private PrintLineService printLineService;

    @ApiOperation("采购入库-提交")
    @PostMapping("/PurchaseToStorageGenerateDoc")
    @Log("采购入库-提交")
    public Result PurchaseToStorageGenerateDoc(@RequestBody List<PurchaseToStorageDto> list) {
        try {
            String uuid = UUID.randomUUID().toString();
            log.info(uuid + " ," + list);
            //同步中间表
            MiddleReturnDto middleReturnDto = purchaseService.PurchaseToStorageInsertMiddleTable(list);
            //提交e10
            JSONObject result = purchaseTwoService.PurchaseToStorageSubmit(middleReturnDto);
            String resultDocNo = result.getJSONObject("payload")
                    .getJSONObject("std_data")
                    .getJSONObject("parameter")
                    .getJSONArray("als_app_request_result")
                    .getJSONObject(0)
                    .getString("doc_no");
            log.info(uuid + " ," + resultDocNo);
            return Result.ok(resultDocNo).message("提交成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail().message("提交失败");
        }

    }




}

