package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/3/19
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "工单退料汇总单身信息Vo")
public class MoReturnCollectBodyVo {
    @ApiModelProperty("工单单号")
    private String docNo;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("料号")
    private String itemCode;

    @ApiModelProperty("申请量")
    private Double applyQty;

    @ApiModelProperty("匹配量")
    private Double matchQty;

    @ApiModelProperty("仓储批次")
    private String warehouseNo;
}
