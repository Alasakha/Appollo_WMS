package com.mgkj.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mgkj.common.constant.WmsConstants;
import com.mgkj.common.result.Result;
import com.mgkj.common.utils.DateUtils;
import com.mgkj.dto.*;
import com.mgkj.entity.InvBarcodeOperation;
import com.mgkj.entity.Item;
import com.mgkj.entity.PurchaseArrival;
import com.mgkj.entity.session.*;
import com.mgkj.exception.BaseException;
import com.mgkj.mapper.*;
import com.mgkj.qywx.CorpiProperties;
import com.mgkj.service.*;
import com.mgkj.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.mgkj.entity.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.mgkj.common.constant.WmsConstants.*;


/**
 * @author yyyjcg
 * @date 2023/12/26
 * @Description
 */
@Service
@Slf4j
public class PurchaseServiceImp  implements PurchaseService {
    @Autowired
    private PurchaseMapper purchaseMapper;
    @Autowired
    private PurchaseArrivalMapper purchaseArrivalMapper;
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private BmBarcodeDetailMapper bmBarcodeDetailMapper;
    @Autowired
    private DocMapper docMapper;
    //    @Autowired
//    private PrintLineService printLineService;
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private POQCRESULTMapper poqcreSULTMapper;

    @Autowired
    private POQCRESULTDMapper poqcreSULTDMapper;

    @Autowired
    private POQCRESULTSDMapper  poqcreSULTSDMapper;
    @Autowired
    private PoArrivalInspectionDMapper  poarrivalInspectionDMapper;

    @Autowired
    private WmsPoArrivalInspectionMapper  wmsPoArrivalInspectionMapper;


    @Autowired
    private PoArrivalInspectionSd1Service poArrivalInspectionSd1Service ;

    @Autowired
    private PoArrivalInspectionSd1Mapper poArrivalInspectionSd1Mapper ;

    @Resource
    private WmsPoArrivalInspectionDService wmsPoArrivalInspectionDService;

    @Resource
    private WmsPoArrivalInspectionDMeasurementValueService wmsPoArrivalInspectionDMeasurementValueService;

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private CorpiProperties corpiProperties;

    @Autowired
    private QcService qcService;

    @Value("${file.upload-dir}")
    private String uploadDir;

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;


    @Override
    public Result UpdatePurchaseArrivalStorageCheck(PurchaseArrivalCheck dto) {
        HashMap<String, String> map = updateDetails(dto);
        if ("0".equals(map.get("code"))) {
            return Result.fail(map.get("message")).message(map.get("message"));
        }

        PurchaseArrivalCheckHeader header = dto.getHeader();
        String loginUuid = header.getEmployeeUUID();
        if(StrUtil.isBlank(loginUuid)){
            return Result.fail().message("操作人(登录信息)不能为空!");
        }
        String checkDocNo = header.getCheckDocNo();
        purchaseMapper.updatePoArrivalInspectionCreateByDocNo(checkDocNo, loginUuid);
        //查询该到货单中已检验并检验合格的到货单身数据 将其数据推送给微信用户；
        WaitToStorageDTO waitToStorageDTO = new WaitToStorageDTO();
        waitToStorageDTO.setArrivalNumber(header.getArrivalDocNo());
        waitToStorageDTO.setItemName(header.getItemName());
        List<String> warehouseList = null;
        //1、合格的推送
        List<WaitToStorageVo> listvo = purchaseMapper.selectOddNumber(header.getInspectionUuid());
        if (!listvo.isEmpty()) {
            //获取仓库编号唯一列表
            warehouseList = listvo.stream().map(WaitToStorageVo::getWarehouseCode).distinct().collect(Collectors.toList());
            //根据仓库列表查对应检验员
            if (warehouseList != null) {
                List<String> cgList = purchaseMapper.getCGQWByWarehouse(warehouseList);
                if (cgList == null) {
                    return Result.fail().message("未查询到仓管员企微账号");
                }
                //去除数组中的null值
                cgList = cgList.stream().filter(Objects::nonNull).collect(Collectors.toList());
                String customerNumbers = "";
                if(listvo != null && !listvo.isEmpty()) {
                    waitToStorageDTO.setOddNumber(listvo.get(0).getOddNumber());
                    waitToStorageDTO.setSupplierName(listvo.get(0).getSupplierFullName());
                    Set<String> set = new HashSet();
                    for(WaitToStorageVo waitVo : listvo) {
                        if(waitVo.getCustomerNumber() != null && !waitVo.getCustomerNumber().trim().equals("")){ set.add(waitVo.getCustomerNumber().trim());}
                    }
                    StringBuffer sb = new StringBuffer();
                    for(String str : set) {
                        sb.append(str).append(",");
                    }
                    if(sb.length() > 0) {
                        customerNumbers = sb.toString().substring(0, sb.length()-1);
                    }
                }

                List<String> cgqywxList = purchaseMapper.getCGQWBySupplierCode(header.getSupplierCode());
                if (cgqywxList != null) {
                    cgList.addAll(cgqywxList);
                }
                MessageDto messageDto = new MessageDto();
                messageDto.setContent( "供应商：" +waitToStorageDTO.getSupplierName() + "采购单：" + waitToStorageDTO.getOddNumber() + ", 到货单单号:" + waitToStorageDTO.getArrivalNumber()  + ",客户单号：" + customerNumbers + ",检验合格待入库物料:" +  header.getItemCode() +"-"+ waitToStorageDTO.getItemName());
                cgList.add("ZouJinLin");
                if ("2".equals(header.getPlantCode())) {
                    cgList.add("JiangZhiGuang");
                }
                cgList = new ArrayList<>(new HashSet<>(cgList));
                cgList.remove("XuChengWu");
                messageDto.setTouser(cgList);
                weChatService.sendWXMessage(messageDto);
            }
        }else{
            //2、不合格的推送
            List<WaitToStorageVo> unlistvo = purchaseMapper.selectOddNumberUndecision(header.getInspectionUuid());
            //获取仓库编号唯一列表
            warehouseList = unlistvo.stream().map(WaitToStorageVo::getWarehouseCode).distinct().collect(Collectors.toList());
            //根据仓库列表查对应检验员
            if (warehouseList != null) {
                List<String> cgList = purchaseMapper.getCGQWByWarehouse(warehouseList);
                if (cgList == null) {
                    return Result.fail().message("未查询到仓管员企微账号");
                }
                //去除数组中的null值
                cgList = cgList.stream().filter(Objects::nonNull).collect(Collectors.toList());
                String customerNumbers = "";
                if(unlistvo != null && !unlistvo.isEmpty()) {
                    waitToStorageDTO.setOddNumber(unlistvo.get(0).getOddNumber());
                    waitToStorageDTO.setSupplierName(unlistvo.get(0).getSupplierFullName());
                    Set<String> set = new HashSet();
                    for(WaitToStorageVo waitVo : unlistvo) {
                        if(waitVo.getCustomerNumber() != null && !waitVo.getCustomerNumber().trim().equals("")){ set.add(waitVo.getCustomerNumber().trim());}
                    }
                    StringBuffer sb = new StringBuffer();
                    for(String str : set) {
                        sb.append(str).append(",");
                    }
                    if(sb.length() > 0) {
                        customerNumbers = sb.toString().substring(0, sb.length()-1);
                    }
                }

                List<String> cgqywxList = purchaseMapper.getCGQWBySupplierCode(header.getSupplierCode());
                if (cgqywxList != null) {
                    cgList.addAll(cgqywxList);
                }
                MessageDto messageDto = new MessageDto();
                messageDto.setContent( "供应商：" +waitToStorageDTO.getSupplierName() + "采购单：" + waitToStorageDTO.getOddNumber() + ", 到货单单号:" + waitToStorageDTO.getArrivalNumber()  + ",客户单号：" + customerNumbers + ",检验不合格物料:" +  header.getItemCode() +"-"+ waitToStorageDTO.getItemName());
                cgList.add("ZouJinLin");
                cgList.add("XuChengWu");
                if ("2".equals(header.getPlantCode())) {
                    cgList.add("JiangZhiGuang");
                }
                cgList = new ArrayList<>(new HashSet<>(cgList));
                messageDto.setTouser(cgList);
                weChatService.sendWXMessage(messageDto);
            }

        }


        return Result.ok("审核成功");
    }



    @DSTransactional
    protected HashMap<String, String> updateDetails(PurchaseArrivalCheck dto) {
        HashMap<String, String> map = new HashMap<>();
        PurchaseArrivalCheckHeader header1 = dto.getHeader();

        String arrivalDocNo = header1.getArrivalDocNo();
        String itemCode = header1.getItemCode();
        String inspectionUuid = header1.getInspectionUuid();
        String decision = header1.getDecision();
        String decisionDescription = header1.getDecisionDescription();

        String EmployeeUUID = header1.getEmployeeUUID();
        String departmentUUID = header1.getDepartmentUUID();

        //列表转字符串
        List<DefectNumReason> defectList = header1.getDefectNumReasonList();
        ObjectMapper objectMapper = new ObjectMapper();
        String defectNumReason = null;
        try {
            // 将列表转换为JSON字符串
            defectNumReason = objectMapper.writeValueAsString(defectList);
            header1.setDefectNumReason(defectNumReason);
        } catch (JsonProcessingException e) {
            // 处理转换异常
            e.printStackTrace();
        }


//        String By = "78D8F9A5-C857-45BF-D5A1-193E1F333691";
        String checkDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS"));
        System.out.println("checkDate:" + checkDate);
        //1. 查询所有相同到货单号和品号的检验单头
        List<PurchaseArrivalCheckHeader> headers = purchaseMapper.selectAllInspectionHeadersByDocNoAndItemCode(arrivalDocNo, itemCode);
        //2.将传来的检验单头判定状态更新到所有相同到货单号+品号的 检验单头中，其中根据判断状态不合格-不合格数量为送检数量；合格-合格数量为送检数量

        //将只更新第一个到货检验单身数据
        List<PurchaseArrivalCheckLine> lines = dto.getLines();
        List<PoArrivalInspectionSd1> poArrivalInspectionSd1List = new ArrayList<>();

        for (PurchaseArrivalCheckLine line : lines) {

            List<BigDecimal> measurementValueList = line.getMeasurementValue();
            //更新到货检验单身
            int b = purchaseMapper.UpdatePurchaseArrivalStorageCheckLine(line);
            if (b <= 0) {
                map.put("code", "0");
                map.put("message", "更新到货检验单身失败");
                return map;
            }
            //将前端传来的测量值插入到PO_ARRIVAL_INSPECTION_SD1
            if (measurementValueList != null && !measurementValueList.isEmpty()) {
                String uuid = line.getUuid();
                //查询该检验单身测量值最大序号，如果是null则初始化为1，否则+1

                for (BigDecimal measurementValue : measurementValueList) {
                    int maxSequenceNumber = purchaseMapper.selectSd1SequenceNumber(uuid);
                    PoArrivalInspectionSd1 poArrivalInspectionSd1 = new PoArrivalInspectionSd1();
                    poArrivalInspectionSd1.setPO_ARRIVAL_INSPECTION_SD1_ID(UUID.randomUUID().toString());
                    poArrivalInspectionSd1.setPO_ARRIVAL_INSPECTION_D_ID(uuid);
                    poArrivalInspectionSd1.setMEASURED_VALUE(measurementValue);
                    poArrivalInspectionSd1.setSequenceNumber(maxSequenceNumber);
                    poArrivalInspectionSd1.setREMARK(line.getRemark());
                    poArrivalInspectionSd1.setDECISION(line.getDecision());
//                    poArrivalInspectionSd1List.add(poArrivalInspectionSd1);
                    System.out.println("uuid: " + uuid + " 序号: " + maxSequenceNumber + "\n");
                    System.out.println("poArrivalInspectionSd1: " + poArrivalInspectionSd1 + "\n");
                    poArrivalInspectionSd1Service.save(poArrivalInspectionSd1);
                }
            }
        }

        //更新到货检验单头
        List<String> docNoList = new ArrayList<>();
        for (PurchaseArrivalCheckHeader header : headers) {
            String qcTimes = header.getQcTimes();
            System.out.println("更新到货检验单头");
            header.setDecision(decision);

            header.setDefectRepairQty(header1.getDefectRepairQty());//不良返修数
            header.setDefectNumReason(header1.getDefectNumReason());//不良数量及原因
            BigDecimal inspectionQty = header.getInspectionQty();
            BigDecimal destroyedQty = header.getDestroyedQty();
            //判断检验状态合格与否，更新为合格不合格数量
            if (decision.equals("2")) {
                //不合格数量为送检数，且合格数为0
                header.setAcceptableQty(BigDecimal.ZERO);
                header.setUnqualifiedQty(inspectionQty);
                header.setDestroyedQty(destroyedQty);
                header.setDecisionDescription(decisionDescription);
                header.setDepartmentUUID(departmentUUID);
                header.setEmployeeUUID(EmployeeUUID);
            } else {
                header.setAcceptableQty(inspectionQty);
                header.setUnqualifiedQty(BigDecimal.ZERO);
                header.setDestroyedQty(destroyedQty);
                header.setDecisionDescription(decisionDescription);
                header.setDepartmentUUID(departmentUUID);
                header.setEmployeeUUID(EmployeeUUID);
            }
            int a = purchaseMapper.UpdatePurchaseArrivalStorageCheckHeader(header);
            if (a <= 0) {
                map.put("code", "0");
                map.put("message", "更新到货检验单头失败");
                return map;
            }

            //审核到货检验单
            JSONObject qcResult = qcService.Qc(header.getCheckDocNo(), qcTimes);

            log.warn("审核到货检验单：\n{}", qcResult);
            JSONObject createArrivalJsonObject = qcResult
                    .getJSONObject("std_data")
                    .getJSONObject("parameter")
                    .getJSONObject("result");
            JSONArray createJsonArray = createArrivalJsonObject.getJSONArray("error");
            if (createJsonArray != null && !createJsonArray.isEmpty()) {
                String errorMsg = createJsonArray.getJSONObject(0).getString("message");
                // "生成到货单失败！\n" + errorMsg
                map.put("code", "0");
                map.put("message", errorMsg);
                return map;
            }
            String docNo = qcResult
                    .getJSONObject("std_data")
                    .getJSONObject("parameter")
                    .getJSONObject("result")
                    .getJSONArray("success")
                    .getJSONObject(0)
                    .getString("DOC_NO");
            docNoList.add(docNo);
            System.out.println("本次批量审核的到货检验单号docNoList:" + docNoList);
        }

        //更新该条码 lotatt30为已检验2
        //查出该到货检验单本次扫的箱码中同到货单号同品的箱码
        List<String> barcodeList = purchaseMapper.selectArrivalBarcode(arrivalDocNo, itemCode);
        // 默认0，合格更新为1，不合格更新为2
        String lotatt08 = "0";
        String lotatt30 = "2";
        if ("1".equals(decision)) {
            lotatt08= "1";
        } else if ("2".equals(decision)) {
            lotatt08 = "2";
        }
        int i = purchaseMapper.UpdateBarcodeDecisionLotatt30ByList(barcodeList,lotatt08,lotatt30);
//        int i = purchaseMapper.UpdateBarcodeLotatt30ByList(barcodeList);
        if (i <= 0) {
            map.put("code", "0");
            map.put("message", "更新标签状态失败");
            return map;
        }
        map.put("code", "1");
        map.put("message", "检验成功！");
        return map;
    }


    @DSTransactional
    protected Integer updateDetailsFree(PurchaseArrivalCheck dto) {

        PurchaseArrivalCheckHeader header1 = dto.getHeader();
        String arrivalDocNo = header1.getArrivalDocNo();
        String itemCode = header1.getItemCode();
        String inspectionUuid = header1.getInspectionUuid();
        String decision = header1.getDecision();
        String decisionDescription = header1.getDecisionDescription();

        String By = header1.getEmployeeUUID();
//        String By = "78D8F9A5-C857-45BF-D5A1-193E1F333691";
        String checkDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS"));
        System.out.println("checkDate:"+checkDate);
        //1. 查询所有相同到货单号和品号的检验单头
        List<PurchaseArrivalCheckHeader> headers = purchaseMapper.selectAllInspectionHeadersByDocNoAndItemCode(arrivalDocNo, itemCode);
        //2.将传来的检验单头判定状态更新到所有相同到货单号+品号的 检验单头中，其中根据判断状态不合格-不合格数量为送检数量；合格-合格数量为送检数量
        //3.更新2步更新掉的所有相同到货单号+品号的 到货单身的状态为已判定
        //4.遍历生成对应的采购质检单头单身
        //更新到货检验单头
        for(PurchaseArrivalCheckHeader header : headers){
            System.out.println("更新到货检验单头");
            header.setDecision(decision);
            BigDecimal inspectionQty = header.getInspectionQty();
            BigDecimal destroyedQty = header.getDestroyedQty();
            //判断检验状态合格与否，更新为合格不合格数量
            if(decision.equals("2")){
                //不合格数量为送检数，且合格数为0
                header.setAcceptableQty(BigDecimal.ZERO);
                header.setUnqualifiedQty(inspectionQty);
                header.setDestroyedQty(destroyedQty);
                header.setDecisionDescription(decisionDescription);
            }else {
                header.setAcceptableQty(inspectionQty);
                header.setUnqualifiedQty(BigDecimal.ZERO);
                header.setDestroyedQty(destroyedQty);
                header.setDecisionDescription(decisionDescription);
            }
            int a = purchaseMapper.UpdatePurchaseArrivalStorageCheckHeader(header);
            if(a<=0){
                return -1;
            }
            //更新到货单信息
            System.out.println("更新到货单信息");
            int b =purchaseMapper.UpdatePurchaseReceiptStorage(header);
            if(b<=0){
                return -1;
            }

            System.out.println("插入采购质检单头");
            POQCRESULT poqcre = new POQCRESULT();
//        poqcre.setOwner_Dept(header.getDepartmentCode());
//        poqcre.setOwner_Emp(header.getEmployeeCode());
            poqcre.setPO_QC_RESULT_ID(UUID.randomUUID().toString());
            poqcre.setApproveStatus("Y");
            poqcre.setApproveDate(checkDate);
            poqcre.setApproveBy(By);
            poqcre.setCreateDate(checkDate);
            poqcre.setLastModifiedDate(checkDate);
            poqcre.setModifiedDate(checkDate);
            poqcre.setCreateBy(By);
            poqcre.setLastModifiedBy(By);
            poqcre.setModifiedBy(By);
            poqcre.setOwner_Org_RTK("PLANT");
            // 找到arrivalDUuid的单头的PLANT值
            String plantId = purchaseMapper.getPlantByPURCHASE_ARRIVAL_D_ID(header.getArrivalDUuid());
            if(plantId == null) {
                throw new BaseException(500, "通过到货单单身UUID找单头的PLANT找不到.");
            }
            poqcre.setOwner_Org_ROid(plantId);
            //到货单单身
            poqcre.setSOURCE_ID_RTK("PURCHASE_ARRIVAL.PURCHASE_ARRIVAL_D");
            //到货单单身uuid
            poqcre.setSOURCE_ID_ROid(header.getArrivalDUuid());
            int c = poqcreSULTMapper.insert(poqcre);
            if(c<=0){
                return -1;
            }
            System.out.println("插入采购质检单身1");
            POQCRESULTD poqcreTD = new POQCRESULTD();
            poqcreTD.setPO_QC_RESULT_D_ID(UUID.randomUUID().toString());
            poqcreTD.setQC_DATE(checkDate);
            poqcreTD.setQUALIFIED_BUSINESS_QTY(header.getAcceptableQty());
            poqcreTD.setUNQUALIFIED_BUSINESS_QTY(header.getUnqualifiedQty());
            poqcreTD.setIN_DESTROYED_BUSINESS_QTY(header.getDestroyedQty());
            poqcreTD.setREMARK(header1.getRemark());
            poqcreTD.setPO_INSPECTION_CODE(header.getCheckDocNo());
            poqcreTD.setDETERMINE_QTY(BigDecimal.ZERO);//待判定数量
            poqcreTD.setRESULT_STATUS("3");
            //到货检验单单头uuid
            poqcreTD.setPO_INSPECTION_ID(header.getInspectionUuid());
            poqcreTD.setCreateDate(checkDate);
            poqcreTD.setLastModifiedDate(checkDate);
            poqcreTD.setModifiedDate(checkDate);
            poqcreTD.setCreateBy(By);
            poqcreTD.setLastModifiedBy(By);
            poqcreTD.setModifiedBy(By);
            //采购质检单单头uuid
            poqcreTD.setPO_QC_RESULT_ID(poqcre.getPO_QC_RESULT_ID());
            int d = poqcreSULTDMapper.insert(poqcreTD);
            if(d<=0){
                return -1;
            }

            System.out.println("插入采购质检单身2");
            POQCRESULTSD poqcreSD = new POQCRESULTSD();
            poqcreSD.setSequenceNumber(1);
            poqcreSD.setACCEPTED_BUSINESS_QTY(header.getAcceptableQty());//允收业务数量
            poqcreSD.setSCRAP_BUSINESS_QTY(header.getDestroyedQty());//报废业务数量
            poqcreSD.setRETURN_BUSINESS_QTY(header.getUnqualifiedQty());//拒收业务数量
            poqcreSD.setQC_DATE(checkDate);//判定日期
            poqcreSD.setREMARK(header1.getRemark());
            poqcreSD.setPO_QC_RESULT_SD_ID(UUID.randomUUID().toString());
            poqcreSD.setApproveStatus("Y");
            poqcreSD.setApproveDate(checkDate);
            poqcreSD.setApproveBy(By);
            poqcreSD.setCreateDate(checkDate);
            poqcreSD.setLastModifiedDate(checkDate);
            poqcreSD.setModifiedDate(checkDate);
            poqcreSD.setCreateBy(By);
            poqcreSD.setLastModifiedBy(By);
            poqcreSD.setModifiedBy(By);
            poqcreSD.setPO_QC_RESULT_D_ID(poqcreTD.getPO_QC_RESULT_D_ID());
            int e = poqcreSULTSDMapper.insert(poqcreSD);
            if(e<=0){
                return -1;
            }
        }

        System.out.println("插入WMS检验单头");
        //保存检验数据到自建表
        WmsPoArrivalInspection wmsPoArrivalInspection = new WmsPoArrivalInspection();
        wmsPoArrivalInspection.setInspectionUuid(header1.getInspectionUuid());
        wmsPoArrivalInspection.setCheckName(header1.getCheckName());
        wmsPoArrivalInspection.setCheckDocNo(header1.getCheckDocNo());
        wmsPoArrivalInspection.setArrivalDocNo(header1.getArrivalDocNo());
        wmsPoArrivalInspection.setArrivalDUuid(header1.getArrivalDUuid());
        wmsPoArrivalInspection.setArrivalDSequenceNumber(header1.getArrivalDSequenceNumber());
        wmsPoArrivalInspection.setItemCode(header1.getItemCode());
        wmsPoArrivalInspection.setItemName(header1.getItemName());
        wmsPoArrivalInspection.setAcceptableQty(header1.getAcceptableQty());
        wmsPoArrivalInspection.setUnqualifiedQty(header1.getUnqualifiedQty());
        wmsPoArrivalInspection.setDestroyedQty(header1.getDestroyedQty());
        wmsPoArrivalInspection.setDecision(header1.getDecision());
        wmsPoArrivalInspection.setResultStatus("3");
        wmsPoArrivalInspection.setDecisionDescription(header1.getDecisionDescription());
        wmsPoArrivalInspection.setCreateDate(header1.getCreateDate());
        wmsPoArrivalInspectionMapper.insert(wmsPoArrivalInspection);

        System.out.println("插入WMS检验单身");
        List<PurchaseArrivalCheckLine> lines = dto.getLines();
        List<WmsPoArrivalInspectionD> wmsPoArrivalInspectionDs = new ArrayList<>();
        List<PoArrivalInspectionD> poArrivalInspectionDs = new ArrayList<>();
        List<WmsPoArrivalInspectionDMeasurementValue> wmsPoArrivalInspectionDMeasurementValues = new ArrayList<>();
        for(PurchaseArrivalCheckLine line : lines) {
//            poarrivalInspectionDMapper.updatePoArrivalInspectionDById(line.getUuid(),line.getDecision(),line.getDefectiveQty());
            PoArrivalInspectionD d = new PoArrivalInspectionD();
            d.setPO_ARRIVAL_INSPECTION_D_ID(line.getUuid());
            d.setDECISION(line.getDecision());
            d.setINSPECTION_QQ(line.getDefectiveQty());
            poArrivalInspectionDs.add(d);

            List<BigDecimal> measurementValueList  =  line.getMeasurementValue();
            WmsPoArrivalInspectionD wmsPoArrivalInspectionD = new WmsPoArrivalInspectionD();
            String uuid = line.getUuid();
            wmsPoArrivalInspectionD.setId(uuid);
            wmsPoArrivalInspectionD.setInspectionUuid(header1.getInspectionUuid());
            wmsPoArrivalInspectionD.setCheckDocNo(header1.getCheckDocNo());
            wmsPoArrivalInspectionD.setSequenceNumber(line.getSequenceNumber());
            wmsPoArrivalInspectionD.setSequence(line.getSequence());
            wmsPoArrivalInspectionD.setInspectionItemCode(line.getInspectionItemCode());
            wmsPoArrivalInspectionD.setInspectionItemName(line.getInspectionItemName());
            wmsPoArrivalInspectionD.setInspectionQty(line.getInspectionQty());
            wmsPoArrivalInspectionD.setInspectionAc(line.getInspectionAc());
            wmsPoArrivalInspectionD.setInspectionRe(line.getInspectionRe());
            wmsPoArrivalInspectionD.setDefectiveQty(line.getDefectiveQty());
            wmsPoArrivalInspectionD.setRemark(line.getRemark());

            WmsPoArrivalInspectionDMeasurementValue mv  = new WmsPoArrivalInspectionDMeasurementValue();
            mv.setId(UUID.randomUUID().toString());
            mv.setWpidId(uuid);
            // 将测量值列表按顺序填充到udf01~udf10字段
            if (measurementValueList != null && !measurementValueList.isEmpty()) {
                for (int i = 0; i < Math.min(10, measurementValueList.size()); i++) {
                    BigDecimal value = measurementValueList.get(i);
                    switch (i) {
                        case 0: mv.setUdf01(value); break;
                        case 1: mv.setUdf02(value); break;
                        case 2: mv.setUdf03(value); break;
                        case 3: mv.setUdf04(value); break;
                        case 4: mv.setUdf05(value); break;
                        case 5: mv.setUdf06(value); break;
                        case 6: mv.setUdf07(value); break;
                        case 7: mv.setUdf08(value); break;
                        case 8: mv.setUdf09(value); break;
                        case 9: mv.setUdf010(value); break;
                    }
                }
            }
            wmsPoArrivalInspectionDs.add(wmsPoArrivalInspectionD);
            wmsPoArrivalInspectionDMeasurementValues.add(mv);
        }
        poarrivalInspectionDMapper.updateBatchById(poArrivalInspectionDs);
        wmsPoArrivalInspectionDService.saveBatch(wmsPoArrivalInspectionDs);
        wmsPoArrivalInspectionDMeasurementValueService.saveBatch(wmsPoArrivalInspectionDMeasurementValues);

        //更新该条码 lotatt30为已检验2
        //查出该到货检验单本次扫的箱码中同到货单号同品的箱码
        List<String> barcodeList = purchaseMapper.selectArrivalBarcode(arrivalDocNo, itemCode);
        int i = purchaseMapper.UpdateBarcodeLotatt30ByList(barcodeList);
        if (i <= 0) {
            return -1;
        }
        return 1;
    }



    @Override
    public Result getPurchaseReceiveInfo(PurchaseReceiveDto purchaseReceiveDto) {
        PurchaseReceiveVo vo = purchaseMapper.getPurchaseReceiveInfo(purchaseReceiveDto);
        return null;
    }

//    @Override
//    public Result getAllPurchaseInfo(PurchaseVo purchaseVo) {
//        List<PurchaseSumDto> purchaseList = new ArrayList<>();
//        String appModule = purchaseVo.getAppModule();
//        purchaseList = getFromRedis(appModule);
//        if (purchaseList!=null){
//            return Result.ok(purchaseList);
//        }
//        //采购收货
//        if (appModule.equals("A001")){
//            purchaseList = purchaseMapper.getAllPurchaseInfo1(purchaseVo);
//            if (purchaseList == null || purchaseList.isEmpty()) {
//                // 数据库中也不存在，则返回 false
//                return Result.fail("数据库中不存在！");
//            }else {
//                setPurchaseCache(purchaseList,appModule);
//            }
//        }
//        //扫码入库
//        if (appModule.equals("A005")){
//            purchaseList = purchaseMapper.getAllPurchaseInfo2(purchaseVo);
//            if (purchaseList == null || purchaseList.isEmpty()) {
//                // 数据库中也不存在，则返回 false
//                return Result.fail("数据库中不存在！");
//            }else {
//                setPurchaseCache(purchaseList,appModule);
//            }
//        }
//        //采购仓退
//        if (appModule.equals("A008")){
//            purchaseList = purchaseMapper.getAllPurchaseInfo3(purchaseVo);
//            if (purchaseList == null || purchaseList.isEmpty()) {
//                // 数据库中也不存在，则返回 false
//                return Result.fail("数据库中不存在！");
//            }else {
//                setPurchaseCache(purchaseList,appModule);
//            }
//        }
//        for (PurchaseSumDto purchaseList1 : purchaseList) {
//            System.out.println(purchaseList1);
//        }
//        return Result.ok(purchaseList);
//    }

    @Override
    public List<PurchaseDetailDto> getPurchaseDetailInfo(PurchaseVo purchaseVo) {
        List<PurchaseDetailDto> purchaseDetailDtoList = new ArrayList<>();
        if (purchaseVo.getAppModule().equals("D001")){
            purchaseDetailDtoList = purchaseMapper.getPurchaseDetailInfo(purchaseVo);
        }
        return purchaseDetailDtoList;
    }


    //查询采购单简易信息(采购单号，品号，品名，规格，采购数量)
    @Override
    @DS("db2")
    public Result getPurcharseInfo(PurchaseOrderSimpleDto purchaseOrderSimpleDto) {
        List<PurchaseOrderSimpleVo> list = purchaseMapper.getPurcharseInfo(purchaseOrderSimpleDto);
        if(list != null && !list.isEmpty()){
            return Result.ok(list);
        }else {
            return Result.fail().message("暂无数据");
        }

    }

    /**
     * 快速收货查询(可用)
     * @param dto
     * @return
     */
    @Override
    public Result<List<PurchaseOrderDetailVo>> getPurcharseDetail(PurchaseOrderDetailDto dto) {
        List<PurchaseOrderDetailVo> list = new ArrayList<>();
        try {
            list = purchaseMapper.getPurcharseDetail(dto);
            if(list != null && !list.isEmpty()){
                return Result.ok(list).message("查询成功");
            }else {
                return Result.ok(list).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(list).message("查询失败");
        }

    }

    /**
     * 快速收货提交（批量提交不同单号同一供应商）
     * @param fastDeliveryDto
     * @return
     */
    @Override
    public Result FastDeliveryGenerateDoc(FastDeliveryDto fastDeliveryDto) {
//        DateTime date = DateUtil.date();
//        //所有要提交的数据
//        List<ParameterDataList> allSubmitList = fastDeliveryDto.getData();
//        Collections.sort(allSubmitList,new Comparator<ParameterDataList>(){
//            @Override
//            public int compare(ParameterDataList p1, ParameterDataList p2) {
//                return p1.getDoc_no().compareTo(p2.getDoc_no());
//            }
//        });
//        //每次提交时的单号
//        String docNo="";
//        List<DocResult> docResults = new ArrayList<>();
//        //存储分次提交的数据
//        List<ParameterDataList> submitList = new ArrayList<>();
//        List<String> resultList = new ArrayList<>();
//        System.out.println("开始上传");
//        for (ParameterDataList data : allSubmitList) {
//            if (!docNo.equals(data.getDoc_no())){
//                System.out.println("docNo:"+docNo);
//                System.out.println("doc_no:"+data.getDoc_no());
//                if (!docNo.equals("")){
//                    System.out.println("第一次上传");
//                    EtenSession etenSession = new EtenSession(FASTDELIVERY_KEY,WMS_TYPE,
//                            new SessionHost(WMS_HOST_PROD,"",WMS_HOST_LANG,WMS_HOST_ACCT, FASTDELIVERY_APPID+FASTDELIVERY_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"), "zhiLink"),
//                            new SessionService(WMS_SERVICE_PROD,FASTDELIVERY_NAME,"",WMS_SERVICE_ID),
//                            new SessionDatakey(WMS_ENTID, WMS_COMPANYID),
//                            new SessionPayload(new StdData(
//                                    new FastDeliveryParameter(WMS_ENTID,WMS_COMPANYID,docNo,submitList, DateUtil.format(date,"yyyy-MM-dd"),DateUtil.format(date,"yyyy-MM-dd"),"zhiLink",
//                                            new FastDeliveryParameterHost(WMS_ACCT,FASTDELIVERY_APPID+FASTDELIVERY_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),FASTDELIVERY_APPMODULE,FASTDELIVERY_NAME, WMS_ENTID, WMS_COMPANYID, DateUtil.format(date,"yyyy-MM-dd"),DateUtil.format(date,"yyyy-MM-dd"), "zhiLink", "0")))));
//
//                    try {
//                        HttpHeaders httpHeaders = new HttpHeaders();
//                        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
//                        System.out.println(etenSession);
//                        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
//                        RestTemplate restTemplate = new RestTemplate();
//                        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
//                        JSONObject result = JSON.parseObject(response);
//                        String resultDocNo = result.getJSONObject("payload")
//                                .getJSONObject("std_data")
//                                .getJSONObject("parameter")
//                                .getJSONArray("result")
//                                .getJSONObject(0)
//                                .getString("doc_no");
////                        return Result.ok(result).message("发送成功");
////                        String resultDocNo = (String) result.get("doc_no");
////                        resultList.add(response.getPayload().getStd_data().getParameter().getResult().getDoc_no());
//                        resultList.add(resultDocNo);
//                        System.out.println(docNo+"上传成功,生成单号为:"+resultDocNo);
//                        System.out.println("submitList:"+submitList);
//                        docResults.add(new DocResult(docNo,resultDocNo,submitList));
//                        System.out.println(docResults);
//                    }catch (Exception e){
//                        return Result.fail().message("上传失败请联系管理员删除上传数据1");
//                    }
////                    submitList.clear();
//                }
//                docNo = data.getDoc_no();
//                submitList.add(data);
//            }else {
//                System.out.println("其他情况");
//                submitList.add(data);
//            }
//            if (allSubmitList.indexOf(data)==allSubmitList.size()-1){
//                System.out.println("最后一个元素");
//                EtenSession etenSession = new EtenSession(FASTDELIVERY_KEY,WMS_TYPE,
//                        new SessionHost(WMS_HOST_PROD,"",WMS_HOST_LANG,WMS_HOST_ACCT, FASTDELIVERY_APPID+FASTDELIVERY_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"), "zhiLink"),
//                        new SessionService(WMS_SERVICE_PROD,FASTDELIVERY_NAME,"",WMS_SERVICE_ID),
//                        new SessionDatakey(WMS_ENTID, WMS_COMPANYID),
//                        new SessionPayload(new StdData(
//                                new FastDeliveryParameter(WMS_ENTID,WMS_COMPANYID,docNo,submitList, DateUtil.format(date,"yyyy-MM-dd"),DateUtil.format(date,"yyyy-MM-dd"),"zhiLink",
//                                        new FastDeliveryParameterHost(WMS_ACCT,FASTDELIVERY_APPID+FASTDELIVERY_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),FASTDELIVERY_APPMODULE,FASTDELIVERY_NAME, WMS_ENTID, WMS_COMPANYID, DateUtil.format(date,"yyyy-MM-dd"),DateUtil.format(date,"yyyy-MM-dd"), "zhiLink", "0")))));
//
//                try {
//                    HttpHeaders httpHeaders = new HttpHeaders();
//                    httpHeaders.add("Content-Type","text/plain; charset=utf-8");
//                    System.out.println(etenSession);
//                    HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
//                    RestTemplate restTemplate = new RestTemplate();
//                    String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
//                    JSONObject result = JSON.parseObject(response);
//                    System.out.println(result);
//                    String resultDocNo = result.getJSONObject("payload")
//                            .getJSONObject("std_data")
//                            .getJSONObject("parameter")
//                            .getJSONArray("result")
//                            .getJSONObject(0)
//                            .getString("doc_no");
////                        return Result.ok(result).message("发送成功");
////                        String resultDocNo = (String) result.get("doc_no");
////                        resultList.add(response.getPayload().getStd_data().getParameter().getResult().getDoc_no());
//                    resultList.add(resultDocNo);
//                    System.out.println(docNo+"上传成功,生成单号为:"+resultDocNo);
//                    docResults.add(new DocResult(docNo,resultDocNo,submitList));
//                    System.out.println(docResults);
//                }catch (Exception e){
//                    e.printStackTrace();
//                    return Result.fail(resultList).message("上传失败请联系管理员删除上传数据2");
//                }
//            }
//        }
//        return Result.ok(docResults).message("全部上传成功");
        DateTime date = DateUtil.date();
        EtenSession etenSession = new EtenSession(FASTDELIVERY_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,"",WMS_HOST_LANG,WMS_HOST_ACCT, FASTDELIVERY_APPID+FASTDELIVERY_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"), "zhiLink"),
                new SessionService(WMS_SERVICE_PROD,FASTDELIVERY_NAME,"",WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID, WMS_COMPANYID),
                new SessionPayload(new StdData(
                        new FastDeliveryParameter(WMS_ENTID,WMS_COMPANYID,"",fastDeliveryDto.getData(), DateUtil.format(date,"yyyy-MM-dd"),DateUtil.format(date,"yyyy-MM-dd"),"zhiLink",
                                new FastDeliveryParameterHost(WMS_ACCT,FASTDELIVERY_APPID+FASTDELIVERY_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),FASTDELIVERY_APPMODULE,FASTDELIVERY_NAME, WMS_ENTID, WMS_COMPANYID, DateUtil.format(date,"yyyy-MM-dd"),DateUtil.format(date,"yyyy-MM-dd"), "zhiLink", "0")))));

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type","text/plain; charset=utf-8");
            System.out.println(etenSession);
            HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
            JSONObject result = JSON.parseObject(response);
            String resultDocNo = result.getJSONObject("payload")
                    .getJSONObject("std_data")
                    .getJSONObject("parameter")
                    .getJSONArray("result")
                    .getJSONObject(0)
                    .getString("doc_no");
//                        return Result.ok(result).message("发送成功");
//                        String resultDocNo = (String) result.get("doc_no");
//                        resultList.add(response.getPayload().getStd_data().getParameter().getResult().getDoc_no());

            System.out.println("上传成功,生成单号为:"+resultDocNo);
            return Result.ok(resultDocNo).message("上传成功,生成单号为:"+resultDocNo);
        }catch (Exception e){
            return Result.fail().message("上传失败请联系管理员");
        }
    }




//    @Override
//    public Result printPurchaseArrival(String docNo) {
//        List<PurchaseArrivalPrintVo> list = purchaseMapper.printPurchaseArrival(docNo);
//        for (PurchaseArrivalPrintVo vo : list) {
//            vo.setBarcode(printLineMapper.getBarcodeByDocNoItemCode(vo.getDocNo(),vo.getItemCode(),vo.getDocDate()));
//        }
//        if (list!=null && !list.isEmpty()){
//            return Result.ok(list).message("信息获取成功");
//        }else{
//            return Result.fail().message("信息获取失败，请联系管理员");
//        }
//
//    }



    @Override
    @Transactional
    public Result producePurchaseInspectionOrder(PurchaseInspectionOrderDto dto) {
        try {
            dto.setTime(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss.SSSSSS"));
            String uuid = UUID.randomUUID().toString();
            String uuid2 = UUID.randomUUID().toString();
            String uuid3 = UUID.randomUUID().toString();
            System.out.println(uuid);
            System.out.println(dto.getTime());
            //判断是否已经存在检验单
            //不存在
            if(dto.getLastCheckStatus().equals("2")){
                //已经检查完毕
                if (dto.getPendingCheckQty()-dto.getThisCheckQty()==0){
                    dto.setResultStatus("3");
                    dto.setArrivalResultStatus("4");
                    purchaseMapper.producePurchaseInspectionOrder1(uuid,dto);
                    purchaseMapper.producePurchaseInspectionOrder2(uuid,uuid2,dto);
                    purchaseMapper.producePurchaseInspectionOrder3(uuid2,uuid3,dto);
                    purchaseMapper.updatePurchaseArrival(dto);
                }else{
                    //部分检查
                    dto.setResultStatus("2");
                    dto.setArrivalResultStatus("3");
                    purchaseMapper.producePurchaseInspectionOrder1(uuid,dto);
                    purchaseMapper.producePurchaseInspectionOrder2(uuid,uuid2,dto);
                    purchaseMapper.producePurchaseInspectionOrder3(uuid2,uuid3,dto);
                    purchaseMapper.updatePurchaseArrival(dto);
                }
            }else {
                //已经存在
                //检查检查完毕
                if (dto.getPendingCheckQty()-dto.getThisCheckQty()==0){
                    dto.setResultStatus("3");
                    dto.setArrivalResultStatus("4");
                    purchaseMapper.updatePurchaseInspectionOrder2(dto);
                    purchaseMapper.updatePurchaseInspectionOrder3(dto);
                    purchaseMapper.updatePurchaseArrival(dto);
                }else {
                    //部分检查
                    dto.setResultStatus("2");
                    dto.setArrivalResultStatus("3");
                    purchaseMapper.updatePurchaseInspectionOrder2(dto);
                    purchaseMapper.updatePurchaseInspectionOrder3(dto);
                    purchaseMapper.updatePurchaseArrival(dto);
                }
            }


            return Result.ok().message("插入成功");

        }catch (Exception e){
            e.printStackTrace();
            return Result.fail().message("插入失败");
        }

    }

    @Override
    public Result<List<PurchaseArrivalDetailVo>> ListCheckPurchase(ListCheckPurchaseDto dto) {
        List<PurchaseArrivalDetailVo> list = new ArrayList<>();
        try {
            list = purchaseMapper.ListCheckPurchase(dto);
            if (list!=null && !list.isEmpty()){
                return Result.ok(list).message("查询成功");
            }else {
                return Result.ok(list).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(list).message("查询失败");
        }


    }

    @Override
    public Result ListPurchaseReturn(ListPurchaseReturnDto dto) {
        try {
            List<ListPurchaseReturnVo> list = purchaseMapper.ListPurchaseReturn(dto);
            if (list!=null && !list.isEmpty()){
                return Result.ok(list).message("查询成功");
            }else {
                return Result.ok(list).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail().message("查询失败联系管理员");
        }
    }

    @Override
    public Result ListPurchaseReturnDetail(String docNo) {
        try {
            List<ListPurchaseReturnDetailVo> list = purchaseMapper.ListPurchaseReturnDetail(docNo);
            if (list!=null && !list.isEmpty()){
                return Result.ok(list).message("查询成功");
            }else {
                return Result.ok(list).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail().message("查询失败联系管理员");
        }
    }


    @Override
    public MiddleReturnDto PurchaseReturnInsertMiddleTable(List<PurchaseReturnSubmitDto> list) {
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        for (PurchaseReturnSubmitDto dto : list) {
//            String lotNo = printLineService.getLotNoFromBarcode(dto.getBarcode(),dto.getItemCode());
            LambdaQueryWrapper<Item> queryWrapper  = new LambdaQueryWrapper<Item>().eq(Item::getItemCode, dto.getItemCode()).eq(Item::getLotControl, "T");
            //开启批号的
            if (itemMapper.selectCount(queryWrapper) > 0) {
                String barcode = dto.getBarcode();
                docNo.add(dto.getDocNo());
                purchaseMapper.insertMiddleTable2(Lsmdt.builder()
                                .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                                .lsmd001(barcode)      //条码编号
                                .lsmd002(dto.getWarehouseCode())      //仓库
                                .lsmd003(dto.getBinCode())//库位
                                .lsmd004(barcode.substring(barcode.indexOf("#") + 1))//批号
//                            .lsmd004("")//批号
                                .lsmd005(dto.getMatchQty())//异动数量
                                .lsmd006(-1)//异动类型
                                .lsmd007(dto.getDocNo())//来源单号
                                .lsmd008("0")
                                .lsmd010("0")
                                .lsmd013("admin")//PDA操作人代号
                                .lsmd014(DateUtil.format(date, "yyyy-MM-dd"))//扫描日期
                                .lsmd015(DateUtil.format(date, "HH:mm:ss.SSS"))//时间
                                .lsmd019("Y")//扣账资料否
                                .lsmd024(dto.getUnitCode())//异动数量单位
                                .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                                .lsmd026(PURCHASERETURN_APPID)//终端设备编号
                                .lsmd027(PURCHASERETURN_APPID + PURCHASERETURN_APPMODULE + WMS_APPVERSION + DateUtil.format(date, "yyMMddhhmmss"))//APP申请串号
                                .lsmd028(PURCHASERETURN_APPMODULE)//APP申请所属模组
                                .lsmd029("N")//ERP异动码
                                .lsmd030("")//备注
                                .lsmd031("1")//条码异动类型
                                .lsmd033(UUID.randomUUID().toString().trim().replace("-", ""))//APP申请序列号
                                .lsmd036(dto.getBinCode())
                                .lsmd037(barcode.substring(barcode.indexOf("#") + 1))
//                            .lsmd037("")
                                .lsmd038("")
                                .lsmd039("002")
                                .lsmd042("NNN")
                                .lsmd045("0.0")
                                .lsmd046(dto.getUnitCode())
                                .lsmd047("0.0")

                                .lsmd053(BigDecimal.ZERO)
                                .lsmd054("0.00")
                                .lsmd055(BigDecimal.ZERO)
                                .lsmd058("9998-12-31")
                                .lsmd081("0.0")
                                .lsmd082("0.0")
                                .lsmd083(dto.getItemCode())
                                .lsmd084(UUID.randomUUID().toString().trim().replace("-", ""))//字符保留栏位
                                .lsmd085(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//过账日期
                                .lsmd086(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//日期保留栏位
                                .lsmd906("0")
                                .lsmd907("0")
                                .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//资料创建日
                                .lsmdcrtid("1")//资料建立者
                                .lsmdent(WMS_ENTID)//企业代码
                                .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//最近修改日
                                .lsmdmodid("1") //资料修改者
                                .lsmdowndp("qqq") //资料所属部门
                                .lsmdownid("dcms") //资料所有者
                                .lsmdsite(WMS_COMPANYID) //营运据点
                                .lsmdstus("Y") //状态码
                                .build()
                );
            }else {
                String barcode = dto.getBarcode();
                docNo.add(dto.getDocNo());
                purchaseMapper.insertMiddleTable2(Lsmdt.builder()
                                .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                                .lsmd001(barcode)      //条码编号
                                .lsmd002(dto.getWarehouseCode())      //仓库
                                .lsmd003(dto.getBinCode())//库位
                                .lsmd004(barcode.substring(barcode.indexOf("#") + 1))//批号
//                            .lsmd004("")//批号
                                .lsmd005(dto.getMatchQty())//异动数量
                                .lsmd006(-1)//异动类型
                                .lsmd007(dto.getDocNo())//来源单号
                                .lsmd008("0")
                                .lsmd010("0")
                                .lsmd013("admin")//PDA操作人代号
                                .lsmd014(DateUtil.format(date, "yyyy-MM-dd"))//扫描日期
                                .lsmd015(DateUtil.format(date, "HH:mm:ss.SSS"))//时间
                                .lsmd019("Y")//扣账资料否
                                .lsmd024(dto.getUnitCode())//异动数量单位
                                .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                                .lsmd026(PURCHASERETURN_APPID)//终端设备编号
                                .lsmd027(PURCHASERETURN_APPID + PURCHASERETURN_APPMODULE + WMS_APPVERSION + DateUtil.format(date, "yyMMddhhmmss"))//APP申请串号
                                .lsmd028(PURCHASERETURN_APPMODULE)//APP申请所属模组
                                .lsmd029("N")//ERP异动码
                                .lsmd030("")//备注
                                .lsmd031("1")//条码异动类型
                                .lsmd033(UUID.randomUUID().toString().trim().replace("-", ""))//APP申请序列号
                                .lsmd036("")
                                .lsmd037("")
                                .lsmd038("")
                                .lsmd039("002")
                                .lsmd042("NNN")
                                .lsmd045("0.0")
                                .lsmd046(dto.getUnitCode())
                                .lsmd047("0.0")
                                .lsmd053(BigDecimal.ZERO)
                                .lsmd054("0.00")
                                .lsmd055(BigDecimal.ZERO)
                                .lsmd058("9998-12-31")
                                .lsmd081("0.0")
                                .lsmd082("0.0")
                                .lsmd083(dto.getItemCode())
                                .lsmd084(UUID.randomUUID().toString().trim().replace("-", ""))//字符保留栏位
                                .lsmd085(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//过账日期
                                .lsmd086(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//日期保留栏位
                                .lsmd906("0")
                                .lsmd907("0")
                                .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//资料创建日
                                .lsmdcrtid("1")//资料建立者
                                .lsmdent(WMS_ENTID)//企业代码
                                .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//最近修改日
                                .lsmdmodid("1") //资料修改者
                                .lsmdowndp("qqq") //资料所属部门
                                .lsmdownid("dcms") //资料所有者
                                .lsmdsite(WMS_COMPANYID) //营运据点
                                .lsmdstus("Y") //状态码
                                .build()
                );
            }
        }
        return new MiddleReturnDto(docNo.get(0),PURCHASERETURN_APPID,PURCHASERETURN_APPID+PURCHASERETURN_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),"");
    }

    /**
     * 扫码入库-同步中间表
     * @param list
     * @return
     */
    @Override
    public MiddleReturnDto PurchaseScanToStorageInsertMiddleTable(List<PurchaseToStorageDto> list, String createBy) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        String shjg = "";
        try {
            for (PurchaseToStorageDto dto : list) {
                // 查询是否启用了批号管理
                boolean isLotControlEnabled = itemMapper.selectCount(
                        new LambdaQueryWrapper<Item>()
                                .eq(Item::getItemCode, dto.getItemCode())
                                .ne(Item::getLotControl, "N")
                ) > 0;

                // 查询是否启用了库位管理
//                boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;

//                System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled + " 库位管理：" + isBinControlEnabled);
                System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled );

                String barcode = dto.getBarcode();
                String binCode = dto.getBinCode();
                String lotNumber = purchaseMapper.selectLotNumber(dto.getBarcode());
                shjg = dto.getShjg();
                docNo.add(dto.getDocNo());

                purchaseMapper.insertMiddleTable3(
                        Lsmdt.builder()
                                .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                                .lsmd001(barcode)      //条码编号
                                .lsmd002(dto.getWarehouseCode())      //仓库
                                .lsmd003(dto.getBinCode())//库位
    //                            .lsmd004(barcode.substring(barcode.indexOf("#")+1))//批号
                                .lsmd004(lotNumber) // 批号
                                .lsmd005(dto.getMatchQty())//异动数量
                                .lsmd006(1)//异动类型
                                .lsmd007(dto.getDocNo())//来源单号
                                .lsmd008("0")
                                .lsmd010("0")
                                .lsmd013("admin")//PDA操作人代号
                                .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
                                .lsmd015(DateUtil.format(date,"HH:mm:ss.SSS"))//时间
                                .lsmd019("Y")//扣账资料否
                                .lsmd024(dto.getUnitCode())//异动数量单位
                                .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                                .lsmd026(PURCHASE_SCAN_INSTORAGE_APPID)//终端设备编号
                                .lsmd027(PURCHASE_SCAN_INSTORAGE_APPID+PURCHASE_SCAN_INSTORAGE_APPMODULE+PURCHASE_INSTORAGE_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"))//APP申请串号
                                .lsmd028(PURCHASE_SCAN_INSTORAGE_APPMODULE)//APP申请所属模组
                                .lsmd029("N")//ERP异动码
                                .lsmd030(dto.getRemark())//备注
                                .lsmd031("1")//条码异动类型
                                .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                                .lsmd036(binCode) // 如果开启库位管理，赋值库位，否则为空
                                .lsmd037(isLotControlEnabled ? lotNumber : "") // 如果开启批号管理，赋值批号，否则为空
                                .lsmd038("")
                                .lsmd039("004")
                                .lsmd042("NNY")
                                .lsmd045("0.0")
                                .lsmd046(dto.getUnitCode())
                                .lsmd047("0.0")
                                .lsmd048("批次说明")
                                .lsmd053(dto.getScrapQty())
                                .lsmd054("0.00")
                                .lsmd055(dto.getRejectQty())
                                .lsmd058("9998-12-31")
                                .lsmd081("0.0")
                                .lsmd082("0.0")
                                .lsmd083(dto.getItemCode())
                                .lsmd084(UUID.randomUUID().toString().trim().replace("-",""))//字符保留栏位
                                .lsmd085(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//过账日期
                                .lsmd086(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//日期保留栏位
                                .lsmd906("0")
                                .lsmd907("0")
                                .lsmdcrtdt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//资料创建日
                                .lsmdcrtid("1")//资料建立者
                                .lsmdent(WMS_ENTID)//企业代码
                                .lsmdmoddt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//最近修改日
                                .lsmdmodid("1") //资料修改者
                                .lsmdowndp("qqq") //资料所属部门
                                .lsmdownid(createBy) //资料所有者
                                .lsmdsite(shjg) //营运据点
                                .lsmdstus("Y") //状态码
                                .build()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MiddleReturnDto(docNo.get(0),PURCHASE_SCAN_INSTORAGE_APPID,PURCHASE_SCAN_INSTORAGE_APPID+PURCHASE_SCAN_INSTORAGE_APPMODULE+PURCHASE_INSTORAGE_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),"zhilink",shjg);

//        return new MiddleReturnDto(docNo.get(0),SCANTOSTORAGE_APPID,"unknown02:00:00:00:00:00ab1cbf8632fb41ceA0054.223240307153009","");

    }

    @Override
    public JSONObject PurchaseReturnStockSubmit(MiddleReturnDto middleReturnDto) {
        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_PROD,PURCHASERETURN_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),PURCHASERETURN_APPMODULE,WMS_APPVERSION,PURCHASERETURN_NAME,WMS_ENTID,WMS_COMPANYID,middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        System.out.println(response);
        System.out.println(httpEntity.getBody());
        JSONObject result = JSON.parseObject(response);
        return result;
    }

    @Override
    public MiddleReturnDto PurchaseToStorageInsertMiddleTable(List<PurchaseToStorageDto> list) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        for (PurchaseToStorageDto dto : list) {
            String barcode = dto.getBarcode();
            docNo.add(dto.getDocNo());
            purchaseMapper.insertMiddleTable2(Lsmdt.builder()
                            .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                            .lsmd001(barcode)      //条码编号
                            .lsmd002(dto.getWarehouseCode())      //仓库
                            .lsmd003(dto.getBinCode())//库位
//                    .lsmd004(barcode.substring(barcode.indexOf("#")+1))//批号
//                    达威联调时wms与e10的批号管理不一致，先全改为* /""
                            .lsmd004("")//批号
                            .lsmd005(dto.getMatchQty())//异动数量
                            .lsmd006(1)//异动类型
                            .lsmd007(dto.getDocNo())//来源单号
                            .lsmd008("0")
                            .lsmd010("0")
                            .lsmd013("admin")//PDA操作人代号
                            .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
                            .lsmd015(DateUtil.format(date,"HH:mm:ss.SSS"))//时间
                            .lsmd019("Y")//扣账资料否
                            .lsmd024(dto.getUnitCode())//异动数量单位
                            .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                            .lsmd026(PURCHASE_INSTORAGE_APPID)//终端设备编号
                            .lsmd027(PURCHASE_INSTORAGE_APPID+PURCHASE_INSTORAGE_APPMODULE+PURCHASE_INSTORAGE_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"))//APP申请串号
                            .lsmd028(PURCHASE_INSTORAGE_APPMODULE)//APP申请所属模组
                            .lsmd029("N")//ERP异动码
                            .lsmd030("")//备注
                            .lsmd031("1")//条码异动类型
                            .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
//                    .lsmd037(barcode.substring(barcode.indexOf("#")+1))
//                    达威联调时wms与e10的批号管理不一致，先全改为* /""
                            .lsmd037("")
                            .lsmd039("002")
                            .lsmd042("NNN")
                            .lsmd045("0.0")
                            .lsmd046(dto.getUnitCode())
                            .lsmd047("0.0")
                            .lsmd053(BigDecimal.ZERO)
                            .lsmd054("0.00")
                            .lsmd055(BigDecimal.ZERO)
                            .lsmd058("9998-12-31")
                            .lsmd081("0.0")
                            .lsmd082("0.0")
                            .lsmd083(dto.getItemCode())
                            .lsmd084(UUID.randomUUID().toString().trim().replace("-",""))//字符保留栏位
                            .lsmd085(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//过账日期
                            .lsmd086(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//日期保留栏位
                            .lsmd906("0")
                            .lsmd907("0")
                            .lsmdcrtdt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//资料创建日
                            .lsmdcrtid("1")//资料建立者
                            .lsmdent(WMS_ENTID)//企业代码
                            .lsmdmoddt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//最近修改日
                            .lsmdmodid("1") //资料修改者
                            .lsmdowndp("qqq") //资料所属部门
                            .lsmdownid("dcms") //资料所有者
                            .lsmdsite(WMS_COMPANYID) //营运据点
                            .lsmdstus("Y") //状态码
                            .lsmd036(dto.getBinCode())
                            .build()
            );
        }
        return new MiddleReturnDto(docNo.get(0),PURCHASE_INSTORAGE_APPID,PURCHASE_INSTORAGE_APPID+PURCHASE_INSTORAGE_APPMODULE+PURCHASE_INSTORAGE_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),"采购入库测试");

//        return new MiddleReturnDto(docNo.get(0),SCANTOSTORAGE_APPID,"unknown02:00:00:00:00:00ab1cbf8632fb41ceA0054.223240307153009","");

    }

    @Override
    public Result<List<PurchaseDetailVo>> getPurchaseDetail(String docNo) {
        try {
            List<PurchaseDetailVo> list = purchaseMapper.getPurchaseDetail(docNo);
            if (!list.isEmpty()){
                return Result.ok(list);
            }else {
                return Result.fail(list).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }    }

    @Override
    public MiddleReturnDto PurchaseReceiptStorageInsertMiddleTable(List<PurchaseReceiptStorageDto> list) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();

            String shjg = "";
            String timestamp = PURCHASE_RECEIPT_APPID+PURCHASE_RECEIPT_APPMODULE+PURCHASE_INSTORAGE_APPVERSION+DateUtil.format(date,"yyMMddHHmmss");
            purchaseMapper.deleteBytimestamp(timestamp);
            for (PurchaseReceiptStorageDto dto : list) {
                //设置工厂编号(收货机构)
                WmsConstants.WMS_COMPANYID = dto.getShjg();

                // 查询是否启用了批号管理
                boolean isLotControlEnabled = itemMapper.selectCount(
                        new LambdaQueryWrapper<Item>()
                                .eq(Item::getItemCode, dto.getItemCode())
                                .ne(Item::getLotControl, "N")
                ) > 0;

                // 查询是否启用了库位管理
//                boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;

//                System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled + " 库位管理：" + isBinControlEnabled);

                // 通过条码扫到是哪个采购单的哪一行
                String orderNum = purchaseMapper.getPurchaseDByBarcode(dto.getBarcode());
                if(orderNum == null || orderNum.trim().equals("")) orderNum = "0";

                // 解析条码
                String barcode = dto.getBarcode();
                docNo.add(dto.getDocNo());
                String binCode = dto.getBinCode();
                shjg = dto.getShjg();
                String lotNumber = purchaseMapper.selectLotNumber(dto.getBarcode());
//                String lotNumber = "250523";
                purchaseMapper.insertMiddleTable3(
                        Lsmdt.builder()
                                .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                                .lsmd001(barcode)      //条码编号
                                .lsmd002(dto.getWarehouseCode())      //仓库
                                .lsmd003(dto.getBinCode())//库位
                                .lsmd004(lotNumber) // 批号
                                .lsmd005(dto.getMatchQty())//异动数量
                                .lsmd006(0)//异动类型
                                .lsmd007(dto.getDocNo())//来源单号
                                .lsmd008(orderNum)  // 来源项次
                                .lsmd010("0")
                                .lsmd013("admin")//PDA操作人代号
                                .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
                                .lsmd015(DateUtil.format(date,"HH:mm:ss.SSS"))//时间
                                .lsmd019("Y")//扣账资料否
                                .lsmd024(dto.getUnitCode())//异动数量单位
                                .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                                .lsmd026(PURCHASE_RECEIPT_APPID)//终端设备编号
                                .lsmd027(timestamp)//APP申请串号
                                .lsmd028(PURCHASE_RECEIPT_APPMODULE)//APP申请所属模组
                                .lsmd029("N")//ERP异动码
                                .lsmd030(dto.getRemark())//备注
//                                .lsmd031("")//条码异动类型
                                .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                                .lsmd036(binCode) // 如果开启库位管理，赋值库位，否则为空
    //                            .lsmd036(isBinControlEnabled ? binCode : "") // 如果开启库位管理，赋值库位，否则为空
                                .lsmd037(isLotControlEnabled ? lotNumber : "") // 如果开启批号管理，赋值批号，否则为空
                                .lsmd038("")
                                .lsmd039("002")
                                .lsmd042("NNN")
                                .lsmd045("0.0")
                                .lsmd046(dto.getUnitCode())
                                .lsmd047("0.0")
                                .lsmd048("")
                                .lsmd053(dto.getScrapQty())
                                .lsmd054("0.00")
                                .lsmd055(dto.getRejectQty())
                                .lsmd058("9998-12-31")
                                .lsmd081("0.0")
                                .lsmd082("0.0")
                                .lsmd083(dto.getItemCode())
                                .lsmd084(UUID.randomUUID().toString().trim().replace("-",""))//字符保留栏位
                                .lsmd085(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//过账日期
                                .lsmd086(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//日期保留栏位
                                .lsmd906("0")
                                .lsmd907("0")
                                .lsmdcrtdt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//资料创建日
                                .lsmdcrtid("1")//资料建立者
                                .lsmdent(WMS_ENTID)//企业代码
                                .lsmdmoddt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//最近修改日
                                .lsmdmodid("1") //资料修改者
                                .lsmdowndp("qqq") //资料所属部门
                                .lsmdownid("dcms") //资料所有者
                                .lsmdsite(shjg) //营运据点
                                .lsmdstus("Y") //状态码
                                .build()
                );
            }

        return new MiddleReturnDto(docNo.get(0),PURCHASE_RECEIPT_APPID,timestamp,"zhilink",shjg);

    }


    @Override
    public List<PurchaseReceiptStorageVo> getPurcharsedeliveryNo(PurcharseDeliveryDto dto) {
        // 查询条码信息
        List<PurchaseReceiptStorageVo> list =purchaseMapper.getBarCodeByDeliveryNo(dto);
        return list;
    }

    //子码采购限制
    private void barcodeLimitByPurchase(String barcode) {
        BarcodeDetails b = purchaseMapper.getBarcodeDetails(barcode);

        if(b.getLot_att30() != null){
           throw new RuntimeException("该条码已生成过到货单，请勿重复生成！");
        }

        // 校验关键字段是否为空
        if (b == null || b.getPrice() == null || b.getWarehouseCode() == null || b.getPlanArrivalDate() == null) {
            return;
        }

        // 校验价格是否为0
        if (BigDecimal.ZERO.compareTo(b.getPrice()) == 0) {
            String errorMsg = String.format(
                    "%s,%s,%s,%s,采购单价为0,不允许到货,请联系采购员:%s",
                    b.getPurchaseOrder(), b.getCustomerNo(), b.getItemCode(), b.getItemName(), b.getEmployeeName()
            );
            throw new RuntimeException(errorMsg);
        }

        // 计算允许的最早预交货日期
        LocalDateTime now = LocalDateTime.now();
        int allowedDays = Arrays.asList("1008", "1009", "2008").contains(b.getWarehouseCode())
                ? 10
                : 5;
        LocalDateTime earliestAllowedDate = now.plusDays(allowedDays);

        // 将Date类型转换为LocalDateTime进行比较（使用系统默认时区）
        LocalDateTime planArrivalDateTime = b.getPlanArrivalDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        // 校验预交货日期
        if (planArrivalDateTime.isAfter(earliestAllowedDate)) {
            String errorMsg = String.format(
                    "%s,%s,%s,%s,预交货日超出允许范围（当前日期+%d天）,请联系采购员:%s,修改交期/退回物料",
                    b.getPurchaseOrder(), b.getCustomerNo(), b.getItemCode(), b.getItemName(), allowedDays,b.getEmployeeName()
            );
            throw new RuntimeException(errorMsg);
        }
    }

    @Override
    public List<PurchaseReceiptStorageVo> getPurcharseByBarcode(PurcharseDeliveryDto dto) {
        //先判断条码是否存在
        int i = purchaseMapper.getPurcharseBarcodeIsNull(dto.getNumber());
        if( i == 0){
            throw new RuntimeException("条码不存在!");
        }


        barcodeLimitByPurchase(dto.getNumber());

        List<PurchaseReceiptStorageVo> list = new ArrayList<>();
        // 查询条码信息
        String barType = bmBarcodeDetailMapper.getBarType(dto.getNumber());
        if (barType.equals("4")) {
            List<PurchaseReceiptStorageVo> list1 =purchaseMapper.getPurcharseByBarcode(dto);
            if (list1 != null) {
                list.addAll(list1);
            }
        }
        //如果是容器码，要查出子条码返回
        if (barType.equals("5")) {
            List<PurchaseReceiptStorageVo> list2 = purchaseMapper.getPurcharseByChildBarcode(dto.getNumber());
            list2.forEach(vo1 -> {
                barcodeLimitByPurchase(vo1.getBarcode());
            });
            if (list2 != null) {
                list.addAll(list2);
            }
        }

        if(list.isEmpty()){
            throw new RuntimeException("条码已到货|检验|入库");
        }
        return list;
    }

    @Override
    public int inserinvBarcodeOperation(Result<List<PurchaseReceiptStorageVo>> purcharsedeliveryNo) {
        List<InvBarcodeOperation> invBarcodeOperationList = new ArrayList<>();

//        for (PurchaseReceiptStorageVo vo : purcharsedeliveryNo.getData()) {
//            InvBarcodeOperation invBarcodeOperation = InvBarcodeOperation.builder()
//                    .id(UUID.randomUUID().toString())
//                    .docOrgNo("abl")
//                    .orgNo("")
//                    .warehouseNo(vo.getWarehouseCode())
//                    .cellNo(vo.getBinCode())
//                    .itemNo(vo.getItemCode())
//                    .barcode(vo.getBarcode())
//                    .createBy(vo.getCreateBy())
//                    .createDate(DateUtil.date() )
//                    .updateBy(vo.getCreateBy())
//                    .createFlag(0)
//                    .lotAtt02(vo.getSupplierNo())
//                    .lotAtt03(vo.getSupplierName())
//                    .lotAtt04(vo.getItemName())
//                    .lotAtt05(vo.getItemSpec())
//                    .lotAtt06(vo.getMatchQty())
//                            .build();
//            invBarcodeOperationList.add(invBarcodeOperation);
//
//        }
//       int i =  purchaseMapper.inserinvBarcodeOperation(invBarcodeOperationList);
//        return i;
        int i= 1;
    return i;
    }

    @Override
    public Result<List<PurchaseReceiptStorageVo>> getBarCodeOperation() {
        List<PurchaseReceiptStorageVo> list = purchaseMapper.getBarCodeOperation();
        return Result.ok(list);
    }


    /**
     * 采购到货检验单头
     * @param dto
     * @return
     */
    @Override
    public Result<PageInfo<PurchaseArrivalCheckHeader>> ListPurchaseArrivalStorageCheck(PurchaseArrivalStorageCheckDto dto) {
        String barcode = dto.getBarcode();
        System.out.println("dto.getBarcode() = " + barcode);

        //根据箱码查品号和到货单号
        PurchaseArrivalCheckHeader p = purchaseMapper.getItemCodeAndArrivalDocNo(barcode);

        if (p.getArrivalDocNo() == null || p.getItemCode() == null){
            return Result.fail(new PageInfo<>(),"箱码不存在对应的品号和到货单号!");
        }
        PageHelper.startPage(dto.getPage(),dto.getSize());
        List<PurchaseArrivalCheckHeader> headerList = purchaseMapper.ListPurchaseArrivalCheckHeader(p);
        return Result.ok(new PageInfo<>(headerList));
    }


    @Override
    public Result<List<PurchaseArrivalCheck>> ListPurchaseArrivalStorageCheckAll(String checkDocNo) {
        List<PurchaseArrivalCheck> checkList = new ArrayList<>();
        PurchaseArrivalCheckHeader p = new PurchaseArrivalCheckHeader();
        p.setCheckDocNo(checkDocNo);
        List<PurchaseArrivalCheckHeader> headerList = purchaseMapper.ListPurchaseArrivalCheckHeader(p);

        for (PurchaseArrivalCheckHeader header : headerList) {
            PurchaseArrivalCheck check = new PurchaseArrivalCheck();
            //找质检附件
            List<Attachment> attachments = purchaseMapper.getAttachments(header.getItemCode());
            if (attachments != null && !attachments.isEmpty()) {
                List<String> fileNames = new ArrayList<>();
                List<String> fileUrls = new ArrayList<>();

                for (Attachment attachment : attachments) {
                    if (attachment != null) {
                        String originalFilename = attachment.getFileName();
                        fileNames.add(originalFilename);

                        // 创建唯一文件名（处理无扩展名情况）
                        String fileExtension = originalFilename.lastIndexOf(".") > 0 ?
                                originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
                        String fileName = UUID.randomUUID().toString() + fileExtension;

                        try {
                            // 创建目标目录
                            Path targetLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
                            Files.createDirectories(targetLocation);

                            // 保存文件
                            Path filePath = targetLocation.resolve(fileName);
                            Files.write(filePath, hexStringToByteArray(attachment.getContent()));

                            // 生成访问URL
                            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                                    .path("/api/preview/")
                                    .path(fileName)
                                    .toUriString();
                            fileUrls.add(fileUrl);
                        } catch (Exception e) {
                            return Result.fail(checkList, "文件保存失败!");
                        }
                    }
                }

                header.setFileName(fileNames);
                header.setContent(fileUrls);
            }
            check.setHeader(header);

            //取出字符串转为列表
            String defectNumReason = header.getDefectNumReason();
            if (defectNumReason != null || defectNumReason != "") {
                // 直接反序列化为列表
                List<DefectNumReason> defectList = JSON.parseArray(
                        defectNumReason,
                        DefectNumReason.class
                );
                header.setDefectNumReasonList(defectList);
            }

            // 检验单单身信息
            List<PurchaseArrivalCheckLine> lineList = purchaseMapper.ListPurchaseArrivalCheckLine(header.getCheckDocNo());
            //如果e10中该品没有质检方案，到货检验单身会无数据，此步手动填充空数据保证检验员能进入页面进行检验
//            if (lineList == null || lineList.isEmpty()) {
//                PurchaseArrivalCheckLine defaultLine = new PurchaseArrivalCheckLine();
//
//                // 填充SQL查询中映射的字段
//                defaultLine.setSequenceNumber(1);         // B.SequenceNumber
//                defaultLine.setUuid(""); // 生成随机UUID
//                defaultLine.setSequence(0);                // B.SEQUENCE
//                defaultLine.setInspectionItemCode("1");     // INSPECTION_ITEM.INSPECTION_ITEM_CODE
//                defaultLine.setInspectionItemName("1");     // INSPECTION_ITEM.INSPECTION_ITEM_NAME
//                defaultLine.setInspectionItemDescription("1"); // INSPECTION_ITEM.ITEM_DESCRIPTION
//                defaultLine.setDefectClass("");            // B.DEFECT_CLASS
//                defaultLine.setInspectionQty(BigDecimal.ZERO); // B.INSPECTION_QTY
//                defaultLine.setInspectionAc(BigDecimal.ZERO);  // B.INSPECTION_AC
//                defaultLine.setInspectionRe(BigDecimal.ZERO);  // B.INSPECTION_RE
//                defaultLine.setDefectiveQty(BigDecimal.ZERO); // B.INSPECTION_QQ
//                defaultLine.setDefectiveReasonDescription(""); // DEFECTIVE_REASONS.DESCRIPTION
//                defaultLine.setDefectReasonUuid("");       // DEFECTIVE_REASONS.DEFECTIVE_REASONS_ID
//                defaultLine.setDecision(true);               // B.DECISION
//                defaultLine.setImpactResult(true);           // B.IMPACT_RESULT
//                defaultLine.setStandardValue(BigDecimal.ZERO);          // B.STANDARD_VALUE
//                defaultLine.setStandardMax(BigDecimal.ZERO); // B.STANDARD_MAX
//                defaultLine.setStandardMin(BigDecimal.ZERO); // B.STANDARD_MIN
//                defaultLine.setQcMax(BigDecimal.ZERO);     // B.QC_MAX
//                defaultLine.setQcMin(BigDecimal.ZERO);     // B.QC_MIN
//                defaultLine.setRemark("该品没有检验方案，此步手动填充空数据保证检验员能进入页面进行检验！");                 // B.REMARK
//                lineList = Collections.singletonList(defaultLine);
//            }

                //用检验单身uuid查测量值，将数据set入measurementValue
                for (PurchaseArrivalCheckLine line : lineList) {
                    String checkDUuid = line.getUuid();
                    List<PoArrivalInspectionSd1> poArrivalInspectionSd1List = poArrivalInspectionSd1Mapper.getMeasurementValueByCheckDUuid(checkDUuid);
                    if (poArrivalInspectionSd1List != null && !poArrivalInspectionSd1List.isEmpty()) {
                        for (PoArrivalInspectionSd1 mv : poArrivalInspectionSd1List) {
                            List<BigDecimal> values = new ArrayList<>();
                            if (mv.getMEASURED_VALUE() != null) values.add(mv.getMEASURED_VALUE());
                            if (mv.getMEASURED_VALUE() != null) values.add(mv.getMEASURED_VALUE());
                            if (mv.getMEASURED_VALUE() != null) values.add(mv.getMEASURED_VALUE());
                            line.setMeasurementValue(values);
                        }
                    }
                }
                check.setLines(lineList);
                checkList.add(check);
            }
            return Result.ok(checkList);
        }


    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    @Override
    public Result<PageInfo<DefectiveReasons>> ListDefectiveReasons(DefectiveReasonsDto dto) {
        PageHelper.startPage(dto.getCurrent(),dto.getSize());

        List<DefectiveReasons> reasonList =   purchaseMapper.ListDefectiveReasons(dto.getReasons());
        PageInfo<DefectiveReasons> pageInfo = new PageInfo<>(reasonList);
        return Result.ok(pageInfo);
    }

    @Override
    public Result<List<PurchaseReceiptStorageVo>> getArrivalNo(PurcharseDeliveryDto dto) {
        String barcode = dto.getNumber();
//        String lotatt30 = purchaseMapper.selectBarcodelotatt30(barcode);
        //1、容器级码查出下级子码
        Integer  i = purchaseMapper.selectBarType(barcode);
        if(i != null && i == 5){
         String uuid =  purchaseMapper.selectFUuid(barcode);
        List<String> childList =  purchaseMapper.selectBarChild(uuid);
            List<PurchaseReceiptStorageVo> list= purchaseMapper.getArrivalNoByChildlist(childList);
            return Result.ok(list);
        }
        //2、直接箱码查询
        List<PurchaseReceiptStorageVo> list= purchaseMapper.getArrivalNo(dto);
        return Result.ok(list);
    }

    @Override
    public Result<PageInfo<PurchaseArrivalCheckHeader>> ListPurchaseArrivalStorageWaitCheck(PurchaseArrivalStorageWaitCheckDto dto) {
        List<String> resultStatusList = null;
        if (StringUtils.isNotBlank(dto.getResultStatus())) {
            resultStatusList = Arrays.asList(dto.getResultStatus().split(","));
        }
        PageHelper.startPage(dto.getPage(),dto.getSize(), "A.CreateDate DESC");
        List<PurchaseArrivalCheckHeader> headerList = purchaseMapper.ListPurchaseArrivalWaitCheckHeader(dto,resultStatusList);
        return Result.ok(new PageInfo<>(headerList));
    }

    @Override
    public Result<PageInfo<PurchaseReceiptStorageWaitCheckVo>> ListPurchaseReceiptStorageWaitCheck(PurchaseReceiptStorageWaitCheckDto dto) {
        PageHelper.startPage(dto.getPage(),dto.getSize());
        List<PurchaseReceiptStorageWaitCheckVo> list = purchaseMapper.ListPurchaseReceiptStorageWaitCheck(dto);
        return Result.ok(new PageInfo<>(list));

}

    @Override
    public Result<PageInfo<PurchaseReceiptStorageWaitCheckItemVo>> ListPurchaseReceiptStorageWaitCheckItem(PurchaseReceiptStorageWaitCheckItemDto dto) {
        PageHelper.startPage(dto.getPage(),dto.getSize());
        List<PurchaseReceiptStorageWaitCheckItemVo> list = purchaseMapper.ListPurchaseReceiptStorageWaitCheckItem(dto.getArrivalNo());
        return Result.ok(new PageInfo<>(list));
    }

    @Override
    public Result<PageInfo<Supplier>> ListSupplierCode(Supplier dto) {
        PageHelper.startPage(dto.getPage(),dto.getSize());

        List<Supplier> supplierList = purchaseMapper.ListSupplierCode(dto.getSupplierName());
        return Result.ok(new PageInfo<>(supplierList));
    }

    @Override
    public Result<PageInfo<PRSVo>> getArrivalInfo(PRSDto dto) {
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        List<PRSVo> resultList = purchaseMapper.getArrivalInfo(dto);
        PageInfo<PRSVo> pageInfo = new PageInfo<>(resultList);
        return Result.ok(pageInfo);
    }

    @Override
    public Result<PageInfo<PRSDVo>> getArrivalInfoD(PRSDDto dto) {
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        List<PRSDVo> resultList = purchaseMapper.getArrivalInfoD(dto);
        PageInfo<PRSDVo> pageInfo = new PageInfo<>(resultList);
        return Result.ok(pageInfo);
    }


    //
//    @Override
//    @Transactional
//    public Result createPurchaseArrival(PurchaseArrivalDto purchaseArrivalDto) {
//        //在eten中插入到货单
////        HttpHeaders requestHeaders = new HttpHeaders();
////        requestHeaders.set();
//        createPurchaseArrivalEten(purchaseArrivalDto);
//        return Result.ok().message("采购单生成成功");
//    }

    //    @Override
//    public Result createPurchaseReceipt(PurchaseArrivalDto purchaseArrivalDto) {
//        return null;
//    }
    //获取到货单数据
    //扫码入库简洁信息





    @Override
    public Result<List<PurchaseArrivalSimpleVo>>  getPurcharseArrivalInfo(PurchaseArrivalSimpleDto purchaseArrivalSimpleDto) {
        List<PurchaseArrivalSimpleVo> info = purchaseMapper.getPurcharseArrivalInfo(purchaseArrivalSimpleDto);
        if (info != null && !info.isEmpty()){
            return Result.ok(info).message("到货单简易信息获取成功");
        }else {
            return Result.fail(info).message("到货单简易信息获取失败");
        }

    }
    //扫码入库详细
    @Override
    public Result<List<PurchaseArrivalDetailVo>> getPurchaseArrivalDetail(PurchaseArrivalDetailDto purchaseArrivalDetailDto) {
        List<PurchaseArrivalDetailVo> list = new ArrayList<>();
        try {
            list = purchaseMapper.getPurchaseArrivalDetail(purchaseArrivalDetailDto);
            if (!list.isEmpty()){
                return Result.ok(list).message("查询成功");
            }else {
                return Result.ok(list).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(list).message("查询失败");
        }

    }
    //扫码入库汇总
    @Override
    public Result getPurcharseArrivalDetailByDocNo(PurchaseArrivalDetailDto purchaseArrivalDetailDto) {
        List<PurchaseArrivalDetailCollectVo> list= purchaseMapper.getPurcharseArrivalDetailByDocNo(purchaseArrivalDetailDto);
        return Result.ok(list).message("获取采购到货详细信息");
    }
    //扫码入库中间表
//    @Transactional
//    @Override
//    public MiddleReturnDto  insertMiddleTable(List<ScanToStorageDto> list) {
//        DateTime date = DateUtil.date();
//        List<String> docNo = new ArrayList<>();
//        for (ScanToStorageDto dto : list) {
//            String barcode = dto.getBarcode();
//            docNo.add(dto.getDocNo());
//            purchaseMapper.insertMiddleTable(new Lsmdt(
//                    UUID.randomUUID().toString().toUpperCase(),                               //主键
//                    barcode,                    //条码编号
//                    dto.getWarehouseCode(),              //仓库
//                    dto.getBinCode(),                    //库位
//                    barcode.substring(barcode.indexOf("#")+1),                    //批号
//                    dto.getMatchQty(),                   //异动数量
//                    1,                                   //异动类型
//                    dto.getDocNo(),                      //来源单号
////                "0",                                //来源项次
////                null,                               //目的单号
////                "0",                                //目的项次
//                    "admin",                            //PDA操作人代号
//                    DateUtil.format(date,"yyyy-MM-dd"),              //扫描日期
//                    DateUtil.format(date,"HH:mm:ss.SSS"),               //时间
//                    "Y",                                //扣账资料否
//                    dto.getUnitCode(),                   //异动数量单位
//                    1.0,                                //异动与库存单位换算率
//                    SCANTOSTORAGE_APPID,                               //终端设备编号
//                    SCANTOSTORAGE_APPID+"A005"+SCANTOSTORAGE_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),  //APP申请串号
//                    "A005",                             //APP申请所属模组
//                    "N",                                //ERP异动码
//                    "",                               //备注
//                    "1",                                //条码异动类型
//                    UUID.randomUUID().toString().trim().replace("-",""),//APP申请序列号
//                    "004",                              //039
//                    "NNY",                              //042
//                    dto.getUnitCode(),                   //046
//                    "0",                                //047
//                    dto.getScrapQty(),
//                    dto.getRejectQty(),
//                    "9998-12-31",                       //058
//                    dto.getItemCode(),                   //物料代码
//                    UUID.randomUUID().toString().trim().replace("-",""),//字符保留栏位
//                    DateUtil.format(DateUtil.date(),"yyyy-MM-dd"),                 //过账日期
//                    DateUtil.format(DateUtil.date(),"yyyy-MM-dd"),                 //日期保留栏位
//                    DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"), //资料创建日
//                    "1",                                //资料建立者
//                    WMS_ENTID,                          //企业代码
//                    DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"),//最近修改日
//                    "1",                                //资料修改者
//                    "qqq",                              //资料所属部门
//                    "dcms",                             //资料所有者
//                    WMS_COMPANYID,                      //营运据点
//                    "Y"                                //状态码
//            ));
//        }
//
//
//
////        return new MiddleReturnDto(docNo.get(0),SCANTOSTORAGE_APPID,SCANTOSTORAGE_APPID+"A005"+SCANTOSTORAGE_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),"");
//        return new MiddleReturnDto(docNo.get(0),SCANTOSTORAGE_APPID,"unknown02:00:00:00:00:00ab1cbf8632fb41ceA0054.223240307153009","");
//
//    }
    //扫码入库提交
    @Override
    public JSONObject scanToStorageSubmit(MiddleReturnDto middleReturnDto) {
        EtenSession etenSession = new EtenSession(SCANTOSTORAGE_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_PROD,SCANTOSTORAGE_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),SCANTOSTORAGE_APPMODULE,SCANTOSTORAGE_APPVERSION,
                                SCANTOSTORAGE_NAME,WMS_ENTID,WMS_COMPANYID,middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        JSONObject result = JSON.parseObject(response);
        return result;
    }

    /**
     * 采购入库提交
     * @param middleReturnDto
     * @return
     */
    @Override
    public JSONObject PurchaseToStorageSubmit(MiddleReturnDto middleReturnDto) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(MO_INSTORAGE_KEY, WMS_TYPE,
                new SessionHost(WMS_HOST_PROD, WMS_HOST_IP, WMS_HOST_LANG, WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_PROD, PURCHASE_INSTORAGE_NAME, WMS_SERVICE_IP, WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID, WMS_COMPANYID),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "", "", "", "", "", "", "", "", "", "", "", "", ""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),
                                PURCHASE_INSTORAGE_APPMODULE, PURCHASE_INSTORAGE_APPVERSION, PURCHASE_INSTORAGE_NAME, WMS_ENTID, WMS_COMPANYID,
                                middleReturnDto.getErpRemark(), "0", ""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT + "/Restful/invokeSrv", httpEntity, String.class);
        System.out.println(response);
        System.out.println(httpEntity.getBody());
        JSONObject result = JSON.parseObject(response);
        return result;
    }


    //在eten中生成到货单
    public void createPurchaseArrivalEten(PurchaseArrivalDto purchaseArrivalDto) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("docNo",purchaseArrivalDto.getDocNo());
        List<PurchaseArrival> purchaseArrivals = purchaseArrivalMapper.selectByMap(map);
        //判断是否已经有数据
        //没有则插入
        if (purchaseArrivals==null||purchaseArrivals.isEmpty()){
            //设置插入的数据
            PurchaseArrival purchaseArrival = setPurchaseArrival(purchaseArrivalDto);
            purchaseArrivalMapper.insert(purchaseArrival);
        }

    }
    //为到货单添加值
    private PurchaseArrival setPurchaseArrival(PurchaseArrivalDto purchaseArrivalDto) {
        PurchaseArrival p = new PurchaseArrival();
        String str = purchaseArrivalDto.getDocNo().substring(purchaseArrivalDto.getDocNo().indexOf("-"));
        p.setDocNo(purchaseArrivalDto.getDocCode()+str.substring(0,7));
        Doc doc = docMapper.selectBydocCode(purchaseArrivalDto.getDocCode());
        p.setDocDate(purchaseArrivalDto.getBusinessTime());
//        p.setDocId(doc.getDocId());
//        p.setCategory(doc.getCategory());
        p.setPurchaseArrivalId(UUID.randomUUID().toString());
        p.setArrivalDate(purchaseArrivalDto.getBusinessTime());
        HashMap<String, Object> map = new HashMap<>();
        purchaseOrderMapper.selectByMap((Map<String, Object>) new HashMap<String,Object>().put("DOC_NO",purchaseArrivalDto.getDocNo()));
        return p;
    }
    //从redis中获取purchase数据
//    public List<PurchaseSumDto> getFromRedis(String appModule){
//        List<PurchaseSumDto> purchaseList = new ArrayList<>();
//        //从redis中找到需要返回的值
//        List<String> purchaseLists = stringRedisTemplate.opsForList().range(PURCHASE_CODE_KEY+":"+appModule, 0, -1);
//        //找打就返回
//        // 2.判断 Redis 中是否有该缓存
//        if (purchaseLists != null && !purchaseLists.isEmpty()) {
//            // 2.1.若 Redis 中存在该缓存，则直接返回
//            for (String str : purchaseLists) {
//                purchaseList.add(JSONUtil.toBean(str, PurchaseSumDto.class));
//            }
//            return purchaseList;
//        }else {
//            return null;
//        }
//    }
    //在redis中添加缓存
//    public void setPurchaseCache(List<PurchaseSumDto> purchaseList,String appModule){
//        //采购收货
//        if (appModule.equals("A001")){
//            //在redis中添加缓存
//            for (PurchaseSumDto purchaseSumDto : purchaseList) {
//                stringRedisTemplate.opsForList().rightPushAll(PURCHASE_CODE_KEY+":"+appModule, JSONUtil.toJsonStr(purchaseSumDto));
//            }
//        }
//        //扫码入库
//        if (appModule.equals("A005")){
//            //在redis中添加缓存
//            for (PurchaseSumDto purchaseSumDto : purchaseList) {
//                stringRedisTemplate.opsForList().rightPushAll(PURCHASE_CODE_KEY+":"+appModule, JSONUtil.toJsonStr(purchaseSumDto));
//            }
//        }
//        //采购仓退
//        if (appModule.equals("A008")){
//            //在redis中添加缓存
//            for (PurchaseSumDto purchaseSumDto : purchaseList) {
//                stringRedisTemplate.opsForList().rightPushAll(PURCHASE_CODE_KEY+":"+appModule, JSONUtil.toJsonStr(purchaseSumDto));
//            }
//        }
//    }

}
