package com.mgkj.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 自动销货单出库齐套判断用的 E10 汇总数量。
 */
@Data
public class SalesOutboundDocSummary {

    /**
     * 销货单号。
     */
    private String docNo;

    /**
     * E10 销货单单身中，品号 1 开头的业务数量汇总。
     */
    private BigDecimal businessQty;

    /**
     * E10 销货单单身中，品号 1 开头的已出库数量汇总。
     */
    private BigDecimal issuedQty;
}
