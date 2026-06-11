package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2024-11-13 23:32
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "标签列印插入Dto")
public class PrintBarcodeDto {
    @ApiModelProperty("工厂编号")
    private String plantCode;

    @ApiModelProperty(value = "品号",required = true)
    private String itemNo;

    @ApiModelProperty(value = "批号",required = true)
    private String lotCode;

    @ApiModelProperty(value = "品名",required = true)
    private String itemName;

    @ApiModelProperty(value = "规格",required = true)
    private String itemSpec;

    @ApiModelProperty(value = "单位编号",required = true)
    private  String unitNo;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty("日期")
    private String date;

    @ApiModelProperty("仓库编号")
    private String warehouseNo;

    @ApiModelProperty("仓库名称")
    private String warehouseName;

    @ApiModelProperty("库位")
    private String binCode;

    @ApiModelProperty(value = "用户打算打印的数量",required = true)
    private BigDecimal printQty;

    @ApiModelProperty(value = "单包数量",required = true)
    private BigDecimal packageQty;

    @ApiModelProperty(value = "创建人ID",required = true)
    private String createBy;
}
