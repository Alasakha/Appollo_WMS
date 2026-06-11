package com.mgkj.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;

/**
 * @author yyyjcg
 * @date 2024/4/11
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableSwagger2
@ApiModel(value = "其他入库提交Dto")
public class OtherInstockSubmitDto {
    @ApiModelProperty("杂货单号")
    private String docNo;

    @ApiModelProperty("条码")
    private String barcode;

    @ApiModelProperty("料号")
    private String itemCode;

//    @ApiModelProperty("单位")
//    private String unitNo;

   @ApiModelProperty("单位")
    private String unitCode;

    @ApiModelProperty("库位编码")
    @JSONField(ordinal = 3)
    private String binCode;

    @ApiModelProperty("仓库编码")
    @JSONField(ordinal = 3)
    private String warehouseCode = "";

    @ApiModelProperty("匹配量")
    private BigDecimal matchQty;

    @ApiModelProperty("备注")
    private String remark;
}
