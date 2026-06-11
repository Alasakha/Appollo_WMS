package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2024-12-25 14:06
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@Builder
@ApiModel(value = "订单详情数据")
public class ProductDetail {
    //对wms调拨接口的参数
    @ApiModelProperty(value = "商品编号")
    private String proID;

    @ApiModelProperty(value = "商品数量")
    private int qty;

    @ApiModelProperty(value = "原价")
    private double oriPrice;

    @ApiModelProperty(value = "销售价")
    private double price;

    @ApiModelProperty(value = "是否赠品  0 非赠品 1赠品")
    private int isFree;

    @ApiModelProperty(value = "商品明细行号")
    private int rowNum;

    @ApiModelProperty(value = "明细备注")
    private String entryRemark;

    @ApiModelProperty(value = "供应商编码(GYS00002-2410170001)")
    private String supplierID;

    @ApiModelProperty(value = "供应商(接口编号)")
    private String supCode;

    @ApiModelProperty(value = "基本单位")
    private String pUnit;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "库存供应商ID", required = true)
    private String supID;

    @ApiModelProperty(value = "仓库编号", required = true)
    private String wareCode;

    @ApiModelProperty(value = "扩展字段")
    private String extContent;

    @ApiModelProperty(value = " 单位")
    private String unitNo;



}
