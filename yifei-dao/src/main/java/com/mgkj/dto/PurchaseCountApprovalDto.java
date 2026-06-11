package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseCountApprovalDto {

    @ApiModelProperty(value = "条码")
    private String barcode;

    @ApiModelProperty(value = "原有数量")
    private BigDecimal originalQty = BigDecimal.ZERO;

    @ApiModelProperty(value = "修改数量")
    private BigDecimal changeqty = BigDecimal.ZERO;

    @ApiModelProperty(value = "创建人")
    private String createBy;
}
