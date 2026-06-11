package com.mgkj.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class IssueReceiptDGroupVo {
    @ApiModelProperty(value = "id列表")
    private List<String> issueReceiptIdList; // id列表
    @ApiModelProperty(value = "批号")
    private String lotCode;
    @ApiModelProperty(value = "品号")
    private String itemCode;
    @ApiModelProperty(value = "品名")
    private String itemName;
    @ApiModelProperty(value = "规格")
    private String itemSpec;
    @ApiModelProperty(value = "出库数量")
    private BigDecimal outboundQty; // 出库数量
    @ApiModelProperty(value = "签收数量")
    private BigDecimal signedForQty; // 签收数量
    @ApiModelProperty(value = "签收状态 Y:已签收 N:未签收")
    private String status; // 签收状态 Y:已签收 N:未签收
}
