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

import java.io.Serializable;

/**
 * @author yyyjcg
 * @date 2024/2/27
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel
public class PurchaseOrderDetailVo implements Serializable {
    @ApiModelProperty("采购单号")
    @TableField("DOC_NO")
    @JSONField(ordinal = 1)
    private String docNo;

    @ApiModelProperty("供应商名称")
    @JSONField(ordinal = 2)
    private String supplierName;

    @ApiModelProperty("供应商编号")
    @JSONField(ordinal = 2)
    private String supplierNo;

    @ApiModelProperty("品号")
    @TableField("ITEM_CODE")
    @JSONField(ordinal = 3)
    private String itemCode;

    @ApiModelProperty("品名")
    @TableField("ITEM_DESCRIPTION")
    @JSONField(ordinal = 4)
    private String itemDescription;

    @ApiModelProperty("规格")
    @TableField("ITEM_SPECIFICATION")
    @JSONField(ordinal = 5)
    private String itemSpecification;

    @ApiModelProperty(value = "业务数量")
    @TableField("BUSINESS_QTY")
    @JSONField(ordinal = 11)
    private Double businessQty;

    @ApiModelProperty(value = "可收数量")
    @JSONField(ordinal = 12)
    private Double recoverQty = 0.00;

    @ApiModelProperty(value = "已录数量")
    @JSONField(ordinal = 13)
    private Double recordQty = 0.00;

    @ApiModelProperty(value = "本次到货数量")
    @JSONField(ordinal = 13)
    private Double thisArrivalQty = 0.00;

    @ApiModelProperty(value = "单位")
    @JSONField(ordinal = 13)
    private String unitCode;

//    @ApiModelProperty("备注")
//    @JSONField(ordinal = 14)
//    private String remark;

    @ApiModelProperty("单据日期")
    @TableField("DOC_DATE")
    @JSONField(ordinal = 15)
    private String docDate;

    @ApiModelProperty("单身序号")
    private String xh;

}
