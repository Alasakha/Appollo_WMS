package com.mgkj.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoReceiptVo {
    @ApiModelProperty(value = "生产入库单号")
    private String docNo;
    @ApiModelProperty(value = "生产入库单ID")
    private String docNoId;
    @ApiModelProperty(value = "客户单号")
    private String khdh;
    @ApiModelProperty(value = "工单号")
    private String moDocNo;
    @ApiModelProperty(value = "品名")
    private String itemName;
    @ApiModelProperty(value = "品号")
    private String itemCode;
    @ApiModelProperty(value = "规格")
    private String itemSpec;
    @ApiModelProperty(value = "待签收数量-入库验收数量")
    private BigDecimal acceptedQty;
    @ApiModelProperty(value = "已签收数量")
    private BigDecimal signedForQty;
    @ApiModelProperty(value = "工单数量")
    private BigDecimal moQty;
}
