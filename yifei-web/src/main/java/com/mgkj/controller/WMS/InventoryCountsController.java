package com.mgkj.controller.WMS;

import com.mgkj.annotation.Log;
import com.mgkj.common.result.Result;
import com.mgkj.dto.updateBarCodeDTO;
import com.mgkj.mapper.BmBarcodeDetailMapper;
import com.mgkj.mapper.DeliveryNoticeMapper;
import com.mgkj.vo.BarCodeDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "库存盘点相关接口")
@RequestMapping("/api/inventoryCounts")
public class InventoryCountsController {

    @Autowired
    private DeliveryNoticeMapper deliveryNoticeMapper;

    @Autowired
    private BmBarcodeDetailMapper bmBarcodeDetailMapper;

    @PostMapping("/queryBarCode")
    @ApiOperation(value = "查询条码信息")
    public Result queryBarCode(@RequestBody String barCode) {
        BarCodeDetailVo detail = deliveryNoticeMapper.getBarCodeDetailByBarCode(barCode);
        if (detail == null) {
            return Result.fail().message("条码不存在");
        }
        return Result.ok(detail);
    }

    @PostMapping("/update")
    @ApiOperation("更新条码数量")
    @Log("更新条码数量")
    public Result update(@RequestBody updateBarCodeDTO dto) {
        try {
            bmBarcodeDetailMapper.updateQty(dto.getBarcode(), dto.getQty());
        } catch (Exception e) {
            return Result.fail().message("盘点失败,请重试！");
        }
        return Result.ok().message("盘点成功");
    }
}
