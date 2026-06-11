package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "当天待检验的到货单头Vo")
public class PurchaseReceiptStorageWaitCheckVo {

    @ApiModelProperty(value = "采购到货单号")
    private String arrivalNo;

    @ApiModelProperty(value = "单据日期", example = "2024-08-01")
    private String docDate;

    @ApiModelProperty(value = "供应商编号")
    private String supplierCode="";

    @ApiModelProperty(value = "供应商名称")
    private String supplierName="";

    @ApiModelProperty("客户单号")
    private String customerNum;

    @ApiModelProperty("检验状态 1免检，2待检，3部分检验，4检验完成")
    private String inspectionStatus;


//    @ApiModelProperty(value = "品号")
//    private String itemCode="";
//
//    @ApiModelProperty(value = "品名")
//    private String itemName="";
//
//    @ApiModelProperty(value = "规格")
//    private String itemSpec="";
//
//    @ApiModelProperty(value = "单位编号")
//    private String unitCode;
//
//    @ApiModelProperty(value = "单位名称")
//    private String unitName;
//
//    @ApiModelProperty(value = "收货机构")
//    private String shjg;


}
