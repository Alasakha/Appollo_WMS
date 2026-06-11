package com.mgkj.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalesSummary {
    private String id;
    private String itemCode;
    private String itemName;
    private String itemSpec;
    private BigDecimal businessQty;
    private BigDecimal matchQty;
    private String createBy;
    private String createTime;
    private String barCode;
    private String docNo;
    private String unitNo;
    private String binCode;
    private String warehouseCode;
    private String customerNo;
    private String shjg;
    private BigDecimal currentNum;
}
