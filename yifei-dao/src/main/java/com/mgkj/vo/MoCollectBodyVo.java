package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/3/11
 * @Description
 */
@Data
@AllArgsConstructor
@EnableSwagger2
@ApiModel(value = "工单发料汇总单身Vo")
public class MoCollectBodyVo {

    @ApiModelProperty("工单单号")
    private String docNo;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("料号")
    private String itemCode;

    @ApiModelProperty("单位")
    private String unitNo;

    @ApiModelProperty("单据量")
    private Double sourceDocQty;

    @ApiModelProperty("申请量")
    private Double applyQty;

    @ApiModelProperty("库存量")
    private Double stockQty;

    @ApiModelProperty("仓库编号")
    private String warehouseCode;

    @ApiModelProperty("仓库名称")
    private String warehouseName;

    @ApiModelProperty("库位")
    private String binCode;

    @ApiModelProperty("匹配量")
    private Double matchQty;

}
