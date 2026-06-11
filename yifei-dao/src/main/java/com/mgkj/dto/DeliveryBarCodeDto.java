package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "配送母条码")
public class DeliveryBarCodeDto {
    @ApiModelProperty(value = "条码")
    private String barcode;
    @ApiModelProperty(value = "品号")
    private String itemCode;
    @ApiModelProperty(value = "品名")
    private String itemName;
    @ApiModelProperty(value = "规格")
    private String itemSpec;
    @ApiModelProperty(value = "数量")
    private Double qty;
    @ApiModelProperty(value = "单箱数量")
    private Double boxQty;
    @ApiModelProperty(value = "库位")
    private String binCode;
    @ApiModelProperty(value="单位编号")
    private String unitNo;
    @ApiModelProperty(value="单位名称")
    private String unitName;
    @ApiModelProperty(value="仓库编码")
    private String warehouseCode;
    @ApiModelProperty(value="仓库名称")
    private String warehouseName;
    @ApiModelProperty(value="备注")
    private String remark;
//    @ApiModelProperty(value = "操作业务(1 领料)")
//    private Integer chagType;
//    @ApiModelProperty(value = "提交状态(0 未提交)")
//    private Integer statusCode;
    @ApiModelProperty(value = "收货机构")
    private Integer shjg;
    @ApiModelProperty(value = "批号")
    private String lotCode;
//    @ApiModelProperty(value = "调出收货机构")
//    private String fromShjg;
//    @ApiModelProperty(value = "调入收货机构")
//    private String toShjg;
    @ApiModelProperty(value = "客户单号")
    private String customerNo="";
    @ApiModelProperty(value = "工单号")
    private String sourceNo="";
}
