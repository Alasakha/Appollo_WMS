package com.mgkj.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
public class IssueReceiptReqVo {
    @ApiModelProperty("工作中心编码")
    private String workCenterCode;

    @ApiModelProperty("工作中心名称")
    private String workCenterName;

    @ApiModelProperty("领料单号")
    private String docNo;

    @ApiModelProperty("单据日期")
    private String docDate;

    @ApiModelProperty("仓库编号")
    private String warehouseCode;

    @ApiModelProperty("仓库名称")
    private String warehouseName;

    @ApiModelProperty("工单号")
    private String modocNo;

    @ApiModelProperty("工单产品品号")
    private String itemCode;

    @ApiModelProperty("工单产品品名")
    private String itemName;

    @ApiModelProperty("工单产品规格")
    private String itemNSpec;

    @ApiModelProperty("客户单号")
    private String customerNo;
}
