package com.mgkj.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoPickingSummary {
    private String id;
    private String docNo;
    private String itemCode;
    private BigDecimal businessQty;
    private BigDecimal matchQty;
    private BigDecimal currentNum;
    private String warehouseCode;
    private String warehouseName;
    private String binCode;
    private String unitCode;
    private String unitName;
    private String barcode;
    private String itemName;
    private String itemSpec;
    private String remark;
    private String createBy;
    private String createTime;
    private String moctyUuid;  // 排产单MOCTY UUID
    private Integer shjg;
}
