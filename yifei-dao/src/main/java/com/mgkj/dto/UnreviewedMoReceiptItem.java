package com.mgkj.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UnreviewedMoReceiptItem {

    private String docNo;

    private String udf021;

    private String itemCode;

    private String itemName;

    private String itemSpecification;

    private BigDecimal acceptedQty;
}
