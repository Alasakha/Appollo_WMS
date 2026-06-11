package com.mgkj.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseInfo {
    private BigDecimal businessQty; // 采购单业务数量
    private String vatDeductible; // VAT抵扣标志
    private String employeeCode;    // 员工编号
    private String adminUnitCode;   // 关联部门.行政单元编码
    private String startDate;     // 关联部门.生效日期
    private String moDoc;  // 工单号
    private String moDocNum;   // 工单序号
    private String docNo;               // 采购单号
    private String purchaseOrderCode;   // 采购单类型编号
    private String arrivalOrderCode;    // 到货单类型编号
    private String arrivalOrderCategory; // 到货单性质
    private String businessUnitCode; // 业务单位编号
    private String priceUnitCode; // 计价单位编号
    private Integer sequenceNumberD;    // D采购单身序号
    private Integer sequenceNumberSD;   // SD采购单身序号（与上一列同名，示例中拆成两个字段）
    private String supplierCode;        // 供应商编号
    private String supplierFullName;    // 供应商全称
    private BigDecimal taxBc;           // 本币税额
    private String taxIncluded;         // 含税（Y/N 或 0/1，可改成 Boolean）
    private BigDecimal taxOc;           // 原币税额
    private BigDecimal taxRate;         // 税率
    private String purchaseType;        // 采购类型
    private String currencyCode;        // 货币编号
    private BigDecimal standardPrice;   // 标准单价
    private String supplyCenterCode;    // 采购域编号
    private String plantCode;    // 工厂编号
    private String itemCode;
    private String itemName;
    private String itemType;
    private String itemSpec;
    private BigDecimal price; // 单价
    private String taxCode;//税种.税种编号
    private BigDecimal inventoryQty; // 库存数量
    private BigDecimal discountedPrice; //折扣价格
    private String paymentCode;// 付款条件.编号
    private String paymentType;//付款条件.类别
    private String inspectMode; // 质检模式
    private BigDecimal exchangeRate;
    private String taxInvoiceCategoryCode; // 发票编号
}
