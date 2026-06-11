package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2023/12/26
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "采购单明细Dto")
public class PurchaseDetailDto {
    @ApiModelProperty(value = "采购单号")
    private String docNo;
    @ApiModelProperty(value = "创建时间")
    private String createDate;
    @ApiModelProperty(value = "供应商编号")
    private String supplierNo;
    @ApiModelProperty(value = "供应商名称")
    private String supplierName;
    @ApiModelProperty(value = "品号")
    private String itemNo;
    @ApiModelProperty(value = "产品编号")
    private String productCode;

    private String stockCode;
    @ApiModelProperty(value = "品名")
    private String itemName;
    @ApiModelProperty(value = "规格")
    private String itemSpec;
    @ApiModelProperty(value = "交易单位编号")
    private String unitNo;
    @ApiModelProperty(value = "库存数量")
    private Integer stockQty;
    @ApiModelProperty(value = "申请量")
    private double applyQty;
    private Integer scanSumQty;
    private String customerNo;
    private String customerName;
    private String warehouseNo;
    private String storageSpacesNo;
    private String lotNo;
    @ApiModelProperty(value = "交易单位名称")
    private String unitName;
    private String referenceUnitNo;
    private String referenceUnitName;
    private double referenceQty;
    private String valuationUnitNo;
    @ApiModelProperty(value = "计价单位名称")
    private String valuationUnitName;
    @ApiModelProperty(value = "计价单位数量")
    private double valuationQty;
    private String stockUnitNo;
    private String stockUnitName;
    private String stockKeep;
    private double sourceDocQty;
    private double sourceValuationQty;

}
