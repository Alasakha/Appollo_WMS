package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "调拨单明细")
public class TransferDocDetail {
    @ApiModelProperty(value = "调拨单单号")
    private String docNo;
    @ApiModelProperty(value = "调拨单单头uuid")
    private String docId;
    @ApiModelProperty(value = "调拨单单身uuid")
    private String docDId;
    @ApiModelProperty(value = "品号")
    private String itemCode;
    @ApiModelProperty(value = "品名")
    private String itemName;
    @ApiModelProperty(value = "规格")
    private String itemSpec;
    @ApiModelProperty(value = "预计发货数")
    private String planQty;
    @ApiModelProperty(value = "需调拨数量")
    private String transferQty;
    @ApiModelProperty(value = "未调拨数量")
    private String untransferQty;
    @ApiModelProperty(value = "仓库编码")
    private String fromWarehouseCode;
    @ApiModelProperty(value = "仓库编码")
    private String toWarehouseCode;
    @ApiModelProperty(value="调出库位编码")
    private String fromBinCode;
    @ApiModelProperty(value="调入库位编码",required = true)
    private String toBinCode;
    @ApiModelProperty(value = "库位")
    private String binCode;
    @ApiModelProperty(value = "库存单位名称")
    private String unitName;
    @ApiModelProperty(value = "库存单位Code")
    private String unitCode;
    @ApiModelProperty(value = "客户单号")
    private String customerNo;
    @ApiModelProperty(value = "调出收货机构")
    private String fromShjg;
    @ApiModelProperty(value = "调入收货机构")
    private String toShjg;
    @ApiModelProperty(value = "条码")
    private String barCode;
    @ApiModelProperty(value = "本次扫码量")
    private Double currentNum;
    @ApiModelProperty(value = "单身序号")
    private Double xh;
}
