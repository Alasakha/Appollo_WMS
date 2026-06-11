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
 * @author yyyjcg
 * @date 2024/3/1
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "采购单详情Vo")
public class PurchaseDetailVo {
    @ApiModelProperty("单号")
    @TableField("DOC_NO")
    @JSONField(ordinal = 1)
    private String docNo;
    @ApiModelProperty("创建日期")
    @JSONField(ordinal = 2)
    private String docDate;

    @ApiModelProperty("供应商编号")
    @JSONField(ordinal = 3)
    private String supplierNo;

    @ApiModelProperty("供应商名称")
    @JSONField(ordinal = 4)
    private String supplierName;

    @ApiModelProperty("物料编号")
    @JSONField(ordinal = 5)
    private String itemNo;

    @ApiModelProperty("产品编码")
    @JSONField(ordinal = 6)
    private String productCode;

    @ApiModelProperty("储位编码")
    @JSONField(ordinal = 7)
    private String stockCode;

    @ApiModelProperty("物料名称")
    @JSONField(ordinal = 8)
    private String itemName;

    @ApiModelProperty("物料规格")
    @JSONField(ordinal = 9)
    private String itemSpec;

    @ApiModelProperty("交易单位编号")
    @JSONField(ordinal = 10)
    private String unitNo;

    @ApiModelProperty("库存数量")
    @JSONField(ordinal = 11)
    private BigDecimal stockQty;

    @ApiModelProperty("申请数量")
    @JSONField(ordinal = 12)
    private BigDecimal applyQty;

    @ApiModelProperty("扫描总数量")
    @JSONField(ordinal = 13)
    private BigDecimal scanSumQty;

    @ApiModelProperty("客户编号")
    @JSONField(ordinal = 14)
    private String customerNo;

    @ApiModelProperty("客户名称")
    @JSONField(ordinal = 15)
    private String customerName;

    @ApiModelProperty("仓库编号")
    @JSONField(ordinal = 16)
    private String warehouseNo;

    @ApiModelProperty("储位编号")
    @JSONField(ordinal = 17)
    private String storageSpacesNo;

    @ApiModelProperty("批号")
    @JSONField(ordinal = 18)
    private String lotNo;

    @ApiModelProperty("交易单位名称")
    @JSONField(ordinal = 19)
    private String unitName;

    @ApiModelProperty("参考单位编码")
    @JSONField(ordinal = 20)
    private String referenceUnitNo;

    @ApiModelProperty("参考单位名称")
    @JSONField(ordinal = 21)
    private String referenceUnitName;

    @ApiModelProperty("参考单位数量")
    @JSONField(ordinal = 22)
    private BigDecimal referenceQty;

    @ApiModelProperty("计价单位编码")
    @JSONField(ordinal = 23)
    private String valuationUnitNo;

    @ApiModelProperty("计价单位名称")
    @JSONField(ordinal = 24)
    private String valuationUnitName;

    @ApiModelProperty("计价单位数量")
    @JSONField(ordinal = 25)
    private BigDecimal valuationQty;

    @ApiModelProperty("库存单位编码")
    @JSONField(ordinal = 26)
    private String stockUnitNo;

    @ApiModelProperty("库存单位名称")
    @JSONField(ordinal = 27)
    private String stockUnitName;

    @ApiModelProperty("库存单位小数位数")
    @JSONField(ordinal = 28)
    private Integer stockKeep;

    @ApiModelProperty("来源单据数量")
    @JSONField(ordinal = 29)
    private BigDecimal sourceDocQty;

    @ApiModelProperty("来源计价数量")
    @JSONField(ordinal = 30)
    private BigDecimal sourceValuationQty;

    @ApiModelProperty("汇率")
    @JSONField(ordinal = 31)
    private BigDecimal rate;

    @ApiModelProperty("保留字段")
    @JSONField(ordinal = 32)
    private Integer keep;}
