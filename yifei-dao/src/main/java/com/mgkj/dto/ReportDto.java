package com.mgkj.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api(tags = "报工Dto")
@Data
public class ReportDto {

    @ApiModelProperty(value = "派工单别",required = true)
    private String te001;

    @ApiModelProperty(value = "派工单号",required = true)
    private String te002;

    @ApiModelProperty(value = "派工序号",required = true)
    private String te003;

    @ApiModelProperty(value = "报工数量(合格数量)",required = true)
    private Double te011;

    //设置工废数量
    @ApiModelProperty("工废数量")
    private Double udf51;

    //设置料废数量
    @ApiModelProperty("料废数量")
    private Double udf52;

    //设置不良数量
    @ApiModelProperty("不良数量")
    private Double udf53;

    @ApiModelProperty("工艺编号")
    private String te009;

    @ApiModelProperty("员工编号")
    private String te004;

    @ApiModelProperty("员工名称")
    private String udf07;
}
