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
 * @date 2024/4/2
 * @Description
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "领料下架simpleDto")
public class IssueReceiptDeatailDto {
    @ApiModelProperty("领料单号")
    private String docNo;

    @ApiModelProperty("申请人(可以编号可以名字)")
    private String applyEmployee;

    @ApiModelProperty("部门/厂商")
    private String department;

    @ApiModelProperty("开始日期")
    private String startDate;

    @ApiModelProperty("结束日期")
    private String endDate;
}
