package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/4/7
 * @Description
 */
@Data
@AllArgsConstructor
@EnableSwagger2
@ApiModel(value = "库存查询Vo")
public class ListStockVo {
    @ApiModelProperty("料号")
    private String itemCode;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("仓库编号")
    private String warehouseNo;

    @ApiModelProperty("仓库名称")
    private String warehouseName;

    @ApiModelProperty("库存")
    private double stockQty;

    @ApiModelProperty("单位")
    private String unit;
}
