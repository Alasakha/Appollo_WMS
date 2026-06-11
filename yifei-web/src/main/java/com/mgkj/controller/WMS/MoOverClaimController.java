package com.mgkj.controller.WMS;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.mgkj.annotation.Log;
import com.mgkj.common.result.Result;
import com.mgkj.common.utils.StringUtils;
import com.mgkj.dto.*;
import com.mgkj.entity.InvBarcodeOperation;
import com.mgkj.mapper.DeliveryNoticeMapper;
import com.mgkj.mapper.InvBarcodeOperationMapper;
import com.mgkj.mapper.PickingMapper;
import com.mgkj.service.InvBarcodeOperationService;
import com.mgkj.service.InvBarcodeTransactionService;
import com.mgkj.service.MoService;
import com.mgkj.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
@RestController
@Api(tags = "工单超领料出库相关接口")
@RequestMapping("/api/moOverClaim")
public class MoOverClaimController {

    @Resource
    private PickingMapper pickingMapper;

    @Resource
    private DeliveryNoticeMapper deliveryNoticeMapper;

    @Resource
    private InvBarcodeOperationService invBarcodeOperationService;

    @Resource
    private MoService moService;

    @ApiOperation("获取工单领料数据")
    @PostMapping("/workInfoToWms/{docNo}")
    @Transactional
    public Result<List<WorkInfoToWmsVo>> moctyListByResourceId(@PathVariable("docNo") String docNo, @RequestBody String createBy) {
        if(docNo == null || docNo.trim().equals("")) {
            return Result.ok();
        }
        // 找到单头数据
        List<WorkInfoToWmsTempVo> res = pickingMapper.getWorkInfoToWmsVoOverClaim(docNo);
        List<WorkInfoToWmsItemVo> items = new ArrayList<>();
        if(res != null && res.size() > 0) {
            // 找到单身数据
            items = pickingMapper.getWorkInfoItemOverClaim(res);
            if(items != null && items.size() > 0) {
                Map<String, List<WorkInfoToWmsItemVo>> map = new HashMap();
                Map<String, WorkInfoToWmsItemVo> moctbmap = new HashMap();  // key: 单别单号品号
                // 先将数组分组，存入MAP
                for(WorkInfoToWmsItemVo t : items) {
                    List<WorkInfoToWmsItemVo> vos = map.get(t.getUdf05());
                    if(vos == null) vos = new ArrayList<>();
                    t.setId(UUID.randomUUID().toString());
                    vos.add(t);
                    map.put(t.getUdf05(), vos);
                    moctbmap.put(t.getDOC_NO() + t.getITEM_CODE() + t.getUdf05(), t);
                }
                QueryMoSummaryDTO queryMoSummaryDTO = new QueryMoSummaryDTO();
                queryMoSummaryDTO.setCreateBy(createBy);
                // 把中间表的值，赋值到moctbmap对象的scanNum
                List<MoPickingSummary> moPickingSummaryList = pickingMapper.queryMoSummary(queryMoSummaryDTO);

                for(MoPickingSummary t : moPickingSummaryList) {
                    WorkInfoToWmsItemVo workInfoToWmsItemVo = moctbmap.get(t.getDocNo() + t.getItemCode() + t.getMoctyUuid());
                    if(workInfoToWmsItemVo != null) {
                        workInfoToWmsItemVo.setScanNum(t.getCurrentNum());
                    }
                }

                // 把工单单身按ID,插入单头中
                for(WorkInfoToWmsTempVo t : res) {
                    List<WorkInfoToWmsItemVo> workInfoToWmsItemVos = map.get(t.getUdf05());
                    t.setMoctb(workInfoToWmsItemVos);
                }
            }
        }

        // 把单头列表分组中的
        Map<String, List<WorkInfoToWmsTempVo>> map = new HashMap<>();
        List<WorkInfoToWmsVo> ans = new ArrayList<>();
        for(WorkInfoToWmsTempVo t : res) {
            if(StringUtils.isEmpty(t.getUdf02())) {  // 没有组的直接先插入
                WorkInfoToWmsVo x = new WorkInfoToWmsVo();
                List<WorkInfoToWmsTempVo> list = new ArrayList<>();
                list.add(t);
                x.setWorkInfo(list);
                ans.add(x);
            }else{   // 有组的先用Map保存
                List<WorkInfoToWmsTempVo> volist = map.get(t.getUdf02());
                if(volist == null) volist = new ArrayList<>();
                volist.add(t);
                map.put(t.getUdf02(), volist);
            }
        }

        for(Map.Entry<String, List<WorkInfoToWmsTempVo>> entry : map.entrySet()) {
            List<WorkInfoToWmsTempVo> value = entry.getValue();
            WorkInfoToWmsVo x = new WorkInfoToWmsVo();
            List<WorkInfoToWmsTempVo> list = new ArrayList<>(value);
            x.setWorkInfo(list);
            ans.add(x);
        }
        return Result.ok(ans);
    }

    @PostMapping("/queryBarCodeDetail")
    @ApiOperation(value = "查询条码信息")
    public Result getBarCodeDetail(@RequestBody String barcode) {
        BarCodeDetailVo barCodeDetail = deliveryNoticeMapper.getBarCodeDetailByBarCode(barcode);
        if (barCodeDetail == null) {
            return Result.fail().message("条码不存在");
        }
        return Result.ok(barCodeDetail);
    }

    @PostMapping("/resetData")
    @ApiOperation(value = "重置数据")
    @Log("重置数据")
    @Transactional
    public Result resetData(@RequestBody String createBy) {
        try {
            // 1.删除中间表
            pickingMapper.deleteMoPickingSummary(createBy);
            // 2.删除条码操作档 -> 条码状态：0未提交 & 条码类型：4工单领料
            pickingMapper.deleteInvBarcodeOperationByCreateBy(createBy);
        } catch (Exception e) {
            return Result.fail().message("重置失败");
        }
        return Result.ok();
    }

    @PostMapping("/deleteScanResult")
    @ApiOperation(value = "删除扫码结果列表")
    @Log("删除扫码结果列表")
    @Transactional
    public Result<?> deleteScanResult(@RequestBody QueryMoSummaryDTO queryMoSummaryDTO) {
        pickingMapper.deleteMoPickingSummaryById(queryMoSummaryDTO.getId());
        pickingMapper.deleteInvBarcodeOperationByCreateBy(queryMoSummaryDTO.getCreateBy());
        return Result.ok();
    }

    @PostMapping("/scanCode")
    @ApiOperation(value = "扫码接口")
    @Log("扫码接口")
    @Transactional
    public Result scanCode(@RequestBody ScanCodeDTO scanCodeDTO) {
        // 1.查询条码详情
        BarCodeDetailVo barCodeDetail = deliveryNoticeMapper.getBarCodeDetailByBarCode(scanCodeDTO.getBarcode());
        if (barCodeDetail == null) {
            return Result.fail().message("条码不存在");
        }

        // 2.查询该条码操作档 汇总数量
        BigDecimal qtySum = Optional.ofNullable(
                deliveryNoticeMapper.getQtySum(scanCodeDTO.getBarcode(), 4)
        ).orElse(BigDecimal.ZERO);

        // 3.计算剩余可分配数量
        BigDecimal remainingQty = barCodeDetail.getQty().subtract(qtySum);
        if (remainingQty.compareTo(BigDecimal.ZERO) <= 0) {
            return Result.fail().message("条码剩余数量不足");
        }

        // 4.查询该记录是否匹配完成
        Integer count = pickingMapper.queryUnMatchQtyList(scanCodeDTO.getId());
        if (count != 0) {
            return Result.fail().message("记录已匹配完成");
        }

        if (scanCodeDTO.getScanNum() == null) {
            scanCodeDTO.setScanNum(BigDecimal.ZERO);
        }
        BigDecimal neededQty = BigDecimal.ZERO; // 需要分配的数量
        BigDecimal allocateQty = BigDecimal.ZERO; // 本次分配的数量

        // 5.查询该单身是否已经有匹配记录
        QueryMoSummaryDTO queryMoSummaryDTO = new QueryMoSummaryDTO();
        queryMoSummaryDTO.setId(scanCodeDTO.getId());
        MoPickingSummary one = pickingMapper.queryMoSummaryOne(queryMoSummaryDTO);
        // 6.计算分配数量
        neededQty = scanCodeDTO.getBusinessQty()
                .subtract(scanCodeDTO.getMatchQty())
                .subtract(scanCodeDTO.getScanNum()); // 需要分配的数量
        allocateQty = neededQty.compareTo(remainingQty) <= 0 ? neededQty : remainingQty; // 本次分配的数量
        if (one != null) {
            pickingMapper.updateMoSummaryOfMatchQty(scanCodeDTO.getId(), allocateQty, scanCodeDTO.getCreateBy(), scanCodeDTO.getBarcode());
        } else {
            MoPickingSummary moPickingSummary = new MoPickingSummary();
            moPickingSummary.setId(UUID.randomUUID().toString());
            moPickingSummary.setDocNo(scanCodeDTO.getDocNo());
            moPickingSummary.setItemCode(scanCodeDTO.getItemCode());
            moPickingSummary.setBusinessQty(scanCodeDTO.getBusinessQty());
            moPickingSummary.setMatchQty(scanCodeDTO.getMatchQty().add(allocateQty));
            moPickingSummary.setCurrentNum(allocateQty);
            moPickingSummary.setUnitCode(scanCodeDTO.getUnitCode());
            moPickingSummary.setUnitName(scanCodeDTO.getUnitName());
            moPickingSummary.setItemName(scanCodeDTO.getItemName());
            moPickingSummary.setItemSpec(scanCodeDTO.getItemSpec());
            moPickingSummary.setWarehouseCode(barCodeDetail.getWarehouseCode());
            moPickingSummary.setWarehouseName(barCodeDetail.getWarehouseName());
            moPickingSummary.setBinCode(barCodeDetail.getBinCode());
            moPickingSummary.setBarcode(scanCodeDTO.getBarcode());
            moPickingSummary.setCreateBy(scanCodeDTO.getCreateBy());
            moPickingSummary.setCreateTime(DateTime.now().toString());
            moPickingSummary.setShjg(barCodeDetail.getShjg());
            moPickingSummary.setMoctyUuid(scanCodeDTO.getMoctyUuid());
            pickingMapper.saveMoPickingSummary(moPickingSummary);
        }

        // 7.记录条码操作记录 变化数量
        InvBarcodeOperation invBarcodeOperation = new InvBarcodeOperation();
        invBarcodeOperation.setId(UUID.randomUUID().toString()); // UUID
        invBarcodeOperation.setBarcode(barCodeDetail.getBarcode()); // 条码编号
        invBarcodeOperation.setQty(allocateQty); // 变化数量
        invBarcodeOperation.setWarehouseNo(barCodeDetail.getWarehouseCode()); // 仓库编号
        invBarcodeOperation.setCellNo(barCodeDetail.getBinCode()); // 库位编号
        invBarcodeOperation.setItemNo(barCodeDetail.getItemCode()); // 品号
        invBarcodeOperation.setItemName(barCodeDetail.getItemName()); // 品名
        invBarcodeOperation.setItemSpec(barCodeDetail.getItemSpec()); // 规格
        invBarcodeOperation.setUnitNo(barCodeDetail.getUnitNo()); // 单位编号
        invBarcodeOperation.setChagType(4); // 4:工单领料
        invBarcodeOperation.setStatusCode(0); // 0:未提交 1:已提交
        invBarcodeOperation.setDocNo(scanCodeDTO.getDocNo()); // 工单号
        invBarcodeOperation.setCreateDate(DateTime.now().toString()); // 创建时间
        invBarcodeOperation.setCreateBy(scanCodeDTO.getCreateBy()); // 操作人
        invBarcodeOperation.setRemark(barCodeDetail.getRemark()); // 备注
        invBarcodeOperation.setLotAtt01(scanCodeDTO.getMoctyUuid());  // MOCTY 排产单 UUID
        boolean b = invBarcodeOperationService.save(invBarcodeOperation);
        if (!b) {
            return Result.fail().message("操作记录保存失败");
        }
        return Result.ok(invBarcodeOperation);
    }

    @PostMapping("/getInvBarcodeOperation")
    @ApiOperation(value = "获取已扫描条码操作档记录")
    public Result<List<InvBarcodeOperationVo>> getInvBarcodeOperation(@RequestBody String createBy) {
        List<InvBarcodeOperationVo> InvBarcodeOperationList = pickingMapper.queryInvBarcodeOperationVo(createBy);
        return Result.ok(InvBarcodeOperationList);
    }

    @PostMapping("/scanResult")
    @ApiOperation(value = "扫码结果列表")
    public Result<List<MoPickingSummary>> scanResult(@RequestBody String createBy) {
        QueryMoSummaryDTO queryMoSummaryDTO = new QueryMoSummaryDTO();
        queryMoSummaryDTO.setCreateBy(createBy);
        List<MoPickingSummary> moPickingSummaryList = pickingMapper.queryMoSummary(queryMoSummaryDTO);
        return Result.ok(moPickingSummaryList);
    }

//    @ApiOperation("工单超领提交")
//    @Log("工单超领提交")
//    @PostMapping("/moIssueSubmit")
//    public Result MoIssueSubmit(@RequestBody MoPickingSubmitDTO moIssueSubmitDto){
//        //同步中间表
//        MiddleReturnDto middleReturnDto = moService.insertABlLjZpMiddleTable(moIssueSubmitDto.getList());
//        //提交
//        JSONObject jsonObject = moService.MoIssueSubmit(middleReturnDto);
//        // 获取执行结果的 code
////        String executionCode = jsonObject
////                .getJSONObject("payload")
////                .getJSONObject("std_data")
////                .getJSONObject("execution")
////                .getString("code");
////        if ("0".equals(executionCode)) {
////            // 1.查询未提交标签
////            List<InvBarcodeOperation> operationList = invBarcodeOperationMapper.queryNotSubimtByTypeAndCreateBy(4, moIssueSubmitDto.getCreateBy());
////            for (InvBarcodeOperation invBarcodeOperation : operationList) {
////                // 2.更新状态为已提交
////                invBarcodeOperationMapper.updateByid(invBarcodeOperation.getId());
////                // 3.扣减条码的剩余数量
////                deliveryNoticeMapper.updateBmBarcodeDetailOfQty(invBarcodeOperation.getBarcode(), invBarcodeOperation.getQty().doubleValue());
////                // 4.保存条码结果档
////                InvBarcodeTransaction invBarcodeTransaction = new InvBarcodeTransaction();
////                BeanUtil.copyProperties(invBarcodeOperation, invBarcodeTransaction);
////                invBarcodeTransactionService.save(invBarcodeTransaction);
////            }
////            // 5.删除中间表
////            pickingMapper.deleteMoPickingSummary(moIssueSubmitDto.getCreateBy());
////            return Result.ok(jsonObject);
////        }
//        return Result.fail(jsonObject);
//    }

}
