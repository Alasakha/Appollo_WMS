package com.mgkj.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "工单报工 Vo")
public class SfcteVo {

    @ApiModelProperty("报工单别")
    @JsonProperty("te001")
    private String TE001;

    @ApiModelProperty("报工单号")
    @JsonProperty("te002")
    private String TE002;

    @ApiModelProperty("报工序号")
    @JsonProperty("te003")
    private String TE003;

    @ApiModelProperty("报工员工编号")
    @JsonProperty("te004")
    private String TE004;

    @ApiModelProperty("品号")
    private String itemNo;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("工序编号")
    private String gxNo;

    @ApiModelProperty("工序名称")
    private String gxName;

    @ApiModelProperty("合格数量")
    @JsonProperty("te011")
    private BigDecimal TE011;

    @ApiModelProperty("不良数量")
    @JsonProperty("udf53")
    private BigDecimal UDF53;

    @ApiModelProperty("工废数量")
    @JsonProperty("udf51")
    private BigDecimal UDF51;

    @ApiModelProperty("废料数量")
    @JsonProperty("udf52")
    private BigDecimal UDF52;

    @ApiModelProperty("不良原因")
    @JsonProperty("udf02")
    private String UDF02;

    @ApiModelProperty("工废原因")
    @JsonProperty("udf03")
    private String UDF03;

    @ApiModelProperty("废料原因")
    @JsonProperty("udf04")
    private String UDF04;

    @ApiModelProperty("备注")
    @JsonProperty("te015")
    private String TE015;

    @ApiModelProperty("报工日期")
    @JsonProperty("create_DATE")
    private String CREATE_DATE;
}
