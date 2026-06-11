package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/4/19
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel("e10提交接口Dto")
public class SubmitCommonDto {

    @ApiModelProperty("单号")
    private String docNo;

    @ApiModelProperty("条码")
    private String barcode;

    @ApiModelProperty("料号")
    private String itemCode;

    @ApiModelProperty("单位")
    private String unitNo;

    @ApiModelProperty("库位编码")
    private String binCode;

    @ApiModelProperty("仓库编码")
    private String warehouseCode = "";

    @ApiModelProperty("匹配量")
    private Double matchQty;

    @ApiModelProperty("备注")
    private String remark;

}
