package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "发货单明细")
public class DeliveryDetail {
    @ApiModelProperty(value = "销货单号")
    private String docNo;
    @ApiModelProperty(value = "销售单号")
    private String salesNo;
    @ApiModelProperty(value = "品号")
    private String itemCode;
    @ApiModelProperty(value = "品名")
    private String itemName;
    @ApiModelProperty(value = "规格")
    private String itemSpec;
    @ApiModelProperty(value = "预计发货数")
    private String planQty;
    @ApiModelProperty(value = "已发货数")
    private String deliveryQty;
    @ApiModelProperty(value = "未发货数")
    private String unDeliveryQty;
    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;
    @ApiModelProperty(value = "库位")
    private String binCode;
    @ApiModelProperty(value = "库存单位名称")
    private String unitName;
    @ApiModelProperty(value = "库存单位Code")
    private String unitCode;
    @ApiModelProperty(value = "客户单号")
    private String customerNo;
    @ApiModelProperty(value = "收货机构")
    private String shjg;
    @ApiModelProperty(value = "条码")
    private String barCode;
    @ApiModelProperty(value = "本次扫码量")
    private Double currentNum;
    @ApiModelProperty(value = "实时已扫量（多人聚合）")
    private String scannedQty;
    @ApiModelProperty(value = "实时剩余量")
    private String remainingQty;
}
