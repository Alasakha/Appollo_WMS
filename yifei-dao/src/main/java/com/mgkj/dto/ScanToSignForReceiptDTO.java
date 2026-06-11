package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ScanToSignForReceiptDTO {
    @ApiModelProperty(value = "条码")
    private String barcode;
    @ApiModelProperty(value = "品号")
    private String itemCode;
    @ApiModelProperty(value = "id列表")
    private List<String> issueReceiptIdList;
}
