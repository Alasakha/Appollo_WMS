package com.mgkj.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2024-11-13 09:32
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel("标签列印信息")
public class ListPrintBarcodeVO {

    @ApiModelProperty("工厂编号")
    private String plantCode;

    @ApiModelProperty("品号")
    private String itemNo;

    @ApiModelProperty("批号")
    private String lotCode;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("仓库编号")
    private String warehouseNo;

    @ApiModelProperty("仓库名称")
    private String warehouseName;

    @ApiModelProperty("库位")
    private String binCode;

    @ApiModelProperty("单位编号")
    private  String unitCode;

    @ApiModelProperty("单位名称")
    private  String unitName;

    @ApiModelProperty("库存数量")
    private BigDecimal qty;

    @ApiModelProperty("日期")
    private String date;
}