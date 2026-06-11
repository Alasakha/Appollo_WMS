package com.mgkj.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoProductInfoDto {
    private String docNo;
    private String itemCode;
    private Integer productType;
    private String unitCode;
    private BigDecimal planQty;
    private String warehouseCode;
}
