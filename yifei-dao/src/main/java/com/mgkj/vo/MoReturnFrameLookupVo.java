package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "车架号反查工单Vo")
public class MoReturnFrameLookupVo {
    @ApiModelProperty("工单单号")
    private String docNo;

    @ApiModelProperty("工单料号")
    private String itemCode;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("工作中心编码")
    private String workCenterCode;

    @ApiModelProperty("工作中心名称")
    private String workCenterName;

    @ApiModelProperty("工单类型(装配/包装)")
    private String moTypeName;
}
