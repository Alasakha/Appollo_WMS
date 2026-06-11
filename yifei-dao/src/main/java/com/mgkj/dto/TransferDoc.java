package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "调拨单单")
public class TransferDoc {
    @ApiModelProperty(value = "调拨单头号")
    private String docNo;
    @ApiModelProperty(value = "调拨单头uuid")
    private String docId;
    @ApiModelProperty(value = "单号日期")
    private String docDate;
    @ApiModelProperty(value = "调出收货机构")
    private String fromShjg;
    @ApiModelProperty(value = "调入收货机构")
    private String toShjg;

}
