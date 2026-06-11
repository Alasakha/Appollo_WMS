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
 * @date 2024/4/10
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableSwagger2
@ToString
@ApiModel("其他入库查询详情Vo")
public class OtherInStockDetailVo {

    @ApiModelProperty("单号")
    private String docNo;

    @ApiModelProperty("日期")
    private String docDate;

    @ApiModelProperty("部门编号")
    private String departmentNo;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("员工编号")
    private String employeeNo;

    @ApiModelProperty("员工名称")
    private String employeeName;

    @ApiModelProperty("料号")
    private String itemCode;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("仓库批次")
    private String warehouseNo;

    @ApiModelProperty("仓库库位")
    private String storageSpacesNo;

    @ApiModelProperty("申请量")
    private double applyQty;

    @ApiModelProperty("库存量")
    private double stockQty;

    @ApiModelProperty("单位")
    private String unitCode;

}
