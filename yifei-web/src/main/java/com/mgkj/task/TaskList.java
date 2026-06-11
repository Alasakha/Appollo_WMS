package com.mgkj.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mgkj.dto.*;
import com.mgkj.dto.agv.CreateAgvTaskRequest;
import com.mgkj.entity.*;
import com.mgkj.mapper.AgvTaskInfoMapper;
import com.mgkj.mapper.DeliveryNoticeMapper;
import com.mgkj.mapper.MOCTAMapper;
import com.mgkj.mapper.MoMapper;
import com.mgkj.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TaskList {
    private static final String AUTO_SALE_OUT_REMARK = "wms-定时任务";

    private final String BASE_URL = "http://192.168.4.51:8182";
    private final int TIMEOUT = 5000;

    private static final String GEN_TASK = "/rcms/services/rest/hikRpcService/genAgvSchedulingTask";
    private static final String BIND_CTNR_BIN = "/rcms/services/rest/hikRpcService/bindCtnrAndBin";
    private static final String BIND_CTNR_TUNNEL = "/rcms/services/rest/hikRpcService/bindCtnrAndTunnel";

    @Resource
    private FinishedgoodsinbounddetailService finishedgoodsinbounddetailService;

    @Resource
    private MoService moService;

    @Resource
    private MoMapper moMapper;

    @Resource
    private MOCTAMapper moctaMapper;

    @Resource
    private WeChatService weChatService;

    @Resource
    private AgvTaskInfoService agvTaskInfoService;

    @Resource
    private AgvTaskInfoMapper agvTaskInfoMapper;

    @Resource
    private AgvWarehouseService agvWarehouseService;

    @Resource
    private SalesoutboundtaskdetailService salesoutboundtaskdetailService;

    @Resource
    private DeliveryNoticeMapper deliveryNoticeMapper;

    @Resource
    private SaleService saleService;


    /**
     * 扫码记录同步到E10定时任务
     * 每30分钟执行一次，从 SalesOutboundTaskDetail 读取 synced=0 的记录，
     * 批量写入E10中间表并提交，提交成功后标记 synced=1。
     */
    //    @Scheduled(cron = "0 0/30 * * * ?")
    public void syncToE10Task() {
        log.info("=== E10同步任务开始 ===");
        int totalSynced = 0;
        int batchLimit = 200;

        while (true) {
            List<Salesoutboundtaskdetail> unsyncedList = deliveryNoticeMapper.getUnsyncedRecords(batchLimit);
            if (CollUtil.isEmpty(unsyncedList)) {
                break;
            }

            log.info("本次待同步记录数：{}", unsyncedList.size());

            // 按 docNo + createBy 聚合（多人扫同一品号，E10只需要更新一次）
            Map<String, List<Salesoutboundtaskdetail>> grouped = unsyncedList.stream()
                    .collect(Collectors.groupingBy(d -> d.getDocNo() + "|" + d.getCreateBy()));

            for (Map.Entry<String, List<Salesoutboundtaskdetail>> entry : grouped.entrySet()) {
                List<Salesoutboundtaskdetail> group = entry.getValue();
                if (CollUtil.isEmpty(group)) continue;

                String createBy = group.get(0).getCreateBy();
                List<SaleOutStockDto> dtoList = group.stream().map(d -> {
                    SaleOutStockDto dto = new SaleOutStockDto();
                    dto.setDocNo(d.getDocNo());
                    dto.setBarcode(d.getBarcode());
                    dto.setItemCode(d.getItemCode());
                    dto.setUnitCode(d.getUnitCode());
                    dto.setBinCode(d.getBinCode());
                    dto.setWarehouseCode(d.getWarehouseCode());
                    dto.setMatchQty(d.getQty());
                    dto.setCurrentNum(d.getQty());
                    dto.setRemark("wms-定时同步");
                    dto.setShjg(d.getOrgNo());
                    return dto;
                }).collect(Collectors.toList());

                try {
                    // 1. 写入E10中间表
                    MiddleReturnDto middleReturnDto = saleService.SaleOutStockInsertMiddleTable(dtoList, createBy);
                    // 2. 提交E10
                    JSONObject jsonObject = saleService.SaleOutStockSubmit(middleReturnDto, createBy);
                    String executionCode = getE10ExecutionCode(jsonObject);

                    if ("0".equals(executionCode)) {
                        List<String> syncedIds = group.stream().map(Salesoutboundtaskdetail::getId).collect(Collectors.toList());
                        deliveryNoticeMapper.batchMarkSynced(syncedIds);
                        totalSynced += syncedIds.size();
                        log.info("E10同步成功，docNo={}, 条数={}", group.get(0).getDocNo(), syncedIds.size());
                    } else {
                        String failMsg = getE10FailureMessage(jsonObject);
                        log.error("E10同步失败，docNo={}, 原因={}", group.get(0).getDocNo(), failMsg);
                    }
                } catch (Exception e) {
                    log.error("E10同步异常，createBy={}, 原因={}", createBy, e.getMessage(), e);
                }
            }
        }

        log.info("=== E10同步任务完成，共同步 {} 条记录 ===", totalSynced);
    }

    /**
     * 自动销货单出库
     */
//    @PostConstruct
    @Scheduled(cron = "0 25 9 * * ?")
    @Scheduled(cron = "0 00 12 * * ?")
    @Scheduled(cron = "0 00 14 * * ?")
    @Scheduled(cron = "0 00 16 * * ?")
    @Scheduled(cron = "0 00 18 * * ?")
    @Scheduled(cron = "0 50 23 * * ?")
    public void submitSalesOutboundTask(){
        QueryWrapper<Salesoutboundtaskdetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 0);
        List<Salesoutboundtaskdetail> list = salesoutboundtaskdetailService.list(queryWrapper);
        if (CollUtil.isEmpty(list)) {
            log.info("自动销货单出库无待处理任务");
            return;
        }

        // 当前自动出库只处理品号 1 开头的销货单单身，其他品号不参与齐套判断。
        List<Salesoutboundtaskdetail> onePrefixTaskList = list.stream()
                .filter(this::isOnePrefixSalesOutboundTask)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(onePrefixTaskList)) {
            log.info("自动销货单出库待处理任务中无品号1开头的明细");
            return;
        }

        Map<String, List<Salesoutboundtaskdetail>> taskMapByDocNo = onePrefixTaskList.stream()
                .collect(Collectors.groupingBy(Salesoutboundtaskdetail::getDocNo));
        List<String> docNoList = new ArrayList<>(taskMapByDocNo.keySet());

        List<SalesOutboundDocSummary> e10SummaryList = deliveryNoticeMapper.getSalesOutboundDocSummary(docNoList);
        Map<String, SalesOutboundDocSummary> e10SummaryMap = CollUtil.isEmpty(e10SummaryList)
                ? Collections.emptyMap()
                : e10SummaryList.stream()
                .collect(Collectors.toMap(SalesOutboundDocSummary::getDocNo, item -> item, (oldValue, newValue) -> oldValue));

        for (Map.Entry<String, List<Salesoutboundtaskdetail>> entry : taskMapByDocNo.entrySet()) {
            String docNo = entry.getKey();
            List<Salesoutboundtaskdetail> taskDetailList = entry.getValue();
            SalesOutboundDocSummary e10Summary = e10SummaryMap.get(docNo);
            if (e10Summary == null) {
                log.info("自动销货单出库跳过，E10未查询到品号1开头单身，docNo={}", docNo);
                continue;
            }

            BigDecimal taskQty = sumSalesOutboundTaskQty(taskDetailList);
            BigDecimal issuedQty = defaultZero(e10Summary.getIssuedQty());
            BigDecimal businessQty = defaultZero(e10Summary.getBusinessQty());
            BigDecimal readyQty = taskQty.add(issuedQty);

            // 齐套公式：同一销货单下所有品号 1 开头的任务数量 + E10 已出库数量 = E10 业务数量。
            if (readyQty.compareTo(businessQty) != 0) {
                log.info("自动销货单出库未齐套跳过，docNo={}, taskQty={}, issuedQty={}, businessQty={}",
                        docNo, taskQty, issuedQty, businessQty);
                continue;
            }

            List<SaleOutStockDto> saleOutStockDtoList = taskDetailList.stream()
                    .map(this::buildSaleOutStockDto)
                    .collect(Collectors.toList());
            SaleOutSubmitDTO submitDTO = buildAutoSaleOutSubmitDTO(taskDetailList, saleOutStockDtoList);
            String invalidMessage = validateSaleOutSubmitDTO(submitDTO);
            if (StrUtil.isNotBlank(invalidMessage)) {
                markSalesOutboundTaskFailed(taskDetailList, invalidMessage);
                log.error("自动销货单出库提交参数错误，docNo={}, invalidMessage={}, submitDTO={}",
                        docNo, invalidMessage, JSONUtil.toJsonStr(submitDTO));
                continue;
            }
            log.info("自动销货单出库提交参数，docNo={}, submitDTO={}", docNo, JSONUtil.toJsonStr(submitDTO));
            handleReadySalesOutboundTask(docNo, taskDetailList, submitDTO, e10Summary);
        }
    }

    private boolean isOnePrefixSalesOutboundTask(Salesoutboundtaskdetail detail) {
        return detail != null
                && StrUtil.isNotBlank(detail.getDocNo())
                && StrUtil.isNotBlank(detail.getItemCode())
                && detail.getItemCode().startsWith("1")
                && detail.getQty() != null;
    }

    private BigDecimal sumSalesOutboundTaskQty(List<Salesoutboundtaskdetail> taskDetailList) {
        return taskDetailList.stream()
                .map(Salesoutboundtaskdetail::getQty)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal defaultZero(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    private SaleOutStockDto buildSaleOutStockDto(Salesoutboundtaskdetail detail) {
        SaleOutStockDto dto = new SaleOutStockDto();
        dto.setDocNo(detail.getDocNo());
        dto.setBarcode(detail.getBarcode());
        dto.setItemCode(detail.getItemCode());
        dto.setUnitCode(detail.getUnitCode());
        dto.setBinCode(detail.getBinCode());
        dto.setWarehouseCode(detail.getWarehouseCode());
        dto.setMatchQty(detail.getQty());
        dto.setCurrentNum(detail.getQty());
        dto.setRemark(AUTO_SALE_OUT_REMARK);
        dto.setShjg(detail.getOrgNo());
        return dto;
    }

    private SaleOutSubmitDTO buildAutoSaleOutSubmitDTO(List<Salesoutboundtaskdetail> taskDetailList,
                                                       List<SaleOutStockDto> saleOutStockDtoList) {
        SaleOutSubmitDTO submitDTO = new SaleOutSubmitDTO();
        submitDTO.setCreateBy(resolveCreateBy(taskDetailList));
        submitDTO.setList(saleOutStockDtoList);
        submitDTO.setInvBarcodeOperationIdList(Collections.emptyList());
        return submitDTO;
    }

    private String validateSaleOutSubmitDTO(SaleOutSubmitDTO submitDTO) {
        if (submitDTO == null) {
            return "提交参数不能为空";
        }
        if (StrUtil.isBlank(submitDTO.getCreateBy())) {
            return "操作人不能为空";
        }
        if (CollUtil.isEmpty(submitDTO.getList())) {
            return "提交明细不能为空";
        }
        for (SaleOutStockDto dto : submitDTO.getList()) {
            if (dto == null) {
                return "提交明细不能为空";
            }
            if (StrUtil.hasBlank(dto.getDocNo(), dto.getBarcode(), dto.getItemCode(), dto.getUnitCode(), dto.getWarehouseCode())) {
                return "提交明细关键字段不能为空";
            }
            if (dto.getCurrentNum() == null || dto.getCurrentNum().compareTo(BigDecimal.ZERO) <= 0) {
                return "异动数量不能小于0";
            }
        }
        return null;
    }

    private void handleReadySalesOutboundTask(String docNo,
                                              List<Salesoutboundtaskdetail> taskDetailList,
                                              SaleOutSubmitDTO submitDTO,
                                              SalesOutboundDocSummary e10Summary) {
        log.info("自动销货单出库已齐套，开始提交E10，docNo={}, taskDetailCount={}, submitDetailCount={}, businessQty={}, issuedQty={}",
                docNo,
                taskDetailList.size(),
                submitDTO.getList().size(),
                defaultZero(e10Summary.getBusinessQty()),
                defaultZero(e10Summary.getIssuedQty()));

        String createBy = submitDTO.getCreateBy();

        try {
            // 复用人工销货出库提交链路：先写 E10 中间表，再调用 E10 上传接口。
            MiddleReturnDto middleReturnDto = saleService.SaleOutStockInsertMiddleTable(submitDTO.getList(), createBy);
            JSONObject jsonObject = saleService.SaleOutStockSubmit(middleReturnDto, createBy);
            String executionCode = getE10ExecutionCode(jsonObject);
            if ("0".equals(executionCode)) {
                String createDocNo = getE10CreateDocNo(jsonObject);
                markSalesOutboundTaskSuccess(taskDetailList, createDocNo);
                log.info("自动销货单出库提交E10成功，docNo={}, createDocNo={}", docNo, createDocNo);
                return;
            }

            String failureMessage = getE10FailureMessage(jsonObject);
            markSalesOutboundTaskFailed(taskDetailList, failureMessage);
            log.error("自动销货单出库提交E10失败，docNo={}, failureMessage={}, response={}",
                    docNo, failureMessage, JSONUtil.toJsonStr(jsonObject));
        } catch (Exception e) {
            String failureMessage = StrUtil.blankToDefault(e.getMessage(), e.getClass().getSimpleName());
            markSalesOutboundTaskFailed(taskDetailList, failureMessage);
            log.error("自动销货单出库提交E10异常，docNo={}, failureMessage={}", docNo, failureMessage, e);
        }
    }

    private String resolveCreateBy(List<Salesoutboundtaskdetail> taskDetailList) {
        return taskDetailList.stream()
                .map(Salesoutboundtaskdetail::getCreateBy)
                .filter(StrUtil::isNotBlank)
                .findFirst()
                .orElse(null);
    }

    private void markSalesOutboundTaskSuccess(List<Salesoutboundtaskdetail> taskDetailList, String createDocNo) {
        for (Salesoutboundtaskdetail detail : taskDetailList) {
            detail.setStatus(1);
            if (StrUtil.isNotBlank(createDocNo)) {
                detail.setCreateDocNo(createDocNo);
            }
        }
        salesoutboundtaskdetailService.updateBatchById(taskDetailList);
    }

    private void markSalesOutboundTaskFailed(List<Salesoutboundtaskdetail> taskDetailList, String failureMessage) {
        String remark = StrUtil.subPre(StrUtil.blankToDefault(failureMessage, "E10提交失败"), 1000);
        for (Salesoutboundtaskdetail detail : taskDetailList) {
            detail.setStatus(2);
            detail.setRemark(remark);
        }
        salesoutboundtaskdetailService.updateBatchById(taskDetailList);
    }

    private String buildFailureRemark(String failureMessage) {
        String remark = StrUtil.blankToDefault(failureMessage, "E10提交失败")
                .replace("\r", " ")
                .replace("\n", " ");
        return StrUtil.subPre(remark, 200);
    }

    private String getE10ExecutionCode(JSONObject jsonObject) {
        JSONObject execution = getE10Execution(jsonObject);
        return execution == null ? null : execution.getString("code");
    }

    private String getE10FailureMessage(JSONObject jsonObject) {
        JSONObject execution = getE10Execution(jsonObject);
        if (execution == null) {
            return "E10返回结果缺少execution节点";
        }
        return StrUtil.blankToDefault(execution.getString("description"), "E10提交失败");
    }

    private JSONObject getE10Execution(JSONObject jsonObject) {
        JSONObject payload = jsonObject == null ? null : jsonObject.getJSONObject("payload");
        JSONObject stdData = payload == null ? null : payload.getJSONObject("std_data");
        return stdData == null ? null : stdData.getJSONObject("execution");
    }

    private String getE10CreateDocNo(JSONObject jsonObject) {
        JSONObject payload = jsonObject == null ? null : jsonObject.getJSONObject("payload");
        JSONObject stdData = payload == null ? null : payload.getJSONObject("std_data");
        JSONObject parameter = stdData == null ? null : stdData.getJSONObject("parameter");
        JSONArray resultArray = parameter == null ? null : parameter.getJSONArray("als_app_request_result");
        if (CollUtil.isEmpty(resultArray)) {
            return null;
        }
        JSONObject firstResult = resultArray.getJSONObject(0);
        return firstResult == null ? null : firstResult.getString("doc_no");
    }

    /**
     * 按包装任务生成AGV调度任务
     */
//    @Scheduled(cron = "0 0/3 * * * ?")
    public void submitAgvTaskByPack() {
        log.info("开始生成包装的AGV任务");
        List<AgvTaskInfo> agvTaskList = agvTaskInfoMapper.selectList(
                new QueryWrapper<AgvTaskInfo>()
                        .eq("taskStatus", 0)
                        .eq("taskType", "ABL2")
                        .orderByDesc("createTime"));
        // 查询所有空巷道信息
        QueryWrapper<AgvWarehouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 0);
        queryWrapper.ne("parentId", "");
        queryWrapper.isNotNull("parentId");
        queryWrapper.orderByDesc("parentId");
        List<AgvWarehouse> agvWarehouses = agvWarehouseService.list(queryWrapper);
        int warehouseIndex = 0;
        for (AgvTaskInfo agvTaskInfo : agvTaskList) {
            if (StrUtil.isBlank(agvTaskInfo.getStartLocationCode())
                    || StrUtil.isBlank(agvTaskInfo.getStartLocationType())
                    || StrUtil.isBlank(agvTaskInfo.getTaskType())) {
                log.error("AGV任务参数错误: {}", JSONUtil.toJsonStr(agvTaskInfo));
                continue;
            }
            while (warehouseIndex < agvWarehouses.size()
                    && (StrUtil.isBlank(agvWarehouses.get(warehouseIndex).getStationCode())
                    || StrUtil.isBlank(agvWarehouses.get(warehouseIndex).getPositionCode()))) {
                warehouseIndex++;
            }
            if (warehouseIndex >= agvWarehouses.size()) {
                agvTaskInfo.setTaskStatus(8);
                agvTaskInfo.setRespParam("无可用结束位置");
                agvTaskInfoService.updateById(agvTaskInfo);
                log.error("无可用结束位置, taskId={}", agvTaskInfo.getId());
                continue;
            }
            // 每条任务都按顺序占用一个空位
            AgvWarehouse endWarehouse = agvWarehouses.get(warehouseIndex++);
            agvTaskInfo.setEndLocationCode(endWarehouse.getStationCode());
            agvTaskInfo.setEndLocationType("06");
            // 绑定起点
            AgvWarehouse one = agvWarehouseService.getOne(new QueryWrapper<AgvWarehouse>().eq("stationCode", agvTaskInfo.getStartLocationCode()));
            if (one == null || StrUtil.isBlank(one.getPositionCode())) {
                agvTaskInfo.setTaskStatus(8);
                agvTaskInfo.setRespParam("起点仓位不存在");
                agvTaskInfoService.updateById(agvTaskInfo);
                log.error("起点仓位不存在, taskId={}, startLocationCode={}", agvTaskInfo.getId(), agvTaskInfo.getStartLocationCode());
                continue;
            }
            String reqTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String reqCode = IdUtil.simpleUUID();
            String bindCtnrAndBinUrl = BASE_URL + BIND_CTNR_BIN;
            BindCtnrAndBinRequest bindCtnrAndBinRequest = new BindCtnrAndBinRequest();
            bindCtnrAndBinRequest.setReqTime(reqTime);
            bindCtnrAndBinRequest.setReqCode(reqCode);
            bindCtnrAndBinRequest.setCtnrTyp("5");
            bindCtnrAndBinRequest.setStgBinCode(one.getPositionCode());
            bindCtnrAndBinRequest.setIndBind("1");
            String bindCtnrAndBinRequestBody = JSONUtil.toJsonStr(bindCtnrAndBinRequest);
            log.info("绑定容器和仓位请求: {}", bindCtnrAndBinRequestBody);
            HttpResponse response = HttpRequest.post(bindCtnrAndBinUrl)
                    .timeout(TIMEOUT)
                    .header("Content-Type", "application/json")
                    .body(bindCtnrAndBinRequestBody)
                    .execute();
            log.info("绑定容器和仓位响应: {}", response.body());
            if (!response.isOk()) {
                log.error("绑定容器和仓位失败: {}", response.body());
                agvTaskInfo.setTaskStatus(8);
                agvTaskInfo.setRespParam(response.body());
                agvTaskInfo.setCreateTime(reqTime);
                agvTaskInfoService.updateById(agvTaskInfo);
                continue;
            }
//            // 解绑终点
//            reqTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            reqCode = IdUtil.simpleUUID();
//            bindCtnrAndBinUrl = BASE_URL + BIND_CTNR_TUNNEL;
//            BindCtnrAndBinRequest bindCtnrAndBinRequest1 = new BindCtnrAndBinRequest();
//            bindCtnrAndBinRequest1.setReqTime(reqTime);
//            bindCtnrAndBinRequest1.setReqCode(reqCode);
//            bindCtnrAndBinRequest1.setCtnrTyp("5");
//            bindCtnrAndBinRequest1.setStgBinCode(endWarehouse.getPositionCode());
//            bindCtnrAndBinRequest1.setIndBind("0");
//            bindCtnrAndBinRequestBody = JSONUtil.toJsonStr(bindCtnrAndBinRequest1);
//            response = HttpRequest.post(bindCtnrAndBinUrl)
//                    .timeout(TIMEOUT)
//                    .header("Content-Type", "application/json")
//                    .body(bindCtnrAndBinRequestBody)
//                    .execute();
//            if (!response.isOk()) {
//                log.error("绑定容器和仓位失败: {}", response.body());
//                agvTaskInfo.setTaskStatus(8);
//                agvTaskInfo.setRespParam(response.body());
//                agvTaskInfo.setCreateTime(reqTime);
//                agvTaskInfoService.updateById(agvTaskInfo);
//                continue;
//            }
            // 生成AGV调度任务
            CreateAgvTaskRequest createAgvTaskRequest = new CreateAgvTaskRequest();
            createAgvTaskRequest.setReqCode(reqCode);
            createAgvTaskRequest.setReqTime(reqTime);
            createAgvTaskRequest.setTaskTyp("ABL2");
            createAgvTaskRequest.setCtnrTyp("5");
            // 路径
            ArrayList<CreateAgvTaskRequest.PositionCodePath> positionCodePaths = new ArrayList<>(2);
            CreateAgvTaskRequest.PositionCodePath startPositionCodePath = new CreateAgvTaskRequest.PositionCodePath();
            startPositionCodePath.setPositionCode(agvTaskInfo.getStartLocationCode());
            startPositionCodePath.setType(agvTaskInfo.getStartLocationType());
            positionCodePaths.add(startPositionCodePath);

            CreateAgvTaskRequest.PositionCodePath endPositionCodePath = new CreateAgvTaskRequest.PositionCodePath();
            endPositionCodePath.setPositionCode(endWarehouse.getParentId());
            endPositionCodePath.setType(agvTaskInfo.getEndLocationType());
            positionCodePaths.add(endPositionCodePath);

            createAgvTaskRequest.setPositionCodePath(positionCodePaths);
            createAgvTaskRequest.setPriority("1");
            String url = BASE_URL + GEN_TASK;
            String requestBody = JSONUtil.toJsonStr(createAgvTaskRequest);
            response = HttpRequest.post(url)
                    .timeout(TIMEOUT)
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .execute();

            if (!response.isOk()) {
                agvTaskInfo.setTaskStatus(8);
                agvTaskInfo.setRespParam(response.body());
                agvTaskInfo.setCreateTime(reqTime);
                agvTaskInfoService.updateById(agvTaskInfo);
                continue;
            }
            log.info("生成AGV调度任务请求: {}", JSONUtil.toJsonStr(createAgvTaskRequest));
            String body = response.body();
            log.info("生成AGV调度任务响应: {}", body);
            cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(body);
            int code = jsonObject.getInt("code");
            String taskCode = jsonObject.getStr("data");
            agvTaskInfo.setTaskCode(taskCode);
            agvTaskInfo.setReqCode(reqCode);
            agvTaskInfo.setRespParam(JSONUtil.toJsonStr(body));
            agvTaskInfo.setCreateTime(reqTime);
            if (code != 0) {
                agvTaskInfo.setTaskStatus(8);
                agvTaskInfoService.updateById(agvTaskInfo);
                log.error("生成AGV调度任务失败: {}", code);
            } else {
                agvTaskInfo.setTaskStatus(1);
                agvTaskInfoService.updateById(agvTaskInfo);
            }
        }
    }

    /**
     * 配送的AGV任务
     */
//    @Scheduled(cron = "0 0/1 * * * ?")
    public void submitAgvTaskByAssembly() {
        log.info("开始生成配送的AGV任务");
        List<AgvTaskInfo> agvTaskList = agvTaskInfoMapper.selectList(
                new QueryWrapper<AgvTaskInfo>()
                .eq("taskStatus", 0)
                .eq("taskType", "ABL1")
                .orderByDesc("createTime"));
        if (agvTaskList.isEmpty()) {
            log.info("没有待处理的配送任务");
            return;
        }
        for (AgvTaskInfo agvTaskInfo : agvTaskList) {
            if (StrUtil.isBlank(agvTaskInfo.getEndLocationCode())
                    || StrUtil.isBlank(agvTaskInfo.getStartLocationCode())
                    || StrUtil.isBlank(agvTaskInfo.getEndLocationType())
                    || StrUtil.isBlank(agvTaskInfo.getStartLocationType())
                    || StrUtil.isBlank(agvTaskInfo.getTaskType())) {
                continue;
            }
            // 绑定起点
            String reqTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String reqCode = IdUtil.simpleUUID();
            String bindCtnrAndBinUrl = BASE_URL + BIND_CTNR_BIN;
            BindCtnrAndBinRequest bindCtnrAndBinRequest = new BindCtnrAndBinRequest();
            bindCtnrAndBinRequest.setReqTime(reqTime);
            bindCtnrAndBinRequest.setReqCode(reqCode);
            if ("168047AA139931".equals(agvTaskInfo.getStartLocationCode())) {
                bindCtnrAndBinRequest.setCtnrTyp("3");
            } else {
                bindCtnrAndBinRequest.setCtnrTyp("4");
            }
            AgvWarehouse one = agvWarehouseService.getOne(new QueryWrapper<AgvWarehouse>().eq("stationCode", agvTaskInfo.getStartLocationCode()));
            bindCtnrAndBinRequest.setStgBinCode(one.getPositionCode());
            bindCtnrAndBinRequest.setIndBind("1");
            String bindCtnrAndBinRequestBody = JSONUtil.toJsonStr(bindCtnrAndBinRequest);
            HttpResponse response = HttpRequest.post(bindCtnrAndBinUrl)
                    .timeout(TIMEOUT)
                    .header("Content-Type", "application/json")
                    .body(bindCtnrAndBinRequestBody)
                    .execute();
            log.info("绑定起点响应: {}", response.body());
            // 解绑终点
            reqTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            reqCode = IdUtil.simpleUUID();
            BindCtnrAndBinRequest bindCtnrAndBinRequest1 = new BindCtnrAndBinRequest();
            bindCtnrAndBinRequest1.setReqTime(reqTime);
            bindCtnrAndBinRequest1.setReqCode(reqCode);
            bindCtnrAndBinRequest1.setCtnrTyp("4");
            one = agvWarehouseService.getOne(new QueryWrapper<AgvWarehouse>().eq("stationCode", agvTaskInfo.getEndLocationCode()));
            bindCtnrAndBinRequest1.setStgBinCode(one.getPositionCode());
            bindCtnrAndBinRequest1.setIndBind("0");
            bindCtnrAndBinRequestBody = JSONUtil.toJsonStr(bindCtnrAndBinRequest1);
            response = HttpRequest.post(bindCtnrAndBinUrl)
                    .timeout(TIMEOUT)
                    .header("Content-Type", "application/json")
                    .body(bindCtnrAndBinRequestBody)
                    .execute();
            log.info("解绑终点响应: {}", response.body());
            // 生成AGV调度任务
            CreateAgvTaskRequest createAgvTaskRequest = new CreateAgvTaskRequest();
            createAgvTaskRequest.setReqCode(reqCode);
            createAgvTaskRequest.setReqTime(reqTime);
            createAgvTaskRequest.setTaskTyp("ABL1");
            if ("168047AA139931".equals(agvTaskInfo.getStartLocationCode())) {
                createAgvTaskRequest.setCtnrTyp("3");
            } else {
                createAgvTaskRequest.setCtnrTyp("4");
            }
            // 路径
            ArrayList<CreateAgvTaskRequest.PositionCodePath> positionCodePaths = new ArrayList<>(2);
            CreateAgvTaskRequest.PositionCodePath startPositionCodePath = new CreateAgvTaskRequest.PositionCodePath();
            startPositionCodePath.setPositionCode(agvTaskInfo.getStartLocationCode());
            startPositionCodePath.setType(agvTaskInfo.getStartLocationType());
            positionCodePaths.add(startPositionCodePath);

            CreateAgvTaskRequest.PositionCodePath endPositionCodePath = new CreateAgvTaskRequest.PositionCodePath();
            endPositionCodePath.setPositionCode(agvTaskInfo.getEndLocationCode());
            endPositionCodePath.setType(agvTaskInfo.getEndLocationType());
            positionCodePaths.add(endPositionCodePath);

            createAgvTaskRequest.setPositionCodePath(positionCodePaths);
            createAgvTaskRequest.setPriority("1");
            String url = BASE_URL + GEN_TASK;
            String requestBody = JSONUtil.toJsonStr(createAgvTaskRequest);
            response = HttpRequest.post(url)
                    .timeout(TIMEOUT)
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .execute();

            if (!response.isOk()) {
                log.error("生成AGV调度任务失败: {}", response.body());
            }
            log.info("生成AGV调度任务请求: {}", JSONUtil.toJsonStr(createAgvTaskRequest));
            String body = response.body();
            log.info("生成AGV调度任务响应: {}", body);
            cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(body);
            int code = jsonObject.getInt("code");
            String taskCode = jsonObject.getStr("data");
            agvTaskInfo.setTaskCode(taskCode);
            agvTaskInfo.setReqCode(reqCode);
            agvTaskInfo.setRespParam(JSONUtil.toJsonStr(body));
            agvTaskInfo.setCreateTime(reqTime);
            if (code != 0) {
                agvTaskInfo.setTaskStatus(8);
                agvTaskInfoService.updateById(agvTaskInfo);
                log.error("生成AGV调度任务失败: {}", code);
            } else {
                agvTaskInfo.setTaskStatus(1);
                agvTaskInfoService.updateById(agvTaskInfo);
            }
        }
    }


//    @PostConstruct
//    @Scheduled(cron = "0 21 9 * * ?")
//    @Scheduled(cron = "0 40 11 * * ?")
//    @Scheduled(cron = "0 50 16 * * ?")
//    @Scheduled(cron = "0 10 22 * * ?")
    public void finishedGoodSinBoundDetailTask() {
        log.info("开始执行 finishedGoodSinBoundDetailTask 任务");
        // 1. 查询待提交数据
        List<FinishedGoodsInboundDetail> detailList =
                finishedgoodsinbounddetailService.list(
                        new QueryWrapper<FinishedGoodsInboundDetail>().in("status", 0, 2)
                );

        if (CollUtil.isEmpty(detailList)) {
            return;
        }

        // 2. 转 DTO 并按工单分组
        Map<String, List<FinishedGoodsInboundDetail>> groupMap =
                detailList.stream().collect(Collectors.groupingBy(FinishedGoodsInboundDetail::getDocNo));

        // 3. 按工单处理
        for (Map.Entry<String, List<FinishedGoodsInboundDetail>> entry : groupMap.entrySet()) {

            boolean isSuccess = true;

            String docNo = entry.getKey();
            List<FinishedGoodsInboundDetail> currentList = entry.getValue();
//            String createBy = currentList.get(0).getCreateBy();

            // 通知人列表
            List<String> creatorList = currentList.stream()
                    .map(FinishedGoodsInboundDetail::getCreateBy)
                    .distinct()
                    .collect(Collectors.toList());

            // 3.1 已完工工单直接跳过
            MOCTA mocta = moctaMapper.getByTA001TA002(docNo);
            if (mocta == null || "Y".equalsIgnoreCase(mocta.getTA011())) {
//                handleThirdPartyError("已完工工单", docNo, "请检查工单", creatorList);
                continue;
            }

            MoQtyInfo moQtyInfo = moctaMapper.getMoQtyByDocNo(docNo);
            if (moQtyInfo == null) {
                handleThirdPartyError("未找到工单信息", docNo, "请检查工单", creatorList);
                continue;
            }
            BigDecimal planQty = moQtyInfo.getPlanQty(); // 工单数量
            BigDecimal completedQty = moQtyInfo.getCompletedQty(); // 已入库数量

            // 4. 计算本次入库总数量
            BigDecimal currentInboundQty = currentList.stream()
                    .map(FinishedGoodsInboundDetail::getQty)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // 判断本次入库数量 + 已入库数量是否等于工单数量，不相等则跳过当前工单
            if (currentInboundQty.add(completedQty).compareTo(planQty) != 0) {
                continue;
            }


            // 5. 批量查询裸机信息
            List<String> barcodes = currentList.stream()
                    .map(FinishedGoodsInboundDetail::getBarcode)
                    .distinct()
                    .collect(Collectors.toList());

            List<MoLjzpInStorageSubmitDto> ljRawList = moMapper.selectLjMoBatch(barcodes);

            Map<String, List<MoLjzpInStorageSubmitDto>> ljMap =
                    ljRawList.stream().collect(Collectors.groupingBy(MoLjzpInStorageSubmitDto::getBarcode));

            List<MoLjzpInStorageSubmitDto> ljInStorageList = new ArrayList<>();
            String ljProduct = null;
            for (FinishedGoodsInboundDetail dto : currentList) {
                List<MoLjzpInStorageSubmitDto> ljList =
                        ljMap.getOrDefault(dto.getBarcode(), Collections.emptyList());

                for (MoLjzpInStorageSubmitDto lj : ljList) {
                    lj.setMatchQty(dto.getQty());
                    ljProduct = lj.getItemCode();
                }
                ljInStorageList.addAll(ljList);
            }

            // 6. 判断是否需要裸机发料
            BigDecimal allNeedQty = mocta.getTA017().add(currentInboundQty);
            BigDecimal issuedQty = moctaMapper.getTb005ByTb001Tb002AndProductId(ljProduct, docNo);
            String bareIssueDocNo = null;

            if (ljProduct != null && allNeedQty.compareTo(issuedQty) > 0) {

                List<MoLjZpIssueSubmitDto> issueList =
                        ljInStorageList.stream().map(lj -> {
                            MoLjZpIssueSubmitDto issueDto = new MoLjZpIssueSubmitDto();
                            issueDto.setDocNo(docNo);
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

                try {
                    String createby = Optional.ofNullable(issueList).filter(CollectionUtils::isNotEmpty).map(list -> list.get(0).getShjg())
                            .map(shjg -> "1".equals(shjg) ? "SC02" : "SC07").orElse("SC02");

                    MiddleReturnDto middleReturnDto = moService.insertABlLjZpMiddleTable(issueList, createby);
                    JSONObject result = moService.MoIssueSubmit(middleReturnDto, createby);
                    String executionCode = result.getJSONObject("payload")
                            .getJSONObject("std_data")
                            .getJSONObject("execution")
                            .getString("code");
                    if (!"0".equals(executionCode)) {
                        String description = result
                                .getJSONObject("payload")
                                .getJSONObject("std_data")
                                .getJSONObject("execution")
                                .getString("description");
                        isSuccess = false;
                        handleThirdPartyError("裸机发料失败", docNo, description, creatorList);
                    } else {
                        bareIssueDocNo = result.getJSONObject("payload")
                                .getJSONObject("std_data")
                                .getJSONObject("parameter")
                                .getJSONArray("als_app_request_result")
                                .getJSONObject(0)
                                .getString("doc_no");
                    }
                } catch (Exception e) {
                    isSuccess = false;
                    handleThirdPartyError("裸机发料异常", docNo, e, creatorList);
                }
            }
            String packageStorageDocNo = null;
            // 7. 主品入库
            MiddleReturnDto middleReturnDto2 = moService.insertMoInStorageMiddleTableNew(currentList);
            String createby2 = Optional.ofNullable(currentList).filter(CollectionUtils::isNotEmpty).map(list -> list.get(0).getOrgNo())
                    .map(shjg -> "1".equals(shjg) ? "SC02" : "SC08").orElse("SC02");
            JSONObject result2 = null;
            try {
                result2 = moService.MoInStorageSubmit(middleReturnDto2, createby2);
                String executionCode2 = result2.getJSONObject("payload")
                        .getJSONObject("std_data")
                        .getJSONObject("execution")
                        .getString("code");

                if (!"0".equals(executionCode2)) {
                    String description = result2
                            .getJSONObject("payload")
                            .getJSONObject("std_data")
                            .getJSONObject("execution")
                            .getString("description");
                    isSuccess = false;
                    handleThirdPartyError("主品入库失败", docNo, description, creatorList);
                }
                packageStorageDocNo = result2.getJSONObject("payload")
                        .getJSONObject("std_data")
                        .getJSONObject("parameter")
                        .getJSONArray("als_app_request_result")
                        .getJSONObject(0)
                        .getString("doc_no");

            } catch (Exception e) {
                isSuccess = false;
                handleThirdPartyError("主品入库异常", docNo, result2, creatorList);
            }
            String time = LocalDateTimeUtil.now().toString();
            if (isSuccess) {
                // 8. 回写条码状态（批量）
                for (FinishedGoodsInboundDetail dto : currentList) {
                    dto.setBarcode(dto.getBarcode());
                    dto.setIssueNo(bareIssueDocNo);
                    dto.setReceiptNo(packageStorageDocNo);
                    dto.setStatus(1);
                    dto.setRemark(time);
                }

                finishedgoodsinbounddetailService.updateBatchById(currentList);

                // 兼容原逻辑
                currentList.forEach(moMapper::updateBarcodeWBUNew);
            } else {
                for (FinishedGoodsInboundDetail dto : currentList) {
                    dto.setBarcode(dto.getBarcode());
                    dto.setIssueNo(bareIssueDocNo);
                    dto.setReceiptNo(packageStorageDocNo);
                    dto.setStatus(2);
                    dto.setRemark(time);
                }

                finishedgoodsinbounddetailService.updateBatchById(currentList);
            }
        }
    }

    private void handleThirdPartyError(String message, String docNo, Object result, List<String> creatorList) {
        String resultStr;

        // 判断 result 是否是异常对象
        if (result instanceof Throwable) {
            Throwable t = (Throwable) result;
            // 将堆栈信息转成字符串
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            resultStr = sw.toString();
        } else {
            resultStr = String.valueOf(result);
        }

        log.error("整车入库失败：{}, 工单:{}, result:{}", message, docNo, resultStr);

        Set<String> userSet = moMapper.queryQywxUser(creatorList);
        String workCenter = moMapper.queryWorkCenter(docNo);
        if ("1005".equals(workCenter)) {
            userSet.addAll(Arrays.asList("HuLiQin","ZhangYiZe","LiYuBao","YangJianBo","YuanZhiFei","ZhuGuoLin","WangShuangLiang"));
        }
        if ("2005".equals(workCenter)){
            userSet.addAll(Arrays.asList("HuLin","YouJingMan","GeMingYing","ZhuGuoLin"));
        }

        MessageDto messageDto = new MessageDto();
        messageDto.setContent("工单:" + docNo + "\n" + message +  "\n原因:\n" + resultStr);
        messageDto.setTouser(new ArrayList<>(userSet));
        try {
            weChatService.sendWXMessage(messageDto);
        } catch (Exception e) {
            log.error("整车入库失败，发送微信消息失败：{}", e.getMessage());
        }
//        weChatService.sendWXMessage(messageDto);
    }

}
