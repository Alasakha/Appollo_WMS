package com.mgkj.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Api(value = "报工新增DTO")
public class ReportAddDto {

    @ApiModelProperty(value = "派工单别",required = true)
    private String te001;

    @ApiModelProperty(value = "派工单号",required = true)
    private String te002;

    @ApiModelProperty(value = "派工序号",required = true)
    private String te003;

    @ApiModelProperty("员工编号")
    private String te004;

    @ApiModelProperty(value = "报工数量(合格数量)",required = true)
    private BigDecimal te011;

    @ApiModelProperty(value = "金额")
    private BigDecimal te031;

    @ApiModelProperty("备注")
    private String te015;

    @ApiModelProperty("工艺名称")
    private BigDecimal UDF07;

    @ApiModelProperty("工废数量")
    private BigDecimal udf51;
}
