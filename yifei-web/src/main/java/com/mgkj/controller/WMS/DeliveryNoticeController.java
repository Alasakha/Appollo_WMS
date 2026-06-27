package com.mgkj.controller.WMS;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.IFill;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mgkj.annotation.Log;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.*;
import com.mgkj.mapper.DeliveryNoticeMapper;
import com.mgkj.mapper.InvBarcodeOperationMapper;
import com.mgkj.service.*;
import com.mgkj.task.TaskList;
import com.mgkj.vo.BarCodeDetailVo;
import com.mgkj.vo.InvBarcodeOperationVo;
import com.mgkj.vo.getBarCodeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@Api(tags = "销货出库相关接口")
@RequestMapping("/api/DeliveryNotice")
public class DeliveryNoticeController {

    @Autowired
    private DeliveryNoticeMapper deliveryNoticeMapper;

    @Autowired
    private SaleService saleService;

    @Autowired
    private InvBarcodeOperationService invBarcodeOperationService;

    @Autowired
    private InvBarcodeOperationMapper invBarcodeOperationMapper;

    @Autowired
    private BmBarcodeDetailService bmBarcodeDetailService;

    @Autowired
    private InvBarcodeTransactionService invBarcodeTransactionService;

    @Resource
    private SalesoutboundtaskdetailService salesoutboundtaskdetailService;

    @Resource
    private TaskList taskList;


    @PostMapping("/getDeliveryNotice")
    @ApiOperation(value = "获取全部发货通知单")
    public Result<PageInfo<DeliveryNotice>> getDeliveryNotice(@RequestBody DeliveryNoticeDTO dto){
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<DeliveryNotice> deliveryNoticeList = deliveryNoticeMapper.getDeliveryNoticeList(dto);
        PageInfo<DeliveryNotice> pageInfo = PageInfo.of(deliveryNoticeList);
        return Result.ok(pageInfo);
    }

    @PostMapping("/getDeliveryWarehouse")
    @ApiOperation(value = "获取发货仓库信息")
    public Result<List<DeliveryWarehouse>> getDeliveryWarehouse(@RequestBody List<String> docNoList) {
        List<DeliveryWarehouse> deliveryWarehouseList = deliveryNoticeMapper.getDeliveryWarehouseList(docNoList);
        return Result.ok(deliveryWarehouseList);
    }

    @PostMapping("/getDeliveryDetailByTimed")
    @ApiOperation(value = "获取发货明细数据-定时提交用")
    @Transactional
    public Result<List<DeliveryDetail>> getDeliveryDetailByTimed(@RequestBody DeliveryDetailDTO detailDTO) {
        List<DeliveryDetail> detailList = deliveryNoticeMapper.getDeliveryDetailByTimed(detailDTO);
        return Result.ok(detailList);
    }

    @PostMapping("/scanBarcodeSaveTable")
    @ApiOperation(value = "扫码接口")
    @Transactional
    public Result<?> scanBarcodeSaveTable(@RequestBody scanBarcodeSaveTableDTO dto) {
        if (StrUtil.isEmpty(dto.getDocNo())) {
            return Result.fail().message("销货单号不能为空，请先选择销货单");
        }
        if (StrUtil.isEmpty(dto.getBarCode())) {
            return Result.fail().message("条码不能为空");
        }
        BarCodeDetailVo barCodeDetail = deliveryNoticeMapper.getBarCodeDetailByBarCode(dto.getBarCode());
        if (barCodeDetail == null) {
            return Result.fail().message("条码不存在");
        }
        if (StrUtil.isEmpty(barCodeDetail.getItemCode())) {
            return Result.fail().message("条码未绑定品号，请检查条码信息");
        }
        DeliveryDetail deliveryDetail = deliveryNoticeMapper.getDeliveryDetailByDocNo(dto.getDocNo(), barCodeDetail.getItemCode());
        if (deliveryDetail == null) {
            return Result.fail().message("当前销货单单身无此品号，请检查销货单");
        }

        // 重复扫码校验：同一销货单下同一条码不能重复扫
        long existCount = salesoutboundtaskdetailService.lambdaQuery()
                .eq(Salesoutboundtaskdetail::getBarcode, dto.getBarCode())
                .eq(Salesoutboundtaskdetail::getDocNo, dto.getDocNo())
                .count();
        if (existCount > 0) {
            return Result.fail().message("此条码已在当前销货单中扫码，请勿重复操作");
        }

        // 构造扫码记录，synced=0 表示待同步到E10
        Salesoutboundtaskdetail record = new Salesoutboundtaskdetail();
        record.setBarcode(dto.getBarCode());
        record.setWarehouseCode(deliveryDetail.getWarehouseCode());
        record.setBinCode(deliveryDetail.getBinCode());
        record.setQty(BigDecimal.ONE);
        record.setDocNo(dto.getDocNo());
        record.setSalesNo(deliveryDetail.getSalesNo());
        record.setCustomerDocNo(deliveryDetail.getCustomerNo());
        record.setUnitCode(deliveryDetail.getUnitCode());
        record.setItemCode(deliveryDetail.getItemCode());
        record.setItemName(deliveryDetail.getItemName());
        record.setItemSpec(deliveryDetail.getItemSpec());
        record.setOrgNo(deliveryDetail.getShjg());
        record.setCreateBy(dto.getCreateBy());
        record.setPlanQty(new BigDecimal(deliveryDetail.getPlanQty()));
        record.setStatus(0);
        record.setSynced(0);

        try {
            salesoutboundtaskdetailService.save(record);
        } catch (Exception e) {
            throw e;
        }

        return Result.ok();
    }

    @PostMapping("/scanBarcodeSaveTable/list")
    @ApiOperation(value = "扫码查询接口")
    public Result<?> scanBarcodeSaveTable(@RequestBody QueryScanBarcodeSaveTableDTO dto) {
        QueryWrapper<Salesoutboundtaskdetail> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("docNo", dto.getDocNo());
        queryWrapper.eq("status", dto.getStatus());
//        queryWrapper.eq("salesNo", dto.getSalesNo());
//        queryWrapper.eq("customerDocNo", dto.getCustomerDocNo());
        List<Salesoutboundtaskdetail> list = salesoutboundtaskdetailService.list(queryWrapper);
        return Result.ok(list);
    }

    @PostMapping("/getDeliveryDetail")
    @ApiOperation(value = "获取发货明细-最新数据")
    @Transactional
    public Result<List<DeliveryDetail>> getDeliveryDetail(@RequestBody DeliveryDetailDTO detailDTO) {
        // 清除对应的发货明细
        deliveryNoticeMapper.deleteDeliveryDetailSummaryByCreateBy(detailDTO.getCreateBy());
        // 查询最新数据
        List<DeliveryDetail> detailList = deliveryNoticeMapper.getDeliveryDetail(detailDTO);
        for (DeliveryDetail detail : detailList) {
            // 保存到发货明细汇总表 sales_summary
            deliveryNoticeMapper.saveSalesSummary(detail, detailDTO.getCreateBy());
        }
        return Result.ok(detailList);
    }

    @PostMapping("/getDeliveryDetailMid")
    @ApiOperation(value = "获取发货明细-中间表数据")
    public Result<List<DeliveryDetail>> getDeliveryDetailMid(@RequestBody DeliveryDetailDTO detailDTO) {
        // 查询发货明细表 sales_summary
        List<DeliveryDetail> detailList = deliveryNoticeMapper.getDeliveryDetailSummaryByCreateBy(detailDTO.getCreateBy());
        return Result.ok(detailList);
    }

    @PostMapping("/getDeliveryDetailWithScanned")
    @ApiOperation(value = "获取发货明细-实时已扫量（按docNo聚合，所有人扫码可见）")
    public Result<List<DeliveryDetail>> getDeliveryDetailWithScanned(@RequestBody DeliveryDetailDTO detailDTO) {
        List<DeliveryDetail> list = deliveryNoticeMapper.getDeliveryDetailWithScanned(null, detailDTO.getDocNoList(), detailDTO.getWarehouseCodeList());
        return Result.ok(list);
    }

    @PostMapping("/getMyScannedList")
    @ApiOperation(value = "查看我的扫码明细")
    public Result<?> getMyScannedList(@RequestBody Map<String, String> params) {
        String docNo = params.get("docNo");
        String createBy = params.get("createBy");
        if (StrUtil.isEmpty(docNo)) {
            return Result.fail().message("销货单号不能为空");
        }
        List<Salesoutboundtaskdetail> list = deliveryNoticeMapper.getMyScannedList(docNo, createBy);
        return Result.ok(list);
    }

    @PostMapping("/deleteScanRecord")
    @ApiOperation(value = "删除扫码记录（仅未同步到E10的记录可删除）")
    @Transactional
    public Result<?> deleteScanRecord(@RequestBody Map<String, String> params) {
        String id = params.get("id");
        if (StrUtil.isEmpty(id)) {
            return Result.fail().message("记录ID不能为空");
        }
        Salesoutboundtaskdetail record = salesoutboundtaskdetailService.getById(id);
        if (record == null) {
            return Result.fail().message("记录不存在");
        }
        if (record.getSynced() != null && record.getSynced() == 1) {
            return Result.fail().message("该记录已同步到E10，无法删除");
        }
        if (record.getStatus() != null && record.getStatus() == 1) {
            return Result.fail().message("该记录已同步到E10，无法删除");
        }
        boolean removed = salesoutboundtaskdetailService.removeById(id);
        if (!removed) {
            return Result.fail().message("删除失败");
        }
        return Result.ok().message("删除成功");
    }

    @PostMapping("/syncPendingToE10")
    @ApiOperation(value = "分批同步待出库扫码记录到E10（不要求全部扫完）")
    public Result<?> syncPendingToE10(@RequestBody Map<String, String> params) {
        String docNo = params.get("docNo");
        return taskList.syncSalesOutboundByDocNo(docNo);
    }

    @PostMapping("/getUnscannedBarcodes")
    @ApiOperation(value = "查询同品号未扫条码（当前销货单下库存有量且未扫码）")
    public Result<?> getUnscannedBarcodes(@RequestBody Map<String, String> params) {
        String docNo = params.get("docNo");
        String itemCode = params.get("itemCode");
        String warehouseCode = params.get("warehouseCode");
        if (StrUtil.isEmpty(docNo)) {
            return Result.fail().message("销货单号不能为空");
        }
        if (StrUtil.isEmpty(itemCode)) {
            return Result.fail().message("品号不能为空");
        }
        List<BarCodeDetailVo> list = deliveryNoticeMapper.getUnscannedBarcodesByItem(docNo, itemCode, warehouseCode);
        return Result.ok(list);
    }

    @PostMapping("/isScanningCodeRecord")
    @ApiOperation(value = "判断是否有扫码记录")
    public Result isScanningCodeRecord(@RequestBody String createBy) {
        Integer count = deliveryNoticeMapper.isScanningCodeRecord(createBy);
        return Result.ok(count > 0);
    }

    @PostMapping("/BarCodeDetail")
    @ApiOperation(value = "查询条码信息")
    public Result<BarCodeDetailVo> BarCodeDetail(String barCode) {
        BarCodeDetailVo barCodeDetail = deliveryNoticeMapper.getBarCodeDetailByBarCode(barCode);
        return Result.ok(barCodeDetail);
    }

    @PostMapping("/getBarCodeDetail")
    @ApiOperation(value = "扫码接口")
    @Transactional
    public Result getBarCodeDetail(@RequestBody getBarCodeDTO barCode) {
        // 1.查询条码详情
        BarCodeDetailVo barCodeDetail = deliveryNoticeMapper.getBarCodeDetailByBarCode(barCode.getBarCode());
        if (barCodeDetail == null) {
            return Result.fail().message("条码不存在");
        }

        // 2.查询销售汇总
        List<SalesSummary> salesSummary = deliveryNoticeMapper.getSalesSummaryByItemCode(barCodeDetail.getItemCode(), barCode.getCreateBy());
        if (salesSummary == null || salesSummary.isEmpty()) {
            return Result.fail().message("品号不符合");
        }

        // 3.查询该条码操作档 汇总数量
        BigDecimal qtySum = Optional.ofNullable(deliveryNoticeMapper.getQtySum(barCode.getBarCode(), 3))
                .orElse(BigDecimal.ZERO);

        // 4.计算剩余可分配数量
        BigDecimal remainingQty = barCodeDetail.getQty().subtract(qtySum);
        if (remainingQty.compareTo(BigDecimal.ZERO) <= 0) {
            return Result.fail().message("条码剩余数量不足");
        }


        // 5.查询是否有未匹配完的记录
        Integer count = deliveryNoticeMapper.getUnMatchQtyList(barCode.getCreateBy());
        if (count == null || count == 0) {
            return Result.fail().message("所有记录已匹配完成，请提交");
        }

        // 返回的标签列表
        List<BmBarcodeDetail> barCodeDetailList = new ArrayList<>();
        // 6.分配数量
        for (SalesSummary s : salesSummary) {
            if (s.getItemCode().equals(barCodeDetail.getItemCode()) && remainingQty.compareTo(BigDecimal.ZERO) > 0 && s.getMatchQty().compareTo(s.getBusinessQty()) < 0) {
                BigDecimal neededQty = s.getBusinessQty().subtract(s.getMatchQty()); // 需要分配的数量
                BigDecimal allocateQty = neededQty.compareTo(remainingQty) <= 0 ? neededQty : remainingQty; // 本次分配的数量
                // 更新中间表匹配量
                deliveryNoticeMapper.updateSalesSummaryOfMatchQty(s.getId(), allocateQty, s.getCreateBy(), barCode.getBarCode());
                // 8.生成新条码 bm_barcode_detail
                BmBarcodeDetail newBarcodeDetail = new BmBarcodeDetail();
                newBarcodeDetail.setId(UUID.randomUUID().toString());
                // 获取当前时间
                Date currentDate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String formattedDate = sdf.format(currentDate);
                // 新条码规则 原条码日期替换为当前日期+序号
                String[] parts = barCodeDetail.getBarcode().split("#");
                String queryBarCode = parts[0] + "#" + formattedDate + "#"; // 拼接品号+当前日期 查询(最大流水号+1)的值
                String maxNumber = deliveryNoticeMapper.getMaxBarCodeDetail(queryBarCode);
                // 新条码barcode
                String newBarCode = barCodeDetail.getBarcode().split("#")[0] + "#" + formattedDate + "#" + maxNumber;
                newBarcodeDetail.setBarcode(newBarCode); // 标签号
                newBarcodeDetail.setId(UUID.randomUUID().toString()); // id
                newBarcodeDetail.setCustomerNo(s.getCustomerNo()); // 客户单号
                newBarcodeDetail.setCombinationLotNo(barCodeDetail.getLotCode()); // 批号
                newBarcodeDetail.setItemNo(barCodeDetail.getItemCode()); // 品号
                newBarcodeDetail.setItemName(barCodeDetail.getItemName()); // 品名
                newBarcodeDetail.setItemSpec(barCodeDetail.getItemSpec()); // 规格
                newBarcodeDetail.setQty(allocateQty); // 取出的数量
                newBarcodeDetail.setSnWarehouseNo(barCodeDetail.getWarehouseCode()); // 仓库编号
                newBarcodeDetail.setSnCellNo(barCodeDetail.getBinCode()); // 库位编号
                newBarcodeDetail.setUnitNo(barCodeDetail.getUnitNo()); // 单位编号
                newBarcodeDetail.setUnitName(barCodeDetail.getUnitName()); // 单位编号
                newBarcodeDetail.setStandardCol02("2"); // 1:领料出库,2:销货出库
                newBarcodeDetail.setRemark(barCodeDetail.getRemark()); // 备注
                newBarcodeDetail.setCreateDate(DateTime.now()); // 创建时间
                newBarcodeDetail.setCreateBy(barCode.getCreateBy()); // 创建人
                boolean save = bmBarcodeDetailService.save(newBarcodeDetail); // 插入标签表
                if (!save) {
                    return Result.fail().message("新条码保存失败");
                }
                barCodeDetailList.add(newBarcodeDetail); // 插入标签列表
                remainingQty = remainingQty.subtract(allocateQty);
            }
        }

        // 7.记录条码操作记录 变化数量
        InvBarcodeOperation invBarcodeOperation = new InvBarcodeOperation();
        invBarcodeOperation.setId(UUID.randomUUID().toString()); // UUID
        invBarcodeOperation.setBarcode(barCodeDetail.getBarcode()); // 条码编号
        invBarcodeOperation.setQty(barCodeDetail.getQty().subtract(remainingQty)); // 变化数量
        invBarcodeOperation.setWarehouseNo(barCodeDetail.getWarehouseCode()); // 仓库编号
        invBarcodeOperation.setCellNo(barCodeDetail.getBinCode()); // 库位编号
        invBarcodeOperation.setItemNo(barCodeDetail.getItemCode()); // 品号
        invBarcodeOperation.setItemName(barCodeDetail.getItemName()); // 品名
        invBarcodeOperation.setItemSpec(barCodeDetail.getItemSpec()); // 规格
        invBarcodeOperation.setUnitNo(barCodeDetail.getUnitNo()); // 单位编号
        invBarcodeOperation.setChagType(3); // 3:销货出库
        invBarcodeOperation.setStatusCode(0); // 0:未提交 1:已提交
        invBarcodeOperation.setCreateDate(DateTime.now().toString()); // 创建时间
        invBarcodeOperation.setCreateBy(barCode.getCreateBy()); // 操作人
        invBarcodeOperation.setRemark(barCodeDetail.getRemark()); // 备注
        boolean b = invBarcodeOperationService.save(invBarcodeOperation);
        if (!b) {
            return Result.fail().message("操作记录保存失败");
        }
        // 返回需要蓝牙打印的条码数据
        if (barCodeDetailList.isEmpty()) {
            return Result.fail().message("暂无数据");
        }
        return Result.ok(barCodeDetailList);
    }

    @PostMapping("/getBarCodeOperation")
    @ApiOperation(value = "获取已扫描中间表数据")
    public Result<List<SalesSummary>> getBarCodeOperation(@RequestBody String createBy) {
        List<SalesSummary> salesSummaryList = deliveryNoticeMapper.getSalesSummaryByCreateBy(createBy);
        return Result.ok(salesSummaryList);
    }

    @PostMapping("/getInvBarcodeOperation")
    @ApiOperation(value = "获取已扫描条码操作档记录")
    public Result<List<InvBarcodeOperationVo>> getInvBarcodeOperation(@RequestBody String createBy) {
         List<InvBarcodeOperationVo> InvBarcodeOperationList = deliveryNoticeMapper.getInvBarcodeOperationVo(createBy);
        return Result.ok(InvBarcodeOperationList);
    }

    @PostMapping("/resetData")
    @ApiOperation(value = "重置数据")
    @Transactional
    public Result resetData(@RequestBody String createBy) {
        try {
            // 1.删除中间表
            deliveryNoticeMapper.deleteDeliveryDetailSummaryByCreateBy(createBy);
            // 2.删除条码操作档 -> 条码状态：未提交 & 条码类型：销货出库
            deliveryNoticeMapper.deleteInvBarcodeOperationByCreateBy(createBy);
        } catch (Exception e) {
            return Result.fail().message("重置失败");
        }
        return Result.ok();
    }

    @PostMapping("/delBarCodeOperation")
    @ApiOperation(value = "删除单个已扫码数据")
    @Transactional
    public Result<Object> delBarCodeOperation(@RequestBody String id) {
        InvBarcodeOperation invBarcodeOperation = deliveryNoticeMapper.getInvBarcodeOperationById(id); // 获取条码操作记录
        BigDecimal qty = invBarcodeOperation.getQty(); // 条码变化量
        // 回滚中间表的匹配量
        List<SalesSummary> salesSummary = deliveryNoticeMapper.getSalesSummaryByItemCode(invBarcodeOperation.getItemNo(), invBarcodeOperation.getCreateBy());
        for (SalesSummary s : salesSummary) {
            if (invBarcodeOperation.getItemNo().equals(s.getItemCode()) && qty.compareTo(BigDecimal.ZERO) > 0) {
                if (s.getCurrentNum().compareTo(qty) > 0) {
                    // 匹配量 > 条码变化量: -> 匹配量 = 匹配量 - 条码变化量
                    int i = deliveryNoticeMapper.updateQtySubtraction(s.getId(), qty, invBarcodeOperation.getCreateBy());
                    if (i <= 0) {
                        return Result.fail().message("中间表匹配量回滚失败");
                    }
                    qty = BigDecimal.ZERO;
                } else {
                    // 匹配量 < 条码变化量 -> 匹配量 = 0
                    int i = deliveryNoticeMapper.updateQtyZero(s.getId(), qty, invBarcodeOperation.getCreateBy());
                    if (i <= 0) {
                        return Result.fail().message("中间表匹配量回滚失败");
                    }
                    qty = qty.subtract(s.getCurrentNum());
                }
            } else {
                break;
            }
        }
        boolean b = deliveryNoticeMapper.deleteInvBarcodeOperationById(id);
        if (!b) {
            return Result.fail().message("条码操作档删除失败");
        }
        return Result.ok().message("删除成功");
    }

    @ApiOperation("提交-销售订单(已审核)->销货单(已审核)->生成销货出库单(已审核)")
    @Log("提交-销售订单(已审核)->销货单(已审核)->生成销货出库单(已审核)")
    @PostMapping("/SaleOutSubmit")
    public synchronized Result SaleOutSubmit(@RequestBody SaleOutSubmitDTO submitDTO) {
        String createBy = submitDTO.getCreateBy();
        if (StrUtil.isBlank(createBy)) {
            return Result.fail().message("操作人不能为空！");
        }

        // 校验是否有未同步到E10的扫码记录
        if (CollUtil.isNotEmpty(submitDTO.getList())) {
            String docNo = submitDTO.getList().get(0).getDocNo();
            Integer unsyncedCount = deliveryNoticeMapper.countUnsyncedRecords(docNo);
            if (unsyncedCount != null && unsyncedCount > 0) {
                return Result.fail().message("仍有 " + unsyncedCount + " 条记录未同步到E10，请稍后再试");
            }
        }

        System.out.println("submitDTO：" + submitDTO);
//        return Result.ok(submitDTO);
            //同步中间表
            JSONObject jsonObject;

            MiddleReturnDto middleReturnDto = saleService.SaleOutStockInsertMiddleTable(submitDTO.getList(), createBy);
            jsonObject = saleService.SaleOutStockSubmit(middleReturnDto, createBy);

            //提交
            System.out.println(jsonObject);
            // 获取执行结果的 code
            String executionCode = jsonObject
                    .getJSONObject("payload")
                    .getJSONObject("std_data")
                    .getJSONObject("execution")
                    .getString("code");
            // E10接口提交成功
            if ("0".equals(executionCode)) {
                String message = jsonObject
                        .getJSONObject("payload")
                        .getJSONObject("std_data")
                        .getJSONObject("parameter")
                        .getJSONArray("als_app_request_result")  // 获取JSON数组
                        .getJSONObject(0)        // 获取数组的第一个元素
                        .getString("doc_no");     // 获取docNo字段

                for (String id : submitDTO.getInvBarcodeOperationIdList()) {
                    // 1.修改操作档状态为已提交
                    InvBarcodeOperation invBarcodeOperation = invBarcodeOperationMapper.selectByid(id);
                    boolean b = invBarcodeOperationMapper.updateByid(invBarcodeOperation.getId());
                    if (!b) {
                        return Result.fail().message("修改操作档状态为已提交失败");
                    }
                    // 2.扣减条码的剩余数量
                    int i = deliveryNoticeMapper.updateBmBarcodeDetailOfQty(invBarcodeOperation.getBarcode(), invBarcodeOperation.getQty().doubleValue());
                    if (i <= 0) {
                        return Result.fail().message("扣减条码的剩余数量失败");
                    }
                    // 4.保存条码结果档
                    InvBarcodeTransaction invBarcodeTransaction = new InvBarcodeTransaction();
                    BeanUtil.copyProperties(invBarcodeOperation, invBarcodeTransaction);
                    invBarcodeTransaction.setDocType("-1");
                    invBarcodeTransaction.setDocNo(message);
                    invBarcodeTransaction.setCreateDate(new Date());
                    invBarcodeTransaction.setLotAtt50("/SaleOutSubmit");

                    boolean save = invBarcodeTransactionService.save(invBarcodeTransaction);
                    if (!save) {
                        return Result.fail().message("保存条码结果档失败");
                    }
                }
                return Result.ok(jsonObject);

//                // 5.删除中间表
//                int i = deliveryNoticeMapper.deleteDeliveryDetailSummaryByCreateBy(submitDTO.getCreateBy());
//                if (i <= 0) {
//                    return Result.fail().message("删除中间表失败");
//                }
            } else {
                String description  = jsonObject
                        .getJSONObject("payload")
                        .getJSONObject("std_data")
                        .getJSONObject("execution")
                        .getString("description");
                return Result.fail(description).message(description);
            }
        }


}