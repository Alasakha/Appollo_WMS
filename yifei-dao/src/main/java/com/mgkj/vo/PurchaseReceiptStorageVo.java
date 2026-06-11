package com.mgkj.vo;

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
@ApiModel(value = "采购收货(单箱级条码)Vo")
public class PurchaseReceiptStorageVo {

    @ApiModelProperty(value = "容器码uuid")
    private String standard_col10;

    @ApiModelProperty(value = "当前条码状态")
    private String lot_att30;

    @ApiModelProperty(value = "条码类型(4.子条码 5.容器码)")
    private String bar_type;

    @ApiModelProperty(value = "送货单头uuid")
    private String parentId;

    @ApiModelProperty(value = "采购到货单号")
    private String arrivalNo;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "送货单号")
    private String deliveryNumber;

    @ApiModelProperty(value = "采购订单号")
    private String docNo;

    @ApiModelProperty(value = "采购员编号")
    private String createBy="";

    @ApiModelProperty(value = "单据日期", example = "2024-08-01")
    private String docDate;

    @ApiModelProperty(value = "供应商编号")
    private String supplierNo="";

    @ApiModelProperty(value = "供应商名称")
    private String supplierName="";

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
    private BigDecimal matchQty = BigDecimal.ZERO;

    @ApiModelProperty(value = "条码")
    private String barcode;

    @ApiModelProperty(value = "客户编号")
    private String customerNo;

    @ApiModelProperty(value = "单位编号")
    private String unitCode;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "收货机构")
    private String shjg;

    @ApiModelProperty(value = "标准收容数量")
    private BigDecimal containerQty;

    @ApiModelProperty(value = "收容器具")
    private String containerName;

    @ApiModelProperty(value = "收容器具尺寸")
    private String containerDetail;
}
