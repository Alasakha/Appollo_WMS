package com.mgkj.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PurchaseReceiptStorageDtoOne {
    @ApiModelProperty("创建人")
    private String createBy;
    @ApiModelProperty("送货单号")
    private String deliveryNumber;
}
