package com.mgkj.dto.agv;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlanMoInfo {
    @ApiModelProperty(value = "工单号")
    private String docNo;
    @ApiModelProperty(value = "生产序号")
    private Double planNo;
    @ApiModelProperty(value = "生产线")
    private String productionLine;
    @ApiModelProperty(value = "客户单号")
    private String customerDocNo;
    @ApiModelProperty(value = "需求数量")
    private BigDecimal requiredQty;
    @ApiModelProperty(value = "已备料数量")
    private BigDecimal preparedQty;
    @ApiModelProperty(value = "工艺名称")
    private String operationName;
    @ApiModelProperty(value = "工艺编号")
    private String operationCode;
    @ApiModelProperty(value = "品号")
    private String itemCode;
    @ApiModelProperty(value = "品名")
    private String itemName;
    @ApiModelProperty(value = "规格")
    private String itemSpec;
}
