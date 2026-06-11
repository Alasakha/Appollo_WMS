package com.mgkj.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IssueReceiptDVo {
    private String issueReceiptId;
    private String lotCode;
    private String itemCode;
    private String itemName;
    private String itemSpec;
    private BigDecimal outboundQty; // 出库数量
    private BigDecimal signedForQty; // 签收数量
    private String status; // 签收状态 Y:已签收 N:未签收
}
