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
 * @date 2024/4/18
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel("销售仓退汇总Vo")
public class ListSaleReturnDetailVo {
    @ApiModelProperty("退货单号")
    private String docNo;

    @ApiModelProperty("日期")
    private String docDate;

    @ApiModelProperty("客户")
    private String customerName;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("料号")
    private String itemCode;

    @ApiModelProperty("仓储批次")
    private String stockCode;

    @ApiModelProperty("申请量")
    private double applyQty;

    @ApiModelProperty("单位")
    private String unitNo;

    @ApiModelProperty("库存量")
    private double stockQty;

    @ApiModelProperty("仓储批次")
    private String lotNo;

    @ApiModelProperty("业务员")
    private String employeeName;

    @ApiModelProperty("部门名称")
    private String departmentName;

}
