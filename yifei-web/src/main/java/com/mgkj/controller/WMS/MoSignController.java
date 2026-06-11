package com.mgkj.controller.WMS;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.mgkj.annotation.Log;
import com.mgkj.common.result.Result;
import com.mgkj.dto.PageDTO;
import com.mgkj.dto.QueryMoReceiptD;
import com.mgkj.dto.signReceiptDTO;
import com.mgkj.exception.BaseException;
import com.mgkj.mapper.PickingMapper;
import com.mgkj.service.impl.E10ApiService;
import com.mgkj.vo.MoReceiptDVo;
import com.mgkj.vo.MoReceiptVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "工单入库签收相关接口")
@RequestMapping("/api/MoSign")
public class MoSignController {

    @Resource
    private PickingMapper pickingMapper;

    @Resource
    private E10ApiService e10ApiService;

//    @PostMapping("/getMoReceiptList")
//    @ApiOperation("查询生产入库单 单头")
//    public Result<?> getMoReceiptList(@RequestBody QueryMoReceiptD dto) {
//        List<MoReceiptVo> list = pickingMapper.queryMoReceipt2(dto);
//        if (list == null) {
//            return Result.fail().message("没有数据");
//        }
//        return Result.ok(list).message("查询成功");
//    }

    @PostMapping("/GetMoReceiptList")
    @ApiOperation("查询未审核的生产入库单")
    public Result<?> GetMoReceiptList(@RequestBody PageDTO dto) {
        if (dto.getPageNum() != null && dto.getPageSize() != null) {
            PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        }
        List<String> list = pickingMapper.GetMoReceiptList();
        if (list == null) {
            return Result.fail().message("没有未审核的生产入库单");
        }
        return Result.ok(list);
    }

    @PostMapping("/updateAcceptedQty/{docDNoID}/{qty}")
    @ApiOperation("修改入库数量")
    public Result<?> updateAcceptedQty(@PathVariable("docDNoID") String docDNoID, @PathVariable("qty") double qty) {
        if (StrUtil.isEmpty(docDNoID)) {
            return Result.fail().message("参数错误:单号id不能为空！");
        }
        if (qty < 0){
            return Result.fail().message("数量不能小于0！");
        }
        pickingMapper.updateAcceptedQty(docDNoID, qty);
        return Result.ok().message("数量修改成功!");
    }

    @PostMapping("/getMoReceiptDList/{docNo}")
    @ApiOperation("查询生产入库单 单身")
    public Result<?> getMoReceiptDList(@PathVariable("docNo") String docNo) {
        if (StrUtil.isEmpty(docNo)) {
            return Result.fail().message("参数错误:单号不能为空！");
        }
        List<MoReceiptDVo> list = pickingMapper.getMoReceiptDList(docNo);
        if (list == null) {
            return Result.fail().message("没有数据");
        }
        return Result.ok(list).message("查询成功");
    }

    @PostMapping("/confirmMoReceipt/{docNo}")
    @ApiOperation("审核生产入库单")
    @Log("审核生产入库单")
    public Result<?> confirmMoReceipt(@PathVariable("docNo") String docNo) {
        if (StrUtil.isEmpty(docNo)) {
            return Result.fail().message("参数错误:单号不能为空！");
        }
        JSONObject jsonObject = e10ApiService.confirmMoReceipt(docNo);
        log.warn("审核生产入库单响应: " + jsonObject);
        JSONObject object = jsonObject.getJSONObject("std_data")
                .getJSONObject("parameter")
                .getJSONObject("result");
        JSONArray jsonArray = object.getJSONArray("error");
        if (jsonArray != null && !jsonArray.isEmpty()) {
            String errorMsg = jsonArray.getJSONObject(0).getString("message");
            errorMsg = "生产入库单审核失败\n" + errorMsg;
            return Result.fail(errorMsg).message(errorMsg);
        }
        List<MoReceiptDVo> list = pickingMapper.getMoReceiptDList(docNo);
        for (MoReceiptDVo a : list) {
            pickingMapper.updateMoReceiptD(a.getMoDocNo(), a.getAcceptedQty().doubleValue());
        }
        return Result.ok().message("审核成功！");
    }

    @PostMapping("/getList")
    @ApiOperation("获取工单入库签收列表-扫码")
    public Result<?> getList(@RequestBody QueryMoReceiptD dto) {
        List<MoReceiptVo> list = pickingMapper.queryMoReceiptNew(dto);
        if (list == null) {
            return Result.fail().message("没有数据");
        }
        return Result.ok(list).message("查询成功");
    }

    @PostMapping("/signReceipt")
    @ApiOperation("签收")
    @Transactional
    public Result<?> signReceipt(@RequestBody List<signReceiptDTO> dto) {
        for (signReceiptDTO a : dto) {
            log.info("工单号：{} 签收数量：{}", a.getMoDocNo(), a.getQty());
            if (a.getQty() != null) {
                MoReceiptVo moReceiptVo = pickingMapper.queryMoReceiptD(a.getMoDocNo());
                if (a.getQty().compareTo(moReceiptVo.getAcceptedQty().doubleValue()) > 0) {
                    throw new BaseException(500, "签收数量不能大于入库数量");
                }
                pickingMapper.updateMoReceiptD(a.getMoDocNo(), a.getQty());
            }
        }
        return Result.ok().message("签收成功");
    }

}
