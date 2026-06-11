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
 * @date 2024/5/30
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel("采购仓退详情Vo")
public class ListPurchaseReturnDetailVo {
    @ApiModelProperty("单号")
    private String docNo;

    @ApiModelProperty("日期")
    private String docDate;

    @ApiModelProperty("供应商")
    private String supplierName;

    @ApiModelProperty("品号")
    private String itemCode;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("单位")
    private String unitName;

    @ApiModelProperty("业务数量")
    private double applyQty;

    @ApiModelProperty("库存数量")
    private double stockQty;

    @ApiModelProperty("仓库编号")
    private String warehouseCode;

    @ApiModelProperty("库存编号")
    private String binCode;


}
