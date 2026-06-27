package com.mgkj.controller.WMS;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageInfo;
import com.mgkj.annotation.Log;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.*;
import com.mgkj.entity.InvBarcodeOperation;
import com.mgkj.mapper.InvBarcodeOperationMapper;
import com.mgkj.mapper.MOCTAMapper;
import com.mgkj.mapper.MoMapper;
import com.mgkj.service.*;
import com.mgkj.service.impl.E10ApiService;
import com.mgkj.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * @author yyyjcg
 * @date 2024/3/8
 * @Description
 */
@Slf4j
@RestController
@Api(tags = "工单管理相关接口")
@CrossOrigin
@RequestMapping("/api/MO")
public class MoController {
    @Resource
    private MoService moService;

    @Autowired
    private MoMapper moMapper;

    @Autowired
    private BmBarcodeDetailService bmBarcodeDetailService;

    @Autowired
    private InvBarcodeOperationService invBarcodeOperationService;

    @Autowired
    private InvBarcodeOperationMapper invBarcodeOperationMapper;

    @Autowired
    private InvBarcodeTransactionService invBarcodeTransactionService;

    @Autowired
    private MOCTAMapper moctaMapper;

    @Autowired
    private WeChatService weChatService;

    @Resource
    private E10ApiService e10ApiService;

    @Resource
    private FinishedgoodsinbounddetailService finishedgoodsinbounddetailService;

    @ApiOperation("工单入库-扫码接口")
    @PostMapping("/scanBarcode")
    public Result<?> scanBarcode(@RequestBody MoInStorageDto dto){
        List<MoInStorageVo> list = moMapper.queryMoInStorageSummary(dto.getCreateBy(), dto.getBarcode());
        if (list.isEmpty()) {
            // 查询bm_barcode_detail
            MoInStorageVo moInStorage = moMapper.queryMoInStorage(dto);
            if (moInStorage.getLotAtt30() != null && moInStorage.getLotAtt30().equals("4")) {
                return Result.fail().message("该条码已入库！");
            }
            String uuid = UUID.randomUUID().toString();
            moInStorage.setId(uuid);
            // 保存中间表
            moMapper.insertMoInStorageSummary(moInStorage, dto.getCreateBy());
            return Result.ok(moInStorage);
        }
        return Result.fail().message("该条码已存在！");
    }

    @ApiOperation("工单入库-查询接口")
    @PostMapping("/queryMoInStorageSummary")
    public Result<?> queryMoInStorageSummary(@RequestBody String createBy){
        List<MoInStorageVo> moInStorage = moMapper.queryMoInStorageSummary(createBy, null);
        if (moInStorage.isEmpty()) {
            return Result.ok().message("没有已扫码数据");
        }
        return Result.ok(moInStorage).message("查询成功");
    }

    @ApiOperation("工单入库-删除")
    @PostMapping("/deleteMoInStorageSummary")
    @Log("工单入库-删除")
    public Result<?> deleteMoInStorageSummary(@RequestBody String id){
        moMapper.deleteMoInStorageSummary(id);
        return Result.ok().message("删除成功");
    }

    //**************************工单入库-查bm_barcode_detail表**************************

//    /**
//     * 工单入库 (调用远程e10的查询接口否则用不了)   工单生成生产入库单并生成条码
//     * @param dto
//     * @return
//     */
//    @ApiOperation("工单入库(免-免生产入库申请)查询")
//    @PostMapping("/ListMoInStorage")
//    public Result<List<MoInStorageVo>> ListMoInStorage(@RequestBody MoInStorageDto dto){
//        //查询bm_barcode_detail
//        List<MoInStorageVo>  list = moMapper.selectListMoInStorage(dto);
//        return Result.ok(list);
//    }

    /**
     * 工单入库-根据barcode查出工单入库提交的数据
     * @param dto
     * @return
     */
//    @Log("生产管理-->根据barcode查出工单入库提交需要的数据")
    @ApiOperation("工单入库(免-免生产入库申请)查询-ABL")
    @PostMapping("/ListMoInStorageByBarcodeABL")
    public Result<?> ListMoInStorageByBarcode(@RequestBody MoInStorageByBarcodeDto dto){
        //1、普通工单提示
        //根据条码判断改工单是否检验完成
        //根据条码查询工单号-查询工单ID-视图查询工作中心；判断是否需要检验判断：注塑、包装车间，金工车间status为null
        String barcode = dto.getBarcode();
        //2、整机入库工单提示
        int  i = moMapper.selectBarcodeBooleanPackageIssuestorage(barcode);
//        if(i >= 1 ){ return Result.fail(null, "该条码已入库！"); }

        String TA021 = moMapper.selectMoDocNo(barcode);
        if (TA021 == null) {
            return Result.fail(null, "条码不存在");
        }
        if(TA021.equals("1002") || TA021.equals("2002")){
            String status = moMapper.selectMoZsStatus(barcode);
            if ("待检验".equals(status)) { return Result.fail(null, "该工单未检验,请先检验");}
            if ("NG".equals(status)) { return Result.fail(null, "该工单检验不合格"); }
//            if(status == null){ return Result.fail(null, "该工单检验状态未知，请检查"); }
        }else if(TA021.equals("1005") || TA021.equals("2005")){
            String status = moMapper.selectMoBzStatus(barcode);
            if ("待检验".equals(status)) {  return Result.fail(null, "该工单未检验,请先检验");  }
            if ("NG".equals(status)) { return Result.fail(null, "该工单检验不合格"); }
            if(status == null){ return Result.fail(null, "该工单检验状态未知，请检查"); }
        }
        //2、整机入库工单提示
//        int  i1 = moMapper.selectBarcodeBooleanPackageIssuestorage(barcode);

        List<MoInStorageVo> list = moMapper.selectListMoInStorageByBarcode(dto);
        MoInStorageCountVo countVo = moMapper.queryCount(barcode);   // 组装工单
        MoInStorageCountVo oneCountVo = moMapper.queryCountBaoZhuang(barcode);   // 包装工单

        // 查询已入库的标签和没有入库的标签
        List<String> alreadyScanCodeList = moMapper.queryAlreadyScanCode(barcode);
        List<String> noScanCodeList = moMapper.queryNoScanCode(barcode);

        HashMap<String, Object> map = new HashMap<>();
        map.put("countVo", countVo);
        map.put("oneCountVo", oneCountVo);
        map.put("alreadyScanCodeList", alreadyScanCodeList);
        map.put("noScanCodeList", noScanCodeList);
        map.put("list", list);
        if(i >= 1 ){
            map.put("list", null);
            return Result.ok(map).message("该条码已入库！");
        }
        return Result.ok(map);
    }

    @ApiOperation("整车入库扫码")
    @PostMapping("/finishedGoodsReceipt/scan")
    public Result<?> finishedGoodsReceiptScan(@RequestBody MoInStorageByBarcodeDto dto) {
        String barcode = dto.getBarcode();
        // 1、查询是否已扫码
        QueryWrapper<FinishedGoodsInboundDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("barcode", barcode);
        long count = finishedgoodsinbounddetailService.count(queryWrapper);
        int i = moMapper.selectBarcodeBooleanPackageIssuestorage(barcode);

        String TA021 = moMapper.selectMoDocNo(barcode);
        if (TA021 == null) {
            return Result.fail(null, "条码不存在");
        }
        if (TA021.equals("1002") || TA021.equals("2002")) {
            String status = moMapper.selectMoZsStatus(barcode);
            if ("待检验".equals(status)) {
                return Result.fail(null, "该工单未检验,请先检验");
            }
            if ("NG".equals(status)) {
                return Result.fail(null, "该工单检验不合格");
            }
        } else if (TA021.equals("1005") || TA021.equals("2005")) {
            String status = moMapper.selectMoBzStatus(barcode);
            if ("待检验".equals(status)) {
                return Result.fail(null, "该工单未检验,请先检验");
            }
            if ("NG".equals(status)) {
                return Result.fail(null, "该工单检验不合格");
            }
            if (status == null) {
                return Result.fail(null, "该工单检验状态未知，请检查");
            }
        }

        List<MoInStorageVo> list = moMapper.selectListMoInStorageByBarcode(dto);
        MoInStorageVo mo = list.get(0);
        MoInStorageCountVo countVo = moMapper.queryCount(barcode);   // 组装工单
        MoInStorageCountVo oneCountVo = moMapper.queryCountBaoZhuang(barcode);   // 包装工单

        // 查询已入库的标签和没有入库的标签
        List<String> alreadyScanCodeList = moMapper.queryAlreadyScanCode(barcode);
        List<String> noScanCodeList = moMapper.queryNoScanCode(barcode);
        List<String> noScanBarCodeList = moMapper.queryNoScanBarCodeList(barcode);

        HashMap<String, Object> map = new HashMap<>();
        map.put("countVo", countVo);
        map.put("oneCountVo", oneCountVo);
        map.put("alreadyScanCodeList", alreadyScanCodeList);
        map.put("noScanCodeList", noScanCodeList);
        map.put("noScanBarCodeList", noScanBarCodeList);
        if (count > 0) {
            return Result.ok(map).message("该条码已存在！");
        }
        if (i > 0) {
            map.put("barcodeInfo", null);
            return Result.ok(map).message("该条码已入库！");
        }
        FinishedGoodsInboundDetail finishedgoodsinbounddetail = new FinishedGoodsInboundDetail();
        finishedgoodsinbounddetail.setBarcode(dto.getBarcode());
        finishedgoodsinbounddetail.setWarehouseCode(mo.getWarehouseCode());
        finishedgoodsinbounddetail.setWarehouseName(mo.getWarehouseName());
        finishedgoodsinbounddetail.setBinCode(mo.getBinCode());
        finishedgoodsinbounddetail.setLotNo(mo.getLotNo());
        finishedgoodsinbounddetail.setQty(BigDecimal.valueOf(mo.getQty()));
        finishedgoodsinbounddetail.setDocNo(mo.getSourceNo());
        finishedgoodsinbounddetail.setCustomerDocNo(mo.getCustomerNo());
        finishedgoodsinbounddetail.setUnitCode(mo.getUnitNo());
        finishedgoodsinbounddetail.setItemCode(mo.getItemNo());
        finishedgoodsinbounddetail.setItemName(mo.getItemName());
        finishedgoodsinbounddetail.setItemSpec(mo.getItemSpec());
        finishedgoodsinbounddetail.setOrgNo(Integer.valueOf(mo.getShjg()));
        finishedgoodsinbounddetail.setCreateBy(dto.getCreateBy());
        boolean saved = finishedgoodsinbounddetailService.save(finishedgoodsinbounddetail);
        if (!saved) {
            return Result.fail(map).message("提交失败！");
        }
        map.put("barcodeInfo", finishedgoodsinbounddetail);
        return Result.ok(map).message("提交成功！");
    }

    @ApiOperation("整车入库-提交数据列表")
    @PostMapping("/finishedGoodsReceipt/list")
    public Result<?> finishedGoodsReceiptList(@RequestBody QueryFinishedGoodsInboundDetail dto) {
        QueryWrapper<FinishedGoodsInboundDetail> queryWrapper = new QueryWrapper<>();
        if (dto.getOrgNo() == null) {
            return Result.fail(null, "工厂ID不能为空");
        }
        queryWrapper.eq("orgNo", dto.getOrgNo());
        if (StrUtil.isNotEmpty(dto.getCustomerDocNo())) {
            queryWrapper.like("customerDocNo", dto.getCustomerDocNo());
        }
        if (StrUtil.isNotEmpty(dto.getDocNo())) {
            queryWrapper.like("docNo", dto.getDocNo());
        }
        if (StrUtil.isNotEmpty(dto.getStartTime())) {
            queryWrapper.gt("createTime", dto.getStartTime());
        }
        if (StrUtil.isNotEmpty(dto.getEndTime())) {
            queryWrapper.lt("createTime", dto.getEndTime());
        }
        if (StrUtil.isNotEmpty(dto.getCreateBy())) {
            queryWrapper.eq("createBy", dto.getCreateBy());
        }
        if (dto.getStatus() != null) {
            queryWrapper.eq("status", dto.getStatus());
        }
        queryWrapper.orderByDesc("createTime");
        return Result.ok(finishedgoodsinbounddetailService.list(queryWrapper));
    }


    @ApiOperation("整车入库-删除扫码")
    @PostMapping("/finishedGoodsReceipt/deleteScan/{id}")
    public Result<?> finishedGoodsReceiptDeleteScan(@PathVariable("id") Long id) {
        boolean b = finishedgoodsinbounddetailService.removeById(id);
        if (!b){
            return Result.fail().message("删除失败！");
        }
        return Result.ok().message("删除成功！");
    }

    //    @Log("生产管理-->工单入库")
    @ApiOperation("工单入库(免-免生产入库申请)-提交-维护工单(已审核)->->生成维护生产入库单(已审核)")
    @PostMapping("/getMoInStorageSubmit/{creatBy}")
    @Log("工单入库(免-免生产入库申请)-提交-维护工单(已审核)->->生成维护生产入库单(已审核)")
    public Result MoInStorageSubmit(@RequestBody List<MoInStorageSubmitDto> dtoList,
                                    @PathVariable("creatBy") String createBy){

        Set<String> uniqueDocNos = dtoList.stream()
                .map(MoInStorageSubmitDto::getDocNo)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        // 查询工单产出信息
        List<MoProductInfoDto> moProductInfoDtoList = moMapper.queryMoProduct(uniqueDocNos);
        // 补充回收料
        appendRecycleProduct(dtoList, moProductInfoDtoList);
        MiddleReturnDto middleReturnDto = moService.insertMoInStorageMiddleTable(dtoList, createBy);
        JSONObject jsonObject = moService.MoInStorageSubmit(middleReturnDto, createBy);
        // 获取执行结果的 code
        String executionCode = jsonObject
                .getJSONObject("payload")
                .getJSONObject("std_data")
                .getJSONObject("execution")
                .getString("code");

        if ("0".equals(executionCode)) {
            String moDocNo = jsonObject
                    .getJSONObject("payload")
                    .getJSONObject("std_data")
                    .getJSONObject("parameter")
                    .getJSONArray("als_app_request_result")
                    .getJSONObject(0)
                    .getString("doc_no");
            for(MoInStorageSubmitDto listDto : dtoList){
                listDto.setMoReceiptNo(moDocNo);
                int i = moMapper.updateBarcodeWBU(listDto);
            }
            moMapper.deleteMoInStorageSummaryByCreatBy(createBy);
        }else{
            return Result.fail(jsonObject).message("添加失败");
        }

        return Result.ok(jsonObject).message("添加成功");

    }

    /**
     * 自动补充回收料入库明细
     */
    private static void appendRecycleProduct(List<MoInStorageSubmitDto> dtoList,
                                      List<MoProductInfoDto> moProductInfoDtoList) {

        if (dtoList == null || dtoList.isEmpty()
                || moProductInfoDtoList == null || moProductInfoDtoList.isEmpty()) {
            return;
        }

        // 按docNo分组
        Map<String, List<MoInStorageSubmitDto>> dtoGroupByDocNo =
                dtoList.stream().collect(Collectors.groupingBy(MoInStorageSubmitDto::getDocNo));

        // 按 docNo 分组 工单产品信息
        Map<String, List<MoProductInfoDto>> productGroupByDocNo =
                moProductInfoDtoList.stream().collect(Collectors.groupingBy(MoProductInfoDto::getDocNo));

        List<MoInStorageSubmitDto> recycleDtoList = new ArrayList<>();

        for (Map.Entry<String, List<MoInStorageSubmitDto>> entry : dtoGroupByDocNo.entrySet()) {

            String docNo = entry.getKey();
            List<MoInStorageSubmitDto> docDtoList = entry.getValue();
            List<MoProductInfoDto> productList = productGroupByDocNo.get(docNo);

            if (productList == null || productList.isEmpty()) {
                continue;
            }

            // 主产品
            MoProductInfoDto mainProduct = productList.stream()
                    .filter(p -> p.getProductType() != null && p.getProductType() == 1)
                    .findFirst()
                    .orElse(null);

            // 回收料
            MoProductInfoDto recycleProduct = productList.stream()
                    .filter(p -> p.getProductType() != null && p.getProductType() == 4)
                    .findFirst()
                    .orElse(null);

            // 没有回收料
            if (mainProduct == null || recycleProduct == null) {
                continue;
            }

            // 主产品实际扫码入库量
            BigDecimal mainInQty = docDtoList.stream()
                    .map(MoInStorageSubmitDto::getMatchQty)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            if (mainInQty.compareTo(BigDecimal.ZERO) <= 0
                    || mainProduct.getPlanQty() == null
                    || mainProduct.getPlanQty().compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }

            // 回收料入库量 = 主产品扫码入库量 / 主产品预计产量 × 回收料预计产量
            BigDecimal recycleInQty = mainInQty
                    .multiply(recycleProduct.getPlanQty())
                    .divide(mainProduct.getPlanQty(), 6, BigDecimal.ROUND_HALF_UP);

            if (recycleInQty.compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }

            MoInStorageSubmitDto baseDto = docDtoList.get(0);
            MoInStorageSubmitDto recycleDto = new MoInStorageSubmitDto();

            recycleDto.setDocNo(docNo);
            recycleDto.setMoReceiptNo(baseDto.getMoReceiptNo());
            recycleDto.setBarcode(baseDto.getBarcode());
            recycleDto.setItemCode(recycleProduct.getItemCode());
            recycleDto.setUnitNo(recycleProduct.getUnitCode());
            recycleDto.setWarehouseCode(recycleProduct.getWarehouseCode());
            recycleDto.setBinCode(baseDto.getBinCode());
            recycleDto.setMatchQty(recycleInQty);
            recycleDto.setRejectQty(BigDecimal.ZERO);
            recycleDto.setScrapQty(BigDecimal.ZERO);
            recycleDto.setRemark("WMS-回收料入库");
            recycleDto.setLotDesc(baseDto.getLotDesc());
            recycleDto.setLotNumber(baseDto.getLotNumber());
            recycleDto.setShjg(baseDto.getShjg());

            recycleDtoList.add(recycleDto);
        }

        // 追加回收料
        dtoList.addAll(recycleDtoList);
    }

    @ApiOperation("ABL装配裸机工单入库-提交-维护主工单(已审核)->生成裸机品维护生产入库单->生成裸机品维护领料出库单(已审核)->->生成主品维护生产入库单(已审核)")
    @PostMapping("/getLjzpMoInStorageSubmit/{createBy}")
    @Log("ABL装配裸机工单入库-提交-维护主工单(已审核)->生成裸机品维护生产入库单->生成裸机品维护领料出库单(已审核)->->生成主品维护生产入库单(已审核)")
//    @DSTransactional
    public Result LjzpMoInStorageSubmit(@RequestBody List<MoInStorageSubmitDto> dtoList, @PathVariable("createBy") String createBy) {
        if (StrUtil.isBlank(createBy)) {
            return Result.fail().message("创建人不能为空");
        }
        JSONObject finalResult = new JSONObject();
        StringBuilder msg = new StringBuilder();
        HashSet<String> userSet = new HashSet<>();
        // 1. 将 dtoList 按工单号分组
        Map<String, List<MoInStorageSubmitDto>> groupedByDocNo = dtoList.stream()
                .collect(Collectors.groupingBy(MoInStorageSubmitDto::getDocNo));

        // 2. 遍历每个工单组进行处理
        for (Map.Entry<String, List<MoInStorageSubmitDto>> entry : groupedByDocNo.entrySet()) {
            String docNo = entry.getKey();

            List<MoInStorageSubmitDto> currentList = entry.getValue();
            BigDecimal sumQty = BigDecimal.ZERO;
            // 3. 查询裸机入库信息并设置各条码的 matchQty
            List<MoLjzpInStorageSubmitDto> ljInStorageList = new ArrayList<>();
            String ljProduct = "";
            for (MoInStorageSubmitDto submitDto : currentList) {
                sumQty = sumQty.add(submitDto.getMatchQty());
                List<MoLjzpInStorageSubmitDto> ljRawList = moService.selectLjMo(submitDto.getBarcode());
                for (MoLjzpInStorageSubmitDto ljInStorage : ljRawList) {
                    ljProduct = ljInStorage.getItemCode();
                    ljInStorage.setMatchQty(submitDto.getMatchQty());
                }
                ljInStorageList.addAll(ljRawList);
            }
            msg.append("工单号:").append(docNo).append("数量:").append(sumQty).append("\n");
            if (docNo.startsWith("5156")) {
                userSet.add("GeMingYing");
                userSet.add("YouJingMan");
                userSet.add("HuLin");
            }
            if (docNo.startsWith("5106")) {
                userSet.add("LiYuBao");
                userSet.add("TanYang");
                userSet.add("ZhangYiZe");
            }
            // 4. 裸机入库
//            System.out.println("裸机入库======================================================");
//            MiddleReturnDto middleReturnDto1 = moService.insertABlLjZpMoInStorageMiddleTable(ljInStorageList);
//            JSONObject result1 = moService.MoInStorageSubmit(middleReturnDto1);
//            String executionCode1 = result1.getJSONObject("payload")
//                    .getJSONObject("std_data").getJSONObject("execution").getString("code");
//            if (!"0".equals(executionCode1)) {
//                return Result.fail(result1);
//            }
//            String bareStorageDocNo = result1.getJSONObject("payload")
//                    .getJSONObject("std_data").getJSONObject("parameter")
//                    .getJSONArray("als_app_request_result").getJSONObject(0)
//                    .getString("doc_no");

            // 判断是否需要调用裸机的发料，如果工单的料已经够这是出库的数量了，则不需要调用领料了
            MOCTA cpDocNo = moctaMapper.getByTA001TA002(docNo);
            if("y".equals(cpDocNo.getTA011()) || "Y".equals(cpDocNo.getTA011())) {
                JSONObject response = new JSONObject();
                response.put("srvver", "1.0");
                response.put("srvcode", "000");

                // 构建payload
                JSONObject payload = new JSONObject();
                JSONObject stdData = new JSONObject();

                // 构建execution部分
                JSONObject execution = new JSONObject();
                execution.put("code", "-1");
                execution.put("sql_code", "");
                execution.put("description", "此成品工单:"+docNo+"已结束！");
                JSONObject parameter = new JSONObject();
                parameter.put("als_app_request_result", new JSONObject());
                stdData.put("execution", execution);
                stdData.put("parameter", parameter);
                payload.put("std_data", stdData);
                response.put("payload", payload);
                return Result.fail(response);

            }

            // 已入库的数量 + 现在要入库的数量
            BigDecimal allNeed = cpDocNo.getTA017().add(new BigDecimal(currentList.size()));
            BigDecimal issuedQty = moctaMapper.getTb005ByTb001Tb002AndProductId(ljProduct, docNo);
            String bareIssueDocNo = "";
//            System.out.println("docNo = " + docNo);
//            System.out.println("ljProduct = " + ljProduct);
//            System.out.println("allNeed = " + allNeed);
//            System.out.println("issuedQty = " + issuedQty);
            if(allNeed.compareTo(issuedQty) > 0) {
                // 5. 裸机发料准备

                List<MoLjZpIssueSubmitDto> ljIssueList = ljInStorageList.stream().map(lj -> {
                    MoLjZpIssueSubmitDto issueDto = new MoLjZpIssueSubmitDto();
                    issueDto.setDocNo(docNo);  // 这个是主品工单(包装工单单号)
                    issueDto.setBarcode(lj.getBarcode());
                    issueDto.setItemCode(lj.getItemCode());
                    issueDto.setUnitCode(lj.getUnitCode());
                    issueDto.setBinCode(lj.getBinCode());
                    issueDto.setWarehouseCode(lj.getWarehouseCode());
                    issueDto.setMatchQty(lj.getMatchQty());
                    issueDto.setCurrentNum(lj.getMatchQty());
                    issueDto.setShjg(lj.getShjg());
                    return issueDto;
                }).collect(Collectors.toList());

                // 6. 裸机发料
                System.out.println("裸机发料======================================================");
                MiddleReturnDto middleReturnDto2 = moService.insertABlLjZpMiddleTable(ljIssueList, createBy);
                JSONObject result2 = moService.MoIssueSubmit(middleReturnDto2, createBy);
                String executionCode2 = result2.getJSONObject("payload")
                        .getJSONObject("std_data").getJSONObject("execution").getString("code");
                if (!"0".equals(executionCode2)) {
                    return Result.fail(result2);
                }
                bareIssueDocNo = result2.getJSONObject("payload")
                        .getJSONObject("std_data").getJSONObject("parameter")
                        .getJSONArray("als_app_request_result").getJSONObject(0)
                        .getString("doc_no");
            }


            // 7. 主品入库
            System.out.println("主品入库======================================================");
            MiddleReturnDto middleReturnDto3 = moService.insertMoInStorageMiddleTable(currentList, createBy);
            JSONObject result3 = moService.MoInStorageSubmit(middleReturnDto3, createBy);
            String executionCode3 = result3.getJSONObject("payload")
                    .getJSONObject("std_data").getJSONObject("execution").getString("code");
            if (!"0".equals(executionCode3)) {
                return Result.fail(result3);
            }
            String packageStorageDocNo = result3.getJSONObject("payload")
                    .getJSONObject("std_data").getJSONObject("parameter")
                    .getJSONArray("als_app_request_result").getJSONObject(0)
                    .getString("doc_no");
            // 撤审生产入库单
            JSONObject jsonObject = e10ApiService.disConfirmMoReceipt(packageStorageDocNo);
            log.warn("撤审生产入库单响应: " + jsonObject);
            JSONObject object = jsonObject.getJSONObject("std_data")
                    .getJSONObject("parameter")
                    .getJSONObject("result");
            JSONArray jsonArray = object.getJSONArray("error");
            if (jsonArray != null && !jsonArray.isEmpty()) {
                String errorMsg = jsonArray.getJSONObject(0).getString("message");
                errorMsg = "生产入库单撤审失败\n" + errorMsg;
                return Result.fail(errorMsg).message(errorMsg);
            }
            // 8. 回写每个 barcode 的三张单号 + 更新状态
            for (MoInStorageSubmitDto submitDto : currentList) {
                submitDto.setMoReceiptNo(packageStorageDocNo);
                moMapper.insertPackageIssuestorage(
                        submitDto.getBarcode(),
                        "",
                        bareIssueDocNo,
                        packageStorageDocNo,
                        docNo);

                moMapper.updateBarcodeWBU(submitDto);
            }

            // 9. 保存最后一次成功结果
            finalResult = result3;
        }

        MessageDto messageDto = new MessageDto();
        try{
            messageDto.setContent("整车入库成功！\n" + msg);
            userSet.add("HuLiQin");
            userSet.add("ZhuGuoLin");
            List<String> userList = new ArrayList<>(userSet);
            messageDto.setTouser(userList);
            weChatService.sendWXMessage(messageDto);
        } catch (Exception e) {
            log.error("发送微信消息失败, content: {}", messageDto.getContent(), e);
        }


        List<String> ids = dtoList.stream()
                .map(MoInStorageSubmitDto::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (ids.isEmpty()) {
            // 可选：记录日志，说明为什么没更新
            log.error("请求参数{}，ids为空", JSON.toJSONString(dtoList));
            return Result.ok(finalResult);
        }
        LambdaUpdateWrapper<FinishedGoodsInboundDetail> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.in(FinishedGoodsInboundDetail::getId, ids);
        lambdaUpdateWrapper.set(FinishedGoodsInboundDetail::getStatus, 1);
        lambdaUpdateWrapper.set(FinishedGoodsInboundDetail::getRemark, LocalDateTimeUtil.now().toString());
        finishedgoodsinbounddetailService.update(lambdaUpdateWrapper);
        return Result.ok(finalResult);
    }



//    <<=============================工单检验-质检-审核UpdateMoStorageCheck未写完========================================>>

    @ApiOperation("生产入库检验-查询检验单头")
    @PostMapping("/ListMoStorageCheck")
    public Result<PageInfo<MoCheckHeader>> ListMoStorageCheck(@RequestBody MoStorageCheckDto dto){
        return moService.ListMoStorageCheck(dto);
    }

    @ApiOperation("生产入库检验-查询检验单详情")
    @PostMapping("/ListMoStorageCheckAll/{checkDocNo}")
    public Result<List<MoCheck>> ListMoStorageCheckAll(@PathVariable("checkDocNo") String checkDocNo){
        return moService.ListMoStorageCheckAll(checkDocNo);
    }

    @ApiOperation("审核生产入库检验单")
    @PostMapping("/UpdateMoStorageCheck")
    @Log("审核生产入库检验单")
    public Result UpdateMoStorageCheck(@RequestBody MoCheck dto){
        return moService.UpdateMoStorageCheck(dto);
    }





//    <<***********************************************工单领退料（无需领料申请单）*************************************************>>


    //工单发料    工单========>领料出库单    没用到
    @ApiOperation("工单发料-简洁查询")
    @PostMapping("/ListMoSimpleInfo")
    public Result ListMoSimpleInfo(@RequestBody MoSimpleDto moSimpleDto){
        return moService.ListMoSimpleInfo(moSimpleDto);
    }

    /**
     * 工单发料(工单生成领料出库单)    查询接口
     * @param moDetailDto
     * @return
     */
    @ApiOperation("工单发料-详细查询")
    @PostMapping("/getMoDetailInfo")
    public Result<List<MoDetailVo>> getMoDetailInfo(@RequestBody(required = false) MoDetailDto moDetailDto){
        return moService.getMoDetailInfo(moDetailDto);
    }
    //没用到
//    @ApiOperation("工单发料-获取工单汇总信息")
//    @GetMapping("/ListMoCollectInfo")
//    public Result ListMoCollectInfo(String docNo){
//        return moService.ListMoCollectInfo(docNo);
//    }
//

    @ApiOperation("工单发料-提交-工单(已审核)->->生成领料出库单(已审核)")
    @PostMapping("/MoIssueSubmit/{createBy}")
    @Log("工单发料-提交-工单(已审核)->->生成领料出库单(已审核)")
    public Result MoIssueSubmit(@RequestBody List<MoIssueSubmitDto> moIssueSubmitDto, @PathVariable("createBy") String createBy){
        if (StrUtil.isBlank(createBy)) {
            return Result.fail().message("创建人不能为空");
        }
        try {
            //同步中间表
            MiddleReturnDto middleReturnDto = moService.insertMiddleTable(moIssueSubmitDto, createBy);
            //提交
            JSONObject jsonObject = moService.MoIssueSubmit(middleReturnDto, createBy);
            return Result.ok(jsonObject).message("添加成功");
        }catch (Exception e){
            return Result.fail().message("添加失败");
        }
    }

    //    工单退料
    @ApiOperation("工单退料-简洁查询")
    @PostMapping("/ListMoReturnSimpleInfo")
    public Result ListMoReturnSimpleInfo(@RequestBody MoReturnSimpleDto moReturnSimpleDto){
        return moService.ListMoReturnSimpleInfo(moReturnSimpleDto);
    }

    //工单退料     工单=====>退料入库单
    @ApiOperation("工单退料-详细查询")
    @PostMapping("/ListMoReturnCollectInfo")
    public Result ListMoReturnCollectInfo(@RequestBody MoReturnSimpleDto moReturnSimpleDto){
        return moService.ListMoReturnCollectInfo(moReturnSimpleDto);
    }

    @ApiOperation("工单退料-提交-工单(已审核)->工单发料(已审核)->领料出库单(已审核)->->生成退料入库单(已审核)")
    @PostMapping("/MoReturnSubmit")
    @Log("工单退料-提交-工单(已审核)->工单发料(已审核)->领料出库单(已审核)->->生成退料入库单(已审核)")
    public Result MoReturnSubmit(@RequestBody List<MoReturnSubmitDto> list){
        try {
            MiddleReturnDto middleReturnDto = moService.insertMoReturnMiddleTable(list);
            JSONObject jsonObject = moService.MoReturnSubmit(middleReturnDto);
            return Result.ok(jsonObject).message("添加成功");
        }catch (Exception e){
            return Result.fail().message("添加失败");
        }
    }




    //    <<***********************************************工单领退料（需领料申请单）*************************************************>>

    //领料下架ISSUE_RECEIPT       领料申请单=======>领料出库单  没用到
//    @ApiOperation("领料下架-简洁查询(标准)")
//    @PostMapping("/ListIssueReceiptSimpleInfo")
//    public Result ListIssueReceiptSimpleInfo(@RequestBody IssueReceiptSimpleDto issueReceiptSimpleDto){
//        return moService.ListIssueReceiptSimpleInfo(issueReceiptSimpleDto);
//    }
//
//    /**
//     * 领料下架(领料申请单生成领料出库单)  查询接口
//     * @param issueReceiptDeatailDto
//     * @return
//     */
//    @ApiOperation("领料下架-详细查询")
//    @PostMapping("/ListIssueReceiptDetailInfo")
//    public Result<List<IssueReceiptDetailVo>> ListIssueReceiptDetailInfo(
//            @RequestBody(required = false) IssueReceiptDeatailDto issueReceiptDeatailDto) {
//        return moService.ListIssueReceiptDetailInfo(issueReceiptDeatailDto);
//    }
//
//
//    @ApiOperation("领料下架-获取领料单汇总信息")
//    @GetMapping("/ListIssueReceiptCollectInfo")
//    @ApiImplicitParam(name ="docNo",value = "领料单号")
//    public Result<IssueReceiptCollectVo> ListIssueReceiptCollectInfo(String docNo){
//        return moService.ListIssueReceiptCollectInfo(docNo);
//    }

    @ApiOperation("获取领料单身为为未审核的领料单")
    @PostMapping("/getIssueReceiptReq")

    public Result<?> getIssueReceiptReq(@RequestBody IssueReceiptReqDto pageDTO){
        if(pageDTO.getEmployeeNo() ==  null){
            System.out.println("员工编号不能为空");
        }
        //根据员工编号查询对应的仓库编号
        List<String> warehouseCodeList = moMapper.getWarehouseCodeByEmployeeNo(pageDTO.getEmployeeNo());
        if(!warehouseCodeList.isEmpty()){
            pageDTO.setWarehouseCodeList(warehouseCodeList);
        }
        List<IssueReceiptReqVo> list = moMapper.getIssueReceiptReq(pageDTO);
        if (!list.isEmpty()) {
            // 使用Stream处理合并逻辑
            List<NewIssueReceiptReqVo> mergedList = list.stream()
                    // 按docNo分组
                    .collect(Collectors.groupingBy(
                            IssueReceiptReqVo::getDocNo,
                            Collectors.toList()
                    ))
                    .entrySet().stream()
                    .map(entry -> {
                        // 获取当前分组的Vo列表
                        List<IssueReceiptReqVo> groupItems = entry.getValue();
                        // 取第一个元素作为公共字段来源
                        IssueReceiptReqVo firstItem = groupItems.get(0);

                        // 创建合并后的Vo对象
                        NewIssueReceiptReqVo newVo = new NewIssueReceiptReqVo();
                        newVo.setWorkCenterCode(firstItem.getWorkCenterCode());
                        newVo.setWorkCenterName(firstItem.getWorkCenterName());
                        newVo.setDocNo(entry.getKey()); // 领料单号来自分组键
                        newVo.setDocDate(firstItem.getDocDate());
                        newVo.setWarehouseCode(firstItem.getWarehouseCode());
                        newVo.setWarehouseName(firstItem.getWarehouseName());

                        // 收集所有品号 用/分隔
                        String itemCodesStr = groupItems.stream()
                                .map(IssueReceiptReqVo::getItemCode)
                                .distinct() // 去重
                                .collect(Collectors.joining("/"));
                        newVo.setItemCode(itemCodesStr);

                        // 收集所有品名 用/分隔
                        String itemNamesStr = groupItems.stream()
                                .map(IssueReceiptReqVo::getItemName)
                                .distinct() // 去重
                                .collect(Collectors.joining("/"));
                        newVo.setItemName(itemNamesStr);

                        // 收集所有工单号并去重
                        List<String> moDocNos = groupItems.stream()
                                .map(IssueReceiptReqVo::getModocNo)
                                .distinct() // 去重
                                .collect(Collectors.toList());
                        newVo.setMoDocNoList(moDocNos);

                        // 收集所有客户单号并去重
                        List<String> customerNos = groupItems.stream()
                                .map(IssueReceiptReqVo::getCustomerNo)
                                .distinct()
                                .collect(Collectors.toList());
                        newVo.setCustomerNo(customerNos);

                        return newVo;
                    })
                    .collect(Collectors.toList());
            // 领料单一致的直接合并
            HashMap<String, Object> result = new HashMap<>();
            result.put("total", mergedList.size());
            result.put("list", mergedList);
            return Result.ok(result);
        }
        return Result.ok();
    }

    @PostMapping("/getIssueReceiptWarehouse")
    @ApiOperation(value = "获取发货仓库信息")
    public Result<List<IssueReceiptWarehouse>> getIssueReceiptWarehouse(@RequestBody List<String> docNoList) {
        List<IssueReceiptWarehouse> issueReceiptWarehouseList = moMapper.getIssueReceiptWarehouseList(docNoList);
        return Result.ok(issueReceiptWarehouseList);
    }


    @ApiOperation("根据领料申请单和仓库编号查出库明细")
    @PostMapping("/getIssueReceiptReqD")
    public Result<List<issueReceiptReqDVo>> selectListMoInStorageByBarcode(@RequestBody IssueReceiptReqDDto dto){
        //清除对应的领料明细
        moMapper.deletePickingSummary(dto.getCreateBy());
        // 清除对应的扫码记录
        QueryWrapper<InvBarcodeOperation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_by",dto.getCreateBy());
        invBarcodeOperationMapper.delete(queryWrapper);
        //查最新数据
        List<issueReceiptReqDVo> list = moMapper.getIssueReceiptReqD(dto);
        // 按 itemCode 分组合并（处理 customerNo/DemandNo 拼接）
        Map<String, issueReceiptReqDVo> mergedMap = list.stream()
                .collect(Collectors.toMap(
                        issueReceiptReqDVo::getItemCode,
                        vo -> {
                            // 创建新对象并初始化字段
                            issueReceiptReqDVo merged = new issueReceiptReqDVo();
                            merged.setItemCode(vo.getItemCode());
                            merged.setItemName(vo.getItemName());
                            merged.setItemSpec(vo.getItemSpec());
                            merged.setWarehouseCode(vo.getWarehouseCode());
                            merged.setBinCode(vo.getBinCode());
                            merged.setUnitCode(vo.getUnitCode());
                            merged.setUnitName(vo.getUnitName());
                            merged.setDocNo(vo.getDocNo());
                            merged.setShjg(vo.getShjg());
                            merged.setCustomerNo(vo.getCustomerNo());

                            // 初始化 customerNo 和 DemandNo（去重并拼接）
                            merged.setCustomerNo(vo.getCustomerNo() != null ? vo.getCustomerNo() : "");
                            merged.setDemandNo(vo.getDemandNo() != null ? vo.getDemandNo() : "");

                            // 初始化数值字段（防 Null）
                            merged.setRequestQty(vo.getRequestQty() != null ? vo.getRequestQty() : BigDecimal.ZERO);
                            merged.setIssueReceiptQty(vo.getIssueReceiptQty() != null ? vo.getIssueReceiptQty() : BigDecimal.ZERO);
                            merged.setCurrectNum(vo.getCurrectNum() != null ? vo.getCurrectNum() : BigDecimal.ZERO);

                            return merged;
                        },
                        //Collectors.toMap的合并函数（mergeFunction）, 流中出现重复的键（相同itemCode）时，mergeFunction会被调用，决定如何合并这两个值
                        (vo1, vo2) -> {
                            // 合并 customerNo 去重
                            Set<String> customerNos = new TreeSet<>();
                            if (vo1.getCustomerNo() != null && !vo1.getCustomerNo().isEmpty()) {
                                customerNos.addAll(Arrays.asList(vo1.getCustomerNo().split("/")));
                            }
                            if (vo2.getCustomerNo() != null && !vo2.getCustomerNo().isEmpty()) {
                                customerNos.addAll(Arrays.asList(vo2.getCustomerNo().split("/")));
                            }
                            vo1.setCustomerNo(String.join("/", customerNos));

                            // 合并 DemandNo 去重
                            Set<String> demandNos = new TreeSet<>();
                            if (vo1.getDemandNo() != null && !vo1.getDemandNo().isEmpty()) {
                                demandNos.addAll(Arrays.asList(vo1.getDemandNo().split("/")));
                            }
                            if (vo2.getDemandNo() != null && !vo2.getDemandNo().isEmpty()) {
                                demandNos.addAll(Arrays.asList(vo2.getDemandNo().split("/")));
                            }
                            vo1.setDemandNo(String.join("/", demandNos));

                            // 数值字段求和
                            vo1.setRequestQty(addBigDecimals(vo1.getRequestQty(), vo2.getRequestQty()));
                            vo1.setIssueReceiptQty(addBigDecimals(vo1.getIssueReceiptQty(), vo2.getIssueReceiptQty()));
                            vo1.setCurrectNum(addBigDecimals(vo1.getCurrectNum(), vo2.getCurrectNum()));

                            return vo1;
                        }
                ));

        // 提取合并结果
        List<issueReceiptReqDVo> mergedList = new ArrayList<>(mergedMap.values());
        for (issueReceiptReqDVo detail : mergedList) {
            // 保存到领料明细汇总表 picking_summary
            int i = moMapper.savePickingSummary(detail, dto.getCreateBy());
            Double num = moMapper.queryInventory(detail.getItemCode());
            detail.setInventoryNum(num);
            if (i <= 0) {
                return Result.fail(list).message(" 保存到领料明细汇总表失败！");
            }
        }
        return Result.ok(mergedList);
    }

    // BigDecimal 安全加法
    private BigDecimal addBigDecimals(BigDecimal num1, BigDecimal num2) {
        return (num1 == null ? BigDecimal.ZERO : num1)
                .add(num2 == null ? BigDecimal.ZERO : num2);
    }

    @PostMapping("/getDeliveryDetailMid")
    @ApiOperation(value = "获取领料明细-中间表数据")
    public Result<List<issueReceiptReqDVo>> getDeliveryDetailMid(@RequestBody String createBy) {
        // 查询发货明细表 picking_summary
        List<issueReceiptReqDVo> detailList = moMapper.getIssueReceiptReqSummaryByCreateBy(createBy);
        return Result.ok(detailList);
    }

    @PostMapping("/isScanningCodeRecord")
    @ApiOperation(value = "判断是否有扫码记录")
    public Result isScanningCodeRecord(@RequestBody String createBy) {
        Integer count = moMapper.isScanningCodeRecord(createBy);
        return Result.ok(count > 0);
    }

    @PostMapping("/BarCodeDetail")
    @ApiOperation(value = "查询条码信息")
    public Result<BarCodeDetailVo> BarCodeDetail(@RequestBody String barCode) {
        BarCodeDetailVo barCodeDetail = moMapper.getBarCodeDetailByBarCode(barCode);
        return Result.ok(barCodeDetail);
    }

    @PostMapping("/print")
    @ApiOperation(value = "蓝牙打印")
    @Log("蓝牙打印")
    public Result<?> printBarCode(@RequestBody printDTO dto) {
        PickingSummary p = moMapper.getPickingSummary(dto.getCreateBy(),dto.getItemNo());
        if (StrUtil.isEmpty(p.getBarCode())) {
            return Result.fail().message("未扫标签");
        }
        // 查询条码详情
        BarCodeDetailVo barCodeDetail = moMapper.getBarCodeDetailByBarCode(p.getBarCode());
        if (barCodeDetail == null) {
            return Result.fail().message("条码不存在");
        }
        // 生成新条码 bm_barcode_detail
        BmBarcodeDetail bmBarcodeDetail = new BmBarcodeDetail();
        // 获取当前时间
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowTime = sdf.format(currentDate);
        //生成新条码   新条码规则 原条码日期替换为当前日期+序号 318011004004#20250418#0001
        String[] parts = barCodeDetail.getBarcode().split( "#");
        String queryBarcode = parts[0] + "#" + nowTime +"#";
        String maxNumber = moMapper.getMaxBarCodeDetail(queryBarcode);
        bmBarcodeDetail.setId(UUID.randomUUID().toString());// id
        bmBarcodeDetail.setBarcode(queryBarcode+maxNumber);// 标签号
        bmBarcodeDetail.setCombinationLotNo(barCodeDetail.getLotCode()); // 批号
        bmBarcodeDetail.setCustomerNo(p.getCustomerNo());// 客户单号
        bmBarcodeDetail.setItemNo(barCodeDetail.getItemCode()); // 品号
        bmBarcodeDetail.setItemName(barCodeDetail.getItemName()); // 品名
        bmBarcodeDetail.setItemSpec(barCodeDetail.getItemSpec()); // 规格
        bmBarcodeDetail.setQty(p.getCurrectNum()); // 取出的数量
        bmBarcodeDetail.setLotDate(DateUtil.date()); // 批号日期
        bmBarcodeDetail.setSnWarehouseNo(barCodeDetail.getWarehouseCode()); // 仓库编号
        bmBarcodeDetail.setSnCellNo(barCodeDetail.getBinCode()); // 库位编号
        bmBarcodeDetail.setUnitNo(barCodeDetail.getUnitNo()); // 单位编号
        bmBarcodeDetail.setUnitName(barCodeDetail.getUnitName()); // 单位名称
        bmBarcodeDetail.setSourceNo(p.getMoDocNo()); // 工单号
        bmBarcodeDetail.setStandardCol02("1"); // 1:领料出库,2:销货出库
        bmBarcodeDetail.setRemark(barCodeDetail.getRemark()); // 备注
        bmBarcodeDetail.setCreateDate(DateTime.now()); // 创建时间
        bmBarcodeDetail.setUpdateDate(DateTime.now()); // 更新时间
        bmBarcodeDetail.setCreateBy(dto.getCreateBy()); // 创建人
        bmBarcodeDetail.setOrgNo(barCodeDetail.getShjg().toString()); // 收货机构
        boolean save = bmBarcodeDetailService.save(bmBarcodeDetail); // 插入标签表
        if (!save) {
            return Result.fail().message("新条码保存失败");
        }
        return Result.ok(bmBarcodeDetail);
    }

    @PostMapping("/getBarCodeDetail")
    @ApiOperation(value = "扫码接口")
    @DSTransactional
    public Result getBarCodeDetail(@RequestBody getBarCodeDTO barCode) {
        // 1. 查询条码详情
        BarCodeDetailVo barCodeDetail = moMapper.getBarCodeDetailByBarCode(barCode.getBarCode());
        if (barCodeDetail == null) {
            return Result.fail().message("条码不存在");
        }

        // 2. 查询领料汇总
        List<PickingSummary> pickingSummary = moMapper.getPickingSummaryByItemCode(barCodeDetail.getItemCode(), barCode.getCreateBy());
        if (pickingSummary == null || pickingSummary.isEmpty()) {
            return Result.fail().message("品号不符合");
        }

        // 3.查询该条码操作档 汇总数量
        BigDecimal qtySum = Optional.ofNullable(
                moMapper.getQtySum(barCode.getBarCode(), barCode.getCreateBy())
        ).orElse(BigDecimal.ZERO);

        // 4.计算剩余可分配数量
        BigDecimal remainingQty = barCodeDetail.getQty().subtract(qtySum);
        if (remainingQty.compareTo(BigDecimal.ZERO) <= 0) {
            return Result.fail().message("条码剩余数量不足");
        }

        // 5.查询是否有未匹配完的记录
        Integer count = moMapper.getUnMatchQtyList(barCode.getCreateBy());
        if (count == null || count == 0) {
            return Result.fail().message("所有记录已匹配完成，请提交");
        }

        // 返回的标签列表
        List<BmBarcodeDetail> barCodeDetailList = new ArrayList<>();
        // 6.分配数量
        for (PickingSummary p : pickingSummary) {
            if (p.getItemCode().equals(barCodeDetail.getItemCode()) &&
                    remainingQty.compareTo(BigDecimal.ZERO) > 0 &&
                    p.getMatchQty().compareTo(p.getBusinessQty()) < 0 &&
                    p.getCurrectNum().compareTo(p.getBusinessQty()) < 0) {
                BigDecimal neededQty = p.getBusinessQty().subtract(p.getMatchQty()); // 需要分配的数量
                BigDecimal allocateQty = neededQty.compareTo(remainingQty) <= 0 ? neededQty : remainingQty; // 本次分配的数量
                // 更新领料汇总
                moMapper.updatePickingSummaryOfMatchQtyTo(p.getId(), p.getMatchQty().add(allocateQty), p.getCreateBy(), barCode.getBarCode(), allocateQty);
                PickingSummary p1 = moMapper.getSummyById(p.getId());
                if (p1.getBusinessQty().equals(p1.getMatchQty())) {
                    //7.生成新条码 bm_barcode_detail
                    BmBarcodeDetail bmBarcodeDetail = new BmBarcodeDetail();
                    // 获取当前时间
                    Date currentDate = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    String nowTime = sdf.format(currentDate);
                    //生成新条码   新条码规则 原条码日期替换为当前日期+序号 318011004004#20250418#0001
                    String[] parts = barCodeDetail.getBarcode().split( "#");
                    String queryBarcode = parts[0] + "#" + nowTime +"#";
                    String maxNumber = moMapper.getMaxBarCodeDetail(queryBarcode);
                    bmBarcodeDetail.setId(UUID.randomUUID().toString());// id
                    bmBarcodeDetail.setBarcode(queryBarcode+maxNumber);// 标签号
                    bmBarcodeDetail.setCombinationLotNo(barCodeDetail.getLotCode()); // 批号
                    bmBarcodeDetail.setCustomerNo(p.getCustomerNo());// 客户单号
                    bmBarcodeDetail.setItemNo(barCodeDetail.getItemCode()); // 品号
                    bmBarcodeDetail.setItemName(barCodeDetail.getItemName()); // 品名
                    bmBarcodeDetail.setItemSpec(barCodeDetail.getItemSpec()); // 规格
                    bmBarcodeDetail.setQty(p1.getCurrectNum()); // 取出的数量
                    bmBarcodeDetail.setLotDate(DateUtil.date()); // 批号日期
                    bmBarcodeDetail.setSnWarehouseNo(barCodeDetail.getWarehouseCode()); // 仓库编号
                    bmBarcodeDetail.setSnCellNo(barCodeDetail.getBinCode()); // 库位编号
                    bmBarcodeDetail.setUnitNo(barCodeDetail.getUnitNo()); // 单位编号
                    bmBarcodeDetail.setUnitName(barCodeDetail.getUnitName()); // 单位名称
                    bmBarcodeDetail.setSourceNo(p.getMoDocNo()); // 工单号
                    bmBarcodeDetail.setStandardCol02("1"); // 1:领料出库,2:销货出库
                    bmBarcodeDetail.setRemark(barCodeDetail.getRemark()); // 备注
                    bmBarcodeDetail.setCreateDate(DateTime.now()); // 创建时间
                    bmBarcodeDetail.setUpdateDate(DateTime.now()); // 更新时间
                    bmBarcodeDetail.setCreateBy(barCode.getCreateBy()); // 创建人
                    bmBarcodeDetail.setOrgNo(barCodeDetail.getShjg().toString()); // 收货机构
                    boolean save = bmBarcodeDetailService.save(bmBarcodeDetail); // 插入标签表
                    if (!save) {
                        return Result.fail().message("新条码保存失败");
                    }
                    barCodeDetailList.add(bmBarcodeDetail); // 插入标签列表
                }

                //扣减剩余可分配数量
                remainingQty = remainingQty.subtract(allocateQty);
            }
        }

        // 8.记录条码操作记录 变化数量
        InvBarcodeOperation invBarcodeOperation = new InvBarcodeOperation();
        invBarcodeOperation.setId(UUID.randomUUID().toString()); // UUID
        invBarcodeOperation.setBarcode(barCodeDetail.getBarcode()); // 条码编号
        invBarcodeOperation.setOrgNo(barCodeDetail.getShjg().toString()); // 收货机构
        invBarcodeOperation.setQty(barCodeDetail.getQty().subtract(remainingQty)); // 变化数量
        invBarcodeOperation.setWarehouseNo(barCodeDetail.getWarehouseCode()); // 仓库编号
        invBarcodeOperation.setCellNo(barCodeDetail.getBinCode()); // 库位编号
        invBarcodeOperation.setCombinationLotNo(barCodeDetail.getLotCode()); // 批号
        invBarcodeOperation.setItemNo(barCodeDetail.getItemCode()); // 品号
        invBarcodeOperation.setItemName(barCodeDetail.getItemName()); // 品名
        invBarcodeOperation.setItemSpec(barCodeDetail.getItemSpec()); // 规格
        invBarcodeOperation.setUnitNo(barCodeDetail.getUnitNo()); // 单位编号
        invBarcodeOperation.setChagType(1); // 1.领料
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
            return Result.fail().message("本次无需打印");
        }
        return Result.ok(barCodeDetailList);
    }

    @PostMapping("/getBarCodeOperation")
    @ApiOperation(value = "获取已扫描数据列表")
    public Result<List<PickingSummary>> getBarCodeOperation(@RequestBody String createBy) {
        List<PickingSummary> pickingSummaryList = moMapper.getPickingSummaryByCreateBy(createBy);
        return Result.ok(pickingSummaryList);
    }

    @PostMapping("/getInvBarcodeOperation")
    @ApiOperation(value = "获取已扫描条码记录")
    public Result<List<InvBarcodeOperationVo>> getInvBarcodeOperation(@RequestBody String createBy) {
        List<InvBarcodeOperationVo> InvBarcodeOperationList = moMapper.getInvBarcodeOperationVo(createBy);
        return Result.ok(InvBarcodeOperationList);
    }

    @PostMapping("/resetData")
    @ApiOperation(value = "重置数据")
    @Transactional
    public Result resetData(@RequestBody String createBy) {
        try {
            // 1.删除中间表
            moMapper.deleteDeliveryDetailSummaryByCreateBy(createBy);
            // 2.删除条码操作档 -> 条码状态：未提交 & 条码类型：领料出库
            moMapper.deleteInvBarcodeOperationByCreateBy(createBy);
        } catch (Exception e) {
            return Result.fail().message("重置失败");
        }
        return Result.ok();
    }

    @PostMapping("/deleteRecord")
    @ApiOperation(value = "删除扫码记录")
    @Transactional
    public Result delete(@RequestBody String id) {
        //还原中间表
        // 1. 查询条码操作表
        InvBarcodeOperation invBarcodeOperation = moMapper.selectInvBarCodeOperation(id);
        System.out.println("invBarcodeOperation:"+invBarcodeOperation);
        log.info("删除扫码记录-/deleteRecord-invBarcodeOperation:"+invBarcodeOperation);
        // 条码变化量
        BigDecimal qty = invBarcodeOperation.getQty();

        // 2. 回滚领料汇总表的匹配量
        List<PickingSummary> pickingSummary = moMapper.getPickingSummaryByItemCode(invBarcodeOperation.getItemNo(),invBarcodeOperation.getCreateBy());
        for (PickingSummary p : pickingSummary) {
            System.out.println("p:"+p.getItemCode());
            log.info("删除扫码记录-/deleteRecord-p:"+p.getItemCode());
            if (p.getItemCode().equals(invBarcodeOperation.getItemNo()) && qty.compareTo(BigDecimal.ZERO) > 0) {
                if (p.getMatchQty().compareTo(qty) > 0) {
                    // 匹配量大于条码变化量 -> 匹配量 = 匹配量 - 条码变化量
                    int i = moMapper.updatePickingSummaryOfMatchQty(p.getId(), p.getMatchQty().subtract(qty), invBarcodeOperation.getCreateBy(), invBarcodeOperation.getBarcode(), qty);
                    if (i <= 0) {
                        return Result.fail().message("中间表匹配量回滚失败");
                    }
                    qty = BigDecimal.ZERO;
                } else {
                    // 匹配量小于条码变化量 -> 匹配量 = 0
                    int i = moMapper.updatePickingSummaryOfMatchQty(p.getId(), BigDecimal.ZERO, invBarcodeOperation.getCreateBy(), invBarcodeOperation.getBarcode(), BigDecimal.ZERO);
                    if (i <= 0) {
                        return Result.fail().message("中间表匹配量回滚失败");
                    }
                    qty = qty.subtract(p.getMatchQty());
                }
            } else {
                break;
            }
        }

        //删除扫码记录
        Boolean b = moMapper.deleteBarCodeOperation(id);
        if (!b) {
            return Result.fail().message("条码操作档删除失败");
        }
        return Result.ok().message("删除成功");

    }






    @ApiOperation("领料下架-提交-维护工单(已审核)->领料申请单(已审核)->生成领料出库单(已审核)")
    @PostMapping("/IssueReceiptSubmit")
    @Log("领料下架-提交-维护工单(已审核)->领料申请单(已审核)->生成领料出库单(已审核)")
    public Result IssueReceiptSubmit(@RequestBody MoIssueReceiptDto Dto){
        String createBy = Dto.getCreateBy();
        if (StrUtil.isBlank(createBy)) {
            return Result.fail().message("操作人不能为空！");
        }
//        List<String> docList = new ArrayList<>();
        for(MoIssueReceiptSubmitDto dto : Dto.getList()){
//            dto.setBinCode("");
//            // 查询本次提交该品号、该批号、该工厂 在e10中的数量
//            String lotNumber = moMapper.selectLotNumber(dto.getBarcode());
//            MoInventoryQtyWarehouse mqw = moMapper.selectItemNumber(dto.getItemCode(),lotNumber,dto.getShjg(),dto.getWarehouseCode());
//            if (mqw != null){
//                Double InventoryQty = mqw.getInventoryQty();
//
//                if(InventoryQty == null || InventoryQty < dto.getCurrectNum().doubleValue()){
//                    System.out.println("工厂" + dto.getShjg() + " 批号为 " + lotNumber + " 的品号 :" + dto.getItemCode() + "本次领料数量:" + dto.getCurrectNum() + " 库存为:" + InventoryQty + " 不足以出库");
//                    return Result.fail().message("工厂" + dto.getShjg() + " 批号为 " + lotNumber + " 的品号 :" + dto.getItemCode() + "本次领料数量:" + dto.getCurrectNum() + " 库存为 " + InventoryQty + " 不足以出库");
//                }
//            }
            moMapper.UpdateIssueReceiptReqDLot(dto.getDocNo());

        }

        MiddleReturnDto middleReturnDto = moService.insertIssueReceiptMiddleTable(Dto.getInvBarcodeOperationIdList(), Dto.getList().get(0).getDocNo(), createBy);
        JSONObject jsonObject = moService.IssueReceiptSubmit(middleReturnDto, createBy);
        // 获取执行结果的 code
        String executionCode = jsonObject
                .getJSONObject("payload")
                .getJSONObject("std_data")
                .getJSONObject("execution")
                .getString("code");

        String message;
        // E10接口提交成功
        if ("0".equals(executionCode)) {
             message = jsonObject
                    .getJSONObject("payload")
                    .getJSONObject("std_data")
                    .getJSONObject("parameter")
                    .getJSONArray("als_app_request_result")  // 获取JSON数组
                    .getJSONObject(0)        // 获取数组的第一个元素
                    .getString("doc_no");     // 获取docNo字段

            for(String id  : Dto.getInvBarcodeOperationIdList()){
                //1.修改操作档状态为已提交
                InvBarcodeOperation invBarcodeOperation = invBarcodeOperationMapper.selectByid(id);
                boolean b = invBarcodeOperationMapper.updateByid(invBarcodeOperation.getId());
                if (!b) {
                    return Result.fail().message("修改操作档状态为提交失败");
                }

                // 2.扣减条码的剩余数量
                int i = moMapper.updateBmBarcodeDetailOfQty(invBarcodeOperation.getBarcode(), invBarcodeOperation.getQty().doubleValue());
                if (i <= 0) {
                    return Result.fail().message("扣减条码的剩余数量失败");
                }
                // 4.保存条码结果档
                InvBarcodeTransaction invBarcodeTransaction = new InvBarcodeTransaction();
                BeanUtil.copyProperties(invBarcodeOperation, invBarcodeTransaction);
                invBarcodeTransaction.setDocType("-1");
                invBarcodeTransaction.setDocNo(message);
                invBarcodeTransaction.setCreateDate(new Date());
                invBarcodeTransaction.setLotAtt50("/IssueReceiptSubmit");
                boolean save = invBarcodeTransactionService.save(invBarcodeTransaction);
                if (!save) {
                    return Result.fail().message("保存条码结果档失败");
                }
            }
            // 5.删除中间表
//            int i = moMapper.deleteDeliveryDetailSummaryByCreateBy(Dto.getCreateBy());
//            if (i <= 0) {
//                return Result.fail().message("删除中间表失败");
//            }
            // 6.清除中间表的当前数量
            moMapper.updatePickingSummaryOfCurrectQty(Dto.getCreateBy());
        }else {
             message = jsonObject
                    .getJSONObject("payload")
                    .getJSONObject("std_data")
                    .getJSONObject("execution")
                    .getString("description");
            return Result.fail().message(message);
        }
        return Result.ok().message(message);
    }





























    @ApiOperation("退料上架-简洁查询")
    @PostMapping("/ListIssueReceiptReturnSimpleInfo")
    public Result<List<IssueReceiptReturnSimpleVo>> ListIssueReceiptReturnSimpleInfo(@RequestBody IssueReceiptReturnSimpleDto dto){
        return moService.ListIssueReceiptReturnSimpleInfo(dto);
    }
    /**
     * 退料上架(退料入库单生成退料入库单)    查询接口
     * @param
     * @return
     */
    @ApiOperation("退料上架-详细查询")
    @PostMapping("/ListIssueReceiptReturnCollectInfo/{docNo}")
    public Result<List<IssueReceiptReturnVo>> ListIssueReceiptReturnCollectInfo(@PathVariable String docNo){
        return moService.ListIssueReceiptReturnCollectInfo(docNo);
    }

    @ApiOperation("退料上架-提交-维护工单(已审核)->领料申请单(已审核)->领料出库单(已审核)->退料入库单(未审核)->->生成退料入库单(已审核)")
    @PostMapping("/IssueReceiptReturnSubmit")
    @Log("退料上架-提交-维护工单(已审核)->领料申请单(已审核)->领料出库单(已审核)->退料入库单(未审核)->->生成退料入库单(已审核)")
    public Result IssueReceiptReturnSubmit(@RequestBody List<MoIssueReceiptReturnSubmitDto> list){
        try {
            MiddleReturnDto middleReturnDto = moService.insertIssueReceiptReturnMiddleTable(list);
            JSONObject jsonObject = moService.IssueReceiptReturnSubmit(middleReturnDto);
            return Result.ok(jsonObject).message("添加成功");
        }catch (Exception e){
            return Result.fail().message("添加失败");
        }
    }





//    //**************************工单入库-查e10表**************************
//    /**
//     * 工单入库 (调用远程e10的查询接口否则用不了)   工单生成生产入库单并生成条码
//     * @param dto
//     * @return
//     */
//    @ApiOperation("工单入库（免）查询")
//    @PostMapping("/ListMoInStorage")
//    public Result ListMoInStorage(@RequestBody MoInStorageDto dto){
//        System.out.println("------------------------");
//        try {
//            JSONObject jsonObject = moService.MoInStorageDto(dto);
//            return Result.ok(jsonObject).message("查询成功");
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.fail().message("查询失败");
//        }
//    }

    @ApiOperation("工单入库（免）查询详情")
    @GetMapping("/getMoInStorageDetail")
    public Result getMoInStorageDetail(String docNo){
        try {
            JSONObject jsonObject = moService.getMoInStorageDetail(docNo);
            return Result.ok(jsonObject).message("查询成功");
        }catch (Exception e){
            return Result.fail().message("查询失败");
        }
    }

//    @ApiOperation("入库上架-查询生产入库申请单详细信息")
//    @PostMapping("/ListMoShelvesDetail/{docNo}")
//    public Result<List<MoShelvesDetailVo>> ListMoDetailInfo(@PathVariable String docNo) {
//        return moService.ListMoShelvesDetailInfo(docNo);
//    }
//
//
//
//    @ApiOperation("入库上架-提交-工单(已审核)->->生成生产入库申请单(已审核)")
//    @PostMapping("/MoInStorageSubmit")
//    public Result MoInStorageShelvesSubmit(@RequestBody List<MoInStorageShelvesDto> dtoList){
////          先判断wms传过来的code和生产入库申请表中code是否相等
////          不相等就更新掉生产入库申请表中对应的code
////            int i = moService.updateMoInStorageCode(dtoList);
////            if (i == 0) {
////                System.out.println("更新生成入库申请单仓库失败");
////            }
//        MiddleReturnDto middleReturnDto = moService.insertMoInStorageShelvesMiddleTable(dtoList);
//        JSONObject jsonObject = moService.MoInStorageShelvesSubmit(middleReturnDto);
//        return Result.ok(jsonObject).message("添加成功");
//
//    }
    @ApiOperation("入库上架-获取生产入库申请单简洁信息")
    @PostMapping("/ListMoShelvesSimple")
    public Result ListReceiptMoSimpleInfo(@RequestBody MoShelvesSimpleDto moShelvesSimpleDto){
        return moService.ListMoShelvesSimpleInfo(moShelvesSimpleDto);
    }

    @ApiOperation("入库上架-查询生产入库申请单详细信息")
    @PostMapping("/ListMoShelvesDetail/{docNo}")
    public Result<List<MoShelvesDetailVo>> ListMoDetailInfo(@PathVariable String docNo) {
        return moService.ListMoShelvesDetailInfo(docNo);
    }

//    @ApiOperation("入库上架-提交-工单(已审核)->生产入库申请单(已审核)->->生成生产入库单(已审核)")
//    @PostMapping("/MoInStorageSubmit")
//    public Result MoInStorageShelvesSubmit(@RequestBody List<MoInStorageShelvesDto> dtoList){
////          先判断wms传过来的code和生产入库申请表中code是否相等
////          不相等就更新掉生产入库申请表中对应的code
////            int i = moService.updateMoInStorageCode(dtoList);
////            if (i == 0) {
////                System.out.println("更新生成入库申请单仓库失败");
////            }
//        MiddleReturnDto middleReturnDto = moService.insertMoInStorageShelvesMiddleTable(dtoList);
//        JSONObject jsonObject = moService.MoInStorageShelvesSubmit(middleReturnDto);
//        return Result.ok(jsonObject).message("添加成功");
//
//    }



    @ApiOperation("入库申请-获取工单简洁信息(工单要勾选入库申请、生管部门要与工作中心生产部门一致)")
    @PostMapping("/ListMoStorageApplySimpleInfo")
    public Result ListMoStorageApplySimpleInfo(@RequestBody MoStorageApplySimpleDto moStorageApplySimpleDto){
        JSONObject jsonObject = moService.ListMoStorageApplySimpleInfo(moStorageApplySimpleDto);
        return Result.ok(jsonObject).message("查询成功");
    }

    @ApiOperation("入库申请-获取工单详细信息——\"description\": \"入参【doc_no】未传值！\"")
    @PostMapping("/ListMoStorageApplyDetailInfo")
    public Result ListMoStorageApplyDetailInfo(@RequestBody MoStorageApplyDetailDto moStorageApplyDetailDto){
        JSONObject jsonObject = moService.ListMoStorageApplyDetailInfo(moStorageApplyDetailDto);
        return Result.ok(jsonObject).message("查询成功");
    }

    @ApiOperation("入库申请-提交-维护工单(已审核)->->生成入库申请单(已审核)")
    @PostMapping("/ListMoStorageApplySubmit")
    @Log("入库申请-提交-维护工单(已审核)->->生成入库申请单(已审核)")
    public Result ListMoStorageApplySubmit(@RequestBody MoStorageApplyDetailDto moStorageApplyDetailDto){
        JSONObject jsonObject = moService.ListMoStorageApplyDetailInfo(moStorageApplyDetailDto);
        return Result.ok(jsonObject).message("查询成功");
    }







}

