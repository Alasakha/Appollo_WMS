package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "发货通知单")
public class DeliveryNotice {
    @ApiModelProperty(value = "申请单号")
    private String docNo;
    @ApiModelProperty(value = "单号日期")
    private String docDate;
    @ApiModelProperty(value = "客户编号")
    private String customerCode;
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "客户单号")
    private String customerNo;
}
