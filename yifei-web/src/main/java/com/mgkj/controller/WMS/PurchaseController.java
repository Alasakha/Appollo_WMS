package com.mgkj.controller.WMS;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.mgkj.annotation.Debounce;
import com.mgkj.annotation.Log;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.*;
import com.mgkj.mapper.DeliverySummaryMapper;
import com.mgkj.mapper.InvBarcodeOperationMapper;
import com.mgkj.mapper.PurchaseMapper;
import com.mgkj.mapper.PurchaseSummaryMapper;
import com.mgkj.service.*;
import com.mgkj.service.impl.E10ApiService;
import com.mgkj.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author yyyjcg
 * @date 2023/12/26
 * @Description
 */
@Slf4j
@RestController
@Api(tags = "采购管理相关接口")
@RequestMapping("/api")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseTwoService purchaseTwoService;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private PurchaseSummaryService purchaseSummaryService;

    @Autowired
    private PurchaseSummaryMapper purchaseSummaryMapper;

    @Autowired
    private DeliverySummaryService deliverySummaryService;
    @Autowired
    private DeliverySummaryMapper deliverySummaryMapper;




    @ApiOperation("采购收货-当天待检验的到货单头")
    @PostMapping("/ListPurchaseReceiptStorageWaitCheck")
    public Result<PageInfo<PurchaseReceiptStorageWaitCheckVo>> ListPurchaseReceiptStorageWaitCheck(@RequestBody PurchaseReceiptStorageWaitCheckDto dto){
        return purchaseService.ListPurchaseReceiptStorageWaitCheck(dto);
    }

    @ApiOperation("采购收货-当天待检验的到货单身物料信息")
    @PostMapping("/ListPurchaseReceiptStorageWaitCheckItem")
    public Result<PageInfo<PurchaseReceiptStorageWaitCheckItemVo>> ListPurchaseReceiptStorageWaitCheckItem(@RequestBody PurchaseReceiptStorageWaitCheckItemDto dto){
        return purchaseService.ListPurchaseReceiptStorageWaitCheckItem(dto);
    }


    //    @Log("采购管理-->条码查到货单号")
    @ApiOperation("采购收货--前置查询(送货单号查采购到货提交需数据)")
    @Log("采购收货--前置查询(送货单号查采购到货提交需数据)")
    @PostMapping("/getPurcharseDeliveryNo")
    @DSTransactional
    public Result<List<PurchaseReceiptStorageVo>> getPurcharsedeliveryNo(@RequestBody PurcharseDeliveryDto dto){
        if(dto.getCreateBy()==null || dto.getCreateBy().isEmpty() || dto.getCreateBy() == ""){
            throw new RuntimeException("创建人不能为空");
        }

        //查delivery_summary(送货单号)
        List<DeliverySummary> list = deliverySummaryMapper.deliveryIsExist(dto.getNumber());
        if(list != null && list.size() > 0){
            StringBuffer stringBuffer = new StringBuffer("送货单号已存在,或被");
            stringBuffer.append(list.get(0).getCreator()).append("已扫码。");
            throw new RuntimeException(stringBuffer.toString());
        }


        List<PurchaseReceiptStorageVo> purcharsedeliveryNo = new ArrayList<>();

        //根据送货单号查送货单身
        List<DeliverySummary> deliveryDetails = purchaseMapper.getByDeliveryNo(dto);
        if(!deliveryDetails.isEmpty()){
            for (DeliverySummary deliveryDetail : deliveryDetails) {
                deliveryDetail.setCreator(dto.getCreateBy());
            }
            deliverySummaryService.saveBatch(deliveryDetails);
            return Result.ok(purcharsedeliveryNo).message("送货单身扫码成功");
        }


        // 2.根据条码到货
        purcharsedeliveryNo = purchaseService.getPurcharseByBarcode(dto);
        if(!purcharsedeliveryNo.isEmpty()) {
            for (PurchaseReceiptStorageVo vo : purcharsedeliveryNo) {

                //扫码判断条码号是否属于已扫的送货单
                if(purchaseMapper.selectIsClude(vo.getBarcode(),dto.getCreateBy()) == 0){
                    throw new RuntimeException("条码号不属于已扫的送货单");
                }

                //查收货操作表purchase_summary(登录人和箱码)
                if(purchaseSummaryMapper.bacodeisExist(dto.getCreateBy(),vo.getBarcode())>0){
                    throw new RuntimeException("条码已存在");
                }
                //查条码对应的delivery_summary记录
                DeliverySummary d = deliverySummaryMapper.getDeliverySummary(vo.getBarcode());

                // 本次分配的数量
                BigDecimal allocateQty = vo.getMatchQty();
                if (d.getMatchQty().add(allocateQty).compareTo(d.getDeliveryNum()) > 0) {
                    throw new RuntimeException("收货数量已满");
                }
                d.setMatchQty(d.getMatchQty().add(allocateQty));
                //更新匹配数
                deliverySummaryService.updateById(d);
                //更新条码状态(null未匹配，1已匹配)
//                purchaseMapper.updatelotAtt31(vo.getBarcode());


                String id = UUID.randomUUID().toString();
                vo.setId(id);
                // 2.记录条码操作记录 变化数量
                PurchaseSummary purchaseSummary = new PurchaseSummary();
                purchaseSummary.setId(id); // UUID
                purchaseSummary.setLotAtt01(vo.getParentId());//送货单头uuid
                purchaseSummary.setLotAtt02(vo.getArrivalNo());//采购到货单号
                purchaseSummary.setLotAtt03(vo.getDeliveryNumber());//送货单号
                purchaseSummary.setLotAtt04(vo.getDocNo());//采购订单号
                purchaseSummary.setLotAtt05(vo.getSupplierNo());//供应商编号
                purchaseSummary.setLotAtt06(vo.getSupplierName());//供应商名称
                purchaseSummary.setLotAtt07(vo.getCustomerNo());//客户编号
                purchaseSummary.setStandardCol11(vo.getContainerQty());//标准收容数量
                purchaseSummary.setLotAtt08(vo.getContainerName());//收容器具
                purchaseSummary.setLotAtt09(vo.getContainerDetail());//容器具尺寸
                purchaseSummary.setLotAtt10(vo.getStandard_col10());//容器码uuid
                purchaseSummary.setLotAtt11(vo.getBar_type());//条码类型
                purchaseSummary.setBarcode(vo.getBarcode()); // 条码编号
                purchaseSummary.setOrgNo(vo.getShjg()); // 收货机构
                purchaseSummary.setQty(vo.getMatchQty()); // 数量
                purchaseSummary.setWarehouseNo(vo.getWarehouseCode()); // 仓库编号
                purchaseSummary.setCellNo(vo.getBinCode()); // 库位编号
                purchaseSummary.setCombinationLotNo(vo.getDocDate()); // 批号 / 日期
                purchaseSummary.setItemNo(vo.getItemCode()); // 品号
                purchaseSummary.setItemName(vo.getItemName()); // 品名
                purchaseSummary.setItemSpec(vo.getItemSpec()); // 规格
                purchaseSummary.setUnitNo(vo.getUnitCode()); // 单位编号
                purchaseSummary.setUnitName(vo.getUnitName());//单位名称
                purchaseSummary.setChagType(100); // 100.采购收货
                purchaseSummary.setStatusCode(0); // 0:未提交 1:已提交
                purchaseSummary.setCreateDate(DateTime.now().toString()); // 创建时间
                purchaseSummary.setCreateBy(dto.getCreateBy()); // 操作人
                boolean b = purchaseSummaryService.save(purchaseSummary);
                if (!b) {
                    return Result.fail(purcharsedeliveryNo).message("扫码记录保存失败");
                }
            }
        }
        return Result.ok(purcharsedeliveryNo);
    }


    @ApiOperation(value = "采购收货--扫码送货单身记录")
    @PostMapping("/getDeliverySummary")
    @Log("采购收货--扫码送货单身记录")
    public Result<List<DeliverySummaryVo>> getDeliverySummary(@RequestBody String creator) {
        List<DeliverySummary> list = deliverySummaryMapper.selectList(new QueryWrapper<DeliverySummary>().eq("creator", creator));
        // 按deliveryNumber分组并转换为DeliverySummaryVo列表
        List<DeliverySummaryVo> result = list.stream()
                .collect(Collectors.groupingBy(DeliverySummary::getDeliveryNumber))
                .values().stream()
                .map(items -> {
                    DeliverySummaryVo vo = new DeliverySummaryVo();
                    vo.setDeliverySummaryList(items);
                    return vo;
                })
                .collect(Collectors.toList());

        if(result != null && result.size() > 0) {
            // 1. 先找出每个送货单中，最大的时间
            for(DeliverySummaryVo t : result) {
                t.setCreateTime(t.getDeliverySummaryList().get(0).getCreateTime());
                for(int i = 1; i < t.getDeliverySummaryList().size(); i++) {
                    if(t.getCreateTime().compareTo(t.getDeliverySummaryList().get(i).getCreateTime()) < 0) {
                        t.setCreateTime(t.getDeliverySummaryList().get(i).getCreateTime());
                    }
                }
            }
            // 2.result数组排序
            result.sort(new Comparator<DeliverySummaryVo>() {
                @Override
                public int compare(DeliverySummaryVo o1, DeliverySummaryVo o2) {
                    return o2.getCreateTime().compareTo(o1.getCreateTime());
                }
            });
        }
        return Result.ok(result);
    }

    @ApiOperation(value = "采购收货--获取送货对应条码")
    @Log("采购收货--获取送货对应条码")
    @PostMapping("/getDeliveryDetailsBarcode")
    public Result<List<DeliveryDetailsBarcodeVo>> getDeliveryDetailsBarcode(@RequestBody PurcharseDeliveryDto dto) {
        String uuid = dto.getUuid() != null && !dto.getUuid().isEmpty() ? dto.getUuid() : dto.getNumber();
        if (uuid == null || uuid.isEmpty()) {
            return Result.<List<DeliveryDetailsBarcodeVo>>fail(null).message("uuid不能为空");
        }
        List<DeliveryDetailsBarcodeVo> list = deliverySummaryMapper.getDeliveryDetailsBarcode(uuid, dto.getCreateBy());
        if(list != null) {
            for(DeliveryDetailsBarcodeVo t : list) {
                if(!t.getLotAtt31().equals("1")) {
                    t.setLotAtt31(null);
                }
            }
        }
        return Result.ok(list);
    }

    @PostMapping("/isScanningCodeRecord")
    @ApiOperation(value = "判断是否有扫码记录")
    @Log("采购收货-判断是否有扫码记录")
    public Result isScanningCodeRecord(@RequestBody String createBy) {
        Integer count = purchaseMapper.isScanningCodeRecord(createBy);
        return Result.ok(count > 0);
    }

//    @PostMapping("/getInvBarcodeOperation")
//    @ApiOperation(value = "获取已扫描条码记录")
//    public Result<Map<String, Object>> getInvBarcodeOperation(@RequestBody PurchaseReceiptStorageDtoOne dto) {
//        List<PurchaseReceiptStorageVo> list = purchaseMapper.getInvBarcodeOperationVo(dto);
//
//        Map<String, List<PurchaseReceiptStorageVo>> boxMap = new HashMap<>();
//        int nullBoxCounter = 0;
//
//        // 遍历列表，将每个对象按 standard_col10 分组
//        for (PurchaseReceiptStorageVo item : list) {
//            String standardCol10 = item.getStandard_col10();
//
//            if (standardCol10 == null) {
//                // 为每个 null 创建一个唯一的键，例如 "null_1", "null_2"
//                String uniqueNullKey = "null_" + (++nullBoxCounter);
//                boxMap.put(uniqueNullKey, Collections.singletonList(item));
//            } else {
//                // 非 null 值按正常逻辑分组
//                boxMap.computeIfAbsent(standardCol10, k -> new ArrayList<>()).add(item);
//            }
//        }
//
//        // 构建结果映射
//        Map<String, Object> result = new HashMap<>();
//        result.put("totalBoxCount", boxMap.size());
//        result.put("boxList", list);
//        return Result.ok(result);
//    }

    @PostMapping("/getInvBarcodeOperation")
    @ApiOperation(value = "获取已扫描条码记录")
    public Result<List<PurchaseReceiptStorageVo>> getInvBarcodeOperation(@RequestBody PurchaseReceiptStorageDtoOne dto) {
        List<PurchaseReceiptStorageVo> list = purchaseMapper.getInvBarcodeOperationVo(dto);
        return Result.ok(list);
    }



    @PostMapping("/resetData")
    @ApiOperation(value = "重置数据")
    @Log("采购到货-重置数据")
    @Transactional
    public Result resetData(@RequestBody String createBy) {
        try {
            //删除delivery_summary
            QueryWrapper<DeliverySummary> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("creator", createBy);
            deliverySummaryMapper.delete(queryWrapper);
            //把对应条码的lot_att31置为null
            purchaseMapper.updateLotAtt31Null(createBy);
            //删除条码操作档 -> 条码状态：未提交 & 条码类型：采购收货
            purchaseMapper.deleteInvBarcodeOperationByCreateBy(createBy);
        } catch (Exception e) {
            throw new RuntimeException("重置数据失败:" + e.toString());
        }
        return Result.ok();
    }

    @PostMapping("/deleteDeliverySummary")
    @ApiOperation(value = "删除(送货单)扫码记录")
    @Log("送货单-删除(送货单)扫码记录")
    @Transactional
    public Result deleteDeliverySummary(@RequestBody String deliveryNumer) {
        //判断有无对应的扫码记录
        int count = deliverySummaryMapper.isDeliverySummaryRecord(deliveryNumer);
        if (count>0) {
            return Result.fail().message("有对应扫码记录，不能删除");
        }
        //删除扫码记录
        int b = deliverySummaryMapper.deleteByDeliveryNumer(deliveryNumer);
        if (b<=0) {
            throw new RuntimeException("条码操作档删除失败:");
        }
        return Result.ok().message("删除成功");

    }
    @PostMapping("/deleteRecord")
    @ApiOperation(value = "删除扫码记录")
    @Log("采购到货单-删除扫码记录")
    @Transactional
    public Result delete(@RequestBody String id) {
        //获取条码记录
        PurchaseSummary p =purchaseSummaryMapper.selectPurchaseSummary(id);
        if (p == null) {
            return Result.fail().message("条码不存在");
        }
        //标签对应的实收数量
        BigDecimal matchQty =deliverySummaryMapper.selectQty(id);
        //扣除delivery_summary的匹配数量
        BigDecimal qty = matchQty.subtract(p.getQty());
        int d = deliverySummaryMapper.updateQty(qty,id);
        if (d<=0) {
            throw new RuntimeException("送货单身中间表匹配数更新失败");
        }

        //把对应条码的lot_att31置为null
        int l = purchaseMapper.updateLotAtt31NullSingle(id);
        if (l<=0) {
            throw new RuntimeException("条码状态更新失败");
        }

        //删除扫码记录
        Boolean b = purchaseMapper.deleteBarCodeOperation(id);
        if (!b) {
            throw new RuntimeException("条码操作档删除失败");
        }

        return Result.ok().message("删除成功");

    }

    @PostMapping("/updatePurchaseReceiveRecord")
    @ApiOperation(value = "采购收货-修改扫码实收数量")
    @Log("采购收货-修改扫码实收数量")
    @Transactional
    public Result updatePurchaseReceiveRecord(@RequestBody PurchaseCountApprovalDto dto) {
        if (StrUtil.isBlank(dto.getId())) {
            return Result.fail().message("记录id不能为空");
        }
        PurchaseSummary p = purchaseSummaryMapper.selectPurchaseSummary(dto.getId());
        if (p == null) {
            return Result.fail().message("扫码记录不存在");
        }
        Integer statusCode = p.getStatusCode();
        if (statusCode != null && statusCode != 0) {
            return Result.fail().message("已提交记录不能修改");
        }
        BigDecimal newQty = dto.getChangeqty();
        if (newQty == null || newQty.compareTo(BigDecimal.ZERO) <= 0) {
            return Result.fail().message("数量必须大于0");
        }
        BigDecimal oldQty = p.getQty() != null ? p.getQty() : BigDecimal.ZERO;
        if (newQty.compareTo(oldQty) == 0) {
            return Result.ok().message("修改成功");
        }
        DeliverySummary deliverySummary = deliverySummaryMapper.selectDeliverySummaryByPurchaseId(dto.getId());
        if (deliverySummary == null) {
            return Result.fail().message("送货单身记录不存在");
        }
        BigDecimal currentMatchQty = deliverySummary.getMatchQty() != null ? deliverySummary.getMatchQty() : BigDecimal.ZERO;
        BigDecimal deliveryNum = deliverySummary.getDeliveryNum() != null ? deliverySummary.getDeliveryNum() : BigDecimal.ZERO;
        BigDecimal newMatchQty = currentMatchQty.subtract(oldQty).add(newQty);
        if (newMatchQty.compareTo(deliveryNum) > 0) {
            return Result.fail().message("收货数量已满");
        }
        int d = deliverySummaryMapper.updateQty(newMatchQty, dto.getId());
        if (d <= 0) {
            throw new RuntimeException("送货单身中间表匹配数更新失败");
        }
        p.setQty(newQty);
        if (!purchaseSummaryService.updateById(p)) {
            throw new RuntimeException("扫码记录更新失败");
        }
        return Result.ok().message("修改成功");
    }


//    @Log("采购管理-->生成到货单")
    @ApiOperation("采购收货-提交-维护采购订单(已审核)->->生成维护采购到货单(已审核)")
    @Log("采购收货-提交-维护采购订单(已审核)->->生成维护采购到货单(已审核)")
    @PostMapping("/PurchaseReceiptStorage")
    public Result PurchaseReceiptStorage(@RequestBody PurchaseReceiptDto Dto) {
        // 1. 判断提交标签有无重复
        Set<String> barcodes = new HashSet<>();
        List<String> duplicates = new ArrayList<>();
        String loguuid = UUID.randomUUID().toString();
        for (PurchaseReceiptStorageDto dto : Dto.getList()) {
            if (dto.getBarcode() != null) {
                log.info(loguuid + " 前端传入条码：" + dto.getBarcode() + ".");
                if (!barcodes.add(dto.getBarcode())) {
                    duplicates.add(dto.getBarcode());
                }
            }
        }

        if (!duplicates.isEmpty()) {
            return Result.fail("发现重复的条码: " + String.join(", ", duplicates), "发现重复的条码: " + String.join(", ", duplicates));
        }


        // 2. 判断提交标签的送货单单头uuid是否一致
//        if(purchaseMapper.selectDistinct(barcodes)>1){
//            return Result.fail("不同送货单条码不能开到货", "不同送货单条码不能开到货");
//        }

        //1、提交e10
        //同步中间表
        MiddleReturnDto middleReturnDto = purchaseService.PurchaseReceiptStorageInsertMiddleTable(Dto.getList());
        JSONObject jsonObject = purchaseTwoService.PurchaseReceiptStorageSubmit(middleReturnDto);
        log.info("e10返回数据：" + jsonObject);
        System.out.println(jsonObject);
        // 获取execution节点
        JSONObject execution = jsonObject
                .getJSONObject("payload")
                .getJSONObject("std_data")
                .getJSONObject("execution");

        // 提取code和description字段
        String executionCode = execution.getString("code");
        String description = execution.getString("description");

        if (!"0".equals(executionCode)) {
            return Result.fail(jsonObject).message(description);
        }

        String docNo = jsonObject
                .getJSONObject("payload")
                .getJSONObject("std_data")
                .getJSONObject("parameter")
                .getJSONArray("als_app_request_result")
                .getJSONObject(0)
                .getString("doc_no");

        log.info(loguuid + " 到货单单号：" + docNo);
        //2、回写到货单号、回写送货单头uuid
        //收集dto中的采购单单号
        List<String> dtodocNoList = new ArrayList<>();
//        List<Map<String, String>> barcodeUpdateList = new ArrayList<>();
        List<Map<String, String>> deliveryUpdateList = new ArrayList<>();
        // 容器码去重
        Set<String> barcodeUpdateSet = new HashSet<>();
        for (PurchaseReceiptStorageDto dto : Dto.getList()) {
            //子条码查容器码
            String containerBarcode = purchaseMapper.getContainerBarcodeByChildBarcode(dto.getBarcode());
            barcodeUpdateSet.add(dto.getBarcode());
            if (containerBarcode != null && containerBarcode.trim().length() > 0) {
                barcodeUpdateSet.add(containerBarcode);
            }
            //采购单列表
            dtodocNoList.add(dto.getDocNo());
        }
        // 直接更新条码状态为已到货
        for(String barcode : barcodeUpdateSet) {
            log.info(loguuid + " 条码：" + barcode + " 状态更新为已到货");
            purchaseMapper.updateLotatt30To1AndLot_att04(barcode, docNo);
        }
        //批量查询采购单号对应的 送货单头uuid
        if (!dtodocNoList.isEmpty()) {
            List<String> uuidResultList = purchaseMapper.getDeliveryUuidsByDocNos(dtodocNoList);

            for (String uuid : uuidResultList) {

                Map<String, String> deliveryMap = new HashMap<>();
                deliveryMap.put("docNo", docNo);
                deliveryMap.put("uuid", uuid);
                deliveryUpdateList.add(deliveryMap);
            }
        }


        if (!deliveryUpdateList.isEmpty()) {
            //更新deliveryList 到货单单号
            purchaseMapper.batchUpdateDeliveryUuid(deliveryUpdateList);
        }
//        //回写检验单单号：到货单单号+到货单单身序号+品号绑定检验单号
//        purchaseMapper.selectPoArrivalInspectionDocNo(docNo,)


        //3.删除对应条码操作档记录
        Dto.getInvBarcodeOperationIdList().forEach(id -> {
            purchaseSummaryMapper.deleteById(id);
        });
        //删除对应送货单身中间表
        QueryWrapper<DeliverySummary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator", Dto.getCreateBy());
        deliverySummaryMapper.delete(queryWrapper);


        //4、企业微信推送
        log.info(jsonObject.toJSONString());
        //根据到货单查询对应待检信息
        //单头
        WaitToInspectedVo head = purchaseMapper.getWaitToInspected(docNo);
        //单身
        List<PurchaseArrivalDVo> body = purchaseMapper.getWaitToInspectedD(docNo);
        //获取仓库编号唯一列表
        List<String> warehouseList = body.stream().map(PurchaseArrivalDVo::getWarehouseCode).distinct().collect(Collectors.toList());
        //根据仓库列表查对应检验员
        if (warehouseList != null) {
            List<String> qcList = purchaseMapper.getInspectorByWarehouse(warehouseList);
            if(qcList == null){
                return Result.fail().message("未查询到检验员企微账号");
            }
            //去除数组中的null值
            qcList = qcList.stream().filter(Objects::nonNull).collect(Collectors.toList());
            MessageDto messageDto = new MessageDto();
            StringBuilder content = new StringBuilder();
            // 拼接头部信息
            content.append("供应商：").append(head.getSupplierName())
                    .append(" , 到货单单号：").append(head.getDeliveryNo())
                    .append("，到货日期：").append(head.getDeliveryDate()).append("\n");

            // 拼接到货明细信息
            content.append("到货明细: ");
            for (int i = 0; i < body.size(); i++) {
                PurchaseArrivalDVo detail = body.get(i);
                content.append(detail.getXh()).append("、 客户单号：").append(detail.getCustomerNo())
                        .append("，待检验物料：").append(detail.getItemCode()).append("-").append(detail.getItemName());
                if (i < body.size() - 1) {
                    content.append("\n               ");
                }
            }
            qcList.add("ZouJinLin");
            messageDto.setContent(content.toString());
            messageDto.setTouser(qcList);
            weChatService.sendWXMessage(messageDto);
        }

        return Result.ok(jsonObject);
    }


    @ApiOperation("采购收货-生成到货单-E10标准接口")
    @Log("采购收货-生成到货单-E10标准接口")
    @Debounce // 防抖 默认3秒
    @PostMapping("/PurchaseReceipts")
    public Result PurchaseReceipts(@RequestBody PurchaseReceiptDto Dto) {
        String createBy = Dto.getCreateBy();
        if(StrUtil.isBlank(createBy)){
            return Result.fail().message("操作人不能为空!");
        }
        // 1. 判断提交标签有无重复
        Set<String> barcodes = new HashSet<>();
        List<String> duplicates = new ArrayList<>();
        String loguuid = UUID.randomUUID().toString();
        for (PurchaseReceiptStorageDto dto : Dto.getList()) {
            if (dto.getBarcode() != null) {
                log.info(loguuid + " 前端传入条码：" + dto.getBarcode() + ".");
                if (!barcodes.add(dto.getBarcode())) {
                    duplicates.add(dto.getBarcode());
                }
            }
        }
        List<PurchaseReceiptStorageDto> dtoList = Dto.getList();
        if (dtoList == null || dtoList.isEmpty()) {
            return Result.fail("条码不能为空");
        }

        // key=barcode，value=合并后的 DTO
        Map<String, PurchaseReceiptStorageDto> grouped = dtoList.stream()
                .collect(Collectors.toMap(
                        PurchaseReceiptStorageDto::getDocNo,
                        dto -> {
                            // 先拷贝一份，避免原对象被修改
                            PurchaseReceiptStorageDto newDto = new PurchaseReceiptStorageDto();
                            BeanUtils.copyProperties(dto, newDto);
                            return newDto;
                        },
                        (a, b) -> {
                            // 合并逻辑：matchQty 相加
                            a.setMatchQty(a.getMatchQty().add(b.getMatchQty()));
                            a.setRejectQty(a.getRejectQty().add(b.getRejectQty()));
                            a.setScrapQty(a.getScrapQty().add(b.getScrapQty()));
                            return a;
                        }
                ));

        // 转为 List
        List<PurchaseReceiptStorageDto> newList = new ArrayList<>(grouped.values());

        if (!duplicates.isEmpty()) {
            return Result.fail("发现重复的条码: " + String.join(", ", duplicates), "发现重复的条码: " + String.join(", ", duplicates));
        }

        PurchaseArrivalEntity.PurchaseArrival arrival = new PurchaseArrivalEntity.PurchaseArrival();
        /* 1. 构造主表 */
        String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        PurchaseReceiptStorageDto first = newList.get(0);
        String firstDocNoDto = first.getDocNo();
        String[] split = firstDocNoDto.split("-");
//        String firstOrderNum = purchaseMapper.getPurchaseDByBarcode(first.getBarcode()); // 采购单序号
        PurchaseInfo firstPurchaseInfo = purchaseMapper.queryPurchaseInfo(split[0] + "-" + split[1], split[2]);
        String arrivalDocNo = firstPurchaseInfo.getArrivalOrderCode() + "-" + today + "0001"; // 到货单号
        arrival.setDOC_NO(arrivalDocNo);
        arrival.setDOC_ID_DOC_CODE(firstPurchaseInfo.getArrivalOrderCode()); // 到货单单据编号
//        arrival.setCATEGORY(firstPurchaseInfo.getArrivalOrderCategory()); // 到货单性质 刚哥触发器
        arrival.setPLANT_ID_PLANT_CODE(firstPurchaseInfo.getSupplyCenterCode()); // 工厂编号
        arrival.setPAYMENT_TERM_ID_PAYMENT_TERM_TYPE(firstPurchaseInfo.getPaymentType()); // 付款条件.类别
        arrival.setPAYMENT_TERM_ID_PAYMENT_TERM_CODE(firstPurchaseInfo.getPaymentCode()); // 付款条件.编号
        arrival.setSUPPLIER_ID_SUPPLIER_CODE(firstPurchaseInfo.getSupplierCode()); // 供应商编号
        arrival.setTAX_BC(firstPurchaseInfo.getTaxBc()); // 本币税额
        arrival.setTAX_ID_TAX_CODE(firstPurchaseInfo.getTaxCode());//税种.税种编号
        arrival.setTAX_INCLUDED(true); // 含税
        arrival.setTAX_OC(firstPurchaseInfo.getTaxOc()); // 原币税额
        arrival.setOwner_Org_SUPPLY_CENTER_SUPPLY_CENTER_CODE(firstPurchaseInfo.getSupplyCenterCode()); // 组织类型.采购域.采购域编号
        arrival.setRECEIVE_Owner_Org_PLANT_PLANT_CODE(firstPurchaseInfo.getPlantCode()); // 收货机构.工厂/储运.工厂/储运编号
        // 区分3600:正常到货  3601:委外到货
        arrival.setDOC_ID_Owner_Org_SUPPLY_CENTER_SUPPLY_CENTER_CODE(firstPurchaseInfo.getSupplyCenterCode()); // 组织类型.采购域.采购域编号
        arrival.setDOC_DATE(LocalDateTime.now()); // 订单时间
        arrival.setCURRENCY_ID_CURRENCY_CODE(firstPurchaseInfo.getCurrencyCode()); // 币种编号
        arrival.setINVOICE_COMPANY_ID_COMPANY_CODE("1"); // 结算公司.公司编号 固定为 1
        arrival.setINVOICE_SUPPLIER_ID_SUPPLIER_CODE(firstPurchaseInfo.getSupplierCode()); // 供应商编号
        arrival.setSUPPLIER_FULL_NAME(firstPurchaseInfo.getSupplierFullName().trim()); // 供应商全称
        arrival.setEXCHANGE_RATE(firstPurchaseInfo.getExchangeRate()); // 汇率
//        arrival.setSOURCE_ID_MO_DOC_NO(firstPurchaseInfo.getMoDoc()); // 工单号
//        arrival.setSOURCE_ID_MO_MO_D_SequenceNumber(firstPurchaseInfo.getMoDocNum()); // 工单序号
        // 单头备注填写提交用户的名字
        arrival.setREMARK("WMS-" + Dto.getCreateBy());
        arrival.setOwner_Dept_ADMIN_UNIT_CODE(firstPurchaseInfo.getAdminUnitCode()); // 关联部门.行政单元编码
        arrival.setOwner_Dept_START_DATE(firstPurchaseInfo.getStartDate()); // 关联部门.生效日期
        arrival.setOwner_Emp_EMPLOYEE_CODE(firstPurchaseInfo.getEmployeeCode()); // 关联员工.员工编号
        arrival.setDEDUCTIBLE_INDICATOR("1".equals(firstPurchaseInfo.getVatDeductible())); // VAT抵扣标志
        arrival.setTAX_INVOICE_CATEGORY_ID_TAX_INVOICE_CATEGORY_CODE(firstPurchaseInfo.getTaxInvoiceCategoryCode()); // 发票编号
        List<PurchaseArrivalEntity.PurchaseArrivalD> dList = new ArrayList<>();
        int sequenceNumber = 1;
        String warehouseCode = newList.get(0).getWarehouseCode();
        String EMPLOYEE_CODE = purchaseMapper.queryQcUserByWarehouseCode(warehouseCode);
        if (EMPLOYEE_CODE == null) {
            EMPLOYEE_CODE = "1".equals(firstPurchaseInfo.getSupplyCenterCode()) ? "240228001" : "2110230";
        }
        StringBuilder exemptItem = new StringBuilder();
        for (PurchaseReceiptStorageDto dto : newList) {
            String docNoDto = dto.getDocNo();
            String[] splitd = docNoDto.split("-");
//            String orderNum = purchaseMapper.getPurchaseDByBarcode(dto.getBarcode()); // 获取采购单序号
            String lotNumber = purchaseMapper.selectLotNumber(dto.getBarcode()); // 批号
            PurchaseInfo purchaseInfo = purchaseMapper.queryPurchaseInfo(splitd[0] + "-" + splitd[1], splitd[2]);
            if (purchaseInfo == null) {
                return Result.fail("采购单不存在", "采购单不存在");
            }
            if ("1".equals(purchaseInfo.getInspectMode())){
                if (exemptItem.length() > 0) {
                    exemptItem.append("、");
                }
                exemptItem.append(dto.getItemCode());
                continue;
            }
            // 构造 PurchaseArrivalD（明细）
            PurchaseArrivalEntity.PurchaseArrivalD d = new PurchaseArrivalEntity.PurchaseArrivalD();
            // 检验状态
            d.setINSPECTION_STATUS("1".equals(purchaseInfo.getInspectMode()) ? "1" : "2"); // 检验状态
            d.setSequenceNumber(sequenceNumber); // 序号
            d.setBUSINESS_QTY(dto.getMatchQty()); //异动数量
            d.setBUSINESS_UNIT_ID_UNIT_CODE(purchaseInfo.getBusinessUnitCode()); // 业务单位
            d.setPRICE_UNIT_ID_UNIT_CODE(purchaseInfo.getPriceUnitCode()); // 计价单位.单位编号
            d.setDISCOUNTED_PRICE(purchaseInfo.getDiscountedPrice()); // 折扣价格

            // 先全部安全地拿到 BigDecimal，null → ZERO
            BigDecimal inventoryQty = Optional.ofNullable(purchaseInfo.getInventoryQty())
                    .orElse(BigDecimal.ZERO);
            BigDecimal businessQty  = Optional.ofNullable(purchaseInfo.getBusinessQty())
                    .orElse(BigDecimal.ZERO);
            BigDecimal matchQty =
                    Optional.ofNullable(dto.getMatchQty())
                            .orElse(BigDecimal.ZERO);
            // 当除数为 0 时直接给 0，避免 ArithmeticException
            BigDecimal inventoryQtyPerBusinessQty = businessQty.compareTo(BigDecimal.ZERO) == 0
                    ? BigDecimal.ZERO
                    : inventoryQty.divide(businessQty, 10, RoundingMode.HALF_UP)
                    .multiply(matchQty);
            d.setINVENTORY_QTY(inventoryQtyPerBusinessQty); // 库存单位数量

//            d.setGENERATE_SOURCE("PO"); // 单据来源
            d.setITEM_DESCRIPTION(purchaseInfo.getItemName()); // 品名
            d.setITEM_ID_ITEM_CODE(purchaseInfo.getItemCode()); // 品号
//            d.setITEM_LOT_ID_ITEM_ID_ITEM_CODE("R0805-10K-1%");
//            d.setITEM_LOT_ID_ITEM_FEATURE_ID_ITEM_FEATURE_CODE("0805");
//            d.setITEM_LOT_ID_ITEM_FEATURE_ID_Parent_ITEM_CODE("R0805");
            d.setITEM_LOT_ID_LOT_CODE(lotNumber); // 批号
            d.setITEM_SPECIFICATION(purchaseInfo.getItemSpec()); // 规格
            d.setITEM_TYPE(purchaseInfo.getItemType()); // 商品类型
            d.setPRICE(purchaseInfo.getPrice()); //单价
            d.setPURCHASE_ORDER_ID_DOC_NO(splitd[0] + "-" + splitd[1]);//采购订单ID.单号
            d.setPURCHASE_TYPE(purchaseInfo.getPurchaseType());//采购类型
            d.setSTANDARD_PRICE(purchaseInfo.getStandardPrice());//标准单价
            d.setTAX_ID_TAX_CODE(purchaseInfo.getTaxCode());//税种.税种编号
            d.setTAX_RATE(purchaseInfo.getTaxRate());//税率
            d.setWAREHOUSE_ID_WAREHOUSE_CODE(dto.getWarehouseCode()); // 仓库编号
            d.setWAREHOUSE_ID_Owner_Org_PLANT_PLANT_CODE(purchaseInfo.getPlantCode()); // 收货机构.工厂/储运.工厂/储运编号
            d.setSOURCE_ID_PURCHASE_ORDER_PURCHASE_ORDER_D_PURCHASE_ORDER_SD_SequenceNumber(1); // SD 序号 固定为1
            d.setSOURCE_ID_PURCHASE_ORDER_PURCHASE_ORDER_D_PURCHASE_ORDER_SD_Parent_SequenceNumber(Integer.valueOf(splitd[2])); // D 序号
            d.setSOURCE_ID_PURCHASE_ORDER_PURCHASE_ORDER_D_PURCHASE_ORDER_SD_Parent_Parent_DOC_NO(splitd[0] + "-" + splitd[1]); // 采购单
//            d.setPRICE(BigDecimal.ZERO); //计价数量
            if (StrUtil.isNotBlank(purchaseInfo.getMoDoc())) {
                d.setREFERENCE_SOURCE_ID_MO_DOC_NO(purchaseInfo.getMoDoc()); // 工单号
                d.setREFERENCE_SOURCE_ID_RTK("MO"); // 工单号
                d.setMO_ID_DOC_NO(purchaseInfo.getMoDoc()); // 工单号
//                d.setSOURCE_ID_MO_MO_D_Parent_DOC_NO(purchaseInfo.getMoDoc()); // 工单号
            }
//            if (StrUtil.isNotBlank(purchaseInfo.getMoDocNum())) {
//                d.setSOURCE_ID_MO_MO_D_SequenceNumber(purchaseInfo.getMoDocNum()); // 工单序号
//            }
            dList.add(d);
            sequenceNumber++;
        }
        arrival.setPURCHASE_ARRIVAL_D(dList);
        // 封装为 StdData
        PurchaseArrivalEntity.StdData stdData = new PurchaseArrivalEntity.StdData();
        PurchaseArrivalEntity.Parameter parameter = new PurchaseArrivalEntity.Parameter();
        parameter.setDatas(Collections.singletonList(arrival));
        stdData.setParameter(parameter);

        // 序列化为 JSON 并打印
        String json = PurchaseArrivalEntity.toJson(stdData);
        log.warn("生成到货单请求参数：\n{}", json);
        E10ApiService e10ApiService = new E10ApiService();
        // 创建到货单
        JSONObject createArrivalResult = e10ApiService.createArrival(json);
//        JSONObject createArrivalResult = null;
        log.warn("生成到货单接口响应：\n{}", createArrivalResult);
        JSONObject createArrivalJsonObject = createArrivalResult
                .getJSONObject("std_data")
                .getJSONObject("parameter")
                .getJSONObject("result");
        JSONArray createJsonArray = createArrivalJsonObject.getJSONArray("error");
        if (createJsonArray != null && !createJsonArray.isEmpty()) {
            String errorMsg = createJsonArray.getJSONObject(0).getString("message");
            return Result.fail().message("生成到货单失败！\n" + errorMsg);
        }

        String docNo = createArrivalResult
                .getJSONObject("std_data")
                .getJSONObject("parameter")
                .getJSONObject("result")
                .getJSONArray("success")
                .getJSONObject(0)
                .getString("DOC_NO");
        log.warn("到货单生成成功-单号：{}", docNo);
        // 审核到货单
        JSONObject confirmArrivalJson = e10ApiService.confirmArrival(docNo);
        log.warn("审核到货单接口响应：\n{}", confirmArrivalJson);
        JSONObject confirmArrivalJsonObject = confirmArrivalJson
                .getJSONObject("std_data")
                .getJSONObject("parameter")
                .getJSONObject("result");
        JSONArray confirmJsonArray = confirmArrivalJsonObject.getJSONArray("error");
        if (confirmJsonArray != null && !confirmJsonArray.isEmpty()) {
            String errorMsg = confirmJsonArray.getJSONObject(0).getString("message");
            log.warn("审核失败删除到货单：\n{}", docNo);
            JSONObject deleteArrival = e10ApiService.deleteArrival(docNo);
            log.warn("删除到货单接口响应：\n{}", deleteArrival);
            return Result.fail().message("到货单审核失败！\n" + errorMsg);
        }
        //更新到货单的创建人和创建实际
        purchaseMapper.updateCreateByDocNo(docNo, createBy);
//        String qcOrderCategory = purchaseMapper.queryCategoryByArrivalOrderCode()
        // 创建到货质检单
        for (PurchaseArrivalEntity.PurchaseArrivalD d : dList) {
            String plantCode = d.getWAREHOUSE_ID_Owner_Org_PLANT_PLANT_CODE();
//            String docIdDocCode = arrival.getDOC_ID_DOC_CODE();
            String qcCategory = "1".equals(d.getPURCHASE_TYPE()) ? "Q1" : "Q2"; // 质检类别
            String qcDocNo = qcCategory + ("1".equals(plantCode) ? "00" : "50");
            QcInspectionOrder qcInspectionOrder = new QcInspectionOrder(); // 生成到货质检
            qcInspectionOrder.setDOC_NO(qcDocNo + "-" + today + "0001");  // 质检单号
            qcInspectionOrder.setCATEGORY(qcCategory);        // 单据性质
            qcInspectionOrder.setQC_TIMES(1);                 // 检验次数
            qcInspectionOrder.setOwner_Org_PLANT_PLANT_CODE(d.getWAREHOUSE_ID_Owner_Org_PLANT_PLANT_CODE());      // 组织类型.工厂/储运.工厂/储运编号
            qcInspectionOrder.setINSPECTION_DATE(LocalDate.now());               // 检验日期
            qcInspectionOrder.setSOURCE_ID_SequenceNumber(d.getSequenceNumber());         // 源单.序号
            qcInspectionOrder.setSOURCE_ID_Parent_DOC_NO(docNo);         // 源单.到货单.单号
            qcInspectionOrder.setOwner_Dept_ADMIN_UNIT_CODE("QPG");      // 关联部门.行政单元编码
            qcInspectionOrder.setOwner_Dept_START_DATE("2016-05-31 00:00:00.0000000");         // 关联部门.生效日期
            qcInspectionOrder.setDEDUCT_ARRIVED_QTY(true); // 扣已到货量 默认勾选
            qcInspectionOrder.setOwner_Emp_EMPLOYEE_CODE(EMPLOYEE_CODE);         // 关联员工.员工编号
            qcInspectionOrder.setDOC_DATE(LocalDateTime.now());              // 单据日期
            qcInspectionOrder.setDOC_ID_DOC_CODE(qcDocNo);                 // 单据类型.单据类型
            qcInspectionOrder.setREMARK("WMS-" + Dto.getCreateBy());        // 备注
            qcInspectionOrder.setDOC_ID_Owner_Org_PLANT_PLANT_CODE(d.getWAREHOUSE_ID_Owner_Org_PLANT_PLANT_CODE()); // 单据类型.组织类型.工厂/储运.工厂/储运编号
//            qcInspectionOrder.setACCEPTABLE_QTY(null);                // 合格数量
//            qcInspectionOrder.setDECISION(null);                        // 判定结果
//            qcInspectionOrder.setDECISION_DESCRIPTION(null);            // 判定说明
//            qcInspectionOrder.setDESTROYED_QTY(null);                 // 破坏数量
//            qcInspectionOrder.setINSPECTION_TIMES(1);                 // 最大抽样次数
            qcInspectionOrder.setINSPECTION_UNIT_ID_UNIT_CODE(d.getBUSINESS_UNIT_ID_UNIT_CODE());    // 品管单位.单位编号
            qcInspectionOrder.setINVENTORY_QTY(d.getINVENTORY_QTY());                 // 库存单位数量
            qcInspectionOrder.setITEM_DESCRIPTION(d.getITEM_DESCRIPTION());                // 品名
            qcInspectionOrder.setITEM_ID_ITEM_CODE(d.getITEM_ID_ITEM_CODE());               // 品号.品号
            qcInspectionOrder.setITEM_SPECIFICATION(d.getITEM_SPECIFICATION());              // 规格
            qcInspectionOrder.setRESULT_STATUS("1");                   // 业务判定状态
            qcInspectionOrder.setSUPPLIER_ID_SUPPLIER_CODE(arrival.getSUPPLIER_ID_SUPPLIER_CODE());       // 供应商.供应商编号
//            qcInspectionOrder.setUDF021("");                          // 自定义字段6
            qcInspectionOrder.setINSPECTION_QTY(d.getBUSINESS_QTY());       // 送检数量
//            qcInspectionOrder.setINSPECTION_PLAN_ID_QC_CATEGORY_ID_QC_CATEGORY_CODE("111"); // 质检方案ID.品管类别编号.品管类别编号
//            qcInspectionOrder.setINSPECTION_PLAN_ID_Owner_Org_PLANT_PLANT_CODE("11");      // 质检方案ID.组织类型.工厂/储运.工厂/储运编号
//            qcInspectionOrder.setINSPECTION_PLAN_ID_ITEM_FEATURE_ID_ITEM_FEATURE_CODE("11"); // 质检方案ID.品号特征码.特征码
//            qcInspectionOrder.setINSPECTION_PLAN_ID_ITEM_FEATURE_ID_Parent_ITEM_CODE("11");  // 质检方案ID.品号特征码.通用品号信息.品号
//            qcInspectionOrder.setINSPECTION_PLAN_ID_OPERATION_ID_OPERATION_CODE("11");    // 质检方案ID.工艺.工艺编号
//            qcInspectionOrder.setINSPECTION_PLAN_ID_ITEM_ID_ITEM_CODE("111");              // 质检方案ID.品号.品号
//            qcInspectionOrder.setSUBMIT_DEPT_ID_ADMIN_UNIT_CODE("111");    // 送检部门.行政单元编码
//            qcInspectionOrder.setSUBMIT_DEPT_ID_START_DATE(LocalDate.now());       // 送检部门.生效日期
//            qcInspectionOrder.setSUBMIT_EMP_ID_EMPLOYEE_CODE("111");       // 送检人员.员工编号
//            qcInspectionOrder.setINSPECTION_DUE_DATE(LocalDate.now());             // 检验期限
            qcInspectionOrder.setCOMPANY_ID_COMPANY_CODE("1");           // 公司.公司编号
            String jsonBody = QcInspectionOrder.toJson(qcInspectionOrder);
            log.warn("创建检验单请求体：{}", jsonBody);
            JSONObject arrivalInspection = e10ApiService.createArrivalInspection(jsonBody);
            log.warn("创建检验单响应：{}", arrivalInspection);
            JSONArray successArray = arrivalInspection.getJSONObject("std_data")
                    .getJSONObject("parameter")
                    .getJSONObject("result")
                    .getJSONArray("success");
            if (successArray != null && !successArray.isEmpty()) {
                String docNo1 = successArray.getJSONObject(0).getString("DOC_NO");
                JSONObject jsonObject = e10ApiService.confirmArrivalInspection(docNo1, "1");
                log.warn("审核检验单响应：\n{}", jsonObject);
                JSONObject jsonObject1 = e10ApiService.disConfirmArrivalInspection(docNo1, "1");
                log.warn("撤审检验单响应：\n{}", jsonObject1);
            }
        }

        //2、回写到货单号、回写送货单头uuid
        //收集dto中的采购单单号
        List<String> dtodocNoList = new ArrayList<>();
//        List<Map<String, String>> barcodeUpdateList = new ArrayList<>();
        List<Map<String, String>> deliveryUpdateList = new ArrayList<>();
        // 容器码去重
        Set<String> barcodeUpdateSet = new HashSet<>();
        for (PurchaseReceiptStorageDto dto : Dto.getList()) {
            //子条码查容器码
            String containerBarcode = purchaseMapper.getContainerBarcodeByChildBarcode(dto.getBarcode());
            barcodeUpdateSet.add(dto.getBarcode());
            if (containerBarcode != null && containerBarcode.trim().length() > 0) {
                barcodeUpdateSet.add(containerBarcode);
            }
            //采购单列表
            dtodocNoList.add(dto.getDocNo());
        }
        // 直接更新条码状态为已到货
        for (String barcode : barcodeUpdateSet) {
            log.info(loguuid + " 条码：" + barcode + " 状态更新为已到货");
            purchaseMapper.updateLotatt30To1AndLot_att04(barcode, docNo);

        }
        //批量查询采购单号对应的 送货单头uuid
        if (!dtodocNoList.isEmpty()) {
            List<String> uuidResultList = purchaseMapper.getDeliveryUuidsByDocNos(dtodocNoList);

            for (String uuid : uuidResultList) {

                Map<String, String> deliveryMap = new HashMap<>();
                deliveryMap.put("docNo", docNo);
                deliveryMap.put("uuid", uuid);
                deliveryUpdateList.add(deliveryMap);
            }
        }


        if (!deliveryUpdateList.isEmpty()) {
            //更新deliveryList 到货单单号
            purchaseMapper.batchUpdateDeliveryUuid(deliveryUpdateList);
        }
//        //回写检验单单号：到货单单号+到货单单身序号+品号绑定检验单号
//        purchaseMapper.selectPoArrivalInspectionDocNo(docNo,)


        //3.删除对应条码操作档记录
        Dto.getInvBarcodeOperationIdList().forEach(id -> {
            purchaseSummaryMapper.deleteById(id);
        });
        //删除对应送货单身中间表
        QueryWrapper<DeliverySummary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator", Dto.getCreateBy());
        deliverySummaryMapper.delete(queryWrapper);


        //4、企业微信推送
        //根据到货单查询对应待检信息
        //单头
        WaitToInspectedVo head = purchaseMapper.getWaitToInspected(docNo);
        //单身
        List<PurchaseArrivalDVo> body = purchaseMapper.getWaitToInspectedD(docNo);
        //获取仓库编号唯一列表
        List<String> warehouseList = body.stream().map(PurchaseArrivalDVo::getWarehouseCode).distinct().collect(Collectors.toList());
        //根据仓库列表查对应检验员
        if (warehouseList != null) {
            List<String> qcList = purchaseMapper.getInspectorByWarehouse(warehouseList);
            if (qcList == null) {
                return Result.fail().message("未查询到检验员企微账号");
            }
            //去除数组中的null值
            qcList = qcList.stream().filter(Objects::nonNull).collect(Collectors.toList());
            MessageDto messageDto = new MessageDto();
            StringBuilder content = new StringBuilder();
            // 拼接头部信息
            content.append("供应商：").append(head.getSupplierName())
                    .append(" , 到货单单号：").append(head.getDeliveryNo())
                    .append("，到货日期：").append(head.getDeliveryDate()).append("\n");

            // 拼接到货明细信息
            content.append("到货明细: ");
            for (int i = 0; i < body.size(); i++) {
                PurchaseArrivalDVo detail = body.get(i);
                content.append(detail.getXh()).append("、 客户单号：").append(detail.getCustomerNo())
                        .append("，待检验物料：").append(detail.getItemCode()).append("-").append(detail.getItemName());
                if (i < body.size() - 1) {
                    content.append("\n               ");
                }
            }
            qcList.add("ZouJinLin");
            messageDto.setContent(content.toString());
            messageDto.setTouser(qcList);
            // 发企微消息
            weChatService.sendWXMessage(messageDto);
        }
        String message = "单号: " + docNo;
        if (exemptItem.length() > 0) {
            // 只有当有免检品号时，才加上 "品号:" 前缀
            message += "\n提交失败品号！！！！！:" + exemptItem.toString();
        }
        return Result.ok().message(message);
    }


    @ApiOperation("查询供应商")
    @PostMapping("/ListSupplierCode")
    public Result<PageInfo<Supplier>> ListSupplierCode(@RequestBody Supplier dto){
        return purchaseService.ListSupplierCode(dto);
    }

    @ApiOperation("到货检验-当天待检验的到货检验单头")
    @PostMapping("/ListPurchaseArrivalStorageWaitCheck")
    public Result<PageInfo<PurchaseArrivalCheckHeader>> ListPurchaseArrivalStorageWaitCheck(@RequestBody PurchaseArrivalStorageWaitCheckDto dto){
        return purchaseService.ListPurchaseArrivalStorageWaitCheck(dto);
    }


    @ApiOperation("到货检验-查询到货检验单头")
    @PostMapping("/ListPurchaseArrivalStorageCheck")
    public Result<PageInfo<PurchaseArrivalCheckHeader>> ListPurchaseArrivalStorageCheck(@RequestBody PurchaseArrivalStorageCheckDto dto){
        //查询该条码是否已到货可进行检验
        BmBarcodeDetail barcode = purchaseMapper.SelectBarcode(dto.getBarcode());
//        if(att30 == null || !att30.equals("1") || !att30.equals("2.1")){
        if(barcode == null){
            return Result.<PageInfo<PurchaseArrivalCheckHeader>>fail(null).message("条码不存在！");
        }
        String att30 = barcode.getLotAtt30();
        if(att30 == null || !att30.equals("1")){
            System.out.println("att30:" + att30);
//            return Result.<PageInfo<PurchaseArrivalCheckHeader>>fail(null).message("该条码未到货或已检验合格或已入库");
            return Result.<PageInfo<PurchaseArrivalCheckHeader>>fail(null).message("该条码未到货或已检验或已入库");
        }
        return  purchaseService.ListPurchaseArrivalStorageCheck(dto);
    }



    //    @Log("采购管理-->查到货检验单详情")
    @ApiOperation("到货检验-查询到货检验单详情")
    @PostMapping("/ListPurchaseArrivalStorageCheckAll/{checkDocNo}")
    public Result<List<PurchaseArrivalCheck>> ListPurchaseArrivalStorageCheckAll(@PathVariable("checkDocNo") String checkDocNo){
        return purchaseService.ListPurchaseArrivalStorageCheckAll(checkDocNo);
    }

    @Log("采购管理-->审核到货检验单")
    @ApiOperation("审核到货检验单")
    @PostMapping("/UpdatePurchaseArrivalStorageCheck")
    public Result UpdatePurchaseArrivalStorageCheck(@RequestBody PurchaseArrivalCheck dto){
        return purchaseService.UpdatePurchaseArrivalStorageCheck(dto);
    }


    @ApiOperation("采购入库-当天检验完成(未入库)的到货单头")
    @PostMapping("/getArrivalInfo")
    public Result<PageInfo<PRSVo>> getArrivalInfo(@RequestBody PRSDto dto){
        return purchaseService.getArrivalInfo(dto);
    }

    @ApiOperation("采购入库-当天检验完成(未入库)的到货单身")
    @PostMapping("/getArrivalInfoD")
    public Result<PageInfo<PRSDVo>> getArrivalInfoD(@RequestBody PRSDDto dto){
        return purchaseService.getArrivalInfoD(dto);
    }



    //    @Log("采购管理-->条码查询标签和到货单号")
    @ApiOperation("箱码查询标签和到货单号")
    @PostMapping("/getArrivalNo")
    public Result<?> getArrivalNo(@RequestBody PurcharseDeliveryDto dto){
        //1、根据送货单入库--查出未入库条码
        List<PurchaseReceiptStorageVo> deliveryBarcode =  purchaseMapper.getPurcharsedeliveryBarcode(dto.getNumber());
        if(!deliveryBarcode.isEmpty()){
//            for (PurchaseReceiptStorageVo delivery : deliveryBarcode) {
//                if ("2".equals(delivery.getLot_att30())) {
                    return Result.ok(deliveryBarcode);
//                }else{
//                    return Result.<List<PurchaseReceiptStorageVo>>fail(null).message("该送货单存在条码未检验");
//                }
            }

        //2、根据箱码入库
        //查询该条码是否已到货已检验可入库
        BmBarcodeDetail barcode = purchaseMapper.SelectBarcode(dto.getNumber());
        if(barcode == null){
            return Result.<List<PurchaseReceiptStorageVo>>fail(null).message("送货单存在箱码未检验或该箱码不存在");
        }
        String att30 =barcode.getLotAtt30();
        if( !att30.equals("2") || att30.equals("")){
            System.out.println("该箱码未检验或已入库");
            return Result.<List<PurchaseReceiptStorageVo>>fail(null).message("该箱码未检验或已入库");
        }
        if (barcode.getLotAtt08() != null && barcode.getLotAtt08().equals("2")) {
            return Result.fail().message("该箱码检验不合格！");
        }
        return purchaseService.getArrivalNo(dto);
//            return Result.<List<PurchaseReceiptStorageVo>>fail(null).message("该送货单存在条码未检验或已入库");

    }


    //    @Log("采购管理-->扫码入库-提交")
//    @ApiOperation("扫码入库-提交-维护采购订单(已审核)->维护到货单(已审核)->->生成维护采购入库单")
//    @PostMapping("/PurchaseScanToStorage")
//    @Log("扫码入库-提交-维护采购订单(已审核)->维护到货单(已审核)->->生成维护采购入库单")
//    public Result PurchaseScanToStorage(@RequestBody List<PurchaseToStorageDto> list) {
//
//
//        //同步中间表
//        MiddleReturnDto middleReturnDto = purchaseService.PurchaseScanToStorageInsertMiddleTable(list);
//        //提交e10
////            JSONObject result = purchaseTwoService.PurchaseScanToStorageSubmit(middleReturnDto);
//        JSONObject jsonObject = purchaseTwoService.PurchaseScanToStorageSubmit(middleReturnDto);
//
//        // 获取执行结果的 code
//        String executionCode
//                = jsonObject
//                .getJSONObject("payload")
//                .getJSONObject("std_data")
//                .getJSONObject("execution")
//                .getString("code");
//        if (!"0".equals(executionCode)) {
//            return Result.fail(jsonObject).message("提交失败");
//            }
//
//            for(PurchaseToStorageDto listDto : list){
//                int i = purchaseMapper.updateBarcodeWBU(listDto);
//            }
//        return Result.ok(jsonObject).message("提交成功");
//
//    }

    @ApiOperation("扫码入库-提交-维护采购订单(已审核)->维护到货单(已审核)->->生成维护采购入库单")
    @PostMapping("/PurchaseScanToStorage/{createBy}")
    @Log("扫码入库-提交-维护采购订单(已审核)->维护到货单(已审核)->->生成维护采购入库单")
    public Result PurchaseScanToStorage(@RequestBody List<PurchaseToStorageDto> list, @PathVariable("createBy") String createBy) {
        if (StrUtil.isBlank(createBy)) {
            return Result.fail().message("创建人不能为空");
        }
        // 按 docNo 分组
        Map<String, List<PurchaseToStorageDto>> groupedByDocNo = list.stream()
                .collect(Collectors.groupingBy(PurchaseToStorageDto::getDocNo));

        for (Map.Entry<String, List<PurchaseToStorageDto>> entry : groupedByDocNo.entrySet()) {
            List<PurchaseToStorageDto> groupList = entry.getValue();
            MiddleReturnDto middleReturnDto = purchaseService.PurchaseScanToStorageInsertMiddleTable(groupList, createBy);
            // 提交e10
            JSONObject jsonObject = purchaseTwoService.PurchaseScanToStorageSubmit(middleReturnDto, createBy);

            // 获取执行结果的 code
            String executionCode = jsonObject
                    .getJSONObject("payload")
                    .getJSONObject("std_data")
                    .getJSONObject("execution")
                    .getString("code");

            if (!"0".equals(executionCode)) {
                return Result.fail(jsonObject).message("提交失败");
            }

            // 更新条码
            for (PurchaseToStorageDto listDto : groupList) {
                int i = purchaseMapper.updateBarcodeWBU(listDto);
            }
        }

        return Result.ok().message("提交成功");
    }

//===================================================采购收货C==================================================//
    @ApiOperation(value = "采购收货--扫码送货单身记录")
    @PostMapping("/getDeliverySummaryC")
    @Log("采购收货C-扫码送货单身记录")
    public Result<List<DeliverySummaryVo>> getDeliverySummaryC(@RequestBody String creator) {
        List<DeliverySummary> list = deliverySummaryMapper.selectList(new QueryWrapper<DeliverySummary>().eq("creator", creator));
        // 按deliveryNumber分组并转换为DeliverySummaryVo列表
        List<DeliverySummaryVo> result = list.stream()
                .collect(Collectors.groupingBy(DeliverySummary::getDeliveryNumber))
                .values().stream()
                .map(items -> {
                    DeliverySummaryVo vo = new DeliverySummaryVo();
                    vo.setDeliverySummaryList(items);
                    return vo;
                })
                .collect(Collectors.toList());

        if(result != null && result.size() > 0) {
            // 1. 先找出每个送货单中，最大的时间
            for(DeliverySummaryVo t : result) {
                t.setCreateTime(t.getDeliverySummaryList().get(0).getCreateTime());
                for(int i = 1; i < t.getDeliverySummaryList().size(); i++) {
                    if(t.getCreateTime().compareTo(t.getDeliverySummaryList().get(i).getCreateTime()) < 0) {
                        t.setCreateTime(t.getDeliverySummaryList().get(i).getCreateTime());
                    }
                }
            }
            // 2.result数组排序
            result.sort(new Comparator<DeliverySummaryVo>() {
                @Override
                public int compare(DeliverySummaryVo o1, DeliverySummaryVo o2) {
                    return o2.getCreateTime().compareTo(o1.getCreateTime());
                }
            });
        }
        return Result.ok(result);
    }

    @PostMapping("/isScanningCodeRecordC")
    @ApiOperation(value = "判断是否有扫码记录")
    @Log("采购收获C-判断是否有扫码记录")
    public Result isScanningCodeRecordC(@RequestBody String createBy) {
        Integer count = purchaseMapper.isScanningCodeRecord(createBy);
        return Result.ok(count > 0);
    }
    @PostMapping("/resetDataC")
    @ApiOperation(value = "重置数据")
    @Log("采购到货C-重置数据")
    @Transactional
    public Result resetDataC(@RequestBody String createBy) {
        try {
            //删除delivery_summary
            QueryWrapper<DeliverySummary> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("creator", createBy);
            deliverySummaryMapper.delete(queryWrapper);
            //把对应条码的lot_att31置为null
            purchaseMapper.updateLotAtt31Null(createBy);
            //删除条码操作档 -> 条码状态：未提交 & 条码类型：采购收货
            purchaseMapper.deleteInvBarcodeOperationByCreateBy(createBy);
        } catch (Exception e) {
            throw new RuntimeException("重置数据失败:" + e.toString());
        }
        return Result.ok();
    }
    @PostMapping("/deleteDeliverySummaryC")
    @ApiOperation(value = "删除(送货单)扫码记录")
    @Log("送货单C-删除(送货单)扫码记录")
    @Transactional
    public Result deleteDeliverySummaryC(@RequestBody String deliveryNumer) {
        //判断有无对应的扫码记录
        int count = deliverySummaryMapper.isDeliverySummaryRecord(deliveryNumer);
        if (count>0) {
            return Result.fail().message("有对应扫码记录，不能删除");
        }
        //删除扫码记录
        int b = deliverySummaryMapper.deleteByDeliveryNumer(deliveryNumer);
        if (b<=0) {
            throw new RuntimeException("条码操作档删除失败:");
        }
        return Result.ok().message("删除成功");

    }

    @ApiOperation("采购收货C--前置查询(送货单号查采购到货提交需数据)")
    @Log("采购收货C--前置查询(送货单号查采购到货提交需数据)")
    @PostMapping("/getPurcharseDeliveryNoC")
    @DSTransactional
    public Result<List<PurchaseReceiptStorageVo>> getPurcharsedeliveryNoC(@RequestBody PurcharseDeliveryDto dto){
        if(dto.getCreateBy()==null || dto.getCreateBy().isEmpty() || dto.getCreateBy() == ""){
            throw new RuntimeException("创建人不能为空");
        }

        //查delivery_summary(送货单号)
        List<DeliverySummary> list = deliverySummaryMapper.deliveryIsExist(dto.getNumber());
        if(list != null && list.size() > 0){
            StringBuffer stringBuffer = new StringBuffer("送货单号已存在,或被");
            stringBuffer.append(list.get(0).getCreator()).append("已扫码。");
            throw new RuntimeException(stringBuffer.toString());
        }


        List<PurchaseReceiptStorageVo> purcharsedeliveryNo = new ArrayList<>();

        //根据送货单号查送货单身
        List<DeliverySummary> deliveryDetails = purchaseMapper.getByDeliveryNoC(dto);
        if(!deliveryDetails.isEmpty()){
            for (DeliverySummary deliveryDetail : deliveryDetails) {
                deliveryDetail.setCreator(dto.getCreateBy());
            }
            deliverySummaryService.saveBatch(deliveryDetails);
            return Result.ok(purcharsedeliveryNo).message("送货单身扫码成功");
        }


        // 2.根据条码到货
        purcharsedeliveryNo = purchaseService.getPurcharseByBarcode(dto);
        if(!purcharsedeliveryNo.isEmpty()) {
            for (PurchaseReceiptStorageVo vo : purcharsedeliveryNo) {

                //扫码判断条码号是否属于已扫的送货单
                if(purchaseMapper.selectIsClude(vo.getBarcode(),dto.getCreateBy()) == 0){
                    throw new RuntimeException("条码号不属于已扫的送货单");
                }

                //查收货操作表purchase_summary(登录人和箱码)
                if(purchaseSummaryMapper.bacodeisExist(dto.getCreateBy(),vo.getBarcode())>0){
                    throw new RuntimeException("条码已存在");
                }
                //查条码对应的delivery_summary记录
                DeliverySummary d = deliverySummaryMapper.getDeliverySummary(vo.getBarcode());

                // 本次分配的数量
                BigDecimal allocateQty = vo.getMatchQty();
                if (d.getMatchQty().add(allocateQty).compareTo(d.getDeliveryNum()) > 0) {
                    throw new RuntimeException("收货数量已满");
                }
                d.setMatchQty(d.getMatchQty().add(allocateQty));
                //更新匹配数
                deliverySummaryService.updateById(d);
                //更新条码状态(null未匹配，1已匹配)
                //                purchaseMapper.updatelotAtt31(vo.getBarcode());


                String id = UUID.randomUUID().toString();
                vo.setId(id);
                // 2.记录条码操作记录 变化数量
                PurchaseSummary purchaseSummary = new PurchaseSummary();
                purchaseSummary.setId(id); // UUID
                purchaseSummary.setLotAtt01(vo.getParentId());//送货单头uuid
                purchaseSummary.setLotAtt02(vo.getArrivalNo());//采购到货单号
                purchaseSummary.setLotAtt03(vo.getDeliveryNumber());//送货单号
                purchaseSummary.setLotAtt04(vo.getDocNo());//采购订单号
                purchaseSummary.setLotAtt05(vo.getSupplierNo());//供应商编号
                purchaseSummary.setLotAtt06(vo.getSupplierName());//供应商名称
                purchaseSummary.setLotAtt07(vo.getCustomerNo());//客户编号
                purchaseSummary.setStandardCol11(vo.getContainerQty());//标准收容数量
                purchaseSummary.setLotAtt08(vo.getContainerName());//收容器具
                purchaseSummary.setLotAtt09(vo.getContainerDetail());//容器具尺寸
                purchaseSummary.setLotAtt10(vo.getStandard_col10());//容器码uuid
                purchaseSummary.setLotAtt11(vo.getBar_type());//条码类型
                purchaseSummary.setBarcode(vo.getBarcode()); // 条码编号
                purchaseSummary.setOrgNo(vo.getShjg()); // 收货机构
                purchaseSummary.setQty(vo.getMatchQty()); // 数量
                purchaseSummary.setWarehouseNo(vo.getWarehouseCode()); // 仓库编号
                purchaseSummary.setCellNo(vo.getBinCode()); // 库位编号
                purchaseSummary.setCombinationLotNo(vo.getDocDate()); // 批号 / 日期
                purchaseSummary.setItemNo(vo.getItemCode()); // 品号
                purchaseSummary.setItemName(vo.getItemName()); // 品名
                purchaseSummary.setItemSpec(vo.getItemSpec()); // 规格
                purchaseSummary.setUnitNo(vo.getUnitCode()); // 单位编号
                purchaseSummary.setUnitName(vo.getUnitName());//单位名称
                purchaseSummary.setChagType(100); // 100.采购收货
                purchaseSummary.setStatusCode(0); // 0:未提交 1:已提交
                purchaseSummary.setCreateDate(DateTime.now().toString()); // 创建时间
                purchaseSummary.setCreateBy(dto.getCreateBy()); // 操作人
                boolean b = purchaseSummaryService.save(purchaseSummary);
                if (!b) {
                    return Result.fail(purcharsedeliveryNo).message("扫码记录保存失败");
                }
            }
        }
        return Result.ok(purcharsedeliveryNo);
    }
    @PostMapping("/getInvBarcodeOperationC")
    @ApiOperation(value = "获取已扫描条码记录")
    public Result<List<PurchaseReceiptStorageVo>> getInvBarcodeOperationC(@RequestBody PurchaseReceiptStorageDtoOne dto) {
        List<PurchaseReceiptStorageVo> list = purchaseMapper.getInvBarcodeOperationVo(dto);
        return Result.ok(list);
    }

    @PostMapping("/deleteRecordC")
    @ApiOperation(value = "删除扫码记录")
    @Log("采购到货单C-删除扫码记录")
    @Transactional
    public Result deleteC(@RequestBody String id) {
        //获取条码记录
        PurchaseSummary p =purchaseSummaryMapper.selectPurchaseSummary(id);
        if (p == null) {
            return Result.fail().message("条码不存在");
        }
        //标签对应的实收数量
        BigDecimal matchQty =deliverySummaryMapper.selectQty(id);
        //扣除delivery_summary的匹配数量
        BigDecimal qty = matchQty.subtract(p.getQty());
        int d = deliverySummaryMapper.updateQty(qty,id);
        if (d<=0) {
            throw new RuntimeException("送货单身中间表匹配数更新失败");
        }

        //把对应条码的lot_att31置为null
        int l = purchaseMapper.updateLotAtt31NullSingle(id);
        if (l<=0) {
            throw new RuntimeException("条码状态更新失败");
        }

        //删除扫码记录
        Boolean b = purchaseMapper.deleteBarCodeOperation(id);
        if (!b) {
            throw new RuntimeException("条码操作档删除失败");
        }

        return Result.ok().message("删除成功");

    }

    @ApiOperation("采购收货C-生成到货单-E10标准接口")
    @Log("采购收货C-生成到货单-E10标准接口")
    @PostMapping("/PurchaseReceiptsC")
    public Result PurchaseReceiptsC(@RequestBody PurchaseReceiptDto Dto) {
        // 1. 判断提交标签有无重复
        Set<String> barcodes = new HashSet<>();
        List<String> duplicates = new ArrayList<>();
        String loguuid = UUID.randomUUID().toString();
        for (PurchaseReceiptStorageDto dto : Dto.getList()) {
            if (dto.getBarcode() != null) {
                log.info(loguuid + " 前端传入条码：" + dto.getBarcode() + ".");
                if (!barcodes.add(dto.getBarcode())) {
                    duplicates.add(dto.getBarcode());
                }
            }
        }
        List<PurchaseReceiptStorageDto> dtoList = Dto.getList();
        if (dtoList == null || dtoList.isEmpty()) {
            return Result.fail("条码不能为空");
        }

        // key=barcode，value=合并后的 DTO
        Map<String, PurchaseReceiptStorageDto> grouped = dtoList.stream()
                .collect(Collectors.toMap(
                        PurchaseReceiptStorageDto::getDocNo,
                        dto -> {
                            // 先拷贝一份，避免原对象被修改
                            PurchaseReceiptStorageDto newDto = new PurchaseReceiptStorageDto();
                            BeanUtils.copyProperties(dto, newDto);
                            return newDto;
                        },
                        (a, b) -> {
                            // 合并逻辑：matchQty 相加
                            a.setMatchQty(a.getMatchQty().add(b.getMatchQty()));
                            a.setRejectQty(a.getRejectQty().add(b.getRejectQty()));
                            a.setScrapQty(a.getScrapQty().add(b.getScrapQty()));
                            return a;
                        }
                ));

        // 转为 List
        List<PurchaseReceiptStorageDto> newList = new ArrayList<>(grouped.values());

        if (!duplicates.isEmpty()) {
            return Result.fail("发现重复的条码: " + String.join(", ", duplicates), "发现重复的条码: " + String.join(", ", duplicates));
        }

        PurchaseArrivalEntity.PurchaseArrival arrival = new PurchaseArrivalEntity.PurchaseArrival();
        /* 1. 构造主表 */
        String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        PurchaseReceiptStorageDto first = newList.get(0);
        String firstDocNoDto = first.getDocNo();
        String[] split = firstDocNoDto.split("-");
    //        String firstOrderNum = purchaseMapper.getPurchaseDByBarcode(first.getBarcode()); // 采购单序号
        PurchaseInfo firstPurchaseInfo = purchaseMapper.queryPurchaseInfo(split[0] + "-" + split[1], split[2]);
        String arrivalDocNo = firstPurchaseInfo.getArrivalOrderCode() + "-" + today + "0001"; // 到货单号
        arrival.setDOC_NO(arrivalDocNo);
        arrival.setDOC_ID_DOC_CODE(firstPurchaseInfo.getArrivalOrderCode()); // 到货单单据编号
    //        arrival.setCATEGORY(firstPurchaseInfo.getArrivalOrderCategory()); // 到货单性质 刚哥触发器
        arrival.setPLANT_ID_PLANT_CODE(firstPurchaseInfo.getSupplyCenterCode()); // 工厂编号
        arrival.setPAYMENT_TERM_ID_PAYMENT_TERM_TYPE(firstPurchaseInfo.getPaymentType()); // 付款条件.类别
        arrival.setPAYMENT_TERM_ID_PAYMENT_TERM_CODE(firstPurchaseInfo.getPaymentCode()); // 付款条件.编号
        arrival.setSUPPLIER_ID_SUPPLIER_CODE(firstPurchaseInfo.getSupplierCode()); // 供应商编号
        arrival.setTAX_BC(firstPurchaseInfo.getTaxBc()); // 本币税额
        arrival.setTAX_ID_TAX_CODE(firstPurchaseInfo.getTaxCode());//税种.税种编号
        arrival.setTAX_INCLUDED(true); // 含税
        arrival.setTAX_OC(firstPurchaseInfo.getTaxOc()); // 原币税额
        arrival.setOwner_Org_SUPPLY_CENTER_SUPPLY_CENTER_CODE(firstPurchaseInfo.getSupplyCenterCode()); // 组织类型.采购域.采购域编号
        arrival.setRECEIVE_Owner_Org_PLANT_PLANT_CODE(firstPurchaseInfo.getPlantCode()); // 收货机构.工厂/储运.工厂/储运编号
        // 区分3600:正常到货  3601:委外到货
        arrival.setDOC_ID_Owner_Org_SUPPLY_CENTER_SUPPLY_CENTER_CODE(firstPurchaseInfo.getSupplyCenterCode()); // 组织类型.采购域.采购域编号
        arrival.setDOC_DATE(LocalDateTime.now()); // 订单时间
        arrival.setCURRENCY_ID_CURRENCY_CODE(firstPurchaseInfo.getCurrencyCode()); // 币种编号
        arrival.setINVOICE_COMPANY_ID_COMPANY_CODE("1"); // 结算公司.公司编号 固定为 1
        arrival.setINVOICE_SUPPLIER_ID_SUPPLIER_CODE(firstPurchaseInfo.getSupplierCode()); // 供应商编号
        arrival.setSUPPLIER_FULL_NAME(firstPurchaseInfo.getSupplierFullName().trim()); // 供应商全称
        arrival.setEXCHANGE_RATE(firstPurchaseInfo.getExchangeRate()); // 汇率
    //        arrival.setSOURCE_ID_MO_DOC_NO(firstPurchaseInfo.getMoDoc()); // 工单号
    //        arrival.setSOURCE_ID_MO_MO_D_SequenceNumber(firstPurchaseInfo.getMoDocNum()); // 工单序号
        // 单头备注填写提交用户的名字
        arrival.setREMARK("WMS-" + Dto.getCreateBy());
        arrival.setOwner_Dept_ADMIN_UNIT_CODE(firstPurchaseInfo.getAdminUnitCode()); // 关联部门.行政单元编码
        arrival.setOwner_Dept_START_DATE(firstPurchaseInfo.getStartDate()); // 关联部门.生效日期
        arrival.setOwner_Emp_EMPLOYEE_CODE(firstPurchaseInfo.getEmployeeCode()); // 关联员工.员工编号
        arrival.setDEDUCTIBLE_INDICATOR("1".equals(firstPurchaseInfo.getVatDeductible())); // VAT抵扣标志
        arrival.setTAX_INVOICE_CATEGORY_ID_TAX_INVOICE_CATEGORY_CODE(firstPurchaseInfo.getTaxCode()); // 税票类别编号
        List<PurchaseArrivalEntity.PurchaseArrivalD> dList = new ArrayList<>();
        int sequenceNumber = 1;
        String warehouseCode = newList.get(0).getWarehouseCode();
        String EMPLOYEE_CODE = purchaseMapper.queryQcUserByWarehouseCode(warehouseCode);
        if (EMPLOYEE_CODE == null) {
            EMPLOYEE_CODE = "1".equals(firstPurchaseInfo.getSupplyCenterCode()) ? "240228001" : "2110230";
        }
        for (PurchaseReceiptStorageDto dto : newList) {
            String docNoDto = dto.getDocNo();
            String[] splitd = docNoDto.split("-");
    //            String orderNum = purchaseMapper.getPurchaseDByBarcode(dto.getBarcode()); // 获取采购单序号
            String lotNumber = purchaseMapper.selectLotNumber(dto.getBarcode()); // 批号
            PurchaseInfo purchaseInfo = purchaseMapper.queryPurchaseInfo(splitd[0] + "-" + splitd[1], splitd[2]);
            if (purchaseInfo == null) {
                return Result.fail("采购单不存在", "采购单不存在");
            }
            // 构造 PurchaseArrivalD（明细）
            PurchaseArrivalEntity.PurchaseArrivalD d = new PurchaseArrivalEntity.PurchaseArrivalD();
            d.setSequenceNumber(sequenceNumber); // 序号
    //            d.setApproveStatus("Y");
            d.setBUSINESS_QTY(dto.getMatchQty()); //异动数量
            d.setBUSINESS_UNIT_ID_UNIT_CODE(purchaseInfo.getBusinessUnitCode()); // 业务单位
            d.setPRICE_UNIT_ID_UNIT_CODE(purchaseInfo.getPriceUnitCode()); // 计价单位.单位编号
            d.setDISCOUNTED_PRICE(purchaseInfo.getDiscountedPrice()); // 折扣价格

            // 先全部安全地拿到 BigDecimal，null → ZERO
            BigDecimal inventoryQty = Optional.ofNullable(purchaseInfo.getInventoryQty())
                    .orElse(BigDecimal.ZERO);
            BigDecimal businessQty  = Optional.ofNullable(purchaseInfo.getBusinessQty())
                    .orElse(BigDecimal.ZERO);
            BigDecimal matchQty = Optional.ofNullable(dto.getMatchQty())
                    .orElse(BigDecimal.ZERO);
            // 当除数为 0 时直接给 0，避免 ArithmeticException
            BigDecimal inventoryQtyPerBusinessQty = businessQty.compareTo(BigDecimal.ZERO) == 0
                    ? BigDecimal.ZERO
                    : inventoryQty.divide(businessQty, 10, RoundingMode.HALF_UP)
                    .multiply(matchQty);
            d.setINVENTORY_QTY(inventoryQtyPerBusinessQty); // 库存单位数量

    //            d.setGENERATE_SOURCE("PO"); // 单据来源
            // 检验状态
    //            d.setINSPECTION_STATUS(purchaseInfo.getInspectMode().equals("1") ? "1" : "2"); // 检验状态
            d.setINSPECTION_STATUS("1".equals(purchaseInfo.getInspectMode()) ? "1" : "2"); // 检验状态
            d.setITEM_DESCRIPTION(purchaseInfo.getItemName()); // 品名
            d.setITEM_ID_ITEM_CODE(purchaseInfo.getItemCode()); // 品号
    //            d.setITEM_LOT_ID_ITEM_ID_ITEM_CODE("R0805-10K-1%");
    //            d.setITEM_LOT_ID_ITEM_FEATURE_ID_ITEM_FEATURE_CODE("0805");
    //            d.setITEM_LOT_ID_ITEM_FEATURE_ID_Parent_ITEM_CODE("R0805");
            d.setITEM_LOT_ID_LOT_CODE(lotNumber); // 批号
            d.setITEM_SPECIFICATION(purchaseInfo.getItemSpec()); // 规格
            d.setITEM_TYPE(purchaseInfo.getItemType()); // 商品类型
            d.setPRICE(purchaseInfo.getPrice()); //单价
            d.setPURCHASE_ORDER_ID_DOC_NO(splitd[0] + "-" + splitd[1]);//采购订单ID.单号
            d.setPURCHASE_TYPE(purchaseInfo.getPurchaseType());//采购类型
            d.setSTANDARD_PRICE(purchaseInfo.getStandardPrice());//标准单价
            d.setTAX_ID_TAX_CODE(purchaseInfo.getTaxCode());//税种.税种编号
            d.setTAX_RATE(purchaseInfo.getTaxRate());//税率
            d.setWAREHOUSE_ID_WAREHOUSE_CODE(dto.getWarehouseCode()); // 仓库编号
            d.setWAREHOUSE_ID_Owner_Org_PLANT_PLANT_CODE(purchaseInfo.getPlantCode()); // 收货机构.工厂/储运.工厂/储运编号
            d.setSOURCE_ID_PURCHASE_ORDER_PURCHASE_ORDER_D_PURCHASE_ORDER_SD_SequenceNumber(1); // SD 序号 固定为1
            d.setSOURCE_ID_PURCHASE_ORDER_PURCHASE_ORDER_D_PURCHASE_ORDER_SD_Parent_SequenceNumber(Integer.valueOf(splitd[2])); // D 序号
            d.setSOURCE_ID_PURCHASE_ORDER_PURCHASE_ORDER_D_PURCHASE_ORDER_SD_Parent_Parent_DOC_NO(splitd[0] + "-" + splitd[1]); // 采购单
    //            d.setPRICE(BigDecimal.ZERO); //计价数量
            if (StrUtil.isNotBlank(purchaseInfo.getMoDoc())) {
                d.setREFERENCE_SOURCE_ID_MO_DOC_NO(purchaseInfo.getMoDoc()); // 工单号
                d.setREFERENCE_SOURCE_ID_RTK("MO"); // 工单号
                d.setMO_ID_DOC_NO(purchaseInfo.getMoDoc()); // 工单号
    //                d.setSOURCE_ID_MO_MO_D_Parent_DOC_NO(purchaseInfo.getMoDoc()); // 工单号
            }
    //            if (StrUtil.isNotBlank(purchaseInfo.getMoDocNum())) {
    //                d.setSOURCE_ID_MO_MO_D_SequenceNumber(purchaseInfo.getMoDocNum()); // 工单序号
    //            }
            dList.add(d);
            sequenceNumber++;
        }
        arrival.setPURCHASE_ARRIVAL_D(dList);
        // 封装为 StdData
        PurchaseArrivalEntity.StdData stdData = new PurchaseArrivalEntity.StdData();
        PurchaseArrivalEntity.Parameter parameter = new PurchaseArrivalEntity.Parameter();
        parameter.setDatas(Collections.singletonList(arrival));
        stdData.setParameter(parameter);

        // 序列化为 JSON 并打印
        String json = PurchaseArrivalEntity.toJson(stdData);
        log.warn("生成到货单请求参数：\n{}", json);
        E10ApiService e10ApiService = new E10ApiService();
        // 创建到货单
        JSONObject createArrivalResult = e10ApiService.createArrival(json);
    //        JSONObject createArrivalResult = null;
        log.warn("生成到货单接口响应：\n{}", createArrivalResult);
        JSONObject createArrivalJsonObject = createArrivalResult
                .getJSONObject("std_data")
                .getJSONObject("parameter")
                .getJSONObject("result");
        JSONArray createJsonArray = createArrivalJsonObject.getJSONArray("error");
        if (createJsonArray != null && !createJsonArray.isEmpty()) {
            String errorMsg = createJsonArray.getJSONObject(0).getString("message");
            return Result.fail().message("生成到货单失败！\n" + errorMsg);
        }

        String docNo = createArrivalResult
                .getJSONObject("std_data")
                .getJSONObject("parameter")
                .getJSONObject("result")
                .getJSONArray("success")
                .getJSONObject(0)
                .getString("DOC_NO");

        log.warn("到货单生成成功-单号：{}", docNo);
        // 审核到货单
        JSONObject confirmArrivalJson = e10ApiService.confirmArrival(docNo);
        log.warn("审核到货单接口响应：\n{}", confirmArrivalJson);
        JSONObject confirmArrivalJsonObject = confirmArrivalJson
                .getJSONObject("std_data")
                .getJSONObject("parameter")
                .getJSONObject("result");
        JSONArray confirmJsonArray = confirmArrivalJsonObject.getJSONArray("error");
        if (confirmJsonArray != null && !confirmJsonArray.isEmpty()) {
            String errorMsg = confirmJsonArray.getJSONObject(0).getString("message");
            log.warn("审核失败删除到货单：\n{}", docNo);
            JSONObject deleteArrival = e10ApiService.deleteArrival(docNo);
            log.warn("删除到货单接口响应：\n{}", deleteArrival);
            return Result.fail().message("到货单审核失败！\n" + errorMsg);
        }
    //        String qcOrderCategory = purchaseMapper.queryCategoryByArrivalOrderCode()
        // 创建到货质检单
        for (PurchaseArrivalEntity.PurchaseArrivalD d : dList) {
            String plantCode = d.getWAREHOUSE_ID_Owner_Org_PLANT_PLANT_CODE();
    //            String docIdDocCode = arrival.getDOC_ID_DOC_CODE();
            String qcCategory = "1".equals(d.getPURCHASE_TYPE()) ? "Q1" : "Q2"; // 质检类别
            String qcDocNo = qcCategory + ("1".equals(plantCode) ? "00" : "50");
            QcInspectionOrder qcInspectionOrder = new QcInspectionOrder(); // 生成到货质检
            qcInspectionOrder.setDOC_NO(qcDocNo + "-" + today + "0001");  // 质检单号
            qcInspectionOrder.setCATEGORY(qcCategory);        // 单据性质
            qcInspectionOrder.setQC_TIMES(1);                 // 检验次数
            qcInspectionOrder.setOwner_Org_PLANT_PLANT_CODE(d.getWAREHOUSE_ID_Owner_Org_PLANT_PLANT_CODE());      // 组织类型.工厂/储运.工厂/储运编号
            qcInspectionOrder.setINSPECTION_DATE(LocalDate.now());               // 检验日期
            qcInspectionOrder.setSOURCE_ID_SequenceNumber(d.getSequenceNumber());         // 源单.序号
            qcInspectionOrder.setSOURCE_ID_Parent_DOC_NO(docNo);         // 源单.到货单.单号
            qcInspectionOrder.setOwner_Dept_ADMIN_UNIT_CODE("QPG");      // 关联部门.行政单元编码
            qcInspectionOrder.setOwner_Dept_START_DATE("2016-05-31 00:00:00.0000000");         // 关联部门.生效日期
            qcInspectionOrder.setDEDUCT_ARRIVED_QTY(true); // 扣已到货量 默认勾选
            qcInspectionOrder.setOwner_Emp_EMPLOYEE_CODE(EMPLOYEE_CODE);         // 关联员工.员工编号
            qcInspectionOrder.setDOC_DATE(LocalDateTime.now());              // 单据日期
            qcInspectionOrder.setDOC_ID_DOC_CODE(qcDocNo);                 // 单据类型.单据类型
            qcInspectionOrder.setREMARK("WMS-" + Dto.getCreateBy());        // 备注
            qcInspectionOrder.setDOC_ID_Owner_Org_PLANT_PLANT_CODE(d.getWAREHOUSE_ID_Owner_Org_PLANT_PLANT_CODE()); // 单据类型.组织类型.工厂/储运.工厂/储运编号
    //            qcInspectionOrder.setACCEPTABLE_QTY(null);                // 合格数量
    //            qcInspectionOrder.setDECISION(null);                        // 判定结果
    //            qcInspectionOrder.setDECISION_DESCRIPTION(null);            // 判定说明
    //            qcInspectionOrder.setDESTROYED_QTY(null);                 // 破坏数量
    //            qcInspectionOrder.setINSPECTION_TIMES(1);                 // 最大抽样次数
            qcInspectionOrder.setINSPECTION_UNIT_ID_UNIT_CODE(d.getBUSINESS_UNIT_ID_UNIT_CODE());    // 品管单位.单位编号
            qcInspectionOrder.setINVENTORY_QTY(d.getINVENTORY_QTY());                 // 库存单位数量
            qcInspectionOrder.setITEM_DESCRIPTION(d.getITEM_DESCRIPTION());                // 品名
            qcInspectionOrder.setITEM_ID_ITEM_CODE(d.getITEM_ID_ITEM_CODE());               // 品号.品号
            qcInspectionOrder.setITEM_SPECIFICATION(d.getITEM_SPECIFICATION());              // 规格
            qcInspectionOrder.setRESULT_STATUS("1");                   // 业务判定状态
            qcInspectionOrder.setSUPPLIER_ID_SUPPLIER_CODE(arrival.getSUPPLIER_ID_SUPPLIER_CODE());       // 供应商.供应商编号
    //            qcInspectionOrder.setUDF021("");                          // 自定义字段6
            qcInspectionOrder.setINSPECTION_QTY(d.getBUSINESS_QTY());       // 送检数量
    //            qcInspectionOrder.setINSPECTION_PLAN_ID_QC_CATEGORY_ID_QC_CATEGORY_CODE("111"); // 质检方案ID.品管类别编号.品管类别编号
    //            qcInspectionOrder.setINSPECTION_PLAN_ID_Owner_Org_PLANT_PLANT_CODE("11");      // 质检方案ID.组织类型.工厂/储运.工厂/储运编号
    //            qcInspectionOrder.setINSPECTION_PLAN_ID_ITEM_FEATURE_ID_ITEM_FEATURE_CODE("11"); // 质检方案ID.品号特征码.特征码
    //            qcInspectionOrder.setINSPECTION_PLAN_ID_ITEM_FEATURE_ID_Parent_ITEM_CODE("11");  // 质检方案ID.品号特征码.通用品号信息.品号
    //            qcInspectionOrder.setINSPECTION_PLAN_ID_OPERATION_ID_OPERATION_CODE("11");    // 质检方案ID.工艺.工艺编号
    //            qcInspectionOrder.setINSPECTION_PLAN_ID_ITEM_ID_ITEM_CODE("111");              // 质检方案ID.品号.品号
    //            qcInspectionOrder.setSUBMIT_DEPT_ID_ADMIN_UNIT_CODE("111");    // 送检部门.行政单元编码
    //            qcInspectionOrder.setSUBMIT_DEPT_ID_START_DATE(LocalDate.now());       // 送检部门.生效日期
    //            qcInspectionOrder.setSUBMIT_EMP_ID_EMPLOYEE_CODE("111");       // 送检人员.员工编号
    //            qcInspectionOrder.setINSPECTION_DUE_DATE(LocalDate.now());             // 检验期限
            qcInspectionOrder.setCOMPANY_ID_COMPANY_CODE("1");           // 公司.公司编号
            String jsonBody = QcInspectionOrder.toJson(qcInspectionOrder);
            log.warn("创建检验单请求体：{}", jsonBody);
            JSONObject arrivalInspection = e10ApiService.createArrivalInspection(jsonBody);
            log.warn("创建检验单响应：{}", arrivalInspection);
            JSONArray successArray = arrivalInspection.getJSONObject("std_data")
                    .getJSONObject("parameter")
                    .getJSONObject("result")
                    .getJSONArray("success");
            if (successArray != null && !successArray.isEmpty()) {
                String docNo1 = successArray.getJSONObject(0).getString("DOC_NO");
                JSONObject jsonObject = e10ApiService.confirmArrivalInspection(docNo1, "1");
                log.warn("审核检验单响应：\n{}", jsonObject);
                JSONObject jsonObject1 = e10ApiService.disConfirmArrivalInspection(docNo1, "1");
                log.warn("撤审检验单响应：\n{}", jsonObject1);
            }
        }

        //2、回写到货单号、回写送货单头uuid
        //收集dto中的采购单单号
        List<String> dtodocNoList = new ArrayList<>();
    //        List<Map<String, String>> barcodeUpdateList = new ArrayList<>();
        List<Map<String, String>> deliveryUpdateList = new ArrayList<>();
        // 容器码去重
        Set<String> barcodeUpdateSet = new HashSet<>();
        for (PurchaseReceiptStorageDto dto : Dto.getList()) {
            //子条码查容器码
            String containerBarcode = purchaseMapper.getContainerBarcodeByChildBarcode(dto.getBarcode());
            barcodeUpdateSet.add(dto.getBarcode());
            if (containerBarcode != null && containerBarcode.trim().length() > 0) {
                barcodeUpdateSet.add(containerBarcode);
            }
            //采购单列表
            dtodocNoList.add(dto.getDocNo());
        }
        // 直接更新条码状态为已到货
        for (String barcode : barcodeUpdateSet) {
            log.info(loguuid + " 条码：" + barcode + " 状态更新为已到货");
            purchaseMapper.updateLotatt30To1AndLot_att04(barcode, docNo);
            //更新送货单单身状态为已到货
            purchaseMapper.updateDeliveryNoteDetails(barcode,docNo);
        }
        //批量查询采购单号对应的 送货单头uuid
        if (!dtodocNoList.isEmpty()) {
            List<String> uuidResultList = purchaseMapper.getDeliveryUuidsByDocNos(dtodocNoList);

            for (String uuid : uuidResultList) {

                Map<String, String> deliveryMap = new HashMap<>();
                deliveryMap.put("docNo", docNo);
                deliveryMap.put("uuid", uuid);
                deliveryUpdateList.add(deliveryMap);
            }
        }


        if (!deliveryUpdateList.isEmpty()) {
            //更新deliveryList 到货单单号
            purchaseMapper.batchUpdateDeliveryUuid(deliveryUpdateList);
        }
    //        //回写检验单单号：到货单单号+到货单单身序号+品号绑定检验单号
    //        purchaseMapper.selectPoArrivalInspectionDocNo(docNo,)


        //3.删除对应条码操作档记录
        Dto.getInvBarcodeOperationIdList().forEach(id -> {
            purchaseSummaryMapper.deleteById(id);
        });
        //删除对应送货单身中间表
        QueryWrapper<DeliverySummary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator", Dto.getCreateBy());
        deliverySummaryMapper.delete(queryWrapper);


        //4、企业微信推送
        //根据到货单查询对应待检信息
        //单头
        WaitToInspectedVo head = purchaseMapper.getWaitToInspected(docNo);
        //单身
        List<PurchaseArrivalDVo> body = purchaseMapper.getWaitToInspectedD(docNo);
        //获取仓库编号唯一列表
        List<String> warehouseList = body.stream().map(PurchaseArrivalDVo::getWarehouseCode).distinct().collect(Collectors.toList());
        //根据仓库列表查对应检验员
        if (warehouseList != null) {
            List<String> qcList = purchaseMapper.getInspectorByWarehouse(warehouseList);
            if (qcList == null) {
                return Result.fail().message("未查询到检验员企微账号");
            }
            //去除数组中的null值
            qcList = qcList.stream().filter(Objects::nonNull).collect(Collectors.toList());
            MessageDto messageDto = new MessageDto();
            StringBuilder content = new StringBuilder();
            // 拼接头部信息
            content.append("供应商：").append(head.getSupplierName())
                    .append(" , 到货单单号：").append(head.getDeliveryNo())
                    .append("，到货日期：").append(head.getDeliveryDate()).append("\n");

            // 拼接到货明细信息
            content.append("到货明细: ");
            for (int i = 0; i < body.size(); i++) {
                PurchaseArrivalDVo detail = body.get(i);
                content.append(detail.getXh()).append("、 客户单号：").append(detail.getCustomerNo())
                        .append("，待检验物料：").append(detail.getItemCode()).append("-").append(detail.getItemName());
                if (i < body.size() - 1) {
                    content.append("\n               ");
                }
            }
            qcList.add("ZouJinLin");
            messageDto.setContent(content.toString());
            messageDto.setTouser(qcList);
            // 发企微消息
            weChatService.sendWXMessage(messageDto);
        }

        return Result.ok().message("提交成功: " + docNo);
    }











//    @Autowired
//    private PrintLineService printLineService;

//    //采购收货
//    @ApiOperation("采购收货-采购单号、物料条码查询详细信息")
//    @PostMapping("/getPurchaseReceiveInfo")
//    public Result getPurchaseReceiveInfo(@RequestBody PurchaseReceiveDto purchaseReceiveDto){
//        return purchaseService.getPurchaseReceiveInfo(purchaseReceiveDto);
//    }

    @ApiOperation("快速收货-查询采购单简洁信息")
    @PostMapping("/getPurcharseInfo")
    public Result getPurcharseInfo(@RequestBody PurchaseOrderSimpleDto purchaseOrderSimpleDto){
        return purchaseService.getPurcharseInfo(purchaseOrderSimpleDto);
    }
    //快速收货提交（批量提交不同单号同一供应商）
    @ApiOperation("快速收货-查询采购单详细信息")
    @PostMapping("/getPurcharseDetail")
    public Result<List<PurchaseOrderDetailVo>> getPurcharseDetail(@RequestBody PurchaseOrderDetailDto dto){
        return purchaseService.getPurcharseDetail(dto);
    }

//    @ApiOperation("快速收货-打印到货单接口（走中间表）")
//    @PostMapping("/printPurchaseArrivalBar")
//    public Result printPurchaseArrivalBar(@RequestBody List<ListBarcodeDto> list){
//        return printLineService.getBarcodeByDocNoItemCode(list);
//    }


    @PostMapping("/FastDeliveryGenerateDoc")
    private Result FastDeliveryGenerateDoc(@RequestBody FastDeliveryDto fastDeliveryDto){
        return purchaseService.FastDeliveryGenerateDoc(fastDeliveryDto);
    }


    //采购质检
    @ApiOperation("采购质检-查询可检列表")
    @PostMapping("/ListCheckPurchase")
    public Result<List<PurchaseArrivalDetailVo>> ListCheckPurchase(@RequestBody ListCheckPurchaseDto dto){
        return purchaseService.ListCheckPurchase(dto);
    }

    @ApiOperation("采购质检-生成采购质检单")
    @PostMapping("/producePurchaseInspectionOrder")
    public Result producePurchaseInspectionOrder(@RequestBody PurchaseInspectionOrderDto dto){
        return purchaseService.producePurchaseInspectionOrder(dto);
    }

    //扫码入库
//    //TODO 测试完成后修改xml代码，在下面
    //获取到货单简洁信息
    @ApiOperation("扫码入库-获取到货单简洁信息")
    @PostMapping("/getPurcharseArrivalInfo")
    public Result<List<PurchaseArrivalSimpleVo>>  getPurcharseArrivalInfo(@RequestBody  PurchaseArrivalSimpleDto purchaseArrivalSimpleDto){
        try {
            if (purchaseArrivalSimpleDto.getBarCode()!=null && !purchaseArrivalSimpleDto.getBarCode().equals("")){
                String barcode = purchaseArrivalSimpleDto.getBarCode();
                System.out.println(barcode);
                purchaseArrivalSimpleDto.setBarCode(barcode.substring(0,barcode.indexOf("#")));
                System.out.println(barcode.substring(0,barcode.indexOf("#")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return purchaseService.getPurcharseArrivalInfo(purchaseArrivalSimpleDto);
        //    AND PLANT.PLANT_CODE= '1'
        //    AND PURCHASE_ARRIVAL.ApproveStatus=N'Y'
        //    AND PURCHASE_ARRIVAL_D.RECEIPT_CLOSE=N'0'
        //    AND  (PURCHASE_ARRIVAL_D.ACCEPTED_BUSINESS_QTY + PURCHASE_ARRIVAL_D.SP_RECEIPT_BUSINESS_QTY - PURCHASE_ARRIVAL_D.RECEIPTED_BUSINESS_QTY ) > 0
    }
    //TODO 测试完成后修改xml代码，在下面


    @ApiOperation("扫码入库-获取到货单详细信息")
    @PostMapping("/getPurchaseArrivalDetail")
    public Result<List<PurchaseArrivalDetailVo>> getPurchaseArrivalDetail(@RequestBody  PurchaseArrivalDetailDto purchaseArrivalDetailDto){
        return purchaseService.getPurchaseArrivalDetail(purchaseArrivalDetailDto);
    }




    //采购收货-无获取简洁信息接口
    @ApiOperation("采购收货-获取采购单详细信息")
    @GetMapping("/getPurchaseDetail/{docNo}")
    public Result<List<PurchaseDetailVo>> getPurchaseDetail(@PathVariable("docNo") String docNo){
        return purchaseService.getPurchaseDetail(docNo);
    }



    @ApiOperation("查询对应的条码履历操作表")
    @PostMapping("/getBarCodeOperation")
    @Log("查询对应的条码履历操作表")
    public Result<List<PurchaseReceiptStorageVo>> getBarCodeOperation(){
        return purchaseService.getBarCodeOperation();
    }


   @ApiOperation("不良原因列表")
    @PostMapping("/ListDefectiveReasons")
    public Result<PageInfo<DefectiveReasons>> ListDefectiveReasons(@RequestBody DefectiveReasonsDto dto){
       return purchaseService.ListDefectiveReasons(dto);
    }



//    //TODO 测试完成后修改xml代码，在下面
//    @ApiOperation("扫码入库-通过到货单号获取到货单汇总信息")
//    @PostMapping("/getPurcharseArrivalDetailByDocNo")
//    public Result getPurcharseArrivalDetailByDocNo(@RequestBody  PurchaseArrivalDetailDto purchaseArrivalDetailDto){
//        return purchaseService.getPurcharseArrivalDetailByDocNo(purchaseArrivalDetailDto);
////        AND PLANT.PLANT_CODE= '1'
////        AND PURCHASE_ARRIVAL.ApproveStatus=N'Y'
////        AND PURCHASE_ARRIVAL_D.RECEIPT_CLOSE=N'0'
////        AND  (PURCHASE_ARRIVAL_D.ACCEPTED_BUSINESS_QTY + PURCHASE_ARRIVAL_D.SP_RECEIPT_BUSINESS_QTY - PURCHASE_ARRIVAL_D.RECEIPTED_BUSINESS_QTY ) > 0
////        AND  PURCHASE_ARRIVAL_D.RETURN_BUSINESS_QTY &lt;&gt; PURCHASE_ARRIVAL_D.BUSINESS_QTY
//    }


//    //扫码入库提交
//    @ApiOperation("扫码入库-提交")
//    @PostMapping("/ScanToStorageGenerateDoc")
//    public Result ScanToStorageGenerateDoc(@RequestBody List<ScanToStorageDto> list){
////        try {
////            //同步中间表
////            MiddleReturnDto middleReturnDto = purchaseService.insertMiddleTable(list);
////            //提交e10
////            JSONObject result = purchaseService.scanToStorageSubmit(middleReturnDto);
////            String resultDocNo = result.getJSONObject("payload")
////                    .getJSONObject("std_data")
////                    .getJSONObject("parameter")
////                    .getJSONArray("result")
////                    .getJSONObject(0)
////                    .getString("doc_no");
////            return Result.ok(resultDocNo).message("提交成功");
////        }catch (Exception e){
////            e.printStackTrace();
////            return Result.fail().message("提交失败");
////        }
//        return null;
//    }

    //采购仓退
    //TODO 修改xml查询语句的条件(解开注释)
    @ApiOperation(value = "采购仓退-简洁查询",notes = "参数可以不穿,默认返回前10条")
    @PostMapping("/ListPurchaseReturn")
    public Result ListPurchaseReturn(@RequestBody(required = false) ListPurchaseReturnDto dto){
        return purchaseService.ListPurchaseReturn(dto);
    }

    @ApiOperation(value = "采购仓退-详细查询",notes = "参数可以不穿,默认返回前10条")
    @GetMapping("/ListPurchaseReturnDetail/{docNo}")
    public Result ListPurchaseReturn(@PathVariable("docNo") String docNo){
        return purchaseService.ListPurchaseReturnDetail(docNo);
    }

    @ApiOperation(value = "采购仓退-提交",notes = "参数可以不穿,默认返回前10条")
    @PostMapping("/ListPurchaseReturnSubject")
    @Log("采购仓退-提交")
    public Result ListPurchaseReturnSubject(@RequestBody List<PurchaseReturnSubmitDto> list){
        try {
            //同步中间表
            MiddleReturnDto middleReturnDto = purchaseService.PurchaseReturnInsertMiddleTable(list);
            //提交
            JSONObject jsonObject = purchaseService.PurchaseReturnStockSubmit(middleReturnDto);
            return Result.ok(jsonObject).message("添加成功");
//            return Result.ok().message("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail().message("添加失败");
        }
    }

}
