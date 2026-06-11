package com.mgkj.controller.WMS;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mgkj.annotation.Log;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.InvBarcodeOperation;
import com.mgkj.entity.InvBarcodeTransaction;
import com.mgkj.mapper.StockMapper;
import com.mgkj.service.InvBarcodeOperationService;
import com.mgkj.service.InvBarcodeTransactionService;
import com.mgkj.service.StockService;
import com.mgkj.vo.BarCodeDetailVo;
import com.mgkj.vo.InvBarcodeOperationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author yyyjcg
 * @date 2024/4/7
 * @Description
 */

@Slf4j
@RestController
@Api(tags = "ABL库存管理相关接口")
@RequestMapping("/api/ABLStock")
@CrossOrigin
public class ABLStockController {
    @Autowired
    private StockService stockService;

    @Autowired
    private StockMapper stockMapper;

    @Resource
    private InvBarcodeOperationService invBarcodeOperationService;

    @Resource
    private InvBarcodeTransactionService invBarcodeTransactionService;


    private static final Logger logger = LoggerFactory.getLogger(ABLStockController.class);


    @PostMapping("/getTransferDoc")
    @ApiOperation("获取全部调拨单")
    public Result<PageInfo<TransferDoc>> getTransferDoc(@RequestBody PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<TransferDoc> transferDocList = stockMapper.getTransferDoc();
        PageInfo<TransferDoc> pageInfo = PageInfo.of(transferDocList);
        return Result.ok(pageInfo);
    }

    @PostMapping("/getTransferDocDetail")
    @ApiOperation(value = "获取调拨单明细")
    @Transactional
    public Result<List<TransferDocDetail>> getTransferDocDetail(@RequestBody TransferDocDetailDTO detailDTO) {
        // 清除对应的发货明细
//        stockMapper.deleteTransferDocDetailSummaryByCreateBy(detailDTO.getCreateBy());
        // 查询最新数据
        List<TransferDocDetail> detailList = stockMapper.getTransferDocDetail(detailDTO);
//        for (DeliveryDetail detail : detailList) {
//            // 保存到发货明细汇总表 sales_summary
//            deliveryNoticeMapper.saveSalesSummary(detail, detailDTO.getCreateBy());
//        }
        return Result.ok(detailList);
    }

    @ApiOperation("查询调拨箱码信息")
    @PostMapping("/getTransferBarcodeInfo")
    @DSTransactional
    public Result getTransferBarcodeInfo (@RequestBody TransferBarcodeDto dto) {

        // 1.查询条码详情
        BarCodeDetailVo barCodeDetail = stockMapper.getBarCodeDetailByBarCode(dto.getBarcode());
        if (barCodeDetail == null) {
            return Result.fail().message("条码不存在");
        }

        // 2.传调拨单头uuid会重复，改成传调拨单单号——存疑不改，
        List<TransferDocDetail> detailList = stockMapper.getTransferDocDetailByDocNoAndItemCode(dto.getDocNo(), dto.getDocId(),barCodeDetail.getItemCode());
        for(TransferDocDetail detail : detailList){
            if(!detail.getItemCode().equals(barCodeDetail.getItemCode())){
                return Result.fail().message("品号不符！");
            }else {
                barCodeDetail.setTransferUuid(detail.getDocDId());
            }
        }

        return Result.ok(barCodeDetail);

    }
    @PostMapping("/saveTransferBarcodeInfo")
    @ApiOperation(value = "保存需调拨数据")
    @Log("保存需调拨数据")
    @DSTransactional
    public Result<?> saveTransferBarcodeInfo(@RequestBody List<TransferInvBarcodeOperationDto> list) {
        if (list == null || list.isEmpty()) {
            return Result.fail().message("调拨数据不能为空");
        }
        List<InvBarcodeOperation> invBarcodeOperationList =new ArrayList<>();
        for (TransferInvBarcodeOperationDto dto : list) {
            // 1.查询该条码操作档 汇总数量
            Double qtySum = stockMapper.getQtySum(dto.getBarcode(), dto.getChagType());
            if (qtySum == null) {
                qtySum = 0.0;
            }

            double remainingQty = dto.getMatchQty() - qtySum;
            if (remainingQty <= 0) {
                return Result.fail().message("条码剩余数量不足");
            }
            InvBarcodeOperation invBarcodeOperation = new InvBarcodeOperation();
            invBarcodeOperation.setId(UUID.randomUUID().toString()); // UUID
            invBarcodeOperation.setBarcode(dto.getBarcode()); // 条码编号
            invBarcodeOperation.setDocNo(dto.getDocNo()); // 调拨单号
            invBarcodeOperation.setQty(BigDecimal.valueOf(dto.getMatchQty())); // 条码数量
            invBarcodeOperation.setSnFromWarehouseNo(dto.getFromWarehouseCode()); // 调出仓库编号
            invBarcodeOperation.setSnFromCellNo(dto.getFromBinCode()); // 调出库位编号
            invBarcodeOperation.setSnToWarehouseNo(dto.getToWarehouseCode()); // 调入库位编号
            invBarcodeOperation.setSnToCellNo(dto.getToBinCode()); // 调入库位编号
            invBarcodeOperation.setItemNo(dto.getItemCode()); // 品号
            invBarcodeOperation.setItemName(dto.getItemName()); // 品名
            invBarcodeOperation.setItemSpec(dto.getItemSpec()); // 规格
            invBarcodeOperation.setUnitNo(dto.getUnitCode()); // 单位编号
            invBarcodeOperation.setUnitName(dto.getUnitName()); // 单位编号
            invBarcodeOperation.setChagType(dto.getChagType()); //
            invBarcodeOperation.setStatusCode(0); // 0:未提交 1:已提交
            invBarcodeOperation.setCreateDate(DateTime.now().toString()); // 创建时间
            invBarcodeOperation.setCreateBy(dto.getCreateBy()); // 操作人
            invBarcodeOperation.setRemark(dto.getRemark()); // 备注
            invBarcodeOperation.setDocOrgNo(dto.getFromShjg()); //调出收货机构
            invBarcodeOperation.setOrgNo(dto.getToShjg()); // 调入收货机构
            boolean b = invBarcodeOperationService.save(invBarcodeOperation);
            if (!b) {
                return Result.fail().message("操作记录保存失败");
            }
            invBarcodeOperationList.add(invBarcodeOperation);
        }
        return Result.ok(invBarcodeOperationList).message("操作记录保存成功");

    }

    @PostMapping("/getInvBarcodeOperation")
    @ApiOperation(value = "获取已扫描条码操作档记录-库存调拨")
    public Result<List<InvBarcodeOperationVo>> getInvBarcodeOperation(@RequestBody String createBy) {
        List<InvBarcodeOperationVo> InvBarcodeOperationList = stockMapper.queryInvBarcodeOperationVo(createBy);
        return Result.ok(InvBarcodeOperationList);
    }

    @PostMapping("/deleteScanResult")
    @ApiOperation(value = "删除扫码结果列表")
    @Log("删除扫码结果列表")
    @Transactional
    public Result<?> deleteScanResult(@RequestBody QueryTransferDTO dto) {
        stockMapper.deleteInvBarcodeOperationByCreateBy(dto.getCreateBy(), dto.getChagType());
        return Result.ok();
    }


    @ApiOperation("库存调拨-提交-维护库存调拨单(未审核)->->维护库存调拨单(已审核)")
    @PostMapping("/ListTransferDocSubmit")
    @Log("库存调拨-提交-维护库存调拨单(未审核)->->维护库存调拨单(已审核)")
    public Result ListTransferDocSubmit(@RequestBody TransferSubmitDto dto) {
        //1、提交e10
        //同步中间表
        MiddleReturnDto middleReturnDto = stockService.insertTransferDocMiddleTable(dto.getList());
        JSONObject jsonObject = stockService.ListTransferDocSubmit(middleReturnDto);
        String executionCode = jsonObject
                .getJSONObject("payload")
                .getJSONObject("std_data")
                .getJSONObject("execution")
                .getString("code");

        System.out.println(jsonObject);
        if (!"0" .equals(executionCode)) {
            return Result.fail(jsonObject);
        }
        for (TransferDocSubmitDto list : dto.getList()) {
            // 1.查询未提交标签
            List<InvBarcodeOperation> operationList = stockMapper.queryTransNotSubimtByTypeAndCreateBy(8, dto.getCreateBy(), list.getBarcode());
            for (InvBarcodeOperation invBarcodeOperation : operationList) {
                // 2.更新状态为已提交
                stockMapper.updateByid(invBarcodeOperation.getId());
                // 3.扣减条码的剩余数量
                stockMapper.updateBmBarcodeDetailOfQty(invBarcodeOperation.getBarcode(), invBarcodeOperation.getQty().doubleValue());
                // 4.保存条码结果档
                InvBarcodeTransaction invBarcodeTransaction = new InvBarcodeTransaction();
                invBarcodeTransaction.setId(UUID.randomUUID().toString());
                invBarcodeTransaction.setBarcode(invBarcodeOperation.getBarcode());
                invBarcodeTransaction.setDocNo(invBarcodeOperation.getDocNo());
                invBarcodeTransaction.setItemNo(invBarcodeOperation.getItemNo());
                invBarcodeTransaction.setItemName(invBarcodeOperation.getItemName());
                invBarcodeTransaction.setItemSpec(invBarcodeOperation.getItemSpec());
                invBarcodeTransaction.setQty(invBarcodeOperation.getQty());
                invBarcodeTransaction.setReferenceQty(invBarcodeOperation.getReferenceQty());
                invBarcodeTransaction.setWarehouseNo(invBarcodeOperation.getSnFromWarehouseNo());
                invBarcodeTransaction.setCellNo(invBarcodeOperation.getSnFromCellNo());
                invBarcodeTransaction.setSnFromWarehouseNo(invBarcodeOperation.getSnFromWarehouseNo());
                invBarcodeTransaction.setSnFromCellNo(invBarcodeOperation.getSnFromCellNo());
                invBarcodeTransaction.setSnToWarehouseNo(invBarcodeOperation.getSnToWarehouseNo());
                invBarcodeTransaction.setSnToCellNo(invBarcodeOperation.getSnToCellNo());
                invBarcodeTransaction.setUnitNo(invBarcodeOperation.getUnitNo());
                invBarcodeTransaction.setUnitName(invBarcodeOperation.getUnitName());
                invBarcodeTransaction.setChagType(invBarcodeOperation.getChagType());
                invBarcodeTransaction.setStatusCode(invBarcodeOperation.getStatusCode());
                invBarcodeTransaction.setCreateBy(invBarcodeOperation.getCreateBy());
                invBarcodeTransaction.setDocReturnFlag(invBarcodeOperation.getDocReturnFlag());
                invBarcodeTransaction.setStockKeep(invBarcodeOperation.getStockKeep());
                invBarcodeTransaction.setStockRate(invBarcodeOperation.getStockRate());
                invBarcodeTransaction.setDocOrgNo(invBarcodeOperation.getDocOrgNo());
                invBarcodeTransaction.setOrgNo(invBarcodeOperation.getOrgNo());
                invBarcodeTransactionService.save(invBarcodeTransaction);

            }
        }


        return Result.ok(jsonObject);


    }

        @ApiOperation("两阶段库存调拨-提交-维护调拨申请单(已审核)->->生成维护库存调出单(已审核)")
    @PostMapping("/ListTransferOutDocSubmit")
        @Log("两阶段库存调拨-提交-维护调拨申请单(已审核)->->生成维护库存调出单(已审核)")
    public Result ListTransferDocOutSubmit(@RequestBody TransferOutSubmitDto dto) {
        //1、提交e10
        //同步中间表
        MiddleReturnDto middleReturnDto = stockService.insertTransferDocOutMiddleTable(dto.getList());
        JSONObject jsonObject = stockService.ListTransferDocOutSubmit(middleReturnDto);
        String executionCode = jsonObject
                .getJSONObject("payload")
                .getJSONObject("std_data")
                .getJSONObject("execution")
                .getString("code");

//        System.out.println(jsonObject);
//        if (!"0" .equals(executionCode)) {
//            return Result.fail(jsonObject);
//        }
//        for (TransferDocSubmitDto list : dto.getList()) {
//            // 1.查询未提交标签
//            List<InvBarcodeOperation> operationList = stockMapper.queryTransNotSubimtByTypeAndCreateBy(8, dto.getCreateBy(), list.getBarcode());
//            for (InvBarcodeOperation invBarcodeOperation : operationList) {
//                // 2.更新状态为已提交
//                stockMapper.updateByid(invBarcodeOperation.getId());
//                // 3.扣减条码的剩余数量
//                stockMapper.updateBmBarcodeDetailOfQty(invBarcodeOperation.getBarcode(), invBarcodeOperation.getQty().doubleValue());
//                // 4.保存条码结果档
//                InvBarcodeTransaction invBarcodeTransaction = new InvBarcodeTransaction();
//                invBarcodeTransaction.setId(UUID.randomUUID().toString());
//                invBarcodeTransaction.setBarcode(invBarcodeOperation.getBarcode());
//                invBarcodeTransaction.setDocNo(invBarcodeOperation.getDocNo());
//                invBarcodeTransaction.setItemNo(invBarcodeOperation.getItemNo());
//                invBarcodeTransaction.setItemName(invBarcodeOperation.getItemName());
//                invBarcodeTransaction.setItemSpec(invBarcodeOperation.getItemSpec());
//                invBarcodeTransaction.setQty(invBarcodeOperation.getQty());
//                invBarcodeTransaction.setReferenceQty(invBarcodeOperation.getReferenceQty());
//                invBarcodeTransaction.setWarehouseNo(invBarcodeOperation.getSnFromWarehouseNo());
//                invBarcodeTransaction.setCellNo(invBarcodeOperation.getSnFromCellNo());
//                invBarcodeTransaction.setSnFromWarehouseNo(invBarcodeOperation.getSnFromWarehouseNo());
//                invBarcodeTransaction.setSnFromCellNo(invBarcodeOperation.getSnFromCellNo());
//                invBarcodeTransaction.setSnToWarehouseNo(invBarcodeOperation.getSnToWarehouseNo());
//                invBarcodeTransaction.setSnToCellNo(invBarcodeOperation.getSnToCellNo());
//                invBarcodeTransaction.setUnitNo(invBarcodeOperation.getUnitNo());
//                invBarcodeTransaction.setUnitName(invBarcodeOperation.getUnitName());
//                invBarcodeTransaction.setChagType(invBarcodeOperation.getChagType());
//                invBarcodeTransaction.setStatusCode(invBarcodeOperation.getStatusCode());
//                invBarcodeTransaction.setCreateBy(invBarcodeOperation.getCreateBy());
//                invBarcodeTransaction.setDocReturnFlag(invBarcodeOperation.getDocReturnFlag());
//                invBarcodeTransaction.setStockKeep(invBarcodeOperation.getStockKeep());
//                invBarcodeTransaction.setStockRate(invBarcodeOperation.getStockRate());
//                invBarcodeTransaction.setDocOrgNo(invBarcodeOperation.getDocOrgNo());
//                invBarcodeTransaction.setOrgNo(invBarcodeOperation.getOrgNo());
//                invBarcodeTransactionService.save(invBarcodeTransaction);
//
//            }
//        }
//

        return Result.ok(jsonObject);


    }




}
