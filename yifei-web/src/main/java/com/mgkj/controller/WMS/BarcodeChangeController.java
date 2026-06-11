package com.mgkj.controller.WMS;

import cn.hutool.core.date.DateTime;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mgkj.common.result.Result;
import com.mgkj.dto.PurcharseDeliveryDto;
import com.mgkj.dto.PurchaseCountApprovalDto;
import com.mgkj.entity.BarcodeChangeRecord;
import com.mgkj.mapper.BarcodeChangeMapper;
import com.mgkj.mapper.BarcodeChangeRecordMapper;
import com.mgkj.service.BarcodeChangeRecordService;
import com.mgkj.service.PurchaseService;
import com.mgkj.vo.PurchaseReceiptStorageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/barcodeChange")
@Api(tags = "条码变更管理")
public class BarcodeChangeController {

    @Autowired
    private BarcodeChangeRecordService barcodeChangeRecordService;
    @Autowired
    private BarcodeChangeMapper barcodeChangeMapper;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private BarcodeChangeRecordMapper barcodeChangeRecordMapper;


    @ApiOperation("条码查询")
    @PostMapping("/query")
    public Result<List<PurchaseReceiptStorageVo>> query(@RequestBody PurcharseDeliveryDto dto) {
        List<PurchaseReceiptStorageVo> purcharsedeliveryNo = purchaseService.getPurcharseByBarcode(dto);
        return Result.ok(purcharsedeliveryNo);
    }

    @ApiOperation("条码数量更改")
    @PostMapping("/barcodeChange")
    @DSTransactional
    public Result barcodeChange(@RequestBody PurchaseCountApprovalDto dto){
        //记录条码更改记录档
        BarcodeChangeRecord record = new BarcodeChangeRecord();
        record.setUuid(UUID.randomUUID().toString());
        record.setBarcode(dto.getBarcode());
        record.setOriginalQuantity(dto.getOriginalQty());
        record.setChangedQuantity(dto.getChangeqty());
        record.setCreator(dto.getCreateBy());
        record.setCreateTime(DateTime.now());
        if(!barcodeChangeRecordService.save(record)){
            return Result.fail().message("条码更改记录档保存失败!");
        }
        //更改对应条码的数量
        if(barcodeChangeMapper.updateBarcodeNum(dto)<1){
            return Result.fail().message("修改条码数量失败!");
        }
        return Result.ok().message("修改成功!");
    }

    @ApiOperation("获取条码更改记录")
    @PostMapping("/getBarcodeChangeRecord")
    public Result<List<BarcodeChangeRecord>> getBarcodeChangeRecord(@RequestBody String createBy){
        // 构建查询条件：按操作人查询，按创建时间倒序排列
        QueryWrapper<BarcodeChangeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator", createBy)  // 匹配操作人
                .orderByDesc("createTime");  // 按时间倒序（最新的在前）
        return Result.ok(barcodeChangeRecordMapper.selectList(queryWrapper));
    }


}