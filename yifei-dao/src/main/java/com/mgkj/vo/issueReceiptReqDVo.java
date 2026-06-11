package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
public class issueReceiptReqDVo {
    @ApiModelProperty(value = "领料申请单单号")
    private String docNo;

    @ApiModelProperty("品号")
    private String itemCode;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("仓库编号")
    private String warehouseCode;

    @ApiModelProperty("库位编号")
    private String binCode;

    @ApiModelProperty("单位编号")
    private String unitCode;

    @ApiModelProperty("单位名称")
    private String unitName;

    @ApiModelProperty("需求量")
    private BigDecimal requestQty;

    @ApiModelProperty("接收数量")
    private BigDecimal issueReceiptQty;

    @ApiModelProperty(value = "客户单号")
    private String customerNo;

    @ApiModelProperty("收货机构")
    private String shjg;

    @ApiModelProperty("工单单号")
    private String MoDocNo;

    @ApiModelProperty("客户/需求单号")
    private String DemandNo;

    @ApiModelProperty("条码")
    private String barcode;

    @ApiModelProperty("库存数量")
    private Double inventoryNum;

    private BigDecimal currectNum;

}
