package com.mgkj.controller.WMS;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.BmBarcodeDetail;
import com.mgkj.mapper.MoMapper;
import com.mgkj.service.BmBarcodeDetailService;
import com.mgkj.vo.MaterialBarCodeDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@Api(tags = "配送条码相关接口")
@RequestMapping("/api/DeliveryBarcode")
public class DeliveryBarcodeController {

    @Autowired
    private MoMapper moMapper;

    @Resource
    private BmBarcodeDetailService bmBarcodeDetailService;


    @PostMapping("/materialBarCodeDetail")
    @ApiOperation(value = "查询配料条码信息")
    public Result<MaterialBarCodeDetailVo> materialBarCodeDetail(@RequestBody String barCode) {
        MaterialBarCodeDetailVo barCodeDetail = moMapper.getMaterialBarCodeDetailByBarCode(barCode);
        return Result.ok(barCodeDetail);
    }



    @PostMapping("/printDeliveryBarcode")
    @ApiOperation(value = "打印配送标签")
    @Transactional
    public Result printDeliveryBarcode(@RequestBody DeliveryBarCodeDto barCodeDetail) {
        // 返回前端的子条码
        List<BmBarcodeDetail> barCodeDetailList = new ArrayList<>();

        // 根据前端传的数量和单箱数，生成需生成的子条码
        Double qty = barCodeDetail.getQty();
        Double boxQty = barCodeDetail.getBoxQty();


        if (qty == null || qty <= 0) {
            return Result.fail("数量必须大于0");
        }
        if (boxQty == null || boxQty <= 0) {
            return Result.fail("单箱数量必须大于0");
        }

        // 计算需要生成的箱子数量
        int boxCount = (int) Math.ceil(qty / boxQty);

        // 获取母条码
        String parentBarcode = barCodeDetail.getBarcode();

        for (int i = 0; i < boxCount; i++) {
            // 生成新条码 bm_barcode_detail
            BmBarcodeDetail bmBarcodeDetail = new BmBarcodeDetail();
            // 计算当前箱的数量
            Double currentBoxQty;
            if (i == boxCount - 1) {
                // 最后一箱：剩余数量
                currentBoxQty = qty - (boxQty * i);
            } else {
                // 前几箱：单箱数量
                currentBoxQty = boxQty;
            }

            // 生成子条码编号（母条码 + #001、#002...）
            String subBarcode = String.format("%s#%03d", parentBarcode, i + 1);
            bmBarcodeDetail.setId(UUID.randomUUID().toString());// id
            bmBarcodeDetail.setBarcode(subBarcode);// 标签号
            bmBarcodeDetail.setCombinationLotNo(barCodeDetail.getLotCode()); // 批号
            bmBarcodeDetail.setCustomerNo(barCodeDetail.getCustomerNo());// 客户单号
            bmBarcodeDetail.setItemNo(barCodeDetail.getItemCode()); // 品号
            bmBarcodeDetail.setItemName(barCodeDetail.getItemName()); // 品名
            bmBarcodeDetail.setItemSpec(barCodeDetail.getItemSpec()); // 规格
            bmBarcodeDetail.setQty(BigDecimal.valueOf(qty));// 数量为总数数量
            bmBarcodeDetail.setReferenceQty(BigDecimal.valueOf(currentBoxQty));// 数量为单箱数量，不够的就是扣除到最后的数量
            bmBarcodeDetail.setLotDate(DateUtil.date()); // 批号日期
            bmBarcodeDetail.setSnWarehouseNo(barCodeDetail.getWarehouseCode()); // 仓库编号
            bmBarcodeDetail.setSnCellNo(barCodeDetail.getBinCode()); // 库位编号
            bmBarcodeDetail.setUnitNo(barCodeDetail.getUnitNo()); // 单位编号
            bmBarcodeDetail.setUnitName(barCodeDetail.getUnitName()); // 单位名称
            bmBarcodeDetail.setSourceNo(barCodeDetail.getSourceNo()); // 工单号
            bmBarcodeDetail.setStandardCol02("1"); // 1:领料出库,2:销货出库
            bmBarcodeDetail.setRemark(barCodeDetail.getRemark()); // 备注
            bmBarcodeDetail.setCreateDate(DateTime.now()); // 创建时间
            bmBarcodeDetail.setUpdateDate(DateTime.now()); // 更新时间
            // bmBarcodeDetail.setCreateBy(barCodeDetail.getCreateBy()); // 创建人
            bmBarcodeDetail.setOrgNo(barCodeDetail.getShjg().toString()); // 收货机构
            bmBarcodeDetail.setLotAtt30("5");
            barCodeDetailList.add(bmBarcodeDetail);
        }

        bmBarcodeDetailService.saveBatch(barCodeDetailList);
        return Result.ok(barCodeDetailList);
    }




}