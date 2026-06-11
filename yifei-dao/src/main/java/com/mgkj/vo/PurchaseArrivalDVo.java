package com.mgkj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class PurchaseArrivalDVo {
    private String xh;
    private String customerNo;
    private String purchaseNo;
    private String itemCode;
    private String itemName;
    private String warehouseCode;
    private String warehouseName;
}
