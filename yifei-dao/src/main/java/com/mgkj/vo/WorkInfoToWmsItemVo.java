package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("WorkInfoToWmsItemVo")
public class WorkInfoToWmsItemVo {

    private String id;

    @ApiModelProperty(value = "MOCTY.UDF05")
    private String udf05;

    @ApiModelProperty(value = "工单号")
    private String DOC_NO;

    @ApiModelProperty(value = "品号")
    private String ITEM_CODE;

    @ApiModelProperty("需求数量")
    private BigDecimal TB004;

    @ApiModelProperty("排产单已领数量")
    private BigDecimal TB005;

    @ApiModelProperty("工单需求数量")
    private BigDecimal gdRequQty;

    @ApiModelProperty("工单已领数量")
    private BigDecimal gdIssuedQty;

    @ApiModelProperty("本次扫描数量")
    private BigDecimal scanNum;

    @ApiModelProperty("单位名称")
    private String UNIT_NAME;

    @ApiModelProperty("单位编号")
    private String UNIT_CODE;

    @ApiModelProperty("品名")
    private String ITEM_DESCRIPTION;

    @ApiModelProperty("规格")
    private String ITEM_SPECIFICATION;

//    @ApiModelProperty("仓库编号")
//    private String WAREHOUSE_CODE;
//
//    @ApiModelProperty("仓库名称")
//    private String WAREHOUSE_NAME;
//
//    @ApiModelProperty("库位")
//    private String BIN_CODE;

}
