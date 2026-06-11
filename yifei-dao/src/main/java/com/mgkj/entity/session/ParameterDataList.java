package com.mgkj.entity.session;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/2/19
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
public class ParameterDataList {

    @ApiModelProperty("采购单号")
    private String doc_no;

    @ApiModelProperty("品号")
    private String itemNo;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("单位")
    private String unitNo;

    @ApiModelProperty("供应商编号")
    private String supplierNo;

    @ApiModelProperty("数量")
    private String qty;

    @ApiModelProperty("仓库编号")
    private String warehouseNo;

    @ApiModelProperty("序号(xh)")
    private String seq;
}
