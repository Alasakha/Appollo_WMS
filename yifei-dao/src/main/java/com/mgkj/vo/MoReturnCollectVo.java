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
 * @date 2024/3/19
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "工单退料汇总信息Vo")
public class MoReturnCollectVo {
    @ApiModelProperty("工单单号")
    private String docNo;

    @ApiModelProperty("成品品名")
    private String itemName;

    @ApiModelProperty("成品料号")
    private String itemCode;

    @ApiModelProperty("部门/厂商(编号)")
    private String departmentCode;

    @ApiModelProperty("部门/厂商")
    private String departmentName;

    @ApiModelProperty("下阶品名")
    private String lowOrderItemCode;

    @ApiModelProperty("下阶料号")
    private String lowOrderItemName;

    @ApiModelProperty("下阶规格")
    private String lowOrderItemSpec;

    @ApiModelProperty("下阶单位")
    private String unitCode;

    @ApiModelProperty("日期")
    private String docDate;

    @ApiModelProperty("申请量")
    private double applyQty;

    @ApiModelProperty("库存量")
    private double stockQty;

    @ApiModelProperty("序号")
    private String xh;

    @ApiModelProperty("仓库编码")
    private String warehouseCode;

    @ApiModelProperty("仓库名称")
    private String warehouseName;

    @ApiModelProperty("库位编码")
    private String binCode;

    @ApiModelProperty("领料数量")
    private double issuedQty;

    @ApiModelProperty("批号")
    private String lotNo;

    @ApiModelProperty("工厂")
    private String orgNo;

}
