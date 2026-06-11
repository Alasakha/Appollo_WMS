package com.mgkj.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class NewIssueReceiptReqVo {

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
    private List<String> moDocNoList;

    @ApiModelProperty("工单产品品号")
    private String itemCode;

    @ApiModelProperty("工单产品品名")
    private String itemName;

    @ApiModelProperty("工单产品规格")
    private String itemNSpec;

    @ApiModelProperty("客户单号")
    private List<String> customerNo;

}
