package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ScanDTO {
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "品号")
    private String itemCode;
    @ApiModelProperty(value = "品名")
    private String itemName;
    @ApiModelProperty(value = "规格")
    private String itemSpec;
    @ApiModelProperty(value = "数量")
    private String qty;
    @ApiModelProperty(value = "工单号")
    private String docNo;
    @ApiModelProperty(value = "条码")
    private String barcode;
}
