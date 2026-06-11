package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yyyjcg
 * @date 2023/12/26
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "采购汇总Dto")
public class PurchaseSumDto {
    private String docNo;
    private String createDate;
    private String supplierNo;
    private String supplierName;
    private String itemNo;
    private String productCode;
    private String stockCode;
    private String itemName;
    private String itemSpec;
    private String unitNo;
    private Integer stockQty;
    private double applyQty;
    private Integer scanSumQty;
    private String customerNo;
    private String customerName;
    private String warehouseNo;
    private String storageSpacesNo;
    private String lotNo;
    private String unitName;
    private String referenceUnitNo;
    private String referenceUnitName;
    private double referenceQty;
    private String valuationUnitNo;
    private String valuationUnitName;
    private double valuationQty;
    private String stockUnitNo;
    private String stockUnitName;
    private String stockKeep;
    private double sourceDocQty;
    private double sourceValuationQty;
    private String rate;
    private String keep;

}
