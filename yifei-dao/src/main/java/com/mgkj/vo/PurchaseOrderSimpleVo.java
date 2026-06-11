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

/**
 * @author yyyjcg
 * @date 2024/1/22
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "采购单Vo")
public class PurchaseOrderSimpleVo {

    @ApiModelProperty("采购单号")
    @TableField("DOC_NO")
    @JSONField(ordinal = 1)
    private String docNo;

    @ApiModelProperty("供应商")
    @TableField("SUPPLIER_FULL_NAME")
    @JSONField(ordinal = 2)
    private String supplierFullName;

    @ApiModelProperty("单据日期")
    @TableField("DOC_DATE")
    @JSONField(ordinal = 3)
    private String docDate;

    @ApiModelProperty("序号")
    @JSONField(ordinal = 4)
    private String serialNumber=null;



//    @ApiModelProperty("品号")
//    @TableField("ITEM_CODE")
//    @JSONField(ordinal = 4)
//    private String itemCode;
//
//
//    @ApiModelProperty("品名")
//    @TableField("ITEM_DESCRIPTION")
//    @JSONField(ordinal = 5)
//    private String itemDescription;
//
//    @ApiModelProperty("规格")
//    @TableField("ITEM_SPECIFICATION")
//    @JSONField(ordinal = 6)
//    private String itemSpecification;
//
//    @ApiModelProperty(value = "业务数量")
//    @JSONField(ordinal = 7)
//    private Double businessQty;
//
//    @ApiModelProperty("批号")
//    @TableField("LOT_CODE")
//    @JSONField(ordinal = 8)
//    private String lotCode;
//
//    @ApiModelProperty("品号id")
//    @TableField("ITEM_ID")
//    @JSONField(ordinal = 9)
//    private String itemId;



}
