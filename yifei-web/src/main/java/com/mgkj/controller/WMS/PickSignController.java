package com.mgkj.controller.WMS;

import com.mgkj.annotation.Log;
import com.mgkj.common.result.Result;
import com.mgkj.dto.QueryIssueReceiptD;
import com.mgkj.dto.ScanToSignForReceiptDTO;
import com.mgkj.mapper.DeliveryNoticeMapper;
import com.mgkj.mapper.PickingMapper;
import com.mgkj.vo.BarCodeDetailVo;
import com.mgkj.vo.IssueReceiptDGroupVo;
import com.mgkj.vo.IssueReceiptDVo;
import com.mgkj.vo.IssueReceiptVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
@RestController
@Api(tags = "领料签收相关接口")
@RequestMapping("/api/PickSign")
public class PickSignController {

    @Resource
    private PickingMapper pickingMapper;

    @Autowired
    private DeliveryNoticeMapper deliveryNoticeMapper;

    @PostMapping("/getListHeader")
    @ApiOperation("获取领料签收列表")
    public Result<List<IssueReceiptVo>> getListHeader(@RequestBody String docNo) {
//        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<IssueReceiptVo> list = pickingMapper.queryIssueReceiptHeader(docNo);
        return Result.ok(list);
    }

    @PostMapping("/getList")
    @ApiOperation("获取领料签收明细")
    public Result<?> getList(@RequestBody QueryIssueReceiptD dto) {
        List<IssueReceiptDVo> list = pickingMapper.queryIssueReceiptReqD(dto);
        Integer sign = pickingMapper.queryUnSignCount(dto);
        // 根据itemCode分组
        Map<String, IssueReceiptDGroupVo> groupMap = new HashMap<>();
        for (IssueReceiptDVo vo : list) {
            String itemCode = vo.getItemCode();
            IssueReceiptDGroupVo groupVo = groupMap.getOrDefault(itemCode, new IssueReceiptDGroupVo());
            groupVo.setLotCode(vo.getLotCode());
            groupVo.setItemCode(itemCode);
            groupVo.setItemName(vo.getItemName());
            groupVo.setItemSpec(vo.getItemSpec());
            groupVo.setOutboundQty(
                    groupVo.getOutboundQty() == null
                            ? vo.getOutboundQty()
                            : groupVo.getOutboundQty().add(vo.getOutboundQty())
            );
            groupVo.setSignedForQty(
                    groupVo.getSignedForQty() == null
                            ? vo.getSignedForQty()
                            : groupVo.getSignedForQty().add(vo.getSignedForQty())
            );
            groupVo.setStatus(vo.getStatus());
            // 初始化issueReceiptId列表
            if (groupVo.getIssueReceiptIdList() == null) {
                groupVo.setIssueReceiptIdList(new ArrayList<>());
            }
            groupVo.getIssueReceiptIdList().add(vo.getIssueReceiptId());
            groupMap.put(itemCode, groupVo);
        }

        // 将分组后的数据转换为List
        List<IssueReceiptDGroupVo> groupList = new ArrayList<>(groupMap.values());
        HashMap<String, Object> map = new HashMap<>();
        map.put("list", groupList);
        map.put("total", groupList.size());
        map.put("sign", sign);
        return Result.ok(map);
    }

    @PostMapping("/scanToSignForReceipt")
    @ApiOperation("扫码签收")
    @Log("扫码签收")
    public Result scanToSignForReceipt(@RequestBody ScanToSignForReceiptDTO dto) {
        BarCodeDetailVo barCodeDetail = deliveryNoticeMapper.getBarCodeDetailByBarCode(dto.getBarcode());
        if (barCodeDetail == null) {
            return Result.fail().message("条码不存在");
        }
        try {
            for (String id : dto.getIssueReceiptIdList()) {
                IssueReceiptDVo issueReceiptDVo = pickingMapper.queryIssueReceiptD(id);
                BigDecimal qty = issueReceiptDVo.getOutboundQty().compareTo(barCodeDetail.getQty()) >= 0
                        ? barCodeDetail.getQty()
                        : issueReceiptDVo.getOutboundQty();
                pickingMapper.updateIssueReceiptD(id, qty);
            }
        } catch (Exception e) {
            return Result.fail().message("签收失败" + e.getMessage());
        }
        return Result.ok().message("签收成功");
    }
}
