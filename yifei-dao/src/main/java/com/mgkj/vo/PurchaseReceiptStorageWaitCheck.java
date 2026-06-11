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
@ApiModel(value = "采购收货(单箱级条码)Vo")
public class PurchaseReceiptStorageWaitCheck {

    @ApiModelProperty(value = "到货单号")
    private String docNo;


    @ApiModelProperty("供应商编码")
    private String supplierCode;

    @ApiModelProperty("供应商名称")
    private String supplierName;

    @ApiModelProperty("客户单号")
    private String receiptNo;

    @ApiModelProperty("创建日期")
    private String createDate;



}
