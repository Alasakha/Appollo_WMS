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
 * @date 2024/4/2
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "领料单汇总单身信息Vo")
public class IssueReceiptDetailVo {
    @ApiModelProperty("领料单号")
    private String docNo;

    @ApiModelProperty("时间")
    private String docDate;

    @ApiModelProperty("部门编号")
    private String departmentCode;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("申请人")
    private String employeeName;


    @ApiModelProperty("料号")
    private String itemCode;

    @ApiModelProperty("料名")
    private String itemName;

    @ApiModelProperty("仓储批次")
    private String warehouseNo;

    @ApiModelProperty("仓储批次")
    private String lotNo;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("单位")
    private String unitNo;

    @ApiModelProperty("单据量（总共要领的数量）")
    private Double sourceDocQty;

    @ApiModelProperty("已领用量")
    private Double issuedQty;

    @ApiModelProperty("申请量（本次最大可领取数量）")
    private Double applyQty;

    @ApiModelProperty("库存量")
    private Double stockQty;

    @ApiModelProperty("序号")
    private String xh;

    @ApiModelProperty("备注")
    private String remark;
}
