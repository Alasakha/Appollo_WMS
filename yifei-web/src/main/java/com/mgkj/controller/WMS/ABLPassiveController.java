package com.mgkj.controller.WMS;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.mgkj.annotation.Log;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.InvBarcodeOperation;
import com.mgkj.entity.InvBarcodeTransaction;
import com.mgkj.mapper.InvBarcodeOperationMapper;
import com.mgkj.mapper.PassiveMapper;
import com.mgkj.service.InvBarcodeOperationService;
import com.mgkj.service.InvBarcodeTransactionService;
import com.mgkj.service.PassiveService;
import com.mgkj.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yyyjcg
 * @date 2023/12/26
 * @Description
 */
@Slf4j
@RestController
@Api(tags = "ABL无源管理相关接口")
@RequestMapping("/api/ABLPassive")
public class ABLPassiveController {

    @Autowired
    private PassiveService passiveService;

    @Autowired
    private PassiveMapper passiveMapper;
    @Autowired
    private InvBarcodeOperationMapper invBarcodeOperationMapper;

    @Resource
    private InvBarcodeOperationService invBarcodeOperationService;

    @Resource
    private InvBarcodeTransactionService invBarcodeTransactionService;

    @ApiOperation("查询无源调拨箱码信息")
    @PostMapping("/getTransferBarcodeInfo")
    @DSTransactional
    public Result getTransferBarcodeInfo (@RequestBody PassiveTransferBarcodeDto dto) {

        // 1.查询条码详情
        BarCodeDetailVo barCodeDetail = passiveMapper.getBarCodeDetailByBarCode(dto.getBarcode());
        if (barCodeDetail == null) {
            return Result.fail().message("条码不存在");
        }

        return Result.ok(barCodeDetail);

    }



    @PostMapping("/saveTransferBarcodeInfo")
    @ApiOperation(value = "扫码匹配并保存需调拨数据")
    @Log("扫码匹配并保存需调拨数据")
    @DSTransactional
    public Result<?> saveTransferBarcodeInfo(@RequestBody PassiveInvBarcodeOperationDto dto) {
        // 1.查询该条码操作档 汇总数量
        BigDecimal qtySum = Optional.ofNullable(passiveMapper.getQtySum(dto.getBarcode(), dto.getChagType()))
                .orElse(BigDecimal.ZERO);

        BigDecimal remainingQty = dto.getMatchQty().subtract(qtySum);
        if (remainingQty.compareTo(BigDecimal.ZERO) <= 0) {
            return Result.fail().message("条码剩余数量不足");
        }

        InvBarcodeOperation invBarcodeOperation = new InvBarcodeOperation();
        invBarcodeOperation.setId(UUID.randomUUID().toString()); // UUID
        invBarcodeOperation.setBarcode(dto.getBarcode()); // 条码编号
        invBarcodeOperation.setQty(dto.getMatchQty()); // 条码数量
        invBarcodeOperation.setSnFromWarehouseNo(dto.getFromWarehouseCode()); // 调出仓库编号
        invBarcodeOperation.setSnFromCellNo(dto.getFromBinCode()); // 调出库位编号
        invBarcodeOperation.setSnToWarehouseNo(dto.getToWarehouseCode()); // 调入库位编号
        invBarcodeOperation.setSnToCellNo(dto.getToBinCode()); // 调入库位编号
        invBarcodeOperation.setItemNo(dto.getItemCode()); // 品号
        invBarcodeOperation.setItemName(dto.getItemName()); // 品名
        invBarcodeOperation.setItemSpec(dto.getItemSpec()); // 规格
        invBarcodeOperation.setUnitNo(dto.getUnitCode()); // 单位编号
        invBarcodeOperation.setUnitName(dto.getUnitName()); // 单位编号
        invBarcodeOperation.setChagType(5); // 5:无源调拨
        invBarcodeOperation.setStatusCode(0); // 0:未提交 1:已提交
        invBarcodeOperation.setCreateDate(DateTime.now().toString()); // 创建时间
        invBarcodeOperation.setCreateBy(dto.getCreateBy()); // 操作人
        invBarcodeOperation.setRemark(dto.getRemark()); // 备注
        invBarcodeOperation.setOrgNo(dto.getShjg()); // 备注
        boolean b = invBarcodeOperationService.save(invBarcodeOperation);
        if (!b) {
            return Result.fail().message("操作记录保存失败");
        }
        return Result.ok(invBarcodeOperation).message("操作记录保存成功");

     }

    @PostMapping("/getInvBarcodeOperation")
    @ApiOperation(value = "获取已扫描条码操作档记录-无源调拨")
    public Result<List<InvBarcodeOperationVo>> getInvBarcodeOperation(@RequestBody String createBy) {
        List<InvBarcodeOperationVo> InvBarcodeOperationList = passiveMapper.queryInvBarcodeOperationVo(createBy);
        return Result.ok(InvBarcodeOperationList);
    }


    @PostMapping("/deleteScanResult")
    @ApiOperation(value = "删除扫码结果列表")
    @Log("删除扫码结果列表")
    @Transactional
    public Result<?> deleteScanResult(@RequestBody QueryPassiveDTO dto) {
        passiveMapper.deleteInvBarcodeOperationByCreateBy(dto.getCreateBy(),dto.getChagType());
        return Result.ok();
    }

    @ApiOperation("无源调拨-提交")
    @PostMapping("/TransferDocSubmit")
    @Log("无源调拨-提交")
    public Result PassiveTransferDocSubmit(@RequestBody PassiveTransferSubmitDto dto ) {

        //1、提交e10
        //同步中间表
        MiddleReturnDto middleReturnDto = passiveService.ABLPassiveTransferInsertMiddleTable(dto.getList());
        //提交e10
        JSONObject jsonObject = passiveService.PassiveTransferSubmit(middleReturnDto);
        String executionCode = jsonObject
                .getJSONObject("payload")
                .getJSONObject("std_data")
                .getJSONObject("execution")
                .getString("code");

        log.info("无源调拨-提交-/TransferDocSubmit e10返参：" + jsonObject);
        if (!"0".equals(executionCode)) {
            return Result.fail(jsonObject);
        }
            for(PassiveTransferDocSubmitDto list :dto.getList()){
            // 1.查询未提交标签
            List<InvBarcodeOperation> operationList = passiveMapper.queryTransNotSubimtByTypeAndCreateBy(5, dto.getCreateBy(),list.getBarcode());
            for (InvBarcodeOperation invBarcodeOperation : operationList) {
                // 2.更新状态为已提交
                passiveMapper.updateByid(invBarcodeOperation.getId());
                // 3.扣减条码的剩余数量
                passiveMapper.updateBmBarcodeDetailOfQty(invBarcodeOperation.getBarcode(), invBarcodeOperation.getQty().doubleValue());
                // 4.保存条码结果档
                InvBarcodeTransaction invBarcodeTransaction = new InvBarcodeTransaction();
                invBarcodeTransaction.setBarcode(invBarcodeOperation.getBarcode());
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
                invBarcodeTransactionService.save(invBarcodeTransaction);

            }
        }


            return Result.ok(jsonObject);
        }






    @ApiOperation("查询无源出入库箱码信息")
    @PostMapping("/getToOrOutBarcodeInfo")
    @DSTransactional
    public Result getToOrOutBarcodeInfo (@RequestBody PassiveToOrOutBarcodeDto dto) {
        // 1.查询条码详情
        BarCodeDetailVo barCodeDetail = passiveMapper.getBarCodeDetailByBarCode(dto.getBarcode());
        if (barCodeDetail == null) {
            return Result.fail().message("条码不存在");
        }
        return Result.ok(barCodeDetail);
    }

    @ApiOperation("查询无源出入库员工部门信息")
    @PostMapping("/getEmployeeInfo")
    public Result<List<PassiveEmployeeInfoVo>> getEmployeeInfo (@RequestBody PassiveEmployeeInfoDto dto) {
        Result<List<PassiveEmployeeInfoVo>> list = passiveService.getEmployeeInfo(dto);
        return list;

    }
    @ApiOperation("查询未结束项目信息")
    @PostMapping("/getUnfinishedProjects")
    public Result<List<UnfinishedProjectsVo>> getUnfinishedProjects (@RequestBody UnfinishedProjectsDto dto) {
        Result<List<UnfinishedProjectsVo>> list = passiveService.getUnfinishedProjects(dto);
        return list;

    }

    @PostMapping("/saveToOrOutBarcodeInfo")
    @ApiOperation(value = "保存需出入库数据")
    @Log("保存需出入库数据")
    @DSTransactional
    public Result<?> saveToOrOutBarcodeInfo(@RequestBody List<PassiveToOrOutInvBarcodeOperationDto> list) {
        if (list == null || list.isEmpty()) {
            return Result.fail("传入的条码列表不能为空");
        }
        // 校验1：检查前端传来的list中所有DTO的shjg是否一致
        Set<String> incomingShjgSet = list.stream()
                .map(PassiveToOrOutInvBarcodeOperationDto::getShjg)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        if (incomingShjgSet.size() > 1) {
            return Result.fail("条码中存在不同收货机构，不允许保存！");
        }


        String createBy = list.get(0).getCreateBy();
        List<String> shjgList = passiveMapper.selectByCreateBy(createBy);
        // 如果前端传来的list中有数据，继续后续校验
        if (!shjgList.isEmpty()) {
            String firstIncomingShjg = list.get(0).getShjg();
            // 校验2：用list中的第一个收货机构去和shjgList的shjg进行对比
                boolean hasDifferentShjg = shjgList.stream()
                        .anyMatch(existingShjg -> !Objects.equals(existingShjg, firstIncomingShjg));

                if (hasDifferentShjg) {
                    return Result.fail("该申请人记录表存在与此不同收货机构的条码数据，不允许保存！");
                }

        }

        List<InvBarcodeOperation> invBarcodeOperationList = new ArrayList<>();
        for (PassiveToOrOutInvBarcodeOperationDto dto : list) {

//             1.查询该条码操作档 汇总数量
//        Double qtySum = passiveMapper.getQtySum(dto.getBarcode(),dto.getChagType());
//        if (qtySum == null) {
//            qtySum = 0.0;
//        }
//
//        double remainingQty = dto.getMatchQty() - qtySum;
//        if (remainingQty <= 0) {
//            return Result.fail().message("条码剩余数量不足");
//        }


            InvBarcodeOperation invBarcodeOperation = new InvBarcodeOperation();
            invBarcodeOperation.setId(UUID.randomUUID().toString()); // UUID
            invBarcodeOperation.setBarcode(dto.getBarcode()); // 条码编号
            invBarcodeOperation.setQty(BigDecimal.valueOf(dto.getMatchQty())); // 要扣掉的数量、要出库的数量
            invBarcodeOperation.setWarehouseNo(dto.getWarehouseCode()); // 仓库编号
            invBarcodeOperation.setCellNo(dto.getBinCode()); // 库位编号
            invBarcodeOperation.setItemNo(dto.getItemCode()); // 品号
            invBarcodeOperation.setItemName(dto.getItemName()); // 品名
            invBarcodeOperation.setItemSpec(dto.getItemSpec()); // 规格
            invBarcodeOperation.setUnitNo(dto.getUnitCode()); // 单位编号
            invBarcodeOperation.setUnitName(dto.getUnitName()); // 单位
            invBarcodeOperation.setChagType(7); // 5无源调拨、6无源入库、7无源出库
            invBarcodeOperation.setStatusCode(0); // 0:未提交 1:已提交
            invBarcodeOperation.setCreateDate(DateTime.now().toString()); // 创建时间
            invBarcodeOperation.setCreateBy(createBy); // 操作人
            invBarcodeOperation.setRemark(dto.getRemark()); // 备注
            invBarcodeOperation.setOrgNo(dto.getShjg());
            invBarcodeOperation.setLotAtt01(dto.getEmployeeCode()); // 人员编号
            invBarcodeOperation.setLotAtt02(dto.getDepartmentCode()); // 部门编号
            invBarcodeOperation.setLotAtt03(dto.getDepartmentName()); // 部门名称
            invBarcodeOperation.setClassifyType(dto.getTypeCode()); // 单据类别
            invBarcodeOperation.setLotAtt04(dto.getProjectId()); // 项目uuid
            invBarcodeOperation.setLotAtt05(dto.getCarType()); // 车型
            invBarcodeOperation.setRemark("zhilink"+'+'+createBy+'+'+dto.getSerialNo());

            //增加登陆者和流水号 ，表记得要用字段
            invBarcodeOperationList.add(invBarcodeOperation);
        }
        boolean b = invBarcodeOperationService.saveBatch(invBarcodeOperationList);
        if (!b) {
            return Result.fail().message("操作记录保存失败");
        }
        return Result.ok(invBarcodeOperationList).message("操作记录保存成功");

    }

    @ApiOperation("无源出库-选择出库类别")
    @PostMapping("/PassiveOutStorageTypeDoc/{shjg}")
    public Result PassiveOutStorageTypeDoc(@PathVariable String shjg) {
        List<PassiveOutStorageTypeDocVo> list = passiveMapper.passiveOutStorageTypeDoc(shjg);
        return Result.ok(list);
    }


    @ApiOperation("无源出库/杂发-提交")
    @PostMapping("/PassiveOutStorageGenerateDoc/{createBy}")
    @Log("无源出库/杂发-提交")
    public Result PassiveOutStorageGenerateDoc(@RequestBody List<String> idList, @PathVariable("createBy") String createBy) {

        //1、用条码操作档uuid查询数据
        List<InvBarcodeOperation> invBarcodeOperationList = invBarcodeOperationMapper.invBarcodeOperationListByIds(idList);
        String remark = invBarcodeOperationList.get(0).getRemark();
        List<PassiveOutStorageDto> sumbmitList = new ArrayList<>();
        for (InvBarcodeOperation invBarcodeOperation : invBarcodeOperationList) {
            PassiveOutStorageDto dto = new PassiveOutStorageDto();
            dto.setBarcode(invBarcodeOperation.getBarcode());
            dto.setMatchQty(invBarcodeOperation.getQty());
            dto.setItemCode(invBarcodeOperation.getItemNo());
            dto.setWarehouseCode(invBarcodeOperation.getWarehouseNo());
            dto.setBinCode(invBarcodeOperation.getCellNo());
            dto.setEmployeeCode(invBarcodeOperation.getLotAtt01());
            dto.setDepartmentCode(invBarcodeOperation.getLotAtt02());
            dto.setUnitCode(invBarcodeOperation.getUnitNo());
            dto.setShjg(invBarcodeOperation.getOrgNo());
            dto.setCarType(invBarcodeOperation.getLotAtt05());
            dto.setProjectId(invBarcodeOperation.getLotAtt04());
            dto.setCreateBy(invBarcodeOperation.getCreateBy());
            dto.setTypeCode(invBarcodeOperation.getClassifyType());
            dto.setTypeCode(invBarcodeOperation.getClassifyType());

            dto.setInToType("其他收发");
            dto.setIsDeduct("Y"); // 默认扣账
            dto.setRejectQty(BigDecimal.ZERO); // 默认拒收数量
            dto.setScrapQty(BigDecimal.ZERO); // 默认报废数量

            sumbmitList.add(dto);
        }



        String typeCode = sumbmitList.get(0).getTypeCode();
//        PassiveOutSubmitDto sumbmitDdto = new PassiveOutSubmitDto();
//        sumbmitDdto.setList(sumbmitList);
//        sumbmitDdto.setTypeCode(typeCode);
//        sumbmitDdto.setCreateBy(createBy);


        //1、提交e10
        //同步中间表
        MiddleReturnDto middleReturnDto = passiveService.ABLPassiveOutStorageInsertMiddleTable(sumbmitList, createBy);
        //提交e10
        JSONObject jsonObject = passiveService.ABLPassiveOutStorageSubmit(middleReturnDto, typeCode, createBy);
        log.info("无源出库/杂发-提交-e10:" + jsonObject);
        String executionCode = jsonObject
                .getJSONObject("payload")
                .getJSONObject("std_data")
                .getJSONObject("execution")
                .getString("code");

        if (!"0".equals(executionCode)) {
            return Result.fail(jsonObject);
        }


        //提交成功后
        // 工厂1——1112、1125 工厂2——1155、1224
        // 判断该单据类型是否含以上四个类型，是则更新该出库单单身 的车型UDF021和项目PROJECT_ID

        String docNo = jsonObject.getJSONObject("payload")
                .getJSONObject("std_data")
                .getJSONObject("parameter")
                .getJSONArray("als_app_request_result")
                .getJSONObject(0)
                .getString("doc_no");
        // 更新单头备注
        passiveMapper.updateRemark(docNo,remark);
        if ("1112".equals(typeCode) || "1125".equals(typeCode) || "1155".equals(typeCode) || "1224".equals(typeCode)) {
            // 查询出货单身的uuid和品号id
            List<TransactionDocDVo> transactionDocDVoList = passiveMapper.selectTransactionDocDByDocNo(docNo);


            for(PassiveOutStorageDto list : sumbmitList){
                String itemCode = list.getItemCode();
                //查询itemId与出货单身匹配
                String itemBusinessId = passiveMapper.selectItemIdByItemCode(itemCode);
//                System.out.println(itemBusinessId);
                for(TransactionDocDVo transactionDocDVo :transactionDocDVoList){

//                    System.out.println(transactionDocDVo.getItemId());
                    if(itemBusinessId.equals(transactionDocDVo.getItemId())){
                        int i = passiveMapper.updateTransactionDocD(transactionDocDVo.getTransactionDocDId(),list.getCarType(),list.getProjectId());
                    }
                }

            }

        }
        // 1.查询未提交标签
        List<InvBarcodeOperation> operationList = passiveMapper.queryNotSubimtByTypeAndCreateBy(7, createBy);

        for (InvBarcodeOperation invBarcodeOperation : operationList) {
            // 2.更新状态为已提交
            passiveMapper.updateByid(invBarcodeOperation.getId());
            // 3.扣减条码的剩余数量
            passiveMapper.updateBmBarcodeDetailOfQty(invBarcodeOperation.getBarcode(), invBarcodeOperation.getQty().doubleValue());
            // 4.保存条码结果档
            InvBarcodeTransaction invBarcodeTransaction = new InvBarcodeTransaction();
            invBarcodeTransaction.setBarcode(invBarcodeOperation.getBarcode());
            invBarcodeTransaction.setDocType("-1");
            invBarcodeTransaction.setDocNo(docNo);
            invBarcodeTransaction.setCreateDate(new Date());
            invBarcodeTransaction.setLotAtt50("/PassiveOutStorageGenerateDoc");
            invBarcodeTransaction.setQty(invBarcodeOperation.getQty());
            invBarcodeTransaction.setItemNo(invBarcodeOperation.getItemNo());
            invBarcodeTransaction.setItemName(invBarcodeOperation.getItemName());
            invBarcodeTransaction.setItemSpec(invBarcodeOperation.getItemSpec());
            invBarcodeTransaction.setWarehouseNo(invBarcodeOperation.getWarehouseNo());
            invBarcodeTransaction.setCellNo(invBarcodeOperation.getCellNo());
            invBarcodeTransaction.setUnitNo(invBarcodeOperation.getUnitNo());
            invBarcodeTransaction.setUnitName(invBarcodeOperation.getUnitName());
            invBarcodeTransaction.setReferenceQty(invBarcodeOperation.getReferenceQty());
            invBarcodeTransaction.setChagType(invBarcodeOperation.getChagType());
            invBarcodeTransaction.setDocReturnFlag(invBarcodeOperation.getDocReturnFlag());
            invBarcodeTransaction.setStatusCode(invBarcodeOperation.getStatusCode());
            invBarcodeTransaction.setStockRate(invBarcodeOperation.getStockRate());
            invBarcodeTransaction.setStockKeep(invBarcodeOperation.getStockKeep());
            invBarcodeTransaction.setCreateBy(invBarcodeOperation.getCreateBy());
            invBarcodeTransactionService.save(invBarcodeTransaction);
        }


        return Result.ok(jsonObject);
//        return Result.ok(null);
    }


    @PostMapping("/getToOrOutInvBarcodeOperation")
    @ApiOperation(value = "获取已扫描条码操作档记录-无源出入库")
    public Result<List<InvBarcodeOperationVo>> getToOrOutInvBarcodeOperation(GetToOrOutInvBarcodeOperationDto dto) {
        List<InvBarcodeOperationVo> InvBarcodeOperationList = passiveMapper.queryToOrOutInvBarcodeOperationVo(dto.getCreateBy(), dto.getChagType());
        return Result.ok(InvBarcodeOperationList);
    }



    @ApiOperation("无源入库-选择入库类别")
    @PostMapping("/PassiveToStorageTypeDoc/{shjg}")
    public Result PassiveToStorageTypeDoc(@PathVariable String shjg) {
        List<PassiveOutStorageTypeDocVo> list = passiveMapper.PassiveToStorageTypeDoc(shjg);
        return Result.ok(list);
    }


    @ApiOperation("无源入库/杂收-提交")
    @PostMapping("/PassiveToStorageGenerateDoc")
    @Log("无源入库/杂收-提交")
    public Result PassiveToStorageGenerateDoc(@RequestBody PassiveToSubmitDto dto) {
            //1、提交e10
            //同步中间表
            MiddleReturnDto middleReturnDto = passiveService.ABLPassiveToStorageInsertMiddleTable(dto.getList());
            //提交e10
            JSONObject jsonObject = passiveService.ABLPassiveToStorageSubmit(middleReturnDto,dto.getTypeCode());
        String executionCode = jsonObject
                .getJSONObject("payload")
                .getJSONObject("std_data")
                .getJSONObject("execution")
                .getString("code");

        log.info("无源入库/杂收-提交-/PassiveToStorageGenerateDoc e10:"+jsonObject);
        if (!"0".equals(executionCode)) {
            return Result.fail(jsonObject);
        }

//        // 1.查询未提交标签
//        List<InvBarcodeOperation> operationList = passiveMapper.queryNotSubimtByTypeAndCreateBy(6, dto.getCreateBy());
//        for (InvBarcodeOperation invBarcodeOperation : operationList) {
//            // 2.更新状态为已提交
//            passiveMapper.updateByid(invBarcodeOperation.getId());
////            passiveMapper.updateByid(invBarcodeOperation.getId());
//            // 3.扣减条码的剩余数量-杂收不应该扣条码数量
////            passiveMapper.updateBmBarcodeDetailOfQty(invBarcodeOperation.getBarcode(), invBarcodeOperation.getQty().doubleValue());
//            // 4.保存条码结果档
//            InvBarcodeTransaction invBarcodeTransaction = new InvBarcodeTransaction();
//            invBarcodeTransaction.setBarcode(invBarcodeOperation.getBarcode());
//            invBarcodeTransaction.setQty(invBarcodeOperation.getQty());
//            invBarcodeTransaction.setItemNo(invBarcodeOperation.getItemNo());
//            invBarcodeTransaction.setItemName(invBarcodeOperation.getItemName());
//            invBarcodeTransaction.setItemSpec(invBarcodeOperation.getItemSpec());
//            invBarcodeTransaction.setWarehouseNo(invBarcodeOperation.getWarehouseNo());
//            invBarcodeTransaction.setCellNo(invBarcodeOperation.getCellNo());
//            invBarcodeTransaction.setUnitNo(invBarcodeOperation.getUnitNo());
//            invBarcodeTransaction.setUnitName(invBarcodeOperation.getUnitName());
//            invBarcodeTransaction.setReferenceQty(invBarcodeOperation.getReferenceQty());
//            invBarcodeTransaction.setChagType(invBarcodeOperation.getChagType());
//            invBarcodeTransaction.setDocReturnFlag(invBarcodeOperation.getDocReturnFlag());
//            invBarcodeTransaction.setStatusCode(invBarcodeOperation.getStatusCode());
//            invBarcodeTransaction.setStockRate(invBarcodeOperation.getStockRate());
//            invBarcodeTransaction.setStockKeep(invBarcodeOperation.getStockKeep());
//            invBarcodeTransaction.setCreateBy(invBarcodeOperation.getCreateBy());
//            invBarcodeTransactionService.save(invBarcodeTransaction);
//        }
//
//

        return Result.ok(jsonObject);

    }









    }

