package com.mgkj.dto;

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
@ApiModel(value = "采购单Dto")
public class PurchaseOrderSimpleDto {

    @ApiModelProperty("采购单号")
    @TableField("DOC_NO")
    @JSONField(ordinal = 1)
    private String docNo;

    @ApiModelProperty("供应商")
    @TableField("SUPPLIER_FULL_NAME")
    @JSONField(ordinal = 2)
    private String supplierFullName;

    @ApiModelProperty("品号")
    @TableField("ITEM_CODE")
    @JSONField(ordinal = 3)
    private String itemCode;

    @ApiModelProperty("单据日期")
    @TableField("DOC_DATE")
    @JSONField(ordinal = 4)
    private String docDate;




}
