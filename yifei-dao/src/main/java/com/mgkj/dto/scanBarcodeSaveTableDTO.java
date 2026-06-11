package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class scanBarcodeSaveTableDTO {
    @ApiModelProperty(value = "销货单号")
    private String docNo;
    @ApiModelProperty(value = "条码")
    private String barCode;
    @ApiModelProperty(value = "本次扫码数量")
    private BigDecimal scanNum;
    @ApiModelProperty(value = "创建人")
    private String createBy;
}
