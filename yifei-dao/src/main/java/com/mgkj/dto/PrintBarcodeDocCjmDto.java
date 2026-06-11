package com.mgkj.dto;

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
@ApiModel(value = "根据车架码插入Dto")
public class PrintBarcodeDocCjmDto {

    @ApiModelProperty(value = "车架码")
    private String barcode;

    @ApiModelProperty(value = "单位净重")
    private BigDecimal blankUnitWeight;

    @ApiModelProperty(value = "净重")
    private BigDecimal blankWeight;

    @ApiModelProperty(value = "容器重量")
    private BigDecimal boxWeight;

    @ApiModelProperty(value = "批号")
    private String lotNo;

    @ApiModelProperty(value = "炉号")
    private String caterIa1HeatNo;

    @ApiModelProperty(value = "箱号")
    private String containerNumber;

    @ApiModelProperty(value = "打印人")
    private String createBy;

    @ApiModelProperty(value = "热处理品号")
    private String heatTreatmentNumber;

    @ApiModelProperty(value = "规格")
    private String itemDesc;

    @ApiModelProperty(value = "品名")
    private String itemName;

    @ApiModelProperty(value = "品号")
    private String itemNo;

    @ApiModelProperty(value = "工单单号")
    private String jobNo;

    @ApiModelProperty(value = "材料批号")
    private String lotNumberOfTheMaterial;

    @ApiModelProperty(value = "数量")
    private BigDecimal qty;

    @ApiModelProperty(value = "单位编号")
    private String unitNo ;

    @ApiModelProperty(value = "单位编号")
    private String snWarehouseNo ;




}
