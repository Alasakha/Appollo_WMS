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
 * @date 2024/5/17
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel("库存调拨")
public class ListTransferDocDetailVo {
    @ApiModelProperty("调拨单号")
    private String docNo;

    @ApiModelProperty("日期")
    private String docDate;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("申请人")
    private String employeeName;

    @ApiModelProperty("品号")
    private String itemNo;

    @ApiModelProperty("品号")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("播出仓库")
    private String fromWarehouseName;

    @ApiModelProperty("播出仓库编号")
    private String fromWarehouseCode;

    @ApiModelProperty("播出库位")
    private String fromBinCode;

    @ApiModelProperty("播入仓库")
    private String toWarehouseName;

    @ApiModelProperty("播入仓库编号")
    private String toWarehouseCode;

    @ApiModelProperty("播入库位")
    private String toBinCode;

    @ApiModelProperty("申请数量")
    private double applyQty;

    @ApiModelProperty("库存量")
    private double stockQty;

    @ApiModelProperty("库存单位")
    private String unitNo;

}
