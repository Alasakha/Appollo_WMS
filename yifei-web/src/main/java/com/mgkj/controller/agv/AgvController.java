package com.mgkj.controller.agv;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.dto.agv.PlanMoInfo;
import com.mgkj.dto.agv.QueryMoList;
import com.mgkj.dto.agv.ScanSubmitByPackDTO;
import com.mgkj.entity.*;
import com.mgkj.mapper.AgvTaskDetailMapper;
import com.mgkj.mapper.AgvTaskInfoMapper;
import com.mgkj.mapper.MoMapper;
import com.mgkj.service.AgvTaskDetailService;
import com.mgkj.service.AgvTaskInfoService;
import com.mgkj.service.AgvWarehouseService;
import com.mgkj.vo.MaterialBarCodeDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "AGV相关接口")
@RequestMapping("/api/agv")
public class AgvController {

    private final String BASE_URL = "http://192.168.4.51:8182";
    private final int TIMEOUT = 5000;

    private static final String GEN_TASK = "/rcms/services/rest/hikRpcService/genAgvSchedulingTask";
    private static final String BIND_CTNR_BIN = "/rcms/services/rest/hikRpcService/bindCtnrAndBin";
    private static final String CANCEL_TASK = "/rcms/services/rest/hikRpcService/cancelTask";


    @Resource
    private AgvTaskDetailService agvTaskDetailService;

    @Resource
    private AgvTaskDetailMapper agvTaskDetailMapper;

    @Resource
    private AgvTaskInfoService agvTaskInfoService;

    @Resource
    private AgvTaskInfoMapper agvTaskInfoMapper;

    @Resource
    private AgvWarehouseService agvWarehouseService;

    @Resource
    private MoMapper moMapper;

    @PostMapping("/deleteScanByPack/{id}")
    @ApiOperation(value = "删除成品包装扫码记录接口")
    public Result deleteScanByPack(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.fail().message("请求参数不能为空");
        }
        agvTaskDetailService.removeById(id);
        return Result.ok();
    }

    @PostMapping("/scanByPackList")
    @ApiOperation(value = "查询成品包装扫码记录接口")
    public Result scanByPackList(@RequestBody ScanDTO dto) {
        if (dto == null || StrUtil.isEmpty(dto.getCreateBy())) {
            return Result.fail().message("请求参数不能为空");
        }
        QueryWrapper<AgvTaskDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", 1);
        queryWrapper.eq("status", 0);
        queryWrapper.eq("createdBy", dto.getCreateBy());
        List<AgvTaskDetail> list = agvTaskDetailService.list(queryWrapper);
        return Result.ok(list);
    }

    @PostMapping("/scanByPack")
    @ApiOperation(value = "成品包装扫码接口")
    public Result scanByPack(@RequestBody ScanDTO dto) {
        if (dto == null || StrUtil.isEmpty(dto.getBarcode()) || StrUtil.isEmpty(dto.getCreateBy())) {
            return Result.fail().message("请求参数不能为空");
        }
        Map<String, Object> map = moMapper.getSnCodeInfo(dto.getBarcode());
        if (map == null) {
            return Result.fail().message("条码不存在");
        }
        QueryWrapper<AgvTaskDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("barcode", dto.getBarcode());
        long count = agvTaskDetailService.count(queryWrapper);
        if (count > 0) {
            return Result.fail().message("当前条码已扫过");
        }
        AgvTaskDetail agvTaskDetail = new AgvTaskDetail();
        agvTaskDetail.setBarcode(dto.getBarcode());
        agvTaskDetail.setItemCode(map.get("itemCode").toString());
        agvTaskDetail.setItemName(map.get("itemName").toString());
        agvTaskDetail.setItemSpec(map.get("itemSpec").toString());
        agvTaskDetail.setDocNo(map.get("khdh").toString());
        agvTaskDetail.setCreatedBy(dto.getCreateBy());
        agvTaskDetail.setStatus(0);
        agvTaskDetail.setType(1);
        agvTaskDetailService.save(agvTaskDetail);
        return Result.ok(agvTaskDetail);
    }

    @PostMapping("/scanSubmitByPack")
    @ApiOperation(value = "成品包装扫码提交")
    public Result scanSubmitByPack(@RequestBody ScanSubmitByPackDTO dto) {
        if (dto == null || StrUtil.isEmpty(dto.getStartLocationCode()) || StrUtil.isEmpty(dto.getCreateBy()) || CollUtil.isEmpty(dto.getAgvTaskDetailList())) {
            return Result.fail().message("请求参数不能为空");
        }
        List<AgvTaskDetail> agvTaskDetailList = dto.getAgvTaskDetailList();
        AgvTaskInfo agvTaskInfo = new AgvTaskInfo();
        agvTaskInfo.setCreateBy(dto.getCreateBy());
        agvTaskInfo.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        agvTaskInfo.setTaskStatus(0);
        agvTaskInfo.setTaskType("ABL2");
        agvTaskInfo.setStartLocationCode(dto.getStartLocationCode());
        agvTaskInfo.setStartLocationType(dto.getStartLocationType());
        agvTaskInfoService.save(agvTaskInfo);
        for (AgvTaskDetail detail : agvTaskDetailList) {
            detail.setAgvTaskId(agvTaskInfo.getId());
            detail.setStatus(1);
        }
        agvTaskDetailService.updateBatchById(agvTaskDetailList);
        return Result.ok().message("提交成功");
    }

    @PostMapping("/moList")
    @ApiOperation("查询排产的工单列表")
    public Result moList(@RequestBody QueryMoList dto) {
        List<PlanMoInfo> list = moMapper.getMoListByMocty(dto.getPlanDate(), dto.getProductionLine());
        return Result.ok(list);
    }

    @PostMapping("/scanSubmit/{createBy}")
    @ApiOperation(value = "备料扫码后提交接口")
    public Result scanSubmit(@RequestBody List<AgvTaskDetail> dto, @PathVariable("createBy") String createBy) {
        if (CollUtil.isEmpty(dto)) {
            return Result.fail().message("请求参数不能为空");
        }
        ArrayList<AgvTaskDetail> agvTaskDetails = new ArrayList<>(dto.size());
        for (AgvTaskDetail detail : dto) {
            AgvTaskDetail agvTaskDetail = new AgvTaskDetail();
            agvTaskDetail.setBarcode(IdUtil.simpleUUID());
            agvTaskDetail.setDocNo(detail.getDocNo());
            agvTaskDetail.setItemCode(detail.getItemCode());
            agvTaskDetail.setItemName(detail.getItemName());
            agvTaskDetail.setItemSpec(detail.getItemSpec());
            agvTaskDetail.setQty(detail.getQty());
            agvTaskDetail.setCreatedBy(createBy);
            agvTaskDetail.setType(0);
            agvTaskDetailService.save(agvTaskDetail);
            agvTaskDetails.add(agvTaskDetail);
        }
        AgvTaskDetail agvTaskDetail = agvTaskDetails.get(0);
        AgvWarehouse agvWarehouse = moMapper.queryAgvTerminusByMo(agvTaskDetail.getBarcode(), agvTaskDetail.getDocNo());
        if (agvWarehouse == null) {
            return Result.fail().message("未找到AGV仓库信息！");
        }
        AgvTaskInfo agvTaskInfo = new AgvTaskInfo();
        agvTaskInfo.setCreateBy(createBy);
        agvTaskInfo.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        agvTaskInfo.setTaskStatus(0);
        agvTaskInfo.setTaskType("ABL1");
        agvTaskInfo.setEndLocationCode(agvWarehouse.getStationCode());
        agvTaskInfo.setEndLocationType("00");
        agvTaskInfo.setEndSiteName(agvWarehouse.getSiteName());
        agvTaskInfo.setDocNo(agvTaskDetail.getDocNo());
        agvTaskInfoService.save(agvTaskInfo);
        for (AgvTaskDetail detail : agvTaskDetails) {
            detail.setAgvTaskId(agvTaskInfo.getId());
            detail.setStatus(1);
        }
        agvTaskDetailService.updateBatchById(agvTaskDetails);
        return Result.ok(agvTaskDetails).message("提交成功！");
    }

    @PostMapping("/updateAgvTaskInfo")
    @ApiOperation(value = "更新AGV任务")
    public Result updateAgvTaskInfo(@RequestBody AgvTaskInfo dto) {
        agvTaskInfoService.updateById(dto);
        return Result.ok().message("更新成功！");
    }

    @PostMapping("/scanGetAgvTaskInfo")
    @ApiOperation(value = "扫码获取AGV任务")
    public Result scanGetAgvTaskInfo(@RequestBody String barcode) {
        if (StrUtil.isEmpty(barcode)) {
            return Result.fail().message("请求参数不能为空");
        }
        AgvTaskInfo agvTaskInfo = agvTaskInfoMapper.getAgvTaskInfoByBarcode(barcode);
        if (agvTaskInfo == null) {
            return Result.fail().message("未找到AGV任务信息！");
        }
        return Result.ok(agvTaskInfo);
    }

    @PostMapping("/scanList/{createBy}")
    @ApiOperation(value = "已扫列表")
    public Result scanList(@PathVariable("createBy") String createBy) {
        if (StrUtil.isEmpty(createBy)) {
            return Result.fail().message("请求参数不能为空");
        }
        List<MaterialBarCodeDetailVo> list = agvTaskDetailMapper.getAgvTaskDetailListByCreatedBy(createBy);
        return Result.ok(list);
    }

    @PostMapping("/delScan/{id}")
    @ApiOperation(value = "删除标签")
    public Result delScan(@PathVariable("id") String id) {
        boolean result = agvTaskDetailService.removeById(id);
        if (result) {
            return Result.ok().message("删除成功！");
        }
        return Result.fail().message("删除失败！");
    }

    @PostMapping("/submit")
    @ApiOperation(value = "提交AGV任务")
    public Result submit(@RequestBody SubmitAgvTaskDTO dto) {
        log.info("提交AGV任务: {}", JSONUtil.toJsonStr(dto));

        if (dto == null) {
            return Result.fail().message("请求参数不能为空");
        }
        if (CollUtil.isEmpty(dto.getPositionCodePath())) {
            return Result.fail().message("位置信息不能为空");
        }

        if (CollUtil.isEmpty(dto.getBarCodeList()) || dto.getBarCodeList().isEmpty()) {
            return Result.fail().message("条码列表不能为空");
        }

        if (StrUtil.isEmpty(dto.getCreateBy())) {
            return Result.fail().message("创建人不能为空");
        }

        if (dto.getPositionCodePath().size() < 2) {
            return Result.fail().message("位置至少需要包含起点和终点");
        }

        SubmitAgvTaskDTO.PositionCodePath startNode = dto.getPositionCodePath().get(0);
        SubmitAgvTaskDTO.PositionCodePath endNode = dto.getPositionCodePath().get(1);

        if (startNode == null || endNode == null) {
            return Result.fail().message("起点或终点不能为空");
        }

        if (StrUtil.isBlank(startNode.getPositionCode())) {
            return Result.fail().message("起点位置不能为空");
        }
        if (StrUtil.isBlank(endNode.getPositionCode())) {
            return Result.fail().message("终点位置不能为空");
        }

        if (StrUtil.isBlank(startNode.getType())) {
            return Result.fail().message("起点类型不能为空");
        }
        if (StrUtil.isBlank(endNode.getType())) {
            return Result.fail().message("终点类型不能为空");
        }

        if (startNode.getPositionCode().equals(endNode.getPositionCode())) {
            return Result.fail().message("起点和终点不能相同");
        }
        List<String> barCodeList = dto.getBarCodeList();
        dto.setBarCodeList(null);
        String createBy = dto.getCreateBy();
        dto.setCreateBy(null);
        String reqTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String reqCode = IdUtil.simpleUUID();
        dto.setReqCode(reqCode);
        dto.setReqTime(reqTime);
//        String bindCtnrAndBinUrl = BASE_URL + BIND_CTNR_BIN;
//        BindCtnrAndBinRequest bindCtnrAndBinRequest = new BindCtnrAndBinRequest();
//        bindCtnrAndBinRequest.setReqTime(reqTime);
//        bindCtnrAndBinRequest.setReqCode(reqCode);
//        bindCtnrAndBinRequest.setCtnrTyp("1");
//        bindCtnrAndBinRequest.setStgBinCode(startNode.getPositionCode());
//        bindCtnrAndBinRequest.setIndBind("1");
//        String bindCtnrAndBinRequestBody = JSONUtil.toJsonStr(bindCtnrAndBinRequest);
//        HttpResponse response = HttpRequest.post(bindCtnrAndBinUrl)
//                .timeout(TIMEOUT)
//                .header("Content-Type", "application/json")
//                .body(bindCtnrAndBinRequestBody)
//                .execute();
//        if (!response.isOk()) {
//            return Result.fail().message("AGV绑定接口调用失败，HTTP状态码：" + response.getStatus());
//        }
        dto.setPriority("1");
        String startLocationCode = dto.getPositionCodePath().get(0).getPositionCode();
        String endLocationCode = dto.getPositionCodePath().get(1).getPositionCode();
        String startLocationType = dto.getPositionCodePath().get(0).getType();
        String endLocationType = dto.getPositionCodePath().get(1).getType();
        String url = BASE_URL + GEN_TASK;
        String requestBody = JSONUtil.toJsonStr(dto);
        HttpResponse response = HttpRequest.post(url)
                .timeout(TIMEOUT)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .execute();

        if (!response.isOk()) {
            return Result.fail().message("AGV接口调用失败，HTTP状态码：" + response.getStatus());
        }
        log.info("生成AGV调度任务请求: {}", JSONUtil.toJsonStr(dto));
        String body = response.body();
        log.info("生成AGV调度任务响应: {}", body);
        JSONObject jsonObject = JSONUtil.parseObj(body);
        int code = jsonObject.getInt("code");
        if (code != 0) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("errCode", code);
            return Result.fail(map).message(jsonObject.getStr("message"));
        }
        String taskCode = jsonObject.getStr("data");
        AgvTaskInfo agvTaskInfo = new AgvTaskInfo();
        agvTaskInfo.setTaskCode(taskCode);
        agvTaskInfo.setReqCode(reqCode);
        agvTaskInfo.setReqParam(requestBody);
        agvTaskInfo.setRespParam(JSONUtil.toJsonStr(body));
        agvTaskInfo.setCreateTime(reqTime);
        agvTaskInfo.setTaskType(dto.getTaskTyp());
        agvTaskInfo.setTaskStatus(1);
        agvTaskInfo.setStartLocationCode(startLocationCode);
        agvTaskInfo.setEndLocationCode(endLocationCode);
        agvTaskInfo.setStartLocationType(startLocationType);
        agvTaskInfo.setEndLocationType(endLocationType);
        agvTaskInfo.setCreateBy(createBy);
        boolean saved = agvTaskInfoService.save(agvTaskInfo);
        if (!saved) {
            return Result.ok().message("新增任务保存中间表失败！");
        }
        LambdaUpdateWrapper<AgvTaskDetail> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(AgvTaskDetail::getId, barCodeList);
        updateWrapper.set(AgvTaskDetail::getTaskCode, taskCode);
        updateWrapper.set(AgvTaskDetail::getReqCode, reqCode);
        updateWrapper.set(AgvTaskDetail::getStatus, 1);
        boolean updated = agvTaskDetailService.update(updateWrapper);
        if (!updated) {
            return Result.ok().message("更新任务详情表失败！");
        }
//        String taskCode = "1234567890";
        return Result.ok(taskCode).message("提交成功！任务编号：" + taskCode);
    }

    @PostMapping("/agvWarehouse/{pageNum}/{pageSize}")
    @ApiOperation(value = "分页查询AGV仓库信息")
    public Result<?> list(@PathVariable("pageNum") Integer pageNum,
                          @PathVariable("pageSize") Integer pageSize,
                          @RequestBody(required = false) AgvWarehouse agvWarehouse) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }

        LambdaQueryWrapper<AgvWarehouse> wrapper = new LambdaQueryWrapper<>();

        if (agvWarehouse != null) {
            if (!StrUtil.isEmpty(agvWarehouse.getPositionCode())) {
                wrapper.like(AgvWarehouse::getPositionCode, agvWarehouse.getPositionCode());
            }
            if (!StrUtil.isEmpty(agvWarehouse.getShelfCode())) {
                wrapper.like(AgvWarehouse::getShelfCode, agvWarehouse.getShelfCode());
            }
            if (!StrUtil.isEmpty(agvWarehouse.getStationCode())) {
                wrapper.like(AgvWarehouse::getStationCode, agvWarehouse.getStationCode());
            }
            if (!StrUtil.isEmpty(agvWarehouse.getSiteName())) {
                wrapper.like(AgvWarehouse::getSiteName, agvWarehouse.getSiteName());
            }
            if (agvWarehouse.getStatus() != null) {
                wrapper.eq(AgvWarehouse::getStatus, agvWarehouse.getStatus());
            }
            if (!StrUtil.isEmpty(agvWarehouse.getType())) {
                wrapper.eq(AgvWarehouse::getType, agvWarehouse.getType());
            }
            if (!StrUtil.isEmpty(agvWarehouse.getParentId())) {
                wrapper.eq(AgvWarehouse::getParentId, agvWarehouse.getParentId());
            }
        }
        PageHelper.startPage(pageNum, pageSize);

        List<AgvWarehouse> list = agvWarehouseService.list(wrapper);

        PageInfo<AgvWarehouse> pageInfo = new PageInfo<>(list);

        return Result.ok(pageInfo);
    }

    @PostMapping("/taskList/{pageNum}/{pageSize}")
    @ApiOperation("AGV调度任务列表")
    public Result<?> taskList(@PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize,
                              @RequestBody QueryAgvTaskInfoDTO dto) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }

        LambdaQueryWrapper<AgvTaskInfo> wrapper = new LambdaQueryWrapper<>();

        if (dto != null) {
            // 任务编码（模糊）
            if (StrUtil.isNotBlank(dto.getTaskCode())) {
                wrapper.like(AgvTaskInfo::getTaskCode, dto.getTaskCode());
            }

            // 任务状态
            if (dto.getTaskStatus() != null) {
                wrapper.eq(AgvTaskInfo::getTaskStatus, dto.getTaskStatus());
            }

            if (StrUtil.isNotBlank(dto.getCreateBy())) {
                wrapper.eq(AgvTaskInfo::getCreateBy, dto.getCreateBy());
            }

            // 创建时间区间
            if (StrUtil.isNotBlank(dto.getStartTime())) {
                wrapper.ge(AgvTaskInfo::getCreateTime, dto.getStartTime());
            }
            if (StrUtil.isNotBlank(dto.getEndTime())) {
                wrapper.le(AgvTaskInfo::getCreateTime, dto.getEndTime());
            }
        }
        wrapper.orderByDesc(AgvTaskInfo::getCreateTime);
        PageHelper.startPage(pageNum, pageSize);
        List<AgvTaskInfo> records = agvTaskInfoService.list(wrapper);
        PageInfo<AgvTaskInfo> pageInfo = new PageInfo<>(records);
        if (CollUtil.isNotEmpty(records)) {

            List<AgvWarehouse> warehouseList = agvWarehouseService.list();
            Map<String, String> stationNameMap = new HashMap<>();
            Map<String, String> positionNameMap = new HashMap<>();

            for (AgvWarehouse w : warehouseList) {
                if (StrUtil.isNotBlank(w.getStationCode())) {
                    stationNameMap.put(w.getStationCode(), w.getSiteName());
                }
                if (StrUtil.isNotBlank(w.getPositionCode())) {
                    positionNameMap.put(w.getPositionCode(), w.getSiteName());
                }
            }
            for (AgvTaskInfo task : records) {
                List<AgvTaskDetail> list = agvTaskDetailMapper.getAgvTaskDetailList(task.getTaskCode());
                task.setTaskDetail(list);
                // 起点名称
                if ("00".equals(task.getStartLocationType())) {
                    task.setStartSiteName(
                            stationNameMap.get(task.getStartLocationCode())
                    );
                } else if ("05".equals(task.getStartLocationType())) {
                    task.setStartSiteName(
                            positionNameMap.get(task.getStartLocationCode())
                    );
                }

                // 终点名称
                if ("00".equals(task.getEndLocationType())) {
                    task.setEndSiteName(
                            stationNameMap.get(task.getEndLocationCode())
                    );
                } else if ("05".equals(task.getEndLocationType())) {
                    task.setEndSiteName(
                            positionNameMap.get(task.getEndLocationCode())
                    );
                }
            }
        }
        return Result.ok(pageInfo);
    }

    @PostMapping("/task/cancel/{taskCode}")
    @ApiOperation("取消AGV调度任务")
    public Result<?> cancelTask(@PathVariable("taskCode") String taskCode) {
        String reqTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String reqCode = IdUtil.simpleUUID();
        HashMap<String, String> dto = new HashMap<>();
        dto.put("taskCode", taskCode);
        dto.put("reqTime", reqTime);
        dto.put("reqCode", reqCode);
        String url = BASE_URL + CANCEL_TASK;
        String requestBody = JSONUtil.toJsonStr(dto);
        HttpResponse response = HttpRequest.post(url)
                .timeout(TIMEOUT)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .execute();

        if (!response.isOk()) {
            return Result.fail().message("AGV接口调用失败，HTTP状态码：" + response.getStatus());
        }
        log.info("取消AGV调度任务请求: {}", JSONUtil.toJsonStr(dto));
        String body = response.body();
        log.info("取消AGV调度任务响应: {}", body);
        JSONObject jsonObject = JSONUtil.parseObj(body);
        int code = jsonObject.getInt("code");
        if (code != 0) {
            return Result.fail().message(jsonObject.getStr("message"));
        }
        String data = jsonObject.getStr("data");
        agvTaskInfoService.update(new AgvTaskInfo(), new LambdaUpdateWrapper<AgvTaskInfo>()
                .eq(AgvTaskInfo::getTaskCode, taskCode)
                .set(AgvTaskInfo::getTaskStatus, 5));
        return Result.ok(data);
    }

    /**
     * 容器绑定 / 解绑库位
     * indBind: 1=绑定 0=解绑
     */
    @PostMapping("/container/bind-bin")
    @ApiOperation("容器绑定/解绑库位")
    public Result<?> bindCtnrAndBin(@RequestBody BindCtnrAndBinRequest dto) {
        if (dto == null) {
            return Result.fail().message("参数不能为空");
        }
        if (dto.getStgBinCode() == null) {
            return Result.fail().message("库位编号不能为空");
        }
        if (dto.getCtnrTyp() == null) {
            return Result.fail().message("容器类型不能为空");
        }
        if (dto.getIndBind() == null) {
            return Result.fail().message("绑定标志不能为空");
        }
        String reqTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String reqCode = IdUtil.simpleUUID();
        dto.setReqCode(reqCode);
        dto.setReqTime(reqTime);
        String url = BASE_URL + BIND_CTNR_BIN;
        String requestBody = JSONUtil.toJsonStr(dto);
        HttpResponse response = HttpRequest.post(url)
                .timeout(TIMEOUT)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .execute();

        if (!response.isOk()) {
            return Result.fail().message("AGV接口调用失败，HTTP状态码：" + response.getStatus());
        }
        log.info("容器绑定/解绑请求: {}", JSONUtil.toJsonStr(dto));
        String body = response.body();
        log.info("容器绑定/解绑响应: {}", body);
        JSONObject jsonObject = JSONUtil.parseObj(body);
        int code = jsonObject.getInt("code");
        if (code != 0) {
            return Result.fail().message(jsonObject.getStr("message"));
        }
        LambdaUpdateWrapper<AgvWarehouse> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(AgvWarehouse::getPositionCode, dto.getStgBinCode());
        if ("1".equals(dto.getIndBind())) {
            updateWrapper.set(AgvWarehouse::getStatus, 1);
        }
        if ("0".equals(dto.getIndBind())) {
            updateWrapper.set(AgvWarehouse::getStatus, 0);
        }
        agvWarehouseService.update(updateWrapper);
        return Result.ok().message("1".equals(dto.getIndBind()) ? "绑定成功" : "解绑成功");
    }
}
