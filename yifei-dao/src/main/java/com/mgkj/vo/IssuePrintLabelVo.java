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
 * @date 2024/1/31
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "领料单打印标签Vo")
public class IssuePrintLabelVo {
    @ApiModelProperty("单据类型")
    private String issueName;
    @ApiModelProperty("领料单号")
    private String docNo;
    @ApiModelProperty("入库单号")
    private String inDocNo;
    @ApiModelProperty("品号")
    private String finishItemNo;
    @ApiModelProperty("品名")
    private String finishItemName;
    @ApiModelProperty("规格")
    private String finishItemSpec;
    @ApiModelProperty("工单单号")
    private String moNo;



}
