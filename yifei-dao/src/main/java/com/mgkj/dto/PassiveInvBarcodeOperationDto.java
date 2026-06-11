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
@ApiModel(value = "无源调拨汇总信息")
public class PassiveInvBarcodeOperationDto {

    private String createBy;
    @ApiModelProperty("箱码")
    private String barcode;
    @ApiModelProperty("箱码数量")
    private BigDecimal qty;
    @ApiModelProperty("需操作数量")
    private BigDecimal matchQty;
    @ApiModelProperty(value = "品号")
    private String itemCode;
    @ApiModelProperty(value = "品名")
    private String itemName;
    @ApiModelProperty(value = "规格")
    private String itemSpec;
    @ApiModelProperty(value="单位编号")
    private String unitCode;
    @ApiModelProperty(value="单位名称")
    private String unitName;

    @ApiModelProperty(value="调出仓库编码",required = true)
    private String fromWarehouseCode = "";
    @ApiModelProperty(value="调出库位编码",required = true)
    private String fromBinCode;
    @ApiModelProperty(value="调入仓库编码",required = true)
    private String toWarehouseCode = "";
    @ApiModelProperty(value="调入库位编码",required = true)
    private String toBinCode;

    @ApiModelProperty(value="备注")
    private String remark;
    @ApiModelProperty(value = "操作业务(5无源调拨，6无源入库，7无源出库，8库存调拨)")
    private Integer chagType;
    @ApiModelProperty(value = "提交状态(0 未提交)")
    private Integer statusCode;
    @ApiModelProperty(value = "收货机构")
    private String shjg;




}
