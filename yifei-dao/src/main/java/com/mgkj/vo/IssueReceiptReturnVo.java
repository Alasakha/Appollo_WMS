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
 * @date 2024/3/22
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "退料单信息Vo")
public class IssueReceiptReturnVo {

    @ApiModelProperty("退料单号")
    private String docNo;

    @ApiModelProperty("日期")
    private String docDate;

    @ApiModelProperty("申请人")
    private String employeeName;

    @ApiModelProperty("部门")
    private String departmentName;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("料号")
    private String itemCode;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("单位")
    private String unitCode;

    @ApiModelProperty("仓库编号")
    private String warehouseCode;

    @ApiModelProperty("仓储名称")
    private String warehouseName;

    @ApiModelProperty("申请数量")
    private double applyQty;

    @ApiModelProperty("库存量")
    private double stockQty;

    @ApiModelProperty("库位")
    private String binCode;

    @ApiModelProperty("匹配量")
    private String matchQty;

    @ApiModelProperty("序号")
    private String xh;
}
