package com.mgkj.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoQtyInfo {
    private BigDecimal planQty;
    private BigDecimal completedQty;
}
