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
 * @date 2024/4/16
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel("通知下架汇总Vo")
public class ListNoticeOutStockDetailVo {
    @ApiModelProperty("销货单号")
    private String docNo;

    @ApiModelProperty("申请日期")
    private String docDate;

    @ApiModelProperty("客户")
    private String customerName;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("料号")
    private String itemCode;

    @ApiModelProperty("申请量")
    private double applyQty;

    @ApiModelProperty("单位")
    private String unitNo;

    @ApiModelProperty("库存量")
    private double stockQty;

    @ApiModelProperty("仓储批次")
    private String lotNo;

    @ApiModelProperty("仓库编号")
    private String warehouseCode;

    @ApiModelProperty("仓库库位")
    private String binCode;



}
