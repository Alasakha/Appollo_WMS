package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class signReceiptDTO {

    @ApiModelProperty(value = "工单号")
    private String moDocNo;

    @ApiModelProperty(value = "签收数量")
    private Double qty;
}
