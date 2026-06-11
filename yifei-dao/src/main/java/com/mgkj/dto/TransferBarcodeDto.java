package com.mgkj.dto;

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
@ApiModel(value = "查询调拨箱码信息")
public class TransferBarcodeDto {

    @ApiModelProperty("箱码")
    private String barcode;
    @ApiModelProperty(value = "调拨单单头uuid")
    private String docId;
    @ApiModelProperty(value = "调拨单单头单号")
    private String docNo;

    private String createBy;

}
