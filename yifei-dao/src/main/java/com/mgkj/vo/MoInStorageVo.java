package com.mgkj.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableSwagger2
@ApiModel(value = "工单条码Vo")
public class MoInStorageVo {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("条码")
    private String barcode;

    @ApiModelProperty("工单号")
    private String sourceNo;

    @ApiModelProperty("品号")
    private String itemNo;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("单位")
    private String unitNo;

     @ApiModelProperty("单位名称")
    private String unitName;

    @ApiModelProperty("数量")
    private Double qty;

    @ApiModelProperty("炉号")
    private String lotDesc;

    @ApiModelProperty("日期")
    private String lotDate;

    @ApiModelProperty("批号")
    private String lotNo;

    @ApiModelProperty(value = "仓库编号")
    private String warehouseCode;

    @ApiModelProperty(value = "仓库库位")
    private String binCode;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

    @ApiModelProperty(value = "收货机构(工厂号)")
    private String shjg;

    @ApiModelProperty("客户单号")
    private String customerNo;

    @ApiModelProperty("入库状态")
    private String lotAtt30;
}
