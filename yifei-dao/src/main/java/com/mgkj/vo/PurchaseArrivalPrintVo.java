package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/3/14
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "到货单详情Vo")
public class PurchaseArrivalPrintVo {
    @ApiModelProperty("品号")
    private String itemCode;

    @ApiModelProperty("品名")
    private String itemDescription;

    @ApiModelProperty("数量")
    private String applyQty;

    @ApiModelProperty("规格")
    private String itemSpecification;

    @ApiModelProperty("到货单号")
    private String docNo;

    @ApiModelProperty("条码")
    private String barcode;

    @ApiModelProperty("供应商")
    private String supplierName;

    @ApiModelProperty("日期")
    private String docDate;

    @ApiModelProperty("单位")
    private String unitCode;


}
