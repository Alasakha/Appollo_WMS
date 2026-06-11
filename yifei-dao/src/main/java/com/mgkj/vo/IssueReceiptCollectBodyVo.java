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
 * @date 2024/3/18
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "领料单汇总单身信息Vo")
public class IssueReceiptCollectBodyVo {
    @ApiModelProperty("料号")
    private String itemNo;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("单位")
    private String unitNo;

    @ApiModelProperty("申请量")
    private String applyQty;

    @ApiModelProperty("库存量")
    private String stockQty;

    @ApiModelProperty("仓储批次")
    private String warehouseNo;

}
