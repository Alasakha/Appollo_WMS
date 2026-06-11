package com.mgkj.dto;

import com.alibaba.fastjson.annotation.JSONField;
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
@ApiModel("出货下架Dto")
public class SaleOrderOutDto {
    @ApiModelProperty(value="销货单单号",required = true)
    private String docNo;

    @ApiModelProperty(value="条码",required = true)
    private String barcode;

    @ApiModelProperty(value="料号",required = true)
    private String itemCode;

    @ApiModelProperty(value="单位",required = true)
    private String unitNo;

    @ApiModelProperty(value="库位编码",required = true)
    @JSONField(ordinal = 3)
    private String binCode;

    @ApiModelProperty(value="仓库编码",required = true)
    @JSONField(ordinal = 3)
    private String warehouseCode = "";

    @ApiModelProperty(value="匹配量",required = true)
    private BigDecimal matchQty;

    @ApiModelProperty(value="备注")
    private String remark;
}
