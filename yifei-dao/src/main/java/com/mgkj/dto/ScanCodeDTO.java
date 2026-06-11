package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScanCodeDTO {
    private String barcode;
    private String docNo;
    private String createBy;
    private String id;
    private String itemCode;
    private BigDecimal businessQty;//业务需求数量
    private BigDecimal matchQty;//已经匹配数量
    private BigDecimal scanNum;//本次扫码数量
    private String unitName;
    private String unitCode;
    private String itemName;
    private String itemSpec;
    @ApiModelProperty("排产UUID")
    private String moctyUuid;
}
