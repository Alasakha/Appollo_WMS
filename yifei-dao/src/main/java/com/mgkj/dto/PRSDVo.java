package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PRSDVo {
    @ApiModelProperty(value = "客户编号")
    private String customerNum;
    @ApiModelProperty(value = "采购单号")
    private String purchaseNo;
    @ApiModelProperty(value = "品号")
    private String itemCode;
    @ApiModelProperty(value = "品名")
    private String itemName;
    @ApiModelProperty(value = "规格")
    private String itemSpec;
    @ApiModelProperty(value = "数量")
    private BigDecimal qty;
    @ApiModelProperty(value = "单位")
    private String unitName;
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;
}
