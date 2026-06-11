package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/3/8
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableSwagger2
@ApiModel(value = "工单发料simpleDto")
public class MoSimpleDto {
    @ApiModelProperty("工单单号")
    private String docNo;
    @ApiModelProperty("条码")
    private String barcode;
    @ApiModelProperty("成品料号")
    private String itemCode;
    @ApiModelProperty("下阶料号")
    private String lowItemCode;
    @ApiModelProperty("部门/厂商")
    private String departmentName;
    @ApiModelProperty("起始日期")
    private String startDate;
    @ApiModelProperty("结束日期")
    private String endDate;

}
