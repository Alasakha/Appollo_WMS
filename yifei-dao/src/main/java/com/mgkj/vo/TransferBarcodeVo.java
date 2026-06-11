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
@ApiModel(value = "无源调拨(单箱级条码)Vo")
public class TransferBarcodeVo {


    @ApiModelProperty(value = "品号")
    private String itemCode="";

    @ApiModelProperty(value = "品名")
    private String itemName="";

    @ApiModelProperty(value = "规格")
    private String itemSpec="";

    @ApiModelProperty(value = "仓库编号")
    private String warehouseCode= "";

    @ApiModelProperty(value = "库位编码")
    private String binCode="";

    @ApiModelProperty(value = "交货数量")
    private String matchQty="";

    @ApiModelProperty(value = "条码")
    private String barcode;

    @ApiModelProperty(value = "单位编号")
    private String unitCode;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "收货机构")
    private String shjg;
}
