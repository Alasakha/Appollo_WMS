package com.mgkj.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/1/23
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "打印采购标签Dto")
public class PurchasePrintLabelDto {

    @ApiModelProperty("采购单号")
    @TableField("DOC_NO")
    @JSONField(ordinal = 1)
    @JsonProperty("DOC_NO")
    private String DOC_NO;

    @ApiModelProperty("工作中心/供应商")
    @TableField("SUPPLIER_FULL_NAME")
    @JSONField(ordinal = 2)
    @JsonProperty("SUPPLIER_FULL_NAME")
    private String SUPPLIER_FULL_NAME;

    @ApiModelProperty("品号")
    @TableField("ITEM_CODE")
    @JSONField(ordinal = 3)
    @JsonProperty("ITEM_CODE")
    private String ITEM_CODE;

    @ApiModelProperty("品名")
    @TableField("ITEM_DESCRIPTION")
    @JSONField(ordinal = 4)
    @JsonProperty("ITEM_DESCRIPTION")
    private String ITEM_DESCRIPTION;

    @ApiModelProperty("规格")
    @TableField("ITEM_SPECIFICATION")
    @JSONField(ordinal = 5)
    @JsonProperty("ITEM_SPECIFICATION")
    private String ITEM_SPECIFICATION;

    @ApiModelProperty("搜索类型:3501(外购)，3520(委外)")
    @TableField("DOC_CODE")
    @JSONField(ordinal = 6)
    @JsonProperty("DOC_CODE")
    private String DOC_CODE;
}
